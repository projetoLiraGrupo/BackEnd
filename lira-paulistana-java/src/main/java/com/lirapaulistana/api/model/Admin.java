package com.lirapaulistana.api.model;

public class Admin {

    private Integer idAdmin;
    private String nome;
    private String email;
    private String senha;

    public Admin() {
    }

    public Admin(Integer idAdmin, String nome, String email, String senha) {
        this.idAdmin = idAdmin;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Integer getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
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

}
