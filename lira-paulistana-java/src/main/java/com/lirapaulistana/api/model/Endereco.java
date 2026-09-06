package com.lirapaulistana.api.model;

public class Endereco {

    private Integer idEndereco;
    private String rua;
    private String numero;
    private String cep;

    public Endereco() {
    }

    public Endereco(Integer idEndereco, String rua, String numero, String cep) {
        this.idEndereco = idEndereco;
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

}
