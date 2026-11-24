package com.lira.grupo.api.lira_api.responseDto;

import com.lira.grupo.api.lira_api.entity.Professor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Representa os dados retornados pela API relacionado ao professor")
public class ProfessorResponseDto {
    @Schema(description = "Identificador do Professor", examples = "1")
    private Integer idProfessor;
    @Schema(description = "Nome do Professor", examples = "David Luiz")
    private String nome;
    @Schema(description = "Email do Professor", examples = "davi@lira.prof.com")
    private String email;
    @Schema(description = "Token do Professor", examples = "1")
    private String token;
    @Schema(description = "Telefone do Professor", examples = "11911111111")
    private String telefone;
    @Schema(description = "Data da criação da conta do Professor", examples = "2026-10-24")
    private LocalDate criadoEm;
    @Schema(description = "Endereço do Professor", implementation = EnderecoResponseDto.class)
    private EnderecoResponseDto fkEndereco;

    public ProfessorResponseDto() {
    }

    public static ProfessorResponseDto from(Professor professor) {
        ProfessorResponseDto dto = new ProfessorResponseDto();
        dto.idProfessor = professor.getIdProfessor();
        dto.nome = professor.getNome();
        dto.email = professor.getEmail();
        dto.telefone = professor.getTelefone();
        dto.criadoEm = professor.getCriadoEm();
        dto.fkEndereco = professor.getFkEndereco() == null
                ? null
                : EnderecoResponseDto.from(professor.getFkEndereco());
        return dto;
    }

    public ProfessorResponseDto(Integer idProfessor, String nome, String email, String token, String telefone, LocalDate criadoEm, EnderecoResponseDto fkEndereco) {
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

    public EnderecoResponseDto getFkEndereco() {
        return fkEndereco;
    }

    public void setFkEndereco(EnderecoResponseDto fkEndereco) {
        this.fkEndereco = fkEndereco;
    }
}
