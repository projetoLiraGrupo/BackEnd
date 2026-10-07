package com.lira.grupo.api.lira_api.entity.dto.response;

import com.lira.grupo.api.lira_api.entity.Aluno;

import java.time.LocalDate;

public class AlunoResponseDto {

    private Integer idAluno;
    private String alunoNome;
    private String alunoEmail;
    private String alunoCpf;
    private Boolean alunoPossuiResponsavel;
    private LocalDate dataDeNascimento;
    private Integer fkEndereco;
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
