package com.lira.grupo.api.lira_api.requestDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Dados para cadastrar um endereço")
public class EnderecoRequestDto {

    @Schema(
            description = "Nome da rua do logradouro",
            examples = "Rua Topázio"
    )
    @NotBlank(message = "O logradouro é obrigatório.")
    @Size(max = 150, message = "O logradouro deve ter no máximo 150 caracteres.")
    private String logradouro;

    @Schema(
            description = "Número do logradouro",
            examples = "258"
    )
    @NotBlank(message = "O número é obrigatório.")
    @Size(max = 10, message = "O número deve ter no máximo 10 caracteres.")
    private String numero;

    @Schema(
            description = "Complemento para o endereço",
            examples = "2° andar"
    )
    @Size(max = 100, message = "O complemento deve ter no máximo 100 caracteres.")
    private String complemento;

    @Schema(
            description = "CEP do endereço",
            examples = "04105-061"
    )
    @NotBlank(message = "O CEP é obrigatório.")
    @Pattern(regexp = "^\\d{5}-?\\d{3}$", message = "O CEP informado é inválido.")
    private String cep;

    public EnderecoRequestDto() {
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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
