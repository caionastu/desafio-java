package com.caionastu.desafiojava.fazenda.api;

import com.caionastu.desafiojava.fazenda.dominio.Endereco;
import com.caionastu.desafiojava.fazenda.dominio.Fazenda;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class FazendaDTO {

    private UUID id;
    private String nome;
    private String cnpj;
    private Endereco endereco;

    public static FazendaDTO from(Fazenda fazenda){
        return new FazendaDTO(fazenda.getId(), fazenda.getNome(), fazenda.getCnpj(), fazenda.getEndereco());
    }
}
