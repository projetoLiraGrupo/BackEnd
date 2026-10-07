package com.lira.grupo.api.lira_api.entity.dto.response;

import com.lira.grupo.api.lira_api.entity.Endereco;

public class EnderecoResponseDto {

    private Integer idEndereco;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;

    public EnderecoResponseDto() {
    }

    public static EnderecoResponseDto from(Endereco endereco) {
        EnderecoResponseDto dto = new EnderecoResponseDto();
        dto.idEndereco = endereco.getIdEndereco();
        dto.cep = endereco.getCep();
        dto.logradouro = endereco.getLogradouro();
        dto.numero = endereco.getNumero();
        dto.complemento = endereco.getComplemento();
        return dto;
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
