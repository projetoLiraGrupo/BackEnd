package com.lira.grupo.api.lira_api.requestDto;

import com.lira.grupo.api.lira_api.entity.Aluno;
import com.lira.grupo.api.lira_api.entity.Aula;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

public class AlunoAulaRequestDto {


//    private Integer idAlunoAula;

    @Schema(description = "Data e hora em que a Aula foi Criada", examples = "2026/10/28T15:32:58.758Z")
    @NotNull(message = "Campo da criadoEm obrigatório.")
    private LocalDateTime criadoEm;

    @Schema(description = "Data e hora em que a Aula foi Atualizada", examples = "2026/10/30T15:32:58.758Z")
    @NotNull(message = "Campo da atualizadoEm obrigatório.")
    private LocalDateTime atualizadoEm;

    @Schema(description = "Identificador do Aluno", examples = "1")
    @NotNull(message = "O Aluno da aula é obrigatório.")
    private Aluno fkAluno;
    @Schema(description = "Identificador da Aula", examples = "1")
    @NotNull(message = "O Aluno da aula é obrigatório.")
    private Aula fkAula;


    public AlunoAulaRequestDto() {
    }

    public AlunoAulaRequestDto(LocalDateTime criadoEm, LocalDateTime atualizadoEm, Aluno fkAluno, Aula fkAula) {
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
