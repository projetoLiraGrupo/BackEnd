package com.lirapaulistana.api.controller;

import com.lirapaulistana.api.model.Experiencia;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experiencias")
public class ExperienciaController {

    private final JdbcTemplate jdbcTemplate;

    public ExperienciaController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public List<Experiencia> listar() {
        String sql = "SELECT idExperiencia, experiencia, inicioEm, terminoEm, Professor_idProfessor AS professorIdProfessor FROM Experiencia ORDER BY idExperiencia";

        return jdbcTemplate.query(sql, new DataClassRowMapper<>(Experiencia.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Experiencia> buscarPorId(@PathVariable Integer id) {
        String sql = "SELECT idExperiencia, experiencia, inicioEm, terminoEm, Professor_idProfessor AS professorIdProfessor FROM Experiencia WHERE idExperiencia = ?";

        List<Experiencia> resultado =
                jdbcTemplate.query(sql, new DataClassRowMapper<>(Experiencia.class), id);

        if (resultado.isEmpty()) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(200).body(resultado.getFirst());
        }
    }

    @GetMapping("/professor/{professorId}")
    public List<Experiencia> listarPorProfessor(@PathVariable Integer professorId) {
        String sql = """
                SELECT idExperiencia, experiencia, inicioEm, terminoEm, Professor_idProfessor AS professorIdProfessor
                FROM Experiencia
                WHERE Professor_idProfessor = ?
                ORDER BY idExperiencia
                """;

        return jdbcTemplate.query(sql, new DataClassRowMapper<>(Experiencia.class), professorId);
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody Experiencia body) {
        String sql = """
                INSERT INTO Experiencia
                    (experiencia, inicioEm, terminoEm, Professor_idProfessor)
                VALUES (?, ?, ?, ?)
                """;

        jdbcTemplate.update(
                sql,
                body.getExperiencia(),
                body.getInicioEm(),
                body.getTerminoEm(),
                body.getProfessorIdProfessor()
        );

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable Integer id,
            @RequestBody Experiencia body
    ) {
        String sql = """
                UPDATE Experiencia
                SET experiencia = ?,
                    inicioEm = ?,
                    terminoEm = ?,
                    Professor_idProfessor = ?
                WHERE idExperiencia = ?
                """;

        int linhas = jdbcTemplate.update(
                sql,
                body.getExperiencia(),
                body.getInicioEm(),
                body.getTerminoEm(),
                body.getProfessorIdProfessor(),
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
        String sql = "DELETE FROM Experiencia WHERE idExperiencia = ?";

        int linhas = jdbcTemplate.update(sql, id);

        if (linhas == 0) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(204).build();
        }
    }
}
