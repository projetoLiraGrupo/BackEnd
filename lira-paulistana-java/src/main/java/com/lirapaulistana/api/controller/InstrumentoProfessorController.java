package com.lirapaulistana.api.controller;

import com.lirapaulistana.api.model.InstrumentoProfessor;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instrumentos-professores")
public class InstrumentoProfessorController {

    private final JdbcTemplate jdbcTemplate;

    public InstrumentoProfessorController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public List<InstrumentoProfessor> listar() {
        String sql = """
                SELECT fkProfessor, fkInstrumento
                FROM InstrumentoProfessor
                ORDER BY fkProfessor, fkInstrumento
                """;

        return jdbcTemplate.query(sql, new DataClassRowMapper<>(InstrumentoProfessor.class));
    }

    @GetMapping("/professor/{professorId}")
    public List<InstrumentoProfessor> listarPorProfessor(@PathVariable Integer professorId) {
        String sql = """
                SELECT fkProfessor, fkInstrumento
                FROM InstrumentoProfessor
                WHERE fkProfessor = ?
                ORDER BY fkInstrumento
                """;

        return jdbcTemplate.query(sql, new DataClassRowMapper<>(InstrumentoProfessor.class), professorId);
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody InstrumentoProfessor body) {
        String sql = """
                INSERT INTO InstrumentoProfessor
                    (fkProfessor, fkInstrumento)
                VALUES (?, ?)
                """;

        jdbcTemplate.update(sql, body.getFkProfessor(), body.getFkInstrumento());

        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{professorId}/{instrumentoId}")
    public ResponseEntity<Void> remover(
            @PathVariable Integer professorId,
            @PathVariable Integer instrumentoId
    ) {
        String sql = """
                DELETE FROM InstrumentoProfessor
                WHERE fkProfessor = ?
                  AND fkInstrumento = ?
                """;

        int linhas = jdbcTemplate.update(sql, professorId, instrumentoId);

        if (linhas == 0) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(204).build();
        }
    }
}
