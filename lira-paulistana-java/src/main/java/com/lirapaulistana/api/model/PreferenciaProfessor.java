package com.lirapaulistana.api.model;

public class PreferenciaProfessor {

    private Integer fkProfessor;
    private Integer fkPreferencia;

    public PreferenciaProfessor() {
    }

    public PreferenciaProfessor(Integer fkProfessor, Integer fkPreferencia) {
        this.fkProfessor = fkProfessor;
        this.fkPreferencia = fkPreferencia;
    }

    public Integer getFkProfessor() {
        return fkProfessor;
    }

    public void setFkProfessor(Integer fkProfessor) {
        this.fkProfessor = fkProfessor;
    }

    public Integer getFkPreferencia() {
        return fkPreferencia;
    }

    public void setFkPreferencia(Integer fkPreferencia) {
        this.fkPreferencia = fkPreferencia;
    }

}
