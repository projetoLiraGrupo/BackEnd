package com.lira.grupo.api.lira_api.requestDto;

import com.lira.grupo.api.lira_api.entity.Endereco;
import com.lira.grupo.api.lira_api.entity.Professor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(description = "Dados para cadastrar uma aula")
public class AulaRequestDto {

    @Schema(description = "Data e hora da aula", examples = "2026/10/28T15:32:58.758Z")
    @NotNull(message = "Campo da aula obrigatório.")
    private LocalDateTime diaHora;

    @Schema(description = "Nivel da aula", examples = "Intermediario")
    @NotBlank(message = "Campo da aula é obrigatória.")
    private String nivel;
    @Schema(description = "Identificador do Endereço", examples = "1")
    @NotNull(message = "O endereço da aula é obrigatório.")
    private Endereco fkEndereco;
    @Schema(description = "Identificador do professor", examples = "1")
    @NotNull(message = "É obrigatório saber o professor da aula.")
    private Professor fkProfessor;


    public AulaRequestDto() {
    }


    public AulaRequestDto(LocalDateTime diaHora, String nivel, Endereco fkEndereco, Professor fkProfessor) {
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

    public Endereco getFkEndereco() {
        return fkEndereco;
    }

    public void setFkEndereco(Endereco fkEndereco) {
        this.fkEndereco = fkEndereco;
    }

    public Professor getFkProfessor() {
        return fkProfessor;
    }

    public void setFkProfessor(Professor fkProfessor) {
        this.fkProfessor = fkProfessor;
    }
}
