package com.lira.grupo.api.lira_api.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Aluno")
public class Aluno implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAluno;

    @Column(nullable = false)
    private String alunoNome;

    @Column(nullable = false, unique = true)
    private String alunoEmail;

    @Column(nullable = false)
    private String alunoSenha;

    @Column(nullable = false, unique = true)
    private String alunoCpf;

    @Column(nullable = false)
    private Boolean alunoPossuiResponsavel = false;

    @Column(nullable = false)
    private Date dataDeNascimento;

    @ManyToOne
    @JoinColumn(
            name = "fkEndereco",
            referencedColumnName = "idEndereco"
    )
    private Endereco endereco;


    public Aluno(Integer idAluno, String alunoNome, String alunoEmail, String alunoSenha, String alunoCpf, Boolean alunoPossuiResponsavel, Date dataDeNascimento, Endereco endereco) {
        this.idAluno = idAluno;
        this.alunoNome = alunoNome;
        this.alunoEmail = alunoEmail;
        this.alunoSenha = alunoSenha;
        this.alunoCpf = alunoCpf;
        this.alunoPossuiResponsavel = alunoPossuiResponsavel;
        this.dataDeNascimento = dataDeNascimento;
        this.endereco = endereco;
    }

    public Aluno() {
    }





    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.alunoSenha;
    }

    @Override
    public String getUsername() {
        return this.alunoEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }





    public Integer getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Integer idAluno) {
        this.idAluno = idAluno;
    }

    public String getAlunoNome() {
        return alunoNome;
    }

    public void setAlunoNome(String alunoNome) {
        this.alunoNome = alunoNome;
    }

    public String getAlunoEmail() {
        return alunoEmail;
    }

    public void setAlunoEmail(String alunoEmail) {
        this.alunoEmail = alunoEmail;
    }

    public String getAlunoSenha() {
        return alunoSenha;
    }

    public void setAlunoSenha(String alunoSenha) {
        this.alunoSenha = alunoSenha;
    }

    public String getAlunoCpf() {
        return alunoCpf;
    }

    public void setAlunoCpf(String alunoCpf) {
        this.alunoCpf = alunoCpf;
    }

    public Boolean getAlunoPossuiResponsavel() {
        return alunoPossuiResponsavel;
    }

    public void setAlunoPossuiResponsavel(Boolean alunoPossuiResponsavel) {
        this.alunoPossuiResponsavel = alunoPossuiResponsavel;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
