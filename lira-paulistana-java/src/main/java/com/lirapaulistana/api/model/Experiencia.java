package com.lirapaulistana.api.model;

import java.time.LocalDate;

public class Experiencia {

    private Integer idExperiencia;
    private String experiencia;
    private LocalDate inicioEm;
    private LocalDate terminoEm;
    private Integer professorIdProfessor;

    public Experiencia() {
    }

    public Experiencia(Integer idExperiencia, String experiencia, LocalDate inicioEm, LocalDate terminoEm, Integer professorIdProfessor) {
        this.idExperiencia = idExperiencia;
        this.experiencia = experiencia;
        this.inicioEm = inicioEm;
        this.terminoEm = terminoEm;
        this.professorIdProfessor = professorIdProfessor;
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

    public Integer getProfessorIdProfessor() {
        return professorIdProfessor;
    }

    public void setProfessorIdProfessor(Integer professorIdProfessor) {
        this.professorIdProfessor = professorIdProfessor;
    }

}
