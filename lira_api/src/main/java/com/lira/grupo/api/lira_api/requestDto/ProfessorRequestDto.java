package com.lira.grupo.api.lira_api.requestDto;

import com.lira.grupo.api.lira_api.entity.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
@Schema(description = "Dados para cadastrar um professor")
public class ProfessorRequestDto {
    @Schema(description = "Nome do Professor", examples = "David Luiz")
    @NotBlank(message = "O nome do professor é obrigatório.")
    private String nome;

    @Schema(description = "Email do Professor", examples = "davi@lira.prof.com")
    @NotBlank(message = "O email do professor é obrigatório.")
    @Email
    private String email;

    @Schema(description = "Senha do Professor", examples = "dfv6+40dsfv")
    @NotBlank(message = "A senha do professor é obrigatória.")
    private String senha;

    @Schema(description = "Telefone do Professor", examples = "11911111111")
    @NotBlank(message = "O telefone do professor é obrigatório.")
    private String telefone;

    @Schema(description = "Data da criação da conta do Professor", examples = "2026-10-24")
    private LocalDate criadoEm;

    @Schema(description = "Identificador do endereço do Professor", examples = "2")
    @NotNull(message = "O endereço do professor é obrigatório.")
    private Endereco fkEndereco;
}
