package com.lira.grupo.api.lira_api.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @Column(name = "id_aluno_aula", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAlunoAula;

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em", nullable = true)
    private LocalDateTime atualizadoEm;

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