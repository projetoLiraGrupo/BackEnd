package com.lira.grupo.api.lira_api.repository;

import com.lira.grupo.api.lira_api.model.Aluno;
//import org.hibernate.internal.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

//    org.hibernate.internal.util.Optional<UserDetails> findByAlunoEmail(String alunoEmail);
    java.util.Optional<org.springframework.security.core.userdetails.UserDetails> findByAlunoEmail(String alunoEmail);
}

