package com.lira.grupo.api.lira_api.model.mapper;

import com.lira.grupo.api.lira_api.model.Aluno;
import com.lira.grupo.api.lira_api.model.dto.AlunoDto;
import com.lira.grupo.api.lira_api.model.dto.response.AlunoResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AlunoMapper {

    AlunoMapper INSTANCE = Mappers.getMapper(AlunoMapper.class);

    AlunoResponseDto transformarParaResponseDto(Aluno aluno);

    @Mapping(target = "idEndereco", source = "endereco.idEndereco")
    AlunoDto transformarParaDto(Aluno aluno);

    Aluno transformarParaEntity(AlunoResponseDto responseDto);

    @Mapping(target = "endereco.idEndereco", source = "idEndereco")
    Aluno transformarParaEntity(AlunoDto dto);
}
