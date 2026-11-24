package com.lira.grupo.api.lira_api.repository;

import com.lira.grupo.api.lira_api.entity.AulaGrupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AulaGrupoRepository extends JpaRepository<AulaGrupo, Integer> {

    Optional<AulaGrupo> findByFkAulaIdAula(Integer idAula);
}
