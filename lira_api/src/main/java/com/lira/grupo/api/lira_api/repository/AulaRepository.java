package com.lira.grupo.api.lira_api.repository;

import com.lira.grupo.api.lira_api.entity.Aula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AulaRepository extends JpaRepository<Aula, Integer> {

    List<Optional<Aula>> findByDiaHoraAfter(LocalDateTime diaHora);

    Boolean existsByFkProfessorIdProfessorAndDiaHoraAndIdAulaNot(Integer fkProgessor, LocalDateTime diaHora, Integer idAula);

    Boolean existsByFkProfessorIdProfessorAndDiaHora(Integer fkProfessor, LocalDateTime diaHora);
}
