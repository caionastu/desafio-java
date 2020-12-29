package com.caionastu.desafiojava.fazenda.dominio;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Fazenda {

    @Id
    @Column
    @GeneratedValue
    private UUID id;

    @NotNull
    @Column
    private String nome;

    @NotNull
    @Column
    private String cnpj;

    @Column
    @NotNull
    @Embedded
    private Endereco endereco;

    public static FazendaBuilder builder() {
        return new FazendaBuilder();
    }

    public void alteraNome(@NonNull String nome) {
        this.nome = nome;
    }

    public static final class FazendaBuilder {
        private String nome;
        private String cnpj;
        private String cidade;
        private SiglaEstados estado;
        private String logradouro;

        private FazendaBuilder() {

        }

        public FazendaBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public FazendaBuilder cnpj(String cnpj) {
            this.cnpj = cnpj;
            return this;
        }

        public FazendaBuilder cidade(String cidade) {
            this.cidade = cidade;
            return this;
        }

        public FazendaBuilder estado(SiglaEstados estado) {
            this.estado = estado;
            return this;
        }

        public FazendaBuilder logradouro(String logradouro) {
            this.logradouro = logradouro;
            return this;
        }

        public Fazenda build() {
            Fazenda fazenda = new Fazenda();
            fazenda.nome = Objects.requireNonNull(nome);
            fazenda.cnpj = Objects.requireNonNull(cnpj);
            fazenda.endereco = new Endereco(logradouro, cidade, estado);

            return fazenda;
        }
    }
}
