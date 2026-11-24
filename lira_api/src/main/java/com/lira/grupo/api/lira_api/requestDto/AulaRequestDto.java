package com.lira.grupo.api.lira_api.requestDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Schema(description = "Dados para cadastrar ou atualizar uma aula")
public class AulaRequestDto {

    @Schema(description = "Data e hora da aula", examples = "2026-12-10T15:30:00")
    @NotNull(message = "A data e hora da aula são obrigatórias.")
    private LocalDateTime diaHora;

    @Schema(description = "Nível da aula", examples = "Intermediario")
    @NotBlank(message = "O nível da aula é obrigatório.")
    private String nivel;

    @Schema(description = "Identificador do endereço", examples = "1")
    @NotNull(message = "O endereço da aula é obrigatório.")
    private Integer fkEndereco;

    @Schema(description = "Identificador do professor", examples = "1")
    @NotNull(message = "É obrigatório saber o professor da aula.")
    private Integer fkProfessor;

    public AulaRequestDto() {
    }

    public AulaRequestDto(LocalDateTime diaHora, String nivel, Integer fkEndereco, Integer fkProfessor) {
        this.diaHora = diaHora;
        this.nivel = nivel;
        this.fkEndereco = fkEndereco;
        this.fkProfessor = fkProfessor;
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

    public Integer getFkEndereco() {
        return fkEndereco;
    }

    public void setFkEndereco(Integer fkEndereco) {
        this.fkEndereco = fkEndereco;
    }

    public Integer getFkProfessor() {
        return fkProfessor;
    }

    public void setFkProfessor(Integer fkProfessor) {
        this.fkProfessor = fkProfessor;
    }
}
