package com.lirapaulistana.api.model;

public class Preferencia {

    private Integer idPreferencia;
    private String categoria;
    private Integer valor;
    private String aplicavelPara;
    private String tipo;

    public Preferencia() {
    }

    public Preferencia(Integer idPreferencia, String categoria, Integer valor, String aplicavelPara, String tipo) {
        this.idPreferencia = idPreferencia;
        this.categoria = categoria;
        this.valor = valor;
        this.aplicavelPara = aplicavelPara;
        this.tipo = tipo;
    }

    public Integer getIdPreferencia() {
        return idPreferencia;
    }

    public void setIdPreferencia(Integer idPreferencia) {
        this.idPreferencia = idPreferencia;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public String getAplicavelPara() {
        return aplicavelPara;
    }

    public void setAplicavelPara(String aplicavelPara) {
        this.aplicavelPara = aplicavelPara;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
