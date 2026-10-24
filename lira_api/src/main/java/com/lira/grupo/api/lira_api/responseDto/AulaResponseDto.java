package com.lira.grupo.api.lira_api.responseDto;

import com.lira.grupo.api.lira_api.entity.Endereco;
import com.lira.grupo.api.lira_api.entity.Professor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Representa os dados retornados pela API relacionado a aula")
public class AulaResponseDto {
    @Schema(description = "Identificador da aula", examples = "1")
    private Integer idAula;
    @Schema(description = "Data e hora da aula", examples = "2026/10/28T15:32:58.758Z")
    private LocalDateTime diaHora;
    @Schema(description = "Nivel da aula", examples = "Intermediario")
    private String nivel;
    @Schema(description = "Endereco da aula", implementation = EnderecoResponseDto.class)
    private Endereco endereco;
    @Schema(description = "Professor da aula", implementation = ProfessorResponseDto.class)
    private Professor fkProfessor;


    public AulaResponseDto() {
    }


    public AulaResponseDto(Integer idAula, LocalDateTime diaHora, String nivel, Endereco endereco, Professor fkProfessor) {
        this.idAula = idAula;
        this.diaHora = diaHora;
        this.nivel = nivel;
        this.endereco = endereco;
        this.fkProfessor = fkProfessor;
    }

    public Integer getIdAula() {
        return idAula;
    }

    public void setIdAula(Integer idAula) {
        this.idAula = idAula;
    }

    public LocalDateTime getDiaHora() {
        return diaHora;
    }

    public void setDiaHora(LocalDateTime diaHora) {
        this.diaHora = diaHora;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Professor getFkProfessor() {
        return fkProfessor;
    }

    public void setFkProfessor(Professor fkProfessor) {
        this.fkProfessor = fkProfessor;
    }
}
