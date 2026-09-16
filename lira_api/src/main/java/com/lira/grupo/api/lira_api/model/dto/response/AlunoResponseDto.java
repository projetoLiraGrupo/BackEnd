package com.lira.grupo.api.lira_api.model.dto.response;

public class AlunoResponseDto {

    private String alunoNome;
    private String alunoEmail;
    private String alunoCpf;

    public AlunoResponseDto() {}


    public AlunoResponseDto(String alunoNome, String alunoEmail, String alunoCpf) {
        this.alunoNome = alunoNome;
        this.alunoEmail = alunoEmail;
        this.alunoCpf = alunoCpf;
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
}
