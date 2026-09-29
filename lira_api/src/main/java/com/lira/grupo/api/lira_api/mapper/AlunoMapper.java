package com.lira.grupo.api.lira_api.mapper;

import com.lira.grupo.api.lira_api.entity.Aluno;
import com.lira.grupo.api.lira_api.entity.dto.AlunoRequestDto;
import com.lira.grupo.api.lira_api.entity.dto.response.AlunoResponseDto;
import org.springframework.stereotype.Component;

@Component
public class AlunoMapper {

    public AlunoResponseDto toResponseDto(Aluno aluno) {
        return new AlunoResponseDto(
                aluno.getAlunoNome(),
                aluno.getAlunoEmail(),
                aluno.getAlunoCpf()
        );
    }

    public Aluno toEntity(AlunoRequestDto dto) {
        Aluno aluno = new Aluno();
        updateEntity(dto, aluno);
        return aluno;
    }

    public void updateEntity(AlunoRequestDto dto, Aluno aluno) {
        aluno.setAlunoNome(dto.getAlunoNome());
        aluno.setAlunoEmail(dto.getAlunoEmail());
        aluno.setAlunoCpf(dto.getAlunoCpf());
        aluno.setDataDeNascimento(dto.getDataDeNascimento());
        aluno.setAlunoPossuiResponsavel(dto.getAlunoPossuiResponsavel());
    }

    public void updatePartialEntity(AlunoRequestDto dto, Aluno aluno) {
        if (dto.getAlunoNome() != null) {
            aluno.setAlunoNome(dto.getAlunoNome());
        }

        if (dto.getAlunoEmail() != null) {
            aluno.setAlunoEmail(dto.getAlunoEmail());
        }

        if (dto.getAlunoCpf() != null) {
            aluno.setAlunoCpf(dto.getAlunoCpf());
        }

        if (dto.getDataDeNascimento() != null) {
            aluno.setDataDeNascimento(dto.getDataDeNascimento());
        }

        if (dto.getAlunoPossuiResponsavel() != null) {
            aluno.setAlunoPossuiResponsavel(dto.getAlunoPossuiResponsavel());
        }
    }
}
