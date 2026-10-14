package com.lira.grupo.api.lira_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class AlunoResponsavel {

    @ManyToOne
    @JoinColumn(name = "fk_aluno")
    private Aluno fkAluno;

    @ManyToOne
    @JoinColumn(name = "fk_responsavel")
    private Responsavel fkResponsavel;


    public AlunoResponsavel() {
    }


    public AlunoResponsavel(Aluno fkAluno, Responsavel fkResponsavel) {
        this.fkAluno = fkAluno;
        this.fkResponsavel = fkResponsavel;
    }

    public Aluno getFkAluno() {
        return fkAluno;
    }

    public void setFkAluno(Aluno fkAluno) {
        this.fkAluno = fkAluno;
    }

    public Responsavel getFkResponsavel() {
        return fkResponsavel;
    }

    public void setFkResponsavel(Responsavel fkResponsavel) {
        this.fkResponsavel = fkResponsavel;
    }
}
