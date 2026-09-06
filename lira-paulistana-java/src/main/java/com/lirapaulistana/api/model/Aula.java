package com.lirapaulistana.api.model;

import java.time.LocalDateTime;

public class Aula {

    private Integer idAula;
    private LocalDateTime diaHora;
    private String nivel;
    private Integer enderecoIdEndereco;
    private Integer professorIdProfessor;
    private Integer alunoIdAluno;
    private Integer qtdAlunos;

    public Aula() {
    }

    public Aula(Integer idAula, LocalDateTime diaHora, String nivel, Integer enderecoIdEndereco, Integer professorIdProfessor, Integer alunoIdAluno, Integer qtdAlunos) {
        this.idAula = idAula;
        this.diaHora = diaHora;
        this.nivel = nivel;
        this.enderecoIdEndereco = enderecoIdEndereco;
        this.professorIdProfessor = professorIdProfessor;
        this.alunoIdAluno = alunoIdAluno;
        this.qtdAlunos = qtdAlunos;
    }

    public Integer getIdAula() {
        return idAula;
    }

    public void setIdAula(Integer idAula) {
        this.idAula = idAula;
    }

    public LocalDateTime getDiaHora() {
        return diaHora;
    }

    public void setDiaHora(LocalDateTime diaHora) {
        this.diaHora = diaHora;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Integer getEnderecoIdEndereco() {
        return enderecoIdEndereco;
    }

    public void setEnderecoIdEndereco(Integer enderecoIdEndereco) {
        this.enderecoIdEndereco = enderecoIdEndereco;
    }

    public Integer getProfessorIdProfessor() {
        return professorIdProfessor;
    }

    public void setProfessorIdProfessor(Integer professorIdProfessor) {
        this.professorIdProfessor = professorIdProfessor;
    }

    public Integer getAlunoIdAluno() {
        return alunoIdAluno;
    }

    public void setAlunoIdAluno(Integer alunoIdAluno) {
        this.alunoIdAluno = alunoIdAluno;
    }

    public Integer getQtdAlunos() {
        return qtdAlunos;
    }

    public void setQtdAlunos(Integer qtdAlunos) {
        this.qtdAlunos = qtdAlunos;
    }

}
