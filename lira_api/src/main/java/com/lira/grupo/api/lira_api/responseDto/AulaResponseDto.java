package com.lira.grupo.api.lira_api.responseDto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Representa os dados retornados pela API relacionado a aula")
public class AulaResponseDto {
    @Schema(description = "Identificador da aula", examples = "1")
    private Integer idAula;
    @Schema(description = "Data e hora da aula", examples = "2026-12-10T15:30:00")
    private LocalDateTime diaHora;
    @Schema(description = "Nível da aula", examples = "Intermediario")
    private String nivel;
    @Schema(description = "Endereço da aula", implementation = EnderecoResponseDto.class)
    private EnderecoResponseDto endereco;
    @Schema(description = "Professor da aula", implementation = ProfessorResponseDto.class)
    private ProfessorResponseDto fkProfessor;

    public AulaResponseDto() {
    }

    public AulaResponseDto(Integer idAula, LocalDateTime diaHora, String nivel,
                           EnderecoResponseDto endereco, ProfessorResponseDto fkProfessor) {
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

    public EnderecoResponseDto getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoResponseDto endereco) {
        this.endereco = endereco;
    }

    public ProfessorResponseDto getFkProfessor() {
        return fkProfessor;
    }

    public void setFkProfessor(ProfessorResponseDto fkProfessor) {
        this.fkProfessor = fkProfessor;
    }
}
