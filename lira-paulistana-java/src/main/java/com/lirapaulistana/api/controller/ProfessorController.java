package com.lirapaulistana.api.controller;

import com.lirapaulistana.api.model.Professor;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    private final JdbcTemplate jdbcTemplate;

    public ProfessorController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public List<Professor> listar() {
        String sql = "SELECT * FROM Professor ORDER BY idProfessor";

        return jdbcTemplate.query(sql, new DataClassRowMapper<>(Professor.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscarPorId(@PathVariable Integer id) {
        String sql = "SELECT * FROM Professor WHERE idProfessor = ?";

        List<Professor> resultado =
                jdbcTemplate.query(sql, new DataClassRowMapper<>(Professor.class), id);

        if (resultado.isEmpty()) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(200).body(resultado.getFirst());
        }
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody Professor body) {
        String sql = """
                INSERT INTO Professor
                    (nome, email, senha, telefone, criadoEm, fkEndereco)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        jdbcTemplate.update(
                sql,
                body.getNome(),
                body.getEmail(),
                body.getSenha(),
                body.getTelefone(),
                body.getCriadoEm(),
                body.getFkEndereco()
        );

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable Integer id,
            @RequestBody Professor body
    ) {
        String sql = """
                UPDATE Professor
                SET nome = ?,
                    email = ?,
                    senha = ?,
                    telefone = ?,
                    criadoEm = ?,
                    fkEndereco = ?
                WHERE idProfessor = ?
                """;

        int linhas = jdbcTemplate.update(
                sql,
                body.getNome(),
                body.getEmail(),
                body.getSenha(),
                body.getTelefone(),
                body.getCriadoEm(),
                body.getFkEndereco(),
                id
        );

        if (linhas == 0) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(204).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        String sql = "DELETE FROM Professor WHERE idProfessor = ?";

        int linhas = jdbcTemplate.update(sql, id);

        if (linhas == 0) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(204).build();
        }
    }
}
