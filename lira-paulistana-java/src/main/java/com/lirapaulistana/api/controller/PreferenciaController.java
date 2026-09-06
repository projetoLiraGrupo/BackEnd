package com.lirapaulistana.api.controller;

import com.lirapaulistana.api.model.Preferencia;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preferencias")
public class PreferenciaController {

    private final JdbcTemplate jdbcTemplate;

    public PreferenciaController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public List<Preferencia> listar() {
        String sql = "SELECT * FROM Preferencia ORDER BY idPreferencia";

        return jdbcTemplate.query(sql, new DataClassRowMapper<>(Preferencia.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Preferencia> buscarPorId(@PathVariable Integer id) {
        String sql = "SELECT * FROM Preferencia WHERE idPreferencia = ?";

        List<Preferencia> resultado =
                jdbcTemplate.query(sql, new DataClassRowMapper<>(Preferencia.class), id);

        if (resultado.isEmpty()) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(200).body(resultado.getFirst());
        }
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody Preferencia body) {
        String sql = """
                INSERT INTO Preferencia
                    (categoria, valor, aplicavelPara, tipo)
                VALUES (?, ?, ?, ?)
                """;

        jdbcTemplate.update(
                sql,
                body.getCategoria(),
                body.getValor(),
                body.getAplicavelPara(),
                body.getTipo()
        );

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable Integer id,
            @RequestBody Preferencia body
    ) {
        String sql = """
                UPDATE Preferencia
                SET categoria = ?,
                    valor = ?,
                    aplicavelPara = ?,
                    tipo = ?
                WHERE idPreferencia = ?
                """;

        int linhas = jdbcTemplate.update(
                sql,
                body.getCategoria(),
                body.getValor(),
                body.getAplicavelPara(),
                body.getTipo(),
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
        String sql = "DELETE FROM Preferencia WHERE idPreferencia = ?";

        int linhas = jdbcTemplate.update(sql, id);

        if (linhas == 0) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(204).build();
        }
    }
}
