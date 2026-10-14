package com.lira.grupo.api.lira_api.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class AulaGrupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aula_grupo")
    private Integer idAulaGrupo;

    @ManyToOne
    @JoinColumn(name = "pk_aula")
    private Aula pkAula;

    @Column(name = "inicio_em")
    private LocalDateTime inicioEm;

    @Column(name = "terminoEm")
    private LocalDateTime terminoEm;

    @Column(name = "qtd_aluno_max")
    private Integer qtdAlunoMax;


    public AulaGrupo() {
    }


    public AulaGrupo(Integer idAulaGrupo, Aula pkAula, LocalDateTime inicioEm, LocalDateTime terminoEm, Integer qtdAlunoMax) {
        this.idAulaGrupo = idAulaGrupo;
        this.pkAula = pkAula;
        this.inicioEm = inicioEm;
        this.terminoEm = terminoEm;
        this.qtdAlunoMax = qtdAlunoMax;
    }

    public Integer getIdAulaGrupo() {
        return idAulaGrupo;
    }

    public void setIdAulaGrupo(Integer idAulaGrupo) {
        this.idAulaGrupo = idAulaGrupo;
    }

    public Aula getPkAula() {
        return pkAula;
    }

    public void setPkAula(Aula pkAula) {
        this.pkAula = pkAula;
    }

    public LocalDateTime getInicioEm() {
        return inicioEm;
    }

    public void setInicioEm(LocalDateTime inicioEm) {
        this.inicioEm = inicioEm;
    }

    public LocalDateTime getTerminoEm() {
        return terminoEm;
    }

    public void setTerminoEm(LocalDateTime terminoEm) {
        this.terminoEm = terminoEm;
    }

    public Integer getQtdAlunoMax() {
        return qtdAlunoMax;
    }

    public void setQtdAlunoMax(Integer qtdAlunoMax) {
        this.qtdAlunoMax = qtdAlunoMax;
    }
}
