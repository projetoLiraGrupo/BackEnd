package com.lira.grupo.api.lira_api.repository;

import com.lira.grupo.api.lira_api.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
}
