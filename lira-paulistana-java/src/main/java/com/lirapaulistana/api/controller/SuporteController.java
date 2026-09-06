package com.lirapaulistana.api.controller;

import com.lirapaulistana.api.model.Suporte;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suportes")
public class SuporteController {

    private final JdbcTemplate jdbcTemplate;

    public SuporteController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public List<Suporte> listar() {
        String sql = "SELECT * FROM Suporte ORDER BY idSuporte";

        return jdbcTemplate.query(sql, new DataClassRowMapper<>(Suporte.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Suporte> buscarPorId(@PathVariable Integer id) {
        String sql = "SELECT * FROM Suporte WHERE idSuporte = ?";

        List<Suporte> resultado =
                jdbcTemplate.query(sql, new DataClassRowMapper<>(Suporte.class), id);

        if (resultado.isEmpty()) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(200).body(resultado.getFirst());
        }
    }

    @GetMapping("/aluno/{alunoId}")
    public List<Suporte> listarPorAluno(@PathVariable Integer alunoId) {
        String sql = """
                SELECT *
                FROM Suporte
                WHERE fkAluno = ?
                ORDER BY idSuporte
                """;

        return jdbcTemplate.query(sql, new DataClassRowMapper<>(Suporte.class), alunoId);
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody Suporte body) {
        String sql = """
                INSERT INTO Suporte
                    (suportecol, nivel, descricao, tipo, fkAluno)
                VALUES (?, ?, ?, ?, ?)
                """;

        jdbcTemplate.update(
                sql,
                body.getSuportecol(),
                body.getNivel(),
                body.getDescricao(),
                body.getTipo(),
                body.getFkAluno()
        );

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable Integer id,
            @RequestBody Suporte body
    ) {
        String sql = """
                UPDATE Suporte
                SET suportecol = ?,
                    nivel = ?,
                    descricao = ?,
                    tipo = ?,
                    fkAluno = ?
                WHERE idSuporte = ?
                """;

        int linhas = jdbcTemplate.update(
                sql,
                body.getSuportecol(),
                body.getNivel(),
                body.getDescricao(),
                body.getTipo(),
                body.getFkAluno(),
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
        String sql = "DELETE FROM Suporte WHERE idSuporte = ?";

        int linhas = jdbcTemplate.update(sql, id);

        if (linhas == 0) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(204).build();
        }
    }
}
