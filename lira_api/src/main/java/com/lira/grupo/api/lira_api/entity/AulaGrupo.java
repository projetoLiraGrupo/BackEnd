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
    @JoinColumn(name = "fk_aula")
    private Aula fkAula;

    @Column(name = "inicio_em")
    private LocalDateTime inicioEm;

    @Column(name = "termino_em")
    private LocalDateTime terminoEm;

    @Column(name = "qtd_aluno_max")
    private Integer qtdAlunoMax;


    public AulaGrupo() {
    }


    public AulaGrupo(Integer idAulaGrupo, Aula fkAula, LocalDateTime inicioEm, LocalDateTime terminoEm, Integer qtdAlunoMax) {
        this.idAulaGrupo = idAulaGrupo;
        this.fkAula = fkAula;
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

    public Aula getFkAula() {
        return fkAula;
    }

    public void setFkAula(Aula fkAula) {
        this.fkAula = fkAula;
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
