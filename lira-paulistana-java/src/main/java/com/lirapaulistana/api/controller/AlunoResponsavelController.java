package com.lirapaulistana.api.controller;

import com.lirapaulistana.api.model.AlunoResponsavel;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/alunos-responsaveis")
public class AlunoResponsavelController {

    private final JdbcTemplate jdbcTemplate;

    public AlunoResponsavelController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public List<AlunoResponsavel> listar() {
        String sql = """
                SELECT idResponsavelAluno, fkAluno, fkResponsavel
                FROM Aluno_has_Responsavel
                ORDER BY idResponsavelAluno
                """;

        return jdbcTemplate.query(sql, new DataClassRowMapper<>(AlunoResponsavel.class));
    }

    @GetMapping("/aluno/{alunoId}")
    public List<AlunoResponsavel> listarPorAluno(@PathVariable Integer alunoId) {
        String sql = """
                SELECT idResponsavelAluno, fkAluno, fkResponsavel
                FROM Aluno_has_Responsavel
                WHERE fkAluno = ?
                ORDER BY idResponsavelAluno
                """;

        return jdbcTemplate.query(sql, new DataClassRowMapper<>(AlunoResponsavel.class), alunoId);
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody AlunoResponsavel body) {
        String sql = """
                INSERT INTO Aluno_has_Responsavel
                    (fkAluno, fkResponsavel)
                VALUES (?, ?)
                """;

        jdbcTemplate.update(sql, body.getFkAluno(), body.getFkResponsavel());

        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{alunoId}/{responsavelId}")
    public ResponseEntity<Void> remover(
            @PathVariable Integer alunoId,
            @PathVariable Integer responsavelId
    ) {
        String sql = """
                DELETE FROM Aluno_has_Responsavel
                WHERE fkAluno = ?
                  AND fkResponsavel = ?
                """;

        int linhas = jdbcTemplate.update(sql, alunoId, responsavelId);

        if (linhas == 0) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(204).build();
        }
    }
}
