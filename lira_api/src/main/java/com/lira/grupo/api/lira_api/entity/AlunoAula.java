package com.lira.grupo.api.lira_api.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "aluno_aula",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_aluno_aula",
                columnNames = {"fk_aluno", "fk_aula"}
        ))
public class AlunoAula {

    @Id
    @Column(name = "id_aluno_aula")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAlunoAula;

    @CreationTimestamp
    @Column(name = "marcado_em", nullable = false, updatable = false)
    private LocalDateTime marcadoEm;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_aluno", nullable = false)
    private Aluno fkAluno;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_aula", nullable = false)
    private Aula fkAula;

    public AlunoAula() {
    }

    public AlunoAula(Aluno fkAluno, Aula fkAula) {
        this.fkAluno = fkAluno;
        this.fkAula = fkAula;
    }

    public Integer getIdAlunoAula() {
        return idAlunoAula;
    }

    public void setIdAlunoAula(Integer idAlunoAula) {
        this.idAlunoAula = idAlunoAula;
    }

    public LocalDateTime getMarcadoEm() {
        return marcadoEm;
    }

    public Aluno getFkAluno() {
        return fkAluno;
    }

    public void setFkAluno(Aluno fkAluno) {
        this.fkAluno = fkAluno;
    }

    public Aula getFkAula() {
        return fkAula;
    }

    public void setFkAula(Aula fkAula) {
        this.fkAula = fkAula;
    }
}