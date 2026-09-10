package com.lira.grupo.api.lira_api.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.sql.Date;

@Entity
@Table(name = "Aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAluno;

    @Column(nullable = false)
    private String alunoNome;

    @Column(nullable = false, unique = true)
    private String alunoEmail;

    @Column(nullable = false)
    private String alunoSenha;

    @Column(nullable = false, unique = true)
    private String alunoCpf;

    @Column(nullable = false)
    private Boolean alunoPossuiResponsavel = false;

    @Column(nullable = false)
    private Date dataDeNascimento;

    @ManyToOne
    @JoinColumn(
            name = "fkEndereco",
            referencedColumnName = "idEndereco"
    )
    private Endereco endereco;

    public Aluno(Integer idAluno, String alunoNome, String alunoEmail, String alunoSenha, String alunoCpf, Boolean alunoPossuiResponsavel, Date dataDeNascimento, Endereco endereco) {
        this.idAluno = idAluno;
        this.alunoNome = alunoNome;
        this.alunoEmail = alunoEmail;
        this.alunoSenha = alunoSenha;
        this.alunoCpf = alunoCpf;
        this.alunoPossuiResponsavel = alunoPossuiResponsavel;
        this.dataDeNascimento = dataDeNascimento;
        this.endereco = endereco;
    }

    public Aluno() {
    }

    public Integer getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Integer idAluno) {
        this.idAluno = idAluno;
    }

    public String getAlunoNome() {
        return alunoNome;
    }

    public void setAlunoNome(String alunoNome) {
        this.alunoNome = alunoNome;
    }

    public String getAlunoEmail() {
        return alunoEmail;
    }

    public void setAlunoEmail(String alunoEmail) {
        this.alunoEmail = alunoEmail;
    }

    public String getAlunoSenha() {
        return alunoSenha;
    }

    public void setAlunoSenha(String alunoSenha) {
        this.alunoSenha = alunoSenha;
    }

    public String getAlunoCpf() {
        return alunoCpf;
    }

    public void setAlunoCpf(String alunoCpf) {
        this.alunoCpf = alunoCpf;
    }

    public Boolean getAlunoPossuiResponsavel() {
        return alunoPossuiResponsavel;
    }

    public void setAlunoPossuiResponsavel(Boolean alunoPossuiResponsavel) {
        this.alunoPossuiResponsavel = alunoPossuiResponsavel;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}