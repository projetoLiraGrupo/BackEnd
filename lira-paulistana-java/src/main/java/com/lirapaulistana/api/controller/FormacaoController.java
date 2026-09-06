package com.lirapaulistana.api.controller;

import com.lirapaulistana.api.model.Formacao;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/formacoes")
public class FormacaoController {

    private final JdbcTemplate jdbcTemplate;

    public FormacaoController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public List<Formacao> listar() {
        String sql = "SELECT * FROM Formacao ORDER BY idFormacao";

        return jdbcTemplate.query(sql, new DataClassRowMapper<>(Formacao.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Formacao> buscarPorId(@PathVariable Integer id) {
        String sql = "SELECT * FROM Formacao WHERE idFormacao = ?";

        List<Formacao> resultado =
                jdbcTemplate.query(sql, new DataClassRowMapper<>(Formacao.class), id);

        if (resultado.isEmpty()) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(200).body(resultado.getFirst());
        }
    }

    @GetMapping("/professor/{professorId}")
    public List<Formacao> listarPorProfessor(@PathVariable Integer professorId) {
        String sql = """
                SELECT *
                FROM Formacao
                WHERE fkProfessor = ?
                ORDER BY idFormacao
                """;

        return jdbcTemplate.query(sql, new DataClassRowMapper<>(Formacao.class), professorId);
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody Formacao body) {
        String sql = """
                INSERT INTO Formacao
                    (curso, instituicao, grau, status, dtTermino, fkProfessor)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        jdbcTemplate.update(
                sql,
                body.getCurso(),
                body.getInstituicao(),
                body.getGrau(),
                body.getStatus(),
                body.getDtTermino(),
                body.getFkProfessor()
        );

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable Integer id,
            @RequestBody Formacao body
    ) {
        String sql = """
                UPDATE Formacao
                SET curso = ?,
                    instituicao = ?,
                    grau = ?,
                    status = ?,
                    dtTermino = ?,
                    fkProfessor = ?
                WHERE idFormacao = ?
                """;

        int linhas = jdbcTemplate.update(
                sql,
                body.getCurso(),
                body.getInstituicao(),
                body.getGrau(),
                body.getStatus(),
                body.getDtTermino(),
                body.getFkProfessor(),
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
        String sql = "DELETE FROM Formacao WHERE idFormacao = ?";

        int linhas = jdbcTemplate.update(sql, id);

        if (linhas == 0) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(204).build();
        }
    }
}
