package com.lira.grupo.api.lira_api.entity;

import jakarta.persistence.*;

@Entity
public class AlunoResponsavel {

    @Id
    @GeneratedValue
    private Integer idAlunoResponsavel;

    @ManyToOne
    @JoinColumn(name = "fk_aluno")
    private Aluno fkAluno;

    @ManyToOne
    @JoinColumn(name = "fk_responsavel")
    private Responsavel fkResponsavel;


    public AlunoResponsavel() {
    }


    public AlunoResponsavel(Integer idAlunoResponsavel, Aluno fkAluno, Responsavel fkResponsavel) {
        this.idAlunoResponsavel = idAlunoResponsavel;
        this.fkAluno = fkAluno;
        this.fkResponsavel = fkResponsavel;
    }

    public Integer getIdAlunoResponsavel() {
        return idAlunoResponsavel;
    }

    public void setIdAlunoResponsavel(Integer idAlunoResponsavel) {
        this.idAlunoResponsavel = idAlunoResponsavel;
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
