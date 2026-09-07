package com.lirapaulistana.api.controller;

import com.lirapaulistana.api.model.Responsavel;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@CrossOrigin
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
    public ResponseEntity<Responsavel> cadastrar(@RequestBody Responsavel body) {
        String sql = """
                INSERT INTO Responsavel
                    (nome, telefone, Endereco_idEndereco)
                VALUES (?, ?, ?)
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update( con -> {
                    PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, body.getNome());
                    ps.setString(2, body.getTelefone());
                    ps.setInt(3, body.getEnderecoIdEndereco());
                    return ps;
                }, keyHolder);
        Number key = keyHolder.getKey();
        body.setIdResponsavel(key.intValue());
        return ResponseEntity.status(201).body(body);
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
