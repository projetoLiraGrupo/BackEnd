package com.lira.grupo.api.lira_api.requestDto;

import com.lira.grupo.api.lira_api.entity.Endereco;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ProfessorRequestDto {

    @NotBlank(message = "O nome do professor é obrigatório.")
    private String nome;

    @NotBlank(message = "O email do professor é obrigatório.")
    @Email
    private String email;

    @NotBlank(message = "A senha do professor é obrigatória.")
    private String senha;

    @NotBlank(message = "O telefone do professor é obrigatório.")
    private String telefone;

    private LocalDate criadoEm;

    @NotNull(message = "O endereço do professor é obrigatório.")
    private Endereco fkEndereco;
}
