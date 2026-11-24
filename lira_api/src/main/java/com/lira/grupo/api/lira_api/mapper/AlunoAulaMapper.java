package com.lira.grupo.api.lira_api.mapper;

import com.lira.grupo.api.lira_api.entity.Aluno;
import com.lira.grupo.api.lira_api.entity.AlunoAula;
import com.lira.grupo.api.lira_api.responseDto.AlunoAulaResponseDto;

import java.util.List;

public class AlunoAulaMapper {

    private AlunoAulaMapper() {
    }

    public static AlunoAulaResponseDto toResponse(AlunoAula entidade) {
        AlunoAulaResponseDto dto = new AlunoAulaResponseDto();
        dto.setIdAlunoAula(entidade.getIdAlunoAula());
        dto.setCriadoEm(entidade.getCriadoEm());

        Aluno aluno = entidade.getFkAluno();
        if (aluno != null) {
            dto.setIdAluno(aluno.getIdAluno());
            dto.setAlunoNome(aluno.getAlunoNome());
        }

        if (entidade.getFkAula() != null) {
            dto.setAula(AulaMapper.toResponse(entidade.getFkAula()));
        }

        return dto;
    }

    public static List<AlunoAulaResponseDto> toResponse(List<AlunoAula> entidades) {
        return entidades.stream()
                .map((AlunoAula marcacao) -> toResponse(marcacao))
                .toList();
    }
}
