package com.lira.grupo.api.lira_api.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(

)
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aula")
    private Integer idAula;

    @Column(name = "aula_dia_hora")
    private LocalDateTime aulaDiaHora;

    @Column(name = "aula_nivel")
    private String aulaNivel;

    @ManyToOne
    @JoinColumn(name = "fk_endereco")
    private Endereco fkEndereco;

    @ManyToOne
    @JoinColumn(name = "fk_professor")
    private Professor fkProfessor;


    public Aula() {
    }


    public Aula(Integer idAula, LocalDateTime diaHora, String nivel, Endereco fkEndereco, Professor fkProfessor) {
        this.idAula = idAula;
        this.aulaDiaHora = diaHora;
        this.aulaNivel = nivel;
        this.fkEndereco = fkEndereco;
        this.fkProfessor = fkProfessor;
    }

    public Integer getIdAula() {
        return idAula;
    }

    public void setIdAula(Integer idAula) {
        this.idAula = idAula;
    }

    public LocalDateTime getDiaHora() {
        return aulaDiaHora;
    }

    public void setDiaHora(LocalDateTime aulaDiaHora) {
        this.aulaDiaHora = aulaDiaHora;
    }

    public String getNivel() {
        return aulaNivel;
    }

    public void setNivel(String nivel) {
        this.aulaNivel = nivel;
    }

    public Endereco getFkEndereco() {
        return fkEndereco;
    }

    public void setFkEndereco(Endereco fkEndereco) {
        this.fkEndereco = fkEndereco;
    }

    public Professor getFkProfessor() {
        return fkProfessor;
    }

    public void setFkProfessor(Professor fkProfessor) {
        this.fkProfessor = fkProfessor;
    }
}
