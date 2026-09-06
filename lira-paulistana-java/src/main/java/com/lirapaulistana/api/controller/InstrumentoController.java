package com.lirapaulistana.api.controller;

import com.lirapaulistana.api.model.Instrumento;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instrumentos")
public class InstrumentoController {

    private final JdbcTemplate jdbcTemplate;

    public InstrumentoController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public List<Instrumento> listar() {
        String sql = "SELECT * FROM Instrumento ORDER BY idInstrumento";

        return jdbcTemplate.query(sql, new DataClassRowMapper<>(Instrumento.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instrumento> buscarPorId(@PathVariable Integer id) {
        String sql = "SELECT * FROM Instrumento WHERE idInstrumento = ?";

        List<Instrumento> resultado =
                jdbcTemplate.query(sql, new DataClassRowMapper<>(Instrumento.class), id);

        if (resultado.isEmpty()) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(200).body(resultado.getFirst());
        }
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody Instrumento body) {
        String sql = """
                INSERT INTO Instrumento
                    (nome, descricao)
                VALUES (?, ?)
                """;

        jdbcTemplate.update(
                sql,
                body.getNome(),
                body.getDescricao()
        );

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable Integer id,
            @RequestBody Instrumento body
    ) {
        String sql = """
                UPDATE Instrumento
                SET nome = ?,
                    descricao = ?
                WHERE idInstrumento = ?
                """;

        int linhas = jdbcTemplate.update(
                sql,
                body.getNome(),
                body.getDescricao(),
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
        String sql = "DELETE FROM Instrumento WHERE idInstrumento = ?";

        int linhas = jdbcTemplate.update(sql, id);

        if (linhas == 0) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(204).build();
        }
    }
}
