package com.caionastu.desafiojava.fazenda.dominio;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Endereco {

    private String cidade;
    private String estado;
    private String logradouro;
}
