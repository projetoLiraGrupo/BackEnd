package com.lira.grupo.api.lira_api.entity;

import jakarta.persistence.*;

@Entity
public class Responsavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_responsavel")
    private Integer idResponsavel;

    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone")
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "fk_endereco")
    private Endereco fkEndereco;


    public Responsavel() {
    }


    public Responsavel(Integer idResponsavel, String nome, String telefone, Endereco fkEndereco) {
        this.idResponsavel = idResponsavel;
        this.nome = nome;
        this.telefone = telefone;
        this.fkEndereco = fkEndereco;
    }

    public Integer getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(Integer idResponsavel) {
        this.idResponsavel = idResponsavel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getFkEndereco() {
        return fkEndereco;
    }

    public void setFkEndereco(Endereco fkEndereco) {
        this.fkEndereco = fkEndereco;
    }
}
