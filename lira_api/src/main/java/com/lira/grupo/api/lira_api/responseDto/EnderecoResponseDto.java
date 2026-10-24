package com.lira.grupo.api.lira_api.responseDto;

import com.lira.grupo.api.lira_api.entity.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representa os dados retornados pela API relacionado ao endereço")
public class EnderecoResponseDto {
    @Schema(
            description = "Identificador do Endereço",
            examples = "1"
    )
    private Integer idEndereco;

    @Schema(
            description = "CEP do endereço",
            examples = "04105-061"
    )
    private String cep;

    @Schema(
            description = "Nome da rua do logradouro",
            examples = "Rua Topázio"
    )
    private String logradouro;

    @Schema(
            description = "Número do logradouro",
            examples = "258"
    )
    private String numero;

    @Schema(
            description = "Complemento para o endereço",
            examples = "2° andar"
    )
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
