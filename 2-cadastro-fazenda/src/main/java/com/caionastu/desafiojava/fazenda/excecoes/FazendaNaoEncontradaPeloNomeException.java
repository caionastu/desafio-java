package com.caionastu.desafiojava.fazenda.excecoes;

import com.totvs.tjf.api.context.stereotype.ApiErrorParameter;
import com.totvs.tjf.api.context.stereotype.error.ApiNotFound;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
@ApiNotFound("FazendaNaoEncontradaPeloNome")
public class FazendaNaoEncontradaPeloNomeException extends RuntimeException {

    @ApiErrorParameter
    private final String nome;
}
