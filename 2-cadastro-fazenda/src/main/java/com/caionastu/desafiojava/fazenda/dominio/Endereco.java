package com.caionastu.desafiojava.fazenda.dominio;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class Endereco {

    private String cidade;
    private SiglaEstados estado;
    private String logradouro;

    public Endereco(@NonNull String logradouro, @NonNull String cidade, @NonNull SiglaEstados estado) {
        this.cidade = cidade;
        this.estado = estado;
        this.logradouro = logradouro;
    }
}
