package com.lira.grupo.api.lira_api.responseDto;

import com.lira.grupo.api.lira_api.entity.Endereco;

import java.time.LocalDate;

public class ProfessorResponseDto {

    private Integer idProfessor;
    private String nome;
    private String email;
    private String token;
    private String telefone;
    private LocalDate criadoEm;
    private Endereco fkEndereco;

    public ProfessorResponseDto() {
    }

    public ProfessorResponseDto(Integer idProfessor, String nome, String email, String token, String telefone, LocalDate criadoEm, Endereco fkEndereco) {
        this.idProfessor = idProfessor;
        this.nome = nome;
        this.email = email;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public Endereco getFkEndereco() {
        return fkEndereco;
    }

    public void setFkEndereco(Endereco fkEndereco) {
        this.fkEndereco = fkEndereco;
    }
}
