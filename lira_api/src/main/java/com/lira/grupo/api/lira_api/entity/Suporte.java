package com.lira.grupo.api.lira_api.entity;

import jakarta.persistence.*;

@Entity
public class Suporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_suporte")
    private Integer idSuporte;

    @Column(name = "nivel")
    private String nivel;

    @Column(name = "descricao")
    private Long descricao;

    @Column(name = "tipo")
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "fk_aluno")
    private Aluno fkAluno;


    public Suporte() {
    }


    public Suporte(Integer idSuporte, String nivel, Long descricao, String tipo, Aluno fkAluno) {
        this.idSuporte = idSuporte;
        this.nivel = nivel;
        this.descricao = descricao;
        this.tipo = tipo;
        this.fkAluno = fkAluno;
    }

    public Integer getIdSuporte() {
        return idSuporte;
    }

    public void setIdSuporte(Integer idSuporte) {
        this.idSuporte = idSuporte;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Long getDescricao() {
        return descricao;
    }

    public void setDescricao(Long descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Aluno getFkAluno() {
        return fkAluno;
    }

    public void setFkAluno(Aluno fkAluno) {
        this.fkAluno = fkAluno;
    }
}
