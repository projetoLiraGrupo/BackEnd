package com.lira.grupo.api.lira_api.service;

import com.lira.grupo.api.lira_api.entity.Aluno;
import com.lira.grupo.api.lira_api.entity.AlunoAula;
import com.lira.grupo.api.lira_api.entity.Aula;
import com.lira.grupo.api.lira_api.entity.AulaGrupo;
import com.lira.grupo.api.lira_api.exception.BusinessRuleException;
import com.lira.grupo.api.lira_api.exception.ConflictException;
import com.lira.grupo.api.lira_api.exception.ResourceNotFoundException;
import com.lira.grupo.api.lira_api.mapper.AlunoAulaMapper;
import com.lira.grupo.api.lira_api.repository.AlunoAulaRepository;
import com.lira.grupo.api.lira_api.repository.AlunoRepository;
import com.lira.grupo.api.lira_api.repository.AulaGrupoRepository;
import com.lira.grupo.api.lira_api.repository.AulaRepository;
import com.lira.grupo.api.lira_api.responseDto.AlunoAulaResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlunoAulaService {


    private static final int VAGAS_AULA_INDIVIDUAL = 1;

    private final AlunoAulaRepository alunoAulaRepository;
    private final AulaRepository aulaRepository;
    private final AlunoRepository alunoRepository;
    private final AulaGrupoRepository aulaGrupoRepository;

    public AlunoAulaService(AlunoAulaRepository alunoAulaRepository,
                            AulaRepository aulaRepository,
                            AlunoRepository alunoRepository,
                            AulaGrupoRepository aulaGrupoRepository) {
        this.alunoAulaRepository = alunoAulaRepository;
        this.aulaRepository = aulaRepository;
        this.alunoRepository = alunoRepository;
        this.aulaGrupoRepository = aulaGrupoRepository;
    }

    @Transactional
    public AlunoAulaResponseDto marcar(Integer idAluno, Integer idAula) {
        Aula aula = buscarAula(idAula);
        Aluno aluno = alunoRepository.findById(idAluno)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno", idAluno));

        validarAulaNaoPassou(aula, "Não é possível marcar uma aula que já passou.");

        if (alunoAulaRepository.existsByFkAlunoIdAlunoAndFkAulaIdAula(idAluno, idAula)) {
            throw new ConflictException("O aluno já está marcado nesta aula.");
        }

        if (alunoAulaRepository.existsByFkAlunoIdAlunoAndFkAulaDiaHora(idAluno, aula.getDiaHora())) {
            throw new ConflictException("O aluno já tem outra aula marcada neste horário.");
        }

        long marcados = alunoAulaRepository.countByFkAulaIdAula(idAula);
        if (marcados >= capacidadeDa(aula)) {
            throw new ConflictException("A aula não tem mais vagas.");
        }

        AlunoAula marcacao = alunoAulaRepository.save(new AlunoAula(aluno, aula));
        return AlunoAulaMapper.toResponse(marcacao);
    }

    @Transactional
    public void desmarcar(Integer idAluno, Integer idAula) {
        AlunoAula marcacao = alunoAulaRepository.findByFkAlunoIdAlunoAndFkAulaIdAula(idAluno, idAula)
                .orElseThrow(() -> new ResourceNotFoundException("Marcação", "aluno " + idAluno + ", aula " + idAula));

        validarAulaNaoPassou(marcacao.getFkAula(), "Não é possível desmarcar uma aula que já passou.");

        alunoAulaRepository.delete(marcacao);
    }

    @Transactional(readOnly = true)
    public List<AlunoAulaResponseDto> listarAulasDoAluno(Integer idAluno) {
        return AlunoAulaMapper.toResponse(alunoAulaRepository.findByFkAlunoIdAluno(idAluno));
    }

    @Transactional(readOnly = true)
    public List<AlunoAulaResponseDto> listarAlunosDaAula(Integer idAula) {
        if (!aulaRepository.existsById(idAula)) {
            throw new ResourceNotFoundException("Aula", idAula);
        }

        return AlunoAulaMapper.toResponse(alunoAulaRepository.findByFkAulaIdAula(idAula));
    }

    private Aula buscarAula(Integer idAula) {
        return aulaRepository.findById(idAula)
                .orElseThrow(() -> new ResourceNotFoundException("Aula", idAula));
    }

    private void validarAulaNaoPassou(Aula aula, String mensagem) {
        if (!aula.getDiaHora().isAfter(LocalDateTime.now())) {
            throw new BusinessRuleException(mensagem);
        }
    }

    private int capacidadeDa(Aula aula) {
        return aulaGrupoRepository.findByFkAulaIdAula(aula.getIdAula())
                .map(AulaGrupo::getQtdAlunoMax)
                .orElse(VAGAS_AULA_INDIVIDUAL);
    }
}
