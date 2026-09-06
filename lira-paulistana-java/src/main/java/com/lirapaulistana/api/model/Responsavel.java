package com.lirapaulistana.api.model;

public class Responsavel {

    private Integer idResponsavel;
    private String nome;
    private String telefone;
    private Integer enderecoIdEndereco;

    public Responsavel() {
    }

    public Responsavel(Integer idResponsavel, String nome, String telefone, Integer enderecoIdEndereco) {
        this.idResponsavel = idResponsavel;
        this.nome = nome;
        this.telefone = telefone;
        this.enderecoIdEndereco = enderecoIdEndereco;
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

    public Integer getEnderecoIdEndereco() {
        return enderecoIdEndereco;
    }

    public void setEnderecoIdEndereco(Integer enderecoIdEndereco) {
        this.enderecoIdEndereco = enderecoIdEndereco;
    }

}
