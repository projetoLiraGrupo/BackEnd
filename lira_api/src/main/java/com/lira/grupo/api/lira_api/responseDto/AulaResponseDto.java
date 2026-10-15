package com.lira.grupo.api.lira_api.responseDto;

import com.lira.grupo.api.lira_api.entity.Endereco;
import com.lira.grupo.api.lira_api.entity.Professor;

import java.time.LocalDateTime;

public class AulaResponseDto {

    private Integer idAula;
    private LocalDateTime diaHora;
    private String nivel;
    private Endereco endereco;
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
