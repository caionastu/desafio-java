package com.caionastu.desafiojava.fazenda.api;

import com.caionastu.desafiojava.fazenda.dominio.Fazenda;
import com.caionastu.desafiojava.fazenda.dominio.FazendaRepository;
import com.caionastu.desafiojava.fazenda.excecoes.FazendaNaoEncontradaPeloIdException;
import com.caionastu.desafiojava.fazenda.excecoes.FazendaNaoEncontradaPeloNomeException;
import com.totvs.tjf.api.context.stereotype.ApiGuideline;
import com.totvs.tjf.api.context.v2.request.ApiFieldRequest;
import com.totvs.tjf.api.context.v2.request.ApiPageRequest;
import com.totvs.tjf.api.context.v2.request.ApiSortRequest;
import com.totvs.tjf.api.context.v2.response.ApiCollectionResponse;
import com.totvs.tjf.core.api.jpa.repository.ApiJpaCollectionResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Api(tags = {"Fazendas"})
@ApiGuideline(ApiGuideline.ApiGuidelineVersion.V2)
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/v1/fazendas")
public class FazendaController {

    private final FazendaRepository repository;

    @GetMapping
    @ApiOperation(value = "Busca todas as fazendas.")
    public ApiCollectionResponse<FazendaDTO> buscarTodos(ApiPageRequest pageRequest, ApiSortRequest sortRequest, ApiFieldRequest fieldRequest) {
        ApiJpaCollectionResult<Fazenda> fazendas = repository.findAllProjected(fieldRequest, pageRequest, sortRequest);
        List<FazendaDTO> fazendasDTO = fazendas
                .getItems()
                .stream()
                .map(FazendaDTO::from)
                .collect(Collectors.toList());

        return ApiCollectionResponse.of(fazendasDTO, fazendas.hasNext());
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca a fazenda pelo id.")
    public ResponseEntity<FazendaDTO> buscarPeloId(@PathVariable UUID id) {
        Fazenda fazenda = repository.findById(id).orElseThrow(() -> new FazendaNaoEncontradaPeloIdException(id));
        return ResponseEntity.ok(FazendaDTO.from(fazenda));
    }

    @GetMapping(path = "nome/{nome}")
    @ApiOperation(value = "Busca a fazenda pelo nome.")
    public ResponseEntity<FazendaDTO> buscarPeloNome(@PathVariable String nome) {
        Fazenda fazenda = repository.findByNome(nome).orElseThrow(() -> new FazendaNaoEncontradaPeloNomeException(nome));
        return ResponseEntity.ok(FazendaDTO.from(fazenda));
    }

    @PostMapping
    @ApiOperation(value = "Cria uma fazenda.")
    public ResponseEntity<FazendaDTO> criar(@Valid @RequestBody NovaFazendaRequest request) {
        Fazenda fazenda = repository.save(request.toModel());
        return ResponseEntity.ok(FazendaDTO.from(fazenda));
    }

    @PostMapping(path = "/{id}")
    @ApiOperation(value = "Altera nome da fazenda.")
    public ResponseEntity<FazendaDTO> alterar(@Valid @RequestBody AlteraFazendaRequest request, @PathVariable UUID id) {
        Fazenda fazenda = repository.findById(id)
                .orElseThrow(() -> new FazendaNaoEncontradaPeloIdException(id));

        fazenda.alteraNome(request.getNome());
        repository.save(fazenda);

        return ResponseEntity.ok(FazendaDTO.from(fazenda));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> remover(@PathVariable UUID id) {
        if (!repository.existsById(id)) {
            throw new FazendaNaoEncontradaPeloIdException(id);
        }

        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
