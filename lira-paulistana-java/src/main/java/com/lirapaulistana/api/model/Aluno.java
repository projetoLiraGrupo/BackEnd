package com.lirapaulistana.api.model;

import java.time.LocalDate;

public class Aluno {

    private Integer idAluno;
    private String nome;
    private LocalDate dataNascimento;
    private String email;
    private String senha;
    private Integer enderecoIdEndereco;

    public Aluno() {
    }

    public Aluno(Integer idAluno, String nome, LocalDate dataNascimento, String email, String senha, Integer enderecoIdEndereco) {
        this.idAluno = idAluno;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.senha = senha;
        this.enderecoIdEndereco = enderecoIdEndereco;
    }

    public Integer getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Integer idAluno) {
        this.idAluno = idAluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getEnderecoIdEndereco() {
        return enderecoIdEndereco;
    }

    public void setEnderecoIdEndereco(Integer enderecoIdEndereco) {
        this.enderecoIdEndereco = enderecoIdEndereco;
    }

}
