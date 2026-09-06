package com.lirapaulistana.api.model;

public class Suporte {

    private Integer idSuporte;
    private Integer suportecol;
    private String nivel;
    private String descricao;
    private String tipo;
    private Integer fkAluno;

    public Suporte() {
    }

    public Suporte(Integer idSuporte, Integer suportecol, String nivel, String descricao, String tipo, Integer fkAluno) {
        this.idSuporte = idSuporte;
        this.suportecol = suportecol;
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

    public Integer getSuportecol() {
        return suportecol;
    }

    public void setSuportecol(Integer suportecol) {
        this.suportecol = suportecol;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getFkAluno() {
        return fkAluno;
    }

    public void setFkAluno(Integer fkAluno) {
        this.fkAluno = fkAluno;
    }

}
