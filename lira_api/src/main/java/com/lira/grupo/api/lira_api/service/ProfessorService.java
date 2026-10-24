package com.lira.grupo.api.lira_api.service;

import com.lira.grupo.api.lira_api.repository.EnderecoRepository;
import com.lira.grupo.api.lira_api.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final EnderecoRepository enderecoRepository;

    public ProfessorService(ProfessorRepository professorRepository, EnderecoRepository enderecoRepository) {
        this.professorRepository = professorRepository;
        this.enderecoRepository = enderecoRepository;
    }
}
