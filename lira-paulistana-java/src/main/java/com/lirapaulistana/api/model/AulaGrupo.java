package com.lirapaulistana.api.model;

import java.time.LocalDateTime;

public class AulaGrupo {

    private Integer idAulaGrupo;
    private Integer aulaIdAula;
    private Integer adminIdAdmin;
    private LocalDateTime inicioEm;
    private LocalDateTime terminoEm;
    private Integer qtdAlunosMax;

    public AulaGrupo() {
    }

    public AulaGrupo(Integer idAulaGrupo, Integer aulaIdAula, Integer adminIdAdmin, LocalDateTime inicioEm, LocalDateTime terminoEm, Integer qtdAlunosMax) {
        this.idAulaGrupo = idAulaGrupo;
        this.aulaIdAula = aulaIdAula;
        this.adminIdAdmin = adminIdAdmin;
        this.inicioEm = inicioEm;
        this.terminoEm = terminoEm;
        this.qtdAlunosMax = qtdAlunosMax;
    }

    public Integer getIdAulaGrupo() {
        return idAulaGrupo;
    }

    public void setIdAulaGrupo(Integer idAulaGrupo) {
        this.idAulaGrupo = idAulaGrupo;
    }

    public Integer getAulaIdAula() {
        return aulaIdAula;
    }

    public void setAulaIdAula(Integer aulaIdAula) {
        this.aulaIdAula = aulaIdAula;
    }

    public Integer getAdminIdAdmin() {
        return adminIdAdmin;
    }

    public void setAdminIdAdmin(Integer adminIdAdmin) {
        this.adminIdAdmin = adminIdAdmin;
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

    public Integer getQtdAlunosMax() {
        return qtdAlunosMax;
    }

    public void setQtdAlunosMax(Integer qtdAlunosMax) {
        this.qtdAlunosMax = qtdAlunosMax;
    }

}
