package com.lira.grupo.api.lira_api.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aula")
    private Integer idAula;

    @Column(name = "dia_hora")
    private LocalDateTime diaHora;

    @Column(name = "nivel")
    private String nivel;

    @ManyToOne
    @JoinColumn(name = "fk_endereco")
    private Endereco fkEndereco;

    @ManyToOne
    @JoinColumn(name = "fk_professor")
    private Professor fkProfessor;


    public Aula() {
    }


    public Aula(Integer idAula, LocalDateTime diaHora, String nivel, Endereco fkEndereco, Professor fkProfessor) {
        this.idAula = idAula;
        this.diaHora = diaHora;
        this.nivel = nivel;
        this.fkEndereco = fkEndereco;
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
