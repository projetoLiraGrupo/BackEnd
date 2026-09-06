package com.lirapaulistana.api.controller;

import com.lirapaulistana.api.model.PreferenciaAluno;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preferencias-alunos")
public class PreferenciaAlunoController {

    private final JdbcTemplate jdbcTemplate;

    public PreferenciaAlunoController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public List<PreferenciaAluno> listar() {
        String sql = """
                SELECT fkAluno, fkPreferencia
                FROM PreferenciaAluno
                ORDER BY fkAluno, fkPreferencia
                """;

        return jdbcTemplate.query(sql, new DataClassRowMapper<>(PreferenciaAluno.class));
    }

    @GetMapping("/aluno/{alunoId}")
    public List<PreferenciaAluno> listarPorAluno(@PathVariable Integer alunoId) {
        String sql = """
                SELECT fkAluno, fkPreferencia
                FROM PreferenciaAluno
                WHERE fkAluno = ?
                ORDER BY fkPreferencia
                """;

        return jdbcTemplate.query(sql, new DataClassRowMapper<>(PreferenciaAluno.class), alunoId);
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody PreferenciaAluno body) {
        String sql = """
                INSERT INTO PreferenciaAluno
                    (fkAluno, fkPreferencia)
                VALUES (?, ?)
                """;

        jdbcTemplate.update(sql, body.getFkAluno(), body.getFkPreferencia());

        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{alunoId}/{preferenciaId}")
    public ResponseEntity<Void> remover(
            @PathVariable Integer alunoId,
            @PathVariable Integer preferenciaId
    ) {
        String sql = """
                DELETE FROM PreferenciaAluno
                WHERE fkAluno = ?
                  AND fkPreferencia = ?
                """;

        int linhas = jdbcTemplate.update(sql, alunoId, preferenciaId);

        if (linhas == 0) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(204).build();
        }
    }
}
