package com.caionastu.desafiojava.fazenda.dominio;

import com.caionastu.desafiojava.fazenda.api.FazendaDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Fazenda {

    @Id
    private UUID id;

    private String nome;

    private String cnpj;

    @Embedded
    private Endereco endereco;
}
