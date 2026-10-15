package com.lira.grupo.api.lira_api.responseDto;

public class InstrumentoResponseDto {

    private Integer idInstrumento;
    private String nome;
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
