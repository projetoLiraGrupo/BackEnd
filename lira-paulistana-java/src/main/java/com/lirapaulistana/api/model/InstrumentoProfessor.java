package com.lirapaulistana.api.model;

public class InstrumentoProfessor {

    private Integer fkProfessor;
    private Integer fkInstrumento;

    public InstrumentoProfessor() {
    }

    public InstrumentoProfessor(Integer fkProfessor, Integer fkInstrumento) {
        this.fkProfessor = fkProfessor;
        this.fkInstrumento = fkInstrumento;
    }

    public Integer getFkProfessor() {
        return fkProfessor;
    }

    public void setFkProfessor(Integer fkProfessor) {
        this.fkProfessor = fkProfessor;
    }

    public Integer getFkInstrumento() {
        return fkInstrumento;
    }

    public void setFkInstrumento(Integer fkInstrumento) {
        this.fkInstrumento = fkInstrumento;
    }

}
