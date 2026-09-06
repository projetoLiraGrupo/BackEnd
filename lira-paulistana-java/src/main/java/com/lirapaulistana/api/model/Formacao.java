package com.lirapaulistana.api.model;

import java.time.LocalDate;

public class Formacao {

    private Integer idFormacao;
    private String curso;
    private String instituicao;
    private String grau;
    private String status;
    private LocalDate dtTermino;
    private Integer fkProfessor;

    public Formacao() {
    }

    public Formacao(Integer idFormacao, String curso, String instituicao, String grau, String status, LocalDate dtTermino, Integer fkProfessor) {
        this.idFormacao = idFormacao;
        this.curso = curso;
        this.instituicao = instituicao;
        this.grau = grau;
        this.status = status;
        this.dtTermino = dtTermino;
        this.fkProfessor = fkProfessor;
    }

    public Integer getIdFormacao() {
        return idFormacao;
    }

    public void setIdFormacao(Integer idFormacao) {
        this.idFormacao = idFormacao;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getGrau() {
        return grau;
    }

    public void setGrau(String grau) {
        this.grau = grau;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDtTermino() {
        return dtTermino;
    }

    public void setDtTermino(LocalDate dtTermino) {
        this.dtTermino = dtTermino;
    }

    public Integer getFkProfessor() {
        return fkProfessor;
    }

    public void setFkProfessor(Integer fkProfessor) {
        this.fkProfessor = fkProfessor;
    }

}
