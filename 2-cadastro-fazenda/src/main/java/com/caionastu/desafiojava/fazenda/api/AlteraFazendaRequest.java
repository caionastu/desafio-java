package com.caionastu.desafiojava.fazenda.api;

import com.caionastu.desafiojava.comum.validacoes.Unicidade;
import com.caionastu.desafiojava.fazenda.dominio.Fazenda;
import com.caionastu.desafiojava.fazenda.dominio.SiglaEstados;
import lombok.Getter;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class AlteraFazendaRequest {

    @NotEmpty
    @Size(max = 200)
    @Unicidade(campo = "nome", entidade = "Fazenda", message = "Já existe fazenda com esse nome.")
    private String nome;

}
