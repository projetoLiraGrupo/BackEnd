package com.lira.grupo.api.lira_api.repository;

import com.lira.grupo.api.lira_api.entity.Aula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AulaRepository extends JpaRepository<Aula, Integer> {

    List<Aula> findByDiaHoraAfter(LocalDateTime diaHora);

    boolean existsByFkProfessorIdProfessorAndDiaHoraAndIdAulaNot(Integer fkProfessor, LocalDateTime diaHora, Integer idAula);

    boolean existsByFkProfessorIdProfessorAndDiaHora(Integer fkProfessor, LocalDateTime diaHora);
}
