package com.lirapaulistana.api.controller;

import com.lirapaulistana.api.model.Responsavel;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/responsaveis")
public class ResponsavelController {

    private final JdbcTemplate jdbcTemplate;

    public ResponsavelController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public List<Responsavel> listar() {
        String sql = "SELECT idResponsavel, nome, telefone, Endereco_idEndereco AS enderecoIdEndereco FROM Responsavel ORDER BY idResponsavel";

        return jdbcTemplate.query(sql, new DataClassRowMapper<>(Responsavel.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Responsavel> buscarPorId(@PathVariable Integer id) {
        String sql = "SELECT idResponsavel, nome, telefone, Endereco_idEndereco AS enderecoIdEndereco FROM Responsavel WHERE idResponsavel = ?";

        List<Responsavel> resultado =
                jdbcTemplate.query(sql, new DataClassRowMapper<>(Responsavel.class), id);

        if (resultado.isEmpty()) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(200).body(resultado.getFirst());
        }
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody Responsavel body) {
        String sql = """
                INSERT INTO Responsavel
                    (nome, telefone, Endereco_idEndereco)
                VALUES (?, ?, ?)
                """;

        jdbcTemplate.update(
                sql,
                body.getNome(),
                body.getTelefone(),
                body.getEnderecoIdEndereco()
        );

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable Integer id,
            @RequestBody Responsavel body
    ) {
        String sql = """
                UPDATE Responsavel
                SET nome = ?,
                    telefone = ?,
                    Endereco_idEndereco = ?
                WHERE idResponsavel = ?
                """;

        int linhas = jdbcTemplate.update(
                sql,
                body.getNome(),
                body.getTelefone(),
                body.getEnderecoIdEndereco(),
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
        String sql = "DELETE FROM Responsavel WHERE idResponsavel = ?";

        int linhas = jdbcTemplate.update(sql, id);

        if (linhas == 0) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(204).build();
        }
    }
}
