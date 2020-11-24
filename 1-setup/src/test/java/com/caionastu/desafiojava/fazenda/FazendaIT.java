package com.caionastu.desafiojava.fazenda;

import com.caionastu.desafiojava.fazenda.dominio.Fazenda;
import com.caionastu.desafiojava.fazenda.dominio.FazendaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.UUID;

@Tag("integration")
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class FazendaIT {

    @Autowired
    private FazendaRepository repository;

    // TODO: 23/11/2020 Remover, apenas testando a configuração do teste integrado
    @Test
    @DisplayName("Configuração inicial teste integrado.")
    void testeInicial() {
        repository.save(new Fazenda(UUID.randomUUID(), "teste"));
        Assertions.assertTrue(true);
    }
}
