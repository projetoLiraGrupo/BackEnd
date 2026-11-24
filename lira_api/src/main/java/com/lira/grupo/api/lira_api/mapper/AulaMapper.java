package com.lira.grupo.api.lira_api.mapper;

import com.lira.grupo.api.lira_api.entity.Aula;
import com.lira.grupo.api.lira_api.requestDto.AulaRequestDto;
import com.lira.grupo.api.lira_api.responseDto.AulaResponseDto;
import com.lira.grupo.api.lira_api.responseDto.EnderecoResponseDto;
import com.lira.grupo.api.lira_api.responseDto.ProfessorResponseDto;

import java.util.List;

public class AulaMapper {

    private AulaMapper() {
    }

    public static List<AulaResponseDto> toResponse(List<Aula> entidades) {
        return entidades.stream()
                .map((Aula aula) -> toResponse(aula))
                .toList();
    }

    public static AulaResponseDto toResponse(Aula entidade) {
        AulaResponseDto dto = new AulaResponseDto();
        dto.setIdAula(entidade.getIdAula());
        dto.setDiaHora(entidade.getDiaHora());
        dto.setNivel(entidade.getNivel());

        if (entidade.getFkEndereco() != null) {
            dto.setEndereco(EnderecoResponseDto.from(entidade.getFkEndereco()));
        }

        if (entidade.getFkProfessor() != null) {
            dto.setFkProfessor(ProfessorResponseDto.from(entidade.getFkProfessor()));
        }

        return dto;
    }


    public static Aula toEntity(AulaRequestDto dto) {
        Aula entidade = new Aula();
        entidade.setDiaHora(dto.getDiaHora());
        entidade.setNivel(dto.getNivel());
        return entidade;
    }
}
