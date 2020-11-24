package com.caionastu.desafiojava.fazenda.api;

import com.caionastu.desafiojava.fazenda.dominio.Fazenda;
import com.caionastu.desafiojava.fazenda.dominio.FazendaRepository;
import com.totvs.tjf.api.context.stereotype.ApiGuideline;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Api(tags = {"Fazendas"})
@ApiGuideline(ApiGuideline.ApiGuidelineVersion.V2)
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/fazendas/")
public class FazendaController {

    private final FazendaRepository repository;

    @PostMapping
    @ApiOperation(value = "Cria uma Fazenda.")
    public List<Fazenda> cria(@RequestBody FazendaRequest request) {
        repository.save(new Fazenda(UUID.randomUUID(), "Fazenda Teste" + new Random().nextInt()));
        System.out.println("Fazenda Request");
        return repository.findAll();
    }
}
