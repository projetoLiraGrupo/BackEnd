package com.lirapaulistana.api.model;

import java.time.LocalDate;

public class Professor {

    private Integer idProfessor;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private LocalDate criadoEm;
    private Integer fkEndereco;

    public Professor() {
    }

    public Professor(Integer idProfessor, String nome, String email, String senha, String telefone, LocalDate criadoEm, Integer fkEndereco) {
        this.idProfessor = idProfessor;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.criadoEm = criadoEm;
        this.fkEndereco = fkEndereco;
    }

    public Integer getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(Integer idProfessor) {
        this.idProfessor = idProfessor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDate criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Integer getFkEndereco() {
        return fkEndereco;
    }

    public void setFkEndereco(Integer fkEndereco) {
        this.fkEndereco = fkEndereco;
    }

}
