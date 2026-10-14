package com.lira.grupo.api.lira_api.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Experiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_experiencia")
    private Integer idExperiencia;

    @Column(name = "experiencia")
    private String experiencia;

    @Column(name = "inicio_em")
    private LocalDate inicioEm;

    @Column(name = "termino_em")
    private LocalDate terminoEm;

    @ManyToOne
    @JoinColumn(name = "fk_professor")
    private Professor fkProfessor;


    public Experiencia() {
    }


    public Experiencia(Integer idExperiencia, String experiencia, LocalDate inicioEm, LocalDate terminoEm, Professor fkProfessor) {
        this.idExperiencia = idExperiencia;
        this.experiencia = experiencia;
        this.inicioEm = inicioEm;
        this.terminoEm = terminoEm;
        this.fkProfessor = fkProfessor;
    }

    public Integer getIdExperiencia() {
        return idExperiencia;
    }

    public void setIdExperiencia(Integer idExperiencia) {
        this.idExperiencia = idExperiencia;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public LocalDate getInicioEm() {
        return inicioEm;
    }

    public void setInicioEm(LocalDate inicioEm) {
        this.inicioEm = inicioEm;
    }

    public LocalDate getTerminoEm() {
        return terminoEm;
    }

    public void setTerminoEm(LocalDate terminoEm) {
        this.terminoEm = terminoEm;
    }

    public Professor getFkProfessor() {
        return fkProfessor;
    }

    public void setFkProfessor(Professor fkProfessor) {
        this.fkProfessor = fkProfessor;
    }
}
