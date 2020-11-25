package com.caionastu.desafiojava.fazenda.api;

import com.caionastu.desafiojava.fazenda.dominio.Fazenda;
import com.caionastu.desafiojava.fazenda.dominio.FazendaRepository;
import com.caionastu.desafiojava.fazenda.excecoes.FazendaNaoEncontradaPeloIdException;
import com.totvs.tjf.api.context.stereotype.ApiGuideline;
import com.totvs.tjf.api.context.v2.request.ApiFieldRequest;
import com.totvs.tjf.api.context.v2.request.ApiPageRequest;
import com.totvs.tjf.api.context.v2.request.ApiSortRequest;
import com.totvs.tjf.api.context.v2.response.ApiCollectionResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.javadoc.doclet.Reporter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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
    public ResponseEntity<ApiCollectionResponse<FazendaDTO>> buscarTodos(ApiPageRequest pageRequest, ApiSortRequest sortRequest, ApiFieldRequest fieldRequest) {
        Collection<FazendaDTO> fazendas = repository.findAllProjected(fieldRequest, pageRequest, sortRequest)
                .getItems()
                .stream()
                .map(FazendaDTO::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ApiCollectionResponse.from(fazendas));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<FazendaDTO> buscarPeloId(@PathVariable UUID id) {
        Fazenda fazenda = repository.findById(id).orElseThrow( () -> new FazendaNaoEncontradaPeloIdException(id));
        return ResponseEntity.ok(FazendaDTO.from(fazenda));
    }

    @PostMapping
    @ApiOperation(value = "Cria uma Fazenda.")
    public List<Fazenda> criar(@RequestBody FazendaRequest request) {

        // TODO: 25/11/2020 Arrumar 
        System.out.println("Fazenda Request");
        return repository.findAll();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> remover(@PathVariable UUID id){
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
