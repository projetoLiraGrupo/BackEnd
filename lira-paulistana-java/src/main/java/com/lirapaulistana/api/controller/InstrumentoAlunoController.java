package com.lirapaulistana.api.controller;

import com.lirapaulistana.api.model.InstrumentoAluno;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instrumentos-alunos")
public class InstrumentoAlunoController {

    private final JdbcTemplate jdbcTemplate;

    public InstrumentoAlunoController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public List<InstrumentoAluno> listar() {
        String sql = """
                SELECT Instrumento_idInstrumento AS instrumentoIdInstrumento, Aluno_idAluno AS alunoIdAluno, nivel
                FROM InstrumentoAluno
                ORDER BY Aluno_idAluno, Instrumento_idInstrumento
                """;

        return jdbcTemplate.query(sql, new DataClassRowMapper<>(InstrumentoAluno.class));
    }

    @GetMapping("/aluno/{alunoId}")
    public List<InstrumentoAluno> listarPorAluno(@PathVariable Integer alunoId) {
        String sql = """
                SELECT Instrumento_idInstrumento AS instrumentoIdInstrumento, Aluno_idAluno AS alunoIdAluno, nivel
                FROM InstrumentoAluno
                WHERE Aluno_idAluno = ?
                ORDER BY Instrumento_idInstrumento
                """;

        return jdbcTemplate.query(sql, new DataClassRowMapper<>(InstrumentoAluno.class), alunoId);
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody InstrumentoAluno body) {
        String sql = """
                INSERT INTO InstrumentoAluno
                    (Instrumento_idInstrumento, Aluno_idAluno, nivel)
                VALUES (?, ?, ?)
                """;

        jdbcTemplate.update(
                sql,
                body.getInstrumentoIdInstrumento(),
                body.getAlunoIdAluno(),
                body.getNivel()
        );

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{instrumentoId}/{alunoId}")
    public ResponseEntity<Void> atualizarNivel(
            @PathVariable Integer instrumentoId,
            @PathVariable Integer alunoId,
            @RequestBody InstrumentoAluno body
    ) {
        String sql = """
                UPDATE InstrumentoAluno
                SET nivel = ?
                WHERE Instrumento_idInstrumento = ?
                  AND Aluno_idAluno = ?
                """;

        int linhas = jdbcTemplate.update(
                sql,
                body.getNivel(),
                instrumentoId,
                alunoId
        );

        if (linhas == 0) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(204).build();
        }
    }

    @DeleteMapping("/{instrumentoId}/{alunoId}")
    public ResponseEntity<Void> remover(
            @PathVariable Integer instrumentoId,
            @PathVariable Integer alunoId
    ) {
        String sql = """
                DELETE FROM InstrumentoAluno
                WHERE Instrumento_idInstrumento = ?
                  AND Aluno_idAluno = ?
                """;

        int linhas = jdbcTemplate.update(sql, instrumentoId, alunoId);

        if (linhas == 0) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(204).build();
        }
    }
}
