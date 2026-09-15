package com.lira.grupo.api.lira_api.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EnderecoDto {

    @NotBlank(message = "O logradouro/rua é obrigatório.")
    @Size(max = 150, message = "O logradouro deve ter no máximo 150 caracteres.")
    private String logradouro;

    @NotBlank(message = "O número é obrigatório.")
    @Size(max = 10, message = "O número deve ter no máximo 10 caracteres.")
    private String numero;

    @Size(max = 100, message = "O complemento deve ter no máximo 100 caracteres.")
    private String complemento;

    @NotBlank(message = "O bairro é obrigatório.")
    @Size(max = 100, message = "O bairro deve ter no máximo 100 caracteres.")
    private String bairro;

    @NotBlank(message = "A cidade é obrigatória.")
    @Size(max = 100, message = "A cidade deve ter no máximo 100 caracteres.")
    private String cidade;

    @NotBlank(message = "O estado/UF é obrigatório.")
    @Size(min = 2, max = 2, message = "O estado deve conter exatamente a sigla de 2 caracteres (ex: SP).")
    private String estado;

    @NotBlank(message = "O CEP é obrigatório.")

    @Pattern(regexp = "^\\d{5}-\\d{3}$|^\\d{8}$", message = "O CEP informado é inválido. Use o formato 00000-000 ou apenas números.")
    private String cep;

    public EnderecoDto() {
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

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
