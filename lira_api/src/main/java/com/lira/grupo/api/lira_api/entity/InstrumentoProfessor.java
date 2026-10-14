package com.lira.grupo.api.lira_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class InstrumentoProfessor {

    @ManyToOne
    @JoinColumn(name = "fk_professor")
    private Professor fkProfessor;

    @ManyToOne
    @JoinColumn(name = "fk_instrumento")
    private Instrumento fkInstrumento;


    public InstrumentoProfessor() {
    }


    public InstrumentoProfessor(Professor fkProfessor, Instrumento fkInstrumento) {
        this.fkProfessor = fkProfessor;
        this.fkInstrumento = fkInstrumento;
    }

    public Professor getFkProfessor() {
        return fkProfessor;
    }

    public void setFkProfessor(Professor fkProfessor) {
        this.fkProfessor = fkProfessor;
    }

    public Instrumento getFkInstrumento() {
        return fkInstrumento;
    }

    public void setFkInstrumento(Instrumento fkInstrumento) {
        this.fkInstrumento = fkInstrumento;
    }
}
