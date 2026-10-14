package com.lira.grupo.api.lira_api.entity;

import jakarta.persistence.*;

@Entity
public class Instrumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_instrumento")
    private Integer idInstrumento;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private Long descricao;


    public Instrumento() {
    }


    public Instrumento(Integer idInstrumento, String nome, Long descricao) {
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

    public Long getDescricao() {
        return descricao;
    }

    public void setDescricao(Long descricao) {
        this.descricao = descricao;
    }
}
