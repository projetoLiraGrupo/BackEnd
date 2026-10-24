package com.lira.grupo.api.lira_api.responseDto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representa os dados retornados pela API relacionado ao instrumento")
public class InstrumentoResponseDto {
    @Schema(description = "Identificador do Instrumento", examples = "4")
    private Integer idInstrumento;
    @Schema(description = "Nome do instrumento", examples = "Contra Baixo")
    private String nome;
    @Schema(description = "Descrição do intrumento", examples = "Instrumento de cordas que produz sons graves, utilizado em diversos estilos musicais e ideal para acompanhar bandas e desenvolver ritmo e percepção musical.")
    private Long descricao;


    public InstrumentoResponseDto() {
    }


    public InstrumentoResponseDto(Integer idInstrumento, String nome, Long descricao) {
        this.idInstrumento = idInstrumento;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Integer getIdInstrumento() {
        return idInstrumento;
    }

    public void setIdInstrumento(Integer idInstrumento) {
        this.idInstrumento = idInstrumento;
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
