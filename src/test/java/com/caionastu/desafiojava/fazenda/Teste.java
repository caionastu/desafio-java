package com.caionastu.desafiojava.fazenda;

import com.caionastu.desafiojava.ApplicationTests;
import com.caionastu.desafiojava.fazenda.dominio.Fazenda;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.UUID;

@Tag("integration")
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = ApplicationTests.class)
public class Teste {

    @Autowired
    private EntityManager repository;

    @Test
    @DisplayName("Teste")
    public void teste() {
        repository.persist(new Fazenda(UUID.randomUUID(), "teste"));
    }
}
