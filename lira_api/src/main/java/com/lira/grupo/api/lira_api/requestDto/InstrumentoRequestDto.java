package com.lira.grupo.api.lira_api.requestDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Dados para cadastrar um instrumento")
public class InstrumentoRequestDto {
    @Schema(description = "Nome do instrumento", examples = "Contra Baixo")
    @NotBlank(message = "O nome do instrumento é obrigatório.")
    private String nome;
    @Schema(description = "Descrição do intrumento", examples = "Instrumento de cordas que produz sons graves, utilizado em diversos estilos musicais e ideal para acompanhar bandas e desenvolver ritmo e percepção musical.")
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
