package com.caionastu.desafiojava.fazenda;

import com.caionastu.desafiojava.fazenda.api.NovaFazendaRequest;
import com.caionastu.desafiojava.fazenda.dominio.Fazenda;
import com.caionastu.desafiojava.fazenda.dominio.FazendaRepository;
import com.caionastu.desafiojava.fazenda.dominio.SiglaEstados;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("integration")
@SpringBootTest
@AutoConfigureMockMvc
class FazendaIT {

    private static final String API_FAZENDAS = "/api/v1/fazendas";

    @Autowired
    private FazendaRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Busca todas as fazendas.")
    void buscaTodos() throws Exception {
        repository.deleteAll();
        repository.save(Fazenda.builder().nome("Fazenda São João").cnpj("37569970000112").logradouro("Rodova Raposo Tavares").cidade("Palmital").estado(SiglaEstados.SP).build());
        repository.save(Fazenda.builder().nome("Fazenda São Joaquina").cnpj("53131916000147").logradouro("Rodova Raposo Tavares").cidade("Assis").estado(SiglaEstados.SP).build());
        repository.save(Fazenda.builder().nome("Fazenda São Mateus").cnpj("39657799000129").logradouro("Rodova Raposo Tavares").cidade("Manaus").estado(SiglaEstados.AM).build());
        repository.save(Fazenda.builder().nome("Fazenda São Fernandes").cnpj("28083749000169").logradouro("Rodova Raposo Tavares").cidade("Santa Catarina").estado(SiglaEstados.PR).build());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(API_FAZENDAS))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Map<?, ?> resultado = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Map.class);

        List<Map<String, String>> items = (List<Map<String, String>>) resultado.get("items");

        assertThat(items).hasSize(4);
    }

    @Test
    @DisplayName("Busca a fazenda pelo id.")
    void buscaPeloId() throws Exception {
        Fazenda fazenda = repository.save(Fazenda.builder().nome("Fazenda Sao Bento").cnpj("54483002000108").logradouro("Rodova Raposo Tavares").cidade("Palmital").estado(SiglaEstados.SP).build());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(API_FAZENDAS + "/" + fazenda.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Fazenda resultado = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Fazenda.class);

        assertThat(resultado.getId()).isEqualTo(fazenda.getId());
        assertThat(resultado.getNome()).isEqualTo(fazenda.getNome());
    }

    @Test
    @DisplayName("Busca a fazenda pelo nome.")
    void buscaPeloNome() throws Exception {
        Fazenda fazenda = repository.save(Fazenda.builder().nome("Fazenda Sao Lucas").cnpj("05620093000170").logradouro("Rodova Raposo Tavares").cidade("Palmital").estado(SiglaEstados.SP).build());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(API_FAZENDAS + "/nome/" + fazenda.getNome()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Fazenda resultado = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Fazenda.class);

        assertThat(resultado.getId()).isEqualTo(fazenda.getId());
        assertThat(resultado.getNome()).isEqualTo(fazenda.getNome());
    }
}
