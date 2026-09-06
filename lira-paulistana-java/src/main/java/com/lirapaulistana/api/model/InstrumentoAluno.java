package com.lirapaulistana.api.model;

public class InstrumentoAluno {

    private Integer instrumentoIdInstrumento;
    private Integer alunoIdAluno;
    private String nivel;

    public InstrumentoAluno() {
    }

    public InstrumentoAluno(Integer instrumentoIdInstrumento, Integer alunoIdAluno, String nivel) {
        this.instrumentoIdInstrumento = instrumentoIdInstrumento;
        this.alunoIdAluno = alunoIdAluno;
        this.nivel = nivel;
    }

    public Integer getInstrumentoIdInstrumento() {
        return instrumentoIdInstrumento;
    }

    public void setInstrumentoIdInstrumento(Integer instrumentoIdInstrumento) {
        this.instrumentoIdInstrumento = instrumentoIdInstrumento;
    }

    public Integer getAlunoIdAluno() {
        return alunoIdAluno;
    }

    public void setAlunoIdAluno(Integer alunoIdAluno) {
        this.alunoIdAluno = alunoIdAluno;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

}
