package com.caionastu.desafiojava.fazenda.api;

import com.caionastu.desafiojava.comum.validacoes.Unicidade;
import com.caionastu.desafiojava.fazenda.dominio.Fazenda;
import com.caionastu.desafiojava.fazenda.dominio.SiglaEstados;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class NovaFazendaRequest {

    @NotEmpty
    @Size(max = 200)
    @Unicidade(campo = "nome", entidade = "Fazenda", message = "Já existe fazenda com esse nome.")
    private String nome;

    @CNPJ
    @NotNull
    @NotEmpty
    @Unicidade(campo = "cnpj", entidade = "Fazenda", message = "Já existe fazenda com esse cnpj.")
    private String cnpj;

    @NotNull
    @NotEmpty
    private String cidade;

    @NotNull
    private SiglaEstados estado;

    @NotNull
    @NotEmpty
    private String logradouro;

    public Fazenda toModel() {
        return Fazenda.builder()
                .nome(nome)
                .logradouro(logradouro)
                .cidade(cidade)
                .estado(estado)
                .cnpj(cnpj)
                .build();
    }
}
