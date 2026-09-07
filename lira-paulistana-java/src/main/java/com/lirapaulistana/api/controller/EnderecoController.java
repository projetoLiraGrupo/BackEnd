package com.lirapaulistana.api.controller;

import com.lirapaulistana.api.model.Endereco;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
@RequestMapping("/api/enderecos")
public class EnderecoController {

    private final JdbcTemplate jdbcTemplate;

    public EnderecoController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public List<Endereco> listar() {
        String sql = "SELECT * FROM Endereco ORDER BY idEndereco";

        return jdbcTemplate.query(sql, new DataClassRowMapper<>(Endereco.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarPorId(@PathVariable Integer id) {
        String sql = "SELECT * FROM Endereco WHERE idEndereco = ?";

        List<Endereco> resultado =
                jdbcTemplate.query(sql, new DataClassRowMapper<>(Endereco.class), id);

        if (resultado.isEmpty()) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(200).body(resultado.getFirst());
        }
    }

    @PostMapping
    public ResponseEntity<Endereco> cadastrar(@RequestBody Endereco body) {
        String sqlVerificacao = """
                SELECT * FROM endereco WHERE LOWER(rua) = LOWER(?) AND LOWER(numero) = LOWER(?) AND LOWER(cep) = LOWER(?);
                """;
        List<Endereco> endereco = jdbcTemplate.query(sqlVerificacao, new BeanPropertyRowMapper<>(Endereco.class),
                body.getRua(), body.getNumero(), body.getCep());
        if(!endereco.isEmpty()){
            return ResponseEntity.status(201).body(endereco.getFirst());
        }
        String sql = """
                INSERT INTO Endereco
                    (rua, numero, cep)
                VALUES (?, ?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, body.getRua());
            ps.setString(2, body.getNumero());
            ps.setString(3, body.getCep());
            return ps;
        }, keyHolder);
        Number key = keyHolder.getKey();
        body.setIdEndereco(key.intValue());
        return ResponseEntity.status(201).body(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable Integer id,
            @RequestBody Endereco body
    ) {
        String sql = """
                UPDATE Endereco
                SET rua = ?,
                    numero = ?,
                    cep = ?
                WHERE idEndereco = ?
                """;

        int linhas = jdbcTemplate.update(
                sql,
                body.getRua(),
                body.getNumero(),
                body.getCep(),
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
        String sql = "DELETE FROM Endereco WHERE idEndereco = ?";

        int linhas = jdbcTemplate.update(sql, id);

        if (linhas == 0) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(204).build();
        }
    }
}
