package com.lira.grupo.api.lira_api.mapper;

import com.lira.grupo.api.lira_api.entity.Aula;
import com.lira.grupo.api.lira_api.requestDto.AulaRequestDto;
import com.lira.grupo.api.lira_api.responseDto.AulaResponseDto;

import java.util.ArrayList;
import java.util.List;

public class AulaMapper {

    public static List<AulaResponseDto> toResponse(List<Aula> entitys){
        List<AulaResponseDto> dto = new ArrayList<>();

        for (Aula entity : entitys) {
            AulaResponseDto dtoResponse = new AulaResponseDto();

            dtoResponse.setIdAula(entity.getIdAula());
            dtoResponse.setEndereco(entity.getFkEndereco());
            dtoResponse.setDiaHora(entity.getDiaHora());
            dtoResponse.setNivel(entity.getNivel());
            dtoResponse.setFkProfessor(entity.getFkProfessor());

            dto.add(dtoResponse);
        }

        return dto;
    }

    public static AulaResponseDto toResponse(Aula entity){
        AulaResponseDto dto = new AulaResponseDto();
        dto.setIdAula(entity.getIdAula());
        dto.setDiaHora(entity.getDiaHora());
        dto.setNivel(entity.getNivel());
        dto.setEndereco(entity.getFkEndereco());
        dto.setFkProfessor(entity.getFkProfessor());
        return dto;
    }

    public static Aula toEntity(AulaRequestDto dto){
        Aula entity = new Aula();

        entity.setDiaHora(dto.getDiaHora());
        entity.setNivel(dto.getNivel());
        entity.setFkEndereco(dto.getFkEndereco());
        entity.setFkProfessor(dto.getFkProfessor());

        return entity;
    }
}
