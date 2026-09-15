package com.lira.grupo.api.lira_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class AlunoDto {

    @NotBlank(message = "O nome do aluno é obrigatório.")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
    private String alunoNome;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "O e-mail informado é inválido.")
    private String alunoEmail;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 6, max = 30, message = "A senha deve ter entre 6 e 30 caracteres.")
    private String alunoSenha;

    @NotBlank(message = "O CPF é obrigatório.")

    // antes de gritar com a Ma_Hir
    // Pesquise regex, é legal;
    @Pattern(regexp = "(^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$)|(^\\d{11}$)", message = "O CPF deve estar no formato válido.")
    private String alunoCpf;

    @NotNull(message = "A data de nascimento é obrigatória.")
    @Past(message = "A data de nascimento deve ser uma data no passado.")
    private java.util.Date dataDeNascimento;

    @NotNull(message = "Informe se o aluno possui responsável.")
    private Boolean alunoPossuiResponsavel;

    @NotNull(message = "A chave estrangeira do endereço é obrigatória.")
    private Integer fkEndereco;

    public AlunoDto(String alunoNome, String alunoEmail, String alunoSenha, String alunoCpf, Date dataDeNascimento, Boolean alunoPossuiResponsavel, Integer fkEndereco) {
        this.alunoNome = alunoNome;
        this.alunoEmail = alunoEmail;
        this.alunoSenha = alunoSenha;
        this.alunoCpf = alunoCpf;
        this.dataDeNascimento = dataDeNascimento;
        this.alunoPossuiResponsavel = alunoPossuiResponsavel;
        this.fkEndereco = fkEndereco;
    }

    public AlunoDto() {
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

    public java.util.Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(java.util.Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public Boolean getAlunoPossuiResponsavel() {
        return alunoPossuiResponsavel;
    }

    public void setAlunoPossuiResponsavel(Boolean alunoPossuiResponsavel) {
        this.alunoPossuiResponsavel = alunoPossuiResponsavel;
    }

    public Integer getFkEndereco() {
        return fkEndereco;
    }

    public void setFkEndereco(Integer fkEndereco) {
        this.fkEndereco = fkEndereco;
    }
}
