package com.lirapaulistana.api.model;

public class AlunoResponsavel {

    private Integer idResponsavelAluno;
    private Integer fkAluno;
    private Integer fkResponsavel;

    public AlunoResponsavel() {
    }

    public AlunoResponsavel(Integer idResponsavelAluno, Integer fkAluno, Integer fkResponsavel) {
        this.idResponsavelAluno = idResponsavelAluno;
        this.fkAluno = fkAluno;
        this.fkResponsavel = fkResponsavel;
    }

    public Integer getIdResponsavelAluno() {
        return idResponsavelAluno;
    }

    public void setIdResponsavelAluno(Integer idResponsavelAluno) {
        this.idResponsavelAluno = idResponsavelAluno;
    }

    public Integer getFkAluno() {
        return fkAluno;
    }

    public void setFkAluno(Integer fkAluno) {
        this.fkAluno = fkAluno;
    }

    public Integer getFkResponsavel() {
        return fkResponsavel;
    }

    public void setFkResponsavel(Integer fkResponsavel) {
        this.fkResponsavel = fkResponsavel;
    }

}
