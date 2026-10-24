package com.lira.grupo.api.lira_api.entity;

import jakarta.persistence.*;

@Entity
public class InstrumentoAluno {

    @Id
    @GeneratedValue
    private Integer idInstrumentoAluno;

    @ManyToOne
    @JoinColumn(name = "fk_instrumento")
    private Instrumento fkInstrumento;

    @ManyToOne
    @JoinColumn(name = "fk_aluno")
    private Aluno fkAluno;

    @Column(name = "nivel")
    private String nivel;


    public InstrumentoAluno() {
    }


    public InstrumentoAluno(Integer idInstrumentoAluno, Instrumento fkInstrumento, Aluno fkAluno, String nivel) {
        this.idInstrumentoAluno = idInstrumentoAluno;
        this.fkInstrumento = fkInstrumento;
        this.fkAluno = fkAluno;
        this.nivel = nivel;
    }

    public Integer getIdInstrumentoAluno() {
        return idInstrumentoAluno;
    }

    public void setIdInstrumentoAluno(Integer idInstrumentoAluno) {
        this.idInstrumentoAluno = idInstrumentoAluno;
    }

    public Instrumento getFkInstrumento() {
        return fkInstrumento;
    }

    public void setFkInstrumento(Instrumento fkInstrumento) {
        this.fkInstrumento = fkInstrumento;
    }

    public Aluno getFkAluno() {
        return fkAluno;
    }

    public void setFkAluno(Aluno fkAluno) {
        this.fkAluno = fkAluno;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
