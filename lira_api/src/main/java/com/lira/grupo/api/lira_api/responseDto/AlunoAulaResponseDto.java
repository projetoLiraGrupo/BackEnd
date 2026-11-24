package com.lira.grupo.api.lira_api.responseDto;

import com.lira.grupo.api.lira_api.entity.Aluno;
import com.lira.grupo.api.lira_api.entity.Aula;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class AlunoAulaResponseDto {


    @Schema(description = "Identificador da Aula", examples = "1")
    private Integer idAlunoAula;

    @Schema(description = "Data e hora em que a Aula foi Criada", examples = "2026/10/28T15:32:58.758Z")
    private LocalDateTime criadoEm;

    @Schema(description = "Data e hora em que a Aula foi Atualizada", examples = "2026/10/30T15:32:58.758Z")
    private LocalDateTime atualizadoEm;

    @Schema(description = "Identificador do Aluno", examples = "1")
    private Aluno fkAluno;

    @Schema(description = "Identificador da Aula", examples = "1")
    private Aula fkAula;

    public AlunoAulaResponseDto() {
    }


    public AlunoAulaResponseDto(Integer idAlunoAula, LocalDateTime criadoEm, LocalDateTime atualizadoEm, Aluno fkAluno, Aula fkAula) {
        this.idAlunoAula = idAlunoAula;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
        this.fkAluno = fkAluno;
        this.fkAula = fkAula;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(LocalDateTime atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }

    public Aluno getFkAluno() {
        return fkAluno;
    }

    public void setFkAluno(Aluno fkAluno) {
        this.fkAluno = fkAluno;
    }

    public Aula getFkAula() {
        return fkAula;
    }

    public void setFkAula(Aula fkAula) {
        this.fkAula = fkAula;
    }
}
