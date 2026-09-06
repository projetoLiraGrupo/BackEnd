package com.lirapaulistana.api.model;

public class Instrumento {

    private Integer idInstrumento;
    private String nome;
    private String descricao;

    public Instrumento() {
    }

    public Instrumento(Integer idInstrumento, String nome, String descricao) {
        this.idInstrumento = idInstrumento;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Integer getIdInstrumento() {
        return idInstrumento;
    }

    public void setIdInstrumento(Integer idInstrumento) {
        this.idInstrumento = idInstrumento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
