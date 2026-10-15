package com.lira.grupo.api.lira_api.requestDto;

import com.lira.grupo.api.lira_api.entity.Endereco;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public class AulaRequestDto {

    @NotBlank(message = "O nome do aluno é obrigatório.")
    private String alunoNome;

    @NotBlank(message = "O email do aluno é obrigatório.")
    @Email
    private String alunoEmail;

    @NotBlank(message = "A senha do aluno é obrigatória.")
    private String alunoSenha;

    @NotBlank(message = "O cpf do aluno é obrigatório.")
    @Pattern(regexp = "\\d{14}")
    private String alunoCpf;

    @NotNull(message = "É obrigatório saber se possui um responsável.")
    private Boolean alunoPossuiResponsavel;

    @NotNull(message = "A data de nascimento do aluno é obrigatório.")
    private LocalDate dataDeNascimento;

    @NotNull(message = "O endereço do aluno é obrigatório.")
    private Endereco endereco;


    public AulaRequestDto() {
    }


    public AulaRequestDto(String alunoNome, String alunoEmail, String alunoSenha, String alunoCpf, Boolean alunoPossuiResponsavel, LocalDate dataDeNascimento, Endereco endereco) {
        this.alunoNome = alunoNome;
        this.alunoEmail = alunoEmail;
        this.alunoSenha = alunoSenha;
        this.alunoCpf = alunoCpf;
        this.alunoPossuiResponsavel = alunoPossuiResponsavel;
        this.dataDeNascimento = dataDeNascimento;
        this.endereco = endereco;
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

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
