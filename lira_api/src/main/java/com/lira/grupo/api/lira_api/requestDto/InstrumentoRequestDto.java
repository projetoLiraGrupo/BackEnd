package com.lira.grupo.api.lira_api.requestDto;

import jakarta.validation.constraints.NotBlank;

public class InstrumentoRequestDto {

    @NotBlank(message = "O nome do instrumento é obrigatório.")
    private String nome;

    @NotBlank(message = "A descrição é obrigatória.")
    private Long descricao;

    public InstrumentoRequestDto() {
    }

    public InstrumentoRequestDto(String nome, Long descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getDescricao() {
        return descricao;
    }

    public void setDescricao(Long descricao) {
        this.descricao = descricao;
    }
}
