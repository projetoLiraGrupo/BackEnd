package com.lira.grupo.api.lira_api.responseDto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Representa a marcação de um aluno em uma aula")
public class AlunoAulaResponseDto {

    @Schema(description = "Identificador da marcação", examples = "1")
    private Integer idAlunoAula;

    @Schema(description = "Data e hora em que a marcação foi feita", examples = "2026-11-24T10:15:00")
    private LocalDateTime criadoEm;

    @Schema(description = "Identificador do aluno", examples = "1")
    private Integer idAluno;

    @Schema(description = "Nome do aluno", examples = "Clark Kent")
    private String alunoNome;

    @Schema(description = "Aula marcada", implementation = AulaResponseDto.class)
    private AulaResponseDto aula;

    public AlunoAulaResponseDto() {
    }

    public AlunoAulaResponseDto(Integer idAlunoAula, LocalDateTime criadoEm, Integer idAluno,
                                String alunoNome, AulaResponseDto aula) {
        this.idAlunoAula = idAlunoAula;
        this.criadoEm = criadoEm;
        this.idAluno = idAluno;
        this.alunoNome = alunoNome;
        this.aula = aula;
    }

    public Integer getIdAlunoAula() {
        return idAlunoAula;
    }

    public void setIdAlunoAula(Integer idAlunoAula) {
        this.idAlunoAula = idAlunoAula;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
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

    public AulaResponseDto getAula() {
        return aula;
    }

    public void setAula(AulaResponseDto aula) {
        this.aula = aula;
    }
}
