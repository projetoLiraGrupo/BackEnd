package com.lira.grupo.api.lira_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Integer idEndereco;

    @Column(name = "endereco_cep", nullable = false, length = 8)
    private String enderecoCep;

    @Column(name = "endereco_logradouro", nullable = false, length = 150)
    private String enderecoLogradouro;

    @Column(name = "endereco_numero", nullable = false, length = 10)
    private String enderecoNumero;

    @Column(name = "endereco_complemento", length = 100)
    private String enderecoComplemento;

    public Endereco() {
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getCep() {
        return enderecoCep;
    }

    public void setCep(String enderecoCep) {
        this.enderecoCep = enderecoCep;
    }

    public String getLogradouro() {
        return enderecoLogradouro;
    }

    public void setLogradouro(String enderecoLogradouro) {
        this.enderecoLogradouro = enderecoLogradouro;
    }

    public String getNumero() {
        return enderecoNumero;
    }

    public void setNumero(String enderecoNumero) {
        this.enderecoNumero = enderecoNumero;
    }

    public String getComplemento() {
        return enderecoComplemento;
    }

    public void setComplemento(String enderecoComplemento) {
        this.enderecoComplemento = enderecoComplemento;
    }
}
