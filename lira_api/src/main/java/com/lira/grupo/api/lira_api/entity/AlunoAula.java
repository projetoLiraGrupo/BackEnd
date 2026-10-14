package com.lira.grupo.api.lira_api.entity;

import jakarta.persistence.*;

@Entity
public class AlunoAula {

    @ManyToOne
    @JoinColumn(name = "fk_aluno")
    private Aluno fkAluno;

    @ManyToOne
    @JoinColumn(name = "fk_aula")
    private Aula fkAula;


    public AlunoAula() {
    }


    public AlunoAula(Aluno fkAluno, Aula fkAula) {
        this.fkAluno = fkAluno;
        this.fkAula = fkAula;
    }

    public Aluno getFkAluno() {
        return fkAluno;
    }

    public void setFkAluno(Aluno fkAluno) {
        this.fkAluno = fkAluno;
    }

    public Aula getFkAula() {
        return fkAula;
    }

    public void setFkAula(Aula fkAula) {
        this.fkAula = fkAula;
    }
}
