package com.lira.grupo.api.lira_api;

import com.lira.grupo.api.lira_api.entity.Endereco;
import com.lira.grupo.api.lira_api.repository.EnderecoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LoginIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EnderecoRepository enderecoRepository;

    private Integer enderecoId;

    @BeforeEach
    void prepararEndereco() {
        Endereco endereco = new Endereco();
        endereco.setCep("04105060");
        endereco.setLogradouro("Rua Topázio");
        endereco.setNumero("258");
        enderecoId = enderecoRepository.save(endereco).getIdEndereco();
    }

    @Test
    void deveCadastrarEAutenticarAluno() throws Exception {
        long marcador = Math.abs(System.nanoTime());
        String email = "teste" + marcador + "@lira.com";
        String cpf = String.format("%011d", marcador % 100_000_000_000L);

        String cadastroJson = """
                {
                  "alunoNome": "Aluno Teste",
                  "alunoEmail": "%s",
                  "alunoSenha": "senha123",
                  "alunoCpf": "%s",
                  "dataDeNascimento": "1999-12-02",
                  "alunoPossuiResponsavel": false,
                  "fkEndereco": %d
                }
                """.formatted(email, cpf, enderecoId);

        mockMvc.perform(post("/alunos/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cadastroJson))
                .andExpect(status().isCreated());

        String loginJson = """
                {
                  "email": "%s",
                  "senha": "senha123"
                }
                """.formatted(email);

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andExpect(cookie().exists("authToken"));
    }

    @Test
    void deveRetornar401ParaSenhaInvalida() throws Exception {
        String loginJson = """
                {
                  "email": "naoexiste@lira.com",
                  "senha": "senha-errada"
                }
                """;

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andExpect(status().isUnauthorized());
    }
}
