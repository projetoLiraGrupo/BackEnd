package com.lira.grupo.api.lira_api.repository;

import com.lira.grupo.api.lira_api.entity.AlunoAula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AlunoAulaRepository extends JpaRepository<AlunoAula, Integer> {

    boolean existsByFkAlunoIdAlunoAndFkAulaIdAula(Integer idAluno, Integer idAula);
    // obs: reclame comigo depois, mas isso é mais facil que fazer uma logica service enorme...


    long countByFkAulaIdAula(Integer idAula);

    boolean existsByFkAlunoIdAlunoAndFkAulaDiaHora(Integer idAluno, LocalDateTime diaHora);

    Optional<AlunoAula> findByFkAlunoIdAlunoAndFkAulaIdAula(Integer idAluno, Integer idAula);

    List<AlunoAula> findByFkAlunoIdAluno(Integer idAluno);

    List<AlunoAula> findByFkAulaIdAula(Integer idAula);
}
