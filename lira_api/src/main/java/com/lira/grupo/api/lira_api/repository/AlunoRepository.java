package com.lira.grupo.api.lira_api.repository;

import com.lira.grupo.api.lira_api.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    Optional<Aluno> findByAlunoEmail(String alunoEmail);

    boolean existsByAlunoEmail(String alunoEmail);

    boolean existsByAlunoCpf(String alunoCpf);

    boolean existsByAlunoEmailAndIdAlunoNot(String alunoEmail, Integer idAluno);

    boolean existsByAlunoCpfAndIdAlunoNot(String alunoCpf, Integer idAluno);
}
