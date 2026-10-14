package com.lira.grupo.api.lira_api.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Formacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_formacao")
    private Integer idFormacao;

    @Column(name = "curso")
    private String curso;

    @Column(name = "instituicao")
    private String instituicao;

    @Column(name = "grau")
    private String grau;

    @Column(name = "status")
    private String status;

    @Column(name = "dt_termino")
    private LocalDate dtTermino;

    @ManyToOne
    @JoinColumn(name = "fk_professor")
    private Professor fkProfessor;


    public Formacao() {
    }


    public Formacao(Integer idFormacao, String curso, String instituicao, String grau, String status, LocalDate dtTermino, Professor fkProfessor) {
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

    public Professor getFkProfessor() {
        return fkProfessor;
    }

    public void setFkProfessor(Professor fkProfessor) {
        this.fkProfessor = fkProfessor;
    }
}
