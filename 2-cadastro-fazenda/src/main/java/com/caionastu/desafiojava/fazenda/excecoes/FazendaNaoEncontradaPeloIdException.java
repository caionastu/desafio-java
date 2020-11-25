package com.caionastu.desafiojava.fazenda.excecoes;

import com.totvs.tjf.api.context.stereotype.ApiErrorParameter;
import com.totvs.tjf.api.context.stereotype.error.ApiNotFound;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
@ApiNotFound("FazendaNaoEncontradaPeloId")
public class FazendaNaoEncontradaPeloIdException extends RuntimeException {

    @ApiErrorParameter
    private final UUID id;
}
