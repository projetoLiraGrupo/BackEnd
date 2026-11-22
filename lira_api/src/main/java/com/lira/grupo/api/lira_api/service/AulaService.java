package com.lira.grupo.api.lira_api.service;

import com.lira.grupo.api.lira_api.entity.Aula;
import com.lira.grupo.api.lira_api.entity.Endereco;
import com.lira.grupo.api.lira_api.entity.Professor;
import com.lira.grupo.api.lira_api.exception.BusinessRuleException;
import com.lira.grupo.api.lira_api.exception.ConflictException;
import com.lira.grupo.api.lira_api.exception.ResourceNotFoundException;
import com.lira.grupo.api.lira_api.mapper.AulaMapper;
import com.lira.grupo.api.lira_api.repository.AulaRepository;
import com.lira.grupo.api.lira_api.repository.EnderecoRepository;
import com.lira.grupo.api.lira_api.repository.ProfessorRepository;
import com.lira.grupo.api.lira_api.requestDto.AulaRequestDto;
import com.lira.grupo.api.lira_api.responseDto.AulaResponseDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    public List<Aula> listar() {
        return aulaRepository.findAll();
    }

    public Aula listarPorId(Integer id) {
        return aulaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aula", id));
    }

    public AulaResponseDto criar(AulaRequestDto aulaParaCriar) {
        validarDataFutura(aulaParaCriar.getDiaHora());
        Professor professor = buscarProfessor(aulaParaCriar);
        Endereco endereco = buscarEndereco(aulaParaCriar);

        if (aulaRepository.existsByFkProfessorIdProfessorAndDiaHora(
                professor.getIdProfessor(), aulaParaCriar.getDiaHora())) {
            throw new ConflictException("O professor já tem uma aula neste horário.");
        }

        Aula aula = AulaMapper.toEntity(aulaParaCriar);
        aula.setFkProfessor(professor);
        aula.setFkEndereco(endereco);

        Aula aulaCriada = aulaRepository.save(aula);
        return AulaMapper.toResponse(aulaCriada);
    }

    public AulaResponseDto atualizar(Integer id, AulaRequestDto aulaParaAtualizar) {
        if (!aulaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Aula", id);
        }

        validarDataFutura(aulaParaAtualizar.getDiaHora());
        Professor professor = buscarProfessor(aulaParaAtualizar);
        Endereco endereco = buscarEndereco(aulaParaAtualizar);

        
        if (aulaRepository.existsByFkProfessorIdProfessorAndDiaHoraAndIdAulaNot(
                professor.getIdProfessor(), aulaParaAtualizar.getDiaHora(), id)) {
            throw new ConflictException("O professor já tem uma aula neste horário.");
        }

        Aula aula = AulaMapper.toEntity(aulaParaAtualizar);
        aula.setIdAula(id);
        aula.setFkProfessor(professor);
        aula.setFkEndereco(endereco);

        Aula aulaAtualizada = aulaRepository.save(aula);
        return AulaMapper.toResponse(aulaAtualizada);
    }

    private void validarDataFutura(LocalDateTime diaHora) {
        if (!diaHora.isAfter(LocalDateTime.now())) {
            throw new BusinessRuleException("A aula precisa ser marcada para uma data futura.");
        }
    }

    private Professor buscarProfessor(AulaRequestDto dto) {
        Integer idProfessor = dto.getFkProfessor().getIdProfessor();
        return professorRepository.findById(idProfessor)
                .orElseThrow(() -> new ResourceNotFoundException("Professor", idProfessor));
    }

    private Endereco buscarEndereco(AulaRequestDto dto) {
        Integer idEndereco = dto.getFkEndereco().getIdEndereco();
        return enderecoRepository.findById(idEndereco)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço", idEndereco));
    }
}
