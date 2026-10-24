package com.lira.grupo.api.lira_api.service;

import com.lira.grupo.api.lira_api.entity.Aula;
import com.lira.grupo.api.lira_api.entity.Endereco;
import com.lira.grupo.api.lira_api.entity.Professor;
import com.lira.grupo.api.lira_api.exception.ResourceNotFoundException;
import com.lira.grupo.api.lira_api.mapper.AulaMapper;
import com.lira.grupo.api.lira_api.repository.AulaRepository;
import com.lira.grupo.api.lira_api.repository.EnderecoRepository;
import com.lira.grupo.api.lira_api.repository.ProfessorRepository;
import com.lira.grupo.api.lira_api.requestDto.AulaRequestDto;
import com.lira.grupo.api.lira_api.responseDto.AulaResponseDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AulaService {

    private final AulaRepository aulaRepository;
    private final EnderecoRepository enderecoRepository;
    private final ProfessorRepository professorRepository;


    public AulaService(AulaRepository aulaRepository, EnderecoRepository enderecoRepository, ProfessorRepository professorRepository) {
        this.aulaRepository = aulaRepository;
        this.enderecoRepository = enderecoRepository;
        this.professorRepository = professorRepository;
    }

    public List<Aula> listar(){
        return aulaRepository.findAll();
    }

    public Aula listarPorId(Integer id){
        Optional<Aula> aulaOptional = aulaRepository.findById(id);
        return aulaOptional.get();
    }

    public AulaResponseDto criar(AulaRequestDto aulaParaCriar){
        Aula aula = AulaMapper.toEntity(aulaParaCriar);
        Aula aulaCriada = aulaRepository.save(aula);
        return AulaMapper.toResponse(aulaCriada);
    }

    public AulaResponseDto atualizar(Integer id, AulaRequestDto aulaParaAtualizar){
        Aula aula = AulaMapper.toEntity(aulaParaAtualizar);


        aula.setIdAula(id);
        Aula aulaAtualizada = aulaRepository.save(aula);
        return AulaMapper.toResponse(aulaAtualizada);
    }
}
