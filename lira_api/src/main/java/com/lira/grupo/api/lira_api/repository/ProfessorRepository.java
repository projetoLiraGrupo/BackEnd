package com.lira.grupo.api.lira_api.repository;

import com.lira.grupo.api.lira_api.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}