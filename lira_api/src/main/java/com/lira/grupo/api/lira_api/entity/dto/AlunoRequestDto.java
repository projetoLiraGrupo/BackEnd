package com.lira.grupo.api.lira_api.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class AlunoRequestDto {

    @NotBlank(message = "O nome do aluno é obrigatório.")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
    @Schema(description = "Nome do usuário", example = "Clark Kent")
    private String alunoNome;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "O e-mail informado é inválido.")
    @Schema(description = "E-mail do usuário", example = "superKentClark@Lira.com")
    private String alunoEmail;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 6, max = 72, message = "A senha deve ter entre 6 e 72 caracteres.")
    @Schema(description = "Senha do usuário", example = "0culos&Capa")
    private String alunoSenha;

    @NotBlank(message = "O CPF é obrigatório.")
    @Pattern(
            regexp = "(^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$)|(^\\d{11}$)",
            message = "O CPF deve conter 11 números ou estar no formato 000.000.000-00."
    )
    @Schema(description = "CPF do usuário", example = "33333333333")
    private String alunoCpf;

    @NotNull(message = "A data de nascimento é obrigatória.")
    @Past(message = "A data de nascimento deve estar no passado.")
    @Schema(description = "Data de nascimento", example = "1999-12-02")
    private LocalDate dataDeNascimento;

    @NotNull(message = "Informe se o aluno possui responsável.")
    @Schema(description = "Indica se o aluno possui responsável", example = "false")
    private Boolean alunoPossuiResponsavel;

    @NotNull(message = "O endereço é obrigatório.")
    @Schema(description = "ID do endereço associado ao aluno", example = "1")
    private Integer fkEndereco;

    public AlunoRequestDto() {
    }

    public AlunoRequestDto(
            String alunoNome,
            String alunoEmail,
            String alunoSenha,
            String alunoCpf,
            LocalDate dataDeNascimento,
            Boolean alunoPossuiResponsavel,
            Integer fkEndereco
    ) {
        this.alunoNome = alunoNome;
        this.alunoEmail = alunoEmail;
        this.alunoSenha = alunoSenha;
        this.alunoCpf = alunoCpf;
        this.dataDeNascimento = dataDeNascimento;
        this.alunoPossuiResponsavel = alunoPossuiResponsavel;
        this.fkEndereco = fkEndereco;
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

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
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
