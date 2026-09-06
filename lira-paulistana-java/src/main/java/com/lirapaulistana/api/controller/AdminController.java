package com.lirapaulistana.api.controller;

import com.lirapaulistana.api.model.Admin;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final JdbcTemplate jdbcTemplate;

    public AdminController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public List<Admin> listar() {
        String sql = "SELECT * FROM Admin ORDER BY idAdmin";

        return jdbcTemplate.query(sql, new DataClassRowMapper<>(Admin.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> buscarPorId(@PathVariable Integer id) {
        String sql = "SELECT * FROM Admin WHERE idAdmin = ?";

        List<Admin> resultado =
                jdbcTemplate.query(sql, new DataClassRowMapper<>(Admin.class), id);

        if (resultado.isEmpty()) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(200).body(resultado.getFirst());
        }
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody Admin body) {
        String sql = """
                INSERT INTO Admin
                    (nome, email, senha)
                VALUES (?, ?, ?)
                """;

        jdbcTemplate.update(
                sql,
                body.getNome(),
                body.getEmail(),
                body.getSenha()
        );

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable Integer id,
            @RequestBody Admin body
    ) {
        String sql = """
                UPDATE Admin
                SET nome = ?,
                    email = ?,
                    senha = ?
                WHERE idAdmin = ?
                """;

        int linhas = jdbcTemplate.update(
                sql,
                body.getNome(),
                body.getEmail(),
                body.getSenha(),
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
        String sql = "DELETE FROM Admin WHERE idAdmin = ?";

        int linhas = jdbcTemplate.update(sql, id);

        if (linhas == 0) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(204).build();
        }
    }
}
