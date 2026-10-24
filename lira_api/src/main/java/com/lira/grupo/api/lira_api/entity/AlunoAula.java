package com.lira.grupo.api.lira_api.entity;

import jakarta.persistence.*;

@Entity
public class AlunoAula {

    @Id
    @GeneratedValue
    private Integer idAlunoAula;

    @ManyToOne
    @JoinColumn(name = "fk_aluno")
    private Aluno fkAluno;

    @ManyToOne
    @JoinColumn(name = "fk_aula")
    private Aula fkAula;


    public AlunoAula() {
    }


    public AlunoAula(Integer idAlunoAula, Aluno fkAluno, Aula fkAula) {
        this.idAlunoAula = idAlunoAula;
        this.fkAluno = fkAluno;
        this.fkAula = fkAula;
    }

    public Integer getIdAlunoAula() {
        return idAlunoAula;
    }

    public void setIdAlunoAula(Integer idAlunoAula) {
        this.idAlunoAula = idAlunoAula;
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
