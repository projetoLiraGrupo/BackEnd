package com.lirapaulistana.api.model;

public class PreferenciaAluno {

    private Integer fkAluno;
    private Integer fkPreferencia;

    public PreferenciaAluno() {
    }

    public PreferenciaAluno(Integer fkAluno, Integer fkPreferencia) {
        this.fkAluno = fkAluno;
        this.fkPreferencia = fkPreferencia;
    }

    public Integer getFkAluno() {
        return fkAluno;
    }

    public void setFkAluno(Integer fkAluno) {
        this.fkAluno = fkAluno;
    }

    public Integer getFkPreferencia() {
        return fkPreferencia;
    }

    public void setFkPreferencia(Integer fkPreferencia) {
        this.fkPreferencia = fkPreferencia;
    }

}
