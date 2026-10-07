package com.lira.grupo.api.lira_api.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequestDto {

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "O e-mail informado é inválido.")
    @Schema(description = "Email do usuário", example = "superKentClark@Lira.com")
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    @Schema(description = "Senha do usuário", example = "0culos&Capa")
    private String senha;

    public LoginRequestDto() {}

    public LoginRequestDto(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
