package com.lirapaulistana.api.controller;

import com.lirapaulistana.api.model.PreferenciaProfessor;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preferencias-professores")
public class PreferenciaProfessorController {

    private final JdbcTemplate jdbcTemplate;

    public PreferenciaProfessorController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public List<PreferenciaProfessor> listar() {
        String sql = """
                SELECT fkProfessor, fkPreferencia
                FROM PreferenciaProfessor
                ORDER BY fkProfessor, fkPreferencia
                """;

        return jdbcTemplate.query(sql, new DataClassRowMapper<>(PreferenciaProfessor.class));
    }

    @GetMapping("/professor/{professorId}")
    public List<PreferenciaProfessor> listarPorProfessor(@PathVariable Integer professorId) {
        String sql = """
                SELECT fkProfessor, fkPreferencia
                FROM PreferenciaProfessor
                WHERE fkProfessor = ?
                ORDER BY fkPreferencia
                """;

        return jdbcTemplate.query(sql, new DataClassRowMapper<>(PreferenciaProfessor.class), professorId);
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody PreferenciaProfessor body) {
        String sql = """
                INSERT INTO PreferenciaProfessor
                    (fkProfessor, fkPreferencia)
                VALUES (?, ?)
                """;

        jdbcTemplate.update(sql, body.getFkProfessor(), body.getFkPreferencia());

        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{professorId}/{preferenciaId}")
    public ResponseEntity<Void> remover(
            @PathVariable Integer professorId,
            @PathVariable Integer preferenciaId
    ) {
        String sql = """
                DELETE FROM PreferenciaProfessor
                WHERE fkProfessor = ?
                  AND fkPreferencia = ?
                """;

        int linhas = jdbcTemplate.update(sql, professorId, preferenciaId);

        if (linhas == 0) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(204).build();
        }
    }
}
