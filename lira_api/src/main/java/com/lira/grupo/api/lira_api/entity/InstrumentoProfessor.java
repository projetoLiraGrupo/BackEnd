package com.lira.grupo.api.lira_api.entity;

import jakarta.persistence.*;

@Entity
public class InstrumentoProfessor {

    @Id
    @GeneratedValue
    private Integer idInstrumentoProfessor;

    @ManyToOne
    @JoinColumn(name = "fk_professor")
    private Professor fkProfessor;

    @ManyToOne
    @JoinColumn(name = "fk_instrumento")
    private Instrumento fkInstrumento;


    public InstrumentoProfessor() {
    }


    public InstrumentoProfessor(Integer idInstrumentoProfessor, Professor fkProfessor, Instrumento fkInstrumento) {
        this.idInstrumentoProfessor = idInstrumentoProfessor;
        this.fkProfessor = fkProfessor;
        this.fkInstrumento = fkInstrumento;
    }

    public Integer getIdInstrumentoProfessor() {
        return idInstrumentoProfessor;
    }

    public void setIdInstrumentoProfessor(Integer idInstrumentoProfessor) {
        this.idInstrumentoProfessor = idInstrumentoProfessor;
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
