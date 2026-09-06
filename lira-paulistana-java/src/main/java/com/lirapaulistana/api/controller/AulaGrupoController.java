package com.lirapaulistana.api.controller;

import com.lirapaulistana.api.model.AulaGrupo;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aulas-grupo")
public class AulaGrupoController {

    private final JdbcTemplate jdbcTemplate;

    public AulaGrupoController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public List<AulaGrupo> listar() {
        String sql = "SELECT idAulaGrupo, Aula_idAula AS aulaIdAula, Admin_idAdmin AS adminIdAdmin, inicioEm, terminoEm, qtdAlunosMax FROM AulaGrupo ORDER BY idAulaGrupo";

        return jdbcTemplate.query(sql, new DataClassRowMapper<>(AulaGrupo.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AulaGrupo> buscarPorId(@PathVariable Integer id) {
        String sql = "SELECT idAulaGrupo, Aula_idAula AS aulaIdAula, Admin_idAdmin AS adminIdAdmin, inicioEm, terminoEm, qtdAlunosMax FROM AulaGrupo WHERE idAulaGrupo = ?";

        List<AulaGrupo> resultado =
                jdbcTemplate.query(sql, new DataClassRowMapper<>(AulaGrupo.class), id);

        if (resultado.isEmpty()) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(200).body(resultado.getFirst());
        }
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody AulaGrupo body) {
        String sql = """
                INSERT INTO AulaGrupo
                    (Aula_idAula, Admin_idAdmin, inicioEm, terminoEm, qtdAlunosMax)
                VALUES (?, ?, ?, ?, ?)
                """;

        jdbcTemplate.update(
                sql,
                body.getAulaIdAula(),
                body.getAdminIdAdmin(),
                body.getInicioEm(),
                body.getTerminoEm(),
                body.getQtdAlunosMax()
        );

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable Integer id,
            @RequestBody AulaGrupo body
    ) {
        String sql = """
                UPDATE AulaGrupo
                SET Aula_idAula = ?,
                    Admin_idAdmin = ?,
                    inicioEm = ?,
                    terminoEm = ?,
                    qtdAlunosMax = ?
                WHERE idAulaGrupo = ?
                """;

        int linhas = jdbcTemplate.update(
                sql,
                body.getAulaIdAula(),
                body.getAdminIdAdmin(),
                body.getInicioEm(),
                body.getTerminoEm(),
                body.getQtdAlunosMax(),
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
        String sql = "DELETE FROM AulaGrupo WHERE idAulaGrupo = ?";

        int linhas = jdbcTemplate.update(sql, id);

        if (linhas == 0) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(204).build();
        }
    }
}
