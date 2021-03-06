package com.caionastu.desafiojava.fazenda.dominio;

import com.totvs.tjf.api.jpa.repository.ApiJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FazendaRepository extends JpaRepository<Fazenda, UUID>, ApiJpaRepository<Fazenda> {

    Optional<Fazenda> findByNome(String nome);
}
