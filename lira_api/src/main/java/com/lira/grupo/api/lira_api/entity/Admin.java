package com.lira.grupo.api.lira_api.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "admin")
public class Admin implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_admin", nullable = false)
    private Integer idAdmin;

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em", nullable = true)
    private LocalDateTime atualizadoEm;

    @Column(name = "admin_nome", nullable = false)
    private String adminNome;

    @Column(name = "admin_email", nullable = false)
    private String adminEmail;

    @Column(name = "admin_senha", nullable = false)
    private String adminSenha;

    @ManyToOne
    @JoinColumn(name = "fk_endereco", nullable = false)
    private Endereco fkEndereco;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
    public Admin() {
    }


//    public Admin(Integer idAdmin, String nome, String email, String senha, Endereco fkEndereco) {
//        this.idAdmin = idAdmin;
//        this.adminNome = nome;
//        this.admin_email = email;
//        this.admin_senha = senha;
//        this.fkEndereco = fkEndereco;
//    }

    @Override
    public String getPassword() {
        return adminSenha;
    }

    @Override
    public String getUsername() {
        return adminEmail;
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


    
    public Integer getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(LocalDateTime atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }

    public String getadminNome() {
        return adminNome;
    }

    public void setadminNome(String adminNome) {
        this.adminNome = adminNome;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String admin_email) {
        this.adminEmail = admin_email;
    }

    public String getAdminSenha() {
        return adminSenha;
    }

    public void setAdminSenha(String admin_senha) {
        this.adminSenha = admin_senha;
    }

    public Endereco getFkEndereco() {
        return fkEndereco;
    }

    public void setFkEndereco(Endereco fkEndereco) {
        this.fkEndereco = fkEndereco;
    }
}
