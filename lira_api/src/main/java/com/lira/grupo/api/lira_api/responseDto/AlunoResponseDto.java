package com.lira.grupo.api.lira_api.responseDto;

import com.lira.grupo.api.lira_api.entity.Aluno;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Representa os dados retornados pela API relacionado ao aulo")
public class AlunoResponseDto {
    @Schema(description = "Identificador do aluno", examples = "1")
    private Integer idAluno;
    @Schema(description = "Nome do aluno", examples = "Clark Kent")
    private String alunoNome;
    @Schema(description = "Email do aluno", examples = "superKentClark@Lira.com")
    private String alunoEmail;
    @Schema(description = "CPF do aluno", examples = "33333333333")
    private String alunoCpf;
    @Schema(description = "Aluno tem um responsavel", examples = "false")
    private Boolean alunoPossuiResponsavel;
    @Schema(description = "Data de nascimento do aluno", examples = "1999-12-02")
    private LocalDate dataDeNascimento;
    @Schema(description = "Identidicador do endereco do aluno", examples = "1")
    private Integer fkEndereco;
    @Schema(description = "Token do aluno", examples = "7f3a9c2e8b1d4f6a0c5e9d2b8a7f1c4e6d0b3a9f5e2c8d1a")
    private String token;

    public AlunoResponseDto() {
    }

    public static AlunoResponseDto from(Aluno aluno) {
        return from(aluno, null);
    }

    public static AlunoResponseDto from(Aluno aluno, String token) {
        AlunoResponseDto dto = new AlunoResponseDto();
        dto.idAluno = aluno.getIdAluno();
        dto.alunoNome = aluno.getAlunoNome();
        dto.alunoEmail = aluno.getAlunoEmail();
        dto.alunoCpf = aluno.getAlunoCpf();
        dto.alunoPossuiResponsavel = aluno.getAlunoPossuiResponsavel();
        dto.dataDeNascimento = aluno.getDataDeNascimento();
        dto.fkEndereco = aluno.getEndereco() == null ? null : aluno.getEndereco().getIdEndereco();
        dto.token = token;
        return dto;
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

    public Integer getFkEndereco() {
        return fkEndereco;
    }

    public void setFkEndereco(Integer fkEndereco) {
        this.fkEndereco = fkEndereco;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
