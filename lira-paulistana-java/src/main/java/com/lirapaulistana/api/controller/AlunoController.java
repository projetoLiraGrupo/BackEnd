package com.lirapaulistana.api.controller;

import com.lirapaulistana.api.model.Aluno;
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
@RequestMapping("/api/alunos")
public class AlunoController {

    private final JdbcTemplate jdbcTemplate;

    public AlunoController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public List<Aluno> listar() {
        String sql = "SELECT idAluno, nome, dataNascimento, email, senha, Endereco_idEndereco AS enderecoIdEndereco FROM Aluno ORDER BY idAluno";
        return jdbcTemplate.query(sql, new DataClassRowMapper<>(Aluno.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarPorId(@PathVariable Integer id) {
        String sql = "SELECT idAluno, nome, dataNascimento, email, senha, Endereco_idEndereco AS enderecoIdEndereco FROM Aluno WHERE idAluno = ?";

        List<Aluno> resultado =
                jdbcTemplate.query(sql, new DataClassRowMapper<>(Aluno.class), id);

        if (resultado.isEmpty()) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(200).body(resultado.getFirst());
        }
    }

    @PostMapping
    public ResponseEntity<Aluno> cadastrar(@RequestBody Aluno body) {
        String sqlValidacao = """
                SELECT * FROM aluno WHERE email = ?
                """;
        List<Aluno> alunoVerificacao = jdbcTemplate.query(sqlValidacao, new BeanPropertyRowMapper<>(Aluno.class),
                body.getEmail());
        if(!alunoVerificacao.isEmpty()){
            return ResponseEntity.status(409).build();
        }

        String sql = """
                INSERT INTO Aluno
                    (nome, dataNascimento, email, senha, Endereco_idEndereco)
                VALUES (?, ?, ?, ?, ?)
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, body.getNome());
            ps.setObject(2, body.getDataNascimento());
            ps.setString(3, body.getEmail());
            ps.setString(4, body.getSenha());
            ps.setInt(5, body.getEnderecoIdEndereco());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        body.setIdAluno(key.intValue());
        return ResponseEntity.status(201).body(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable Integer id,
            @RequestBody Aluno body
    ) {
        String sql = """
                UPDATE Aluno
                SET nome = ?,
                    dataNascimento = ?,
                    email = ?,
                    senha = ?,
                    Endereco_idEndereco = ?
                WHERE idAluno = ?
                """;

        int linhas = jdbcTemplate.update(
                sql,
                body.getNome(),
                body.getDataNascimento(),
                body.getEmail(),
                body.getSenha(),
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
        String sql = "DELETE FROM Aluno WHERE idAluno = ?";

        int linhas = jdbcTemplate.update(sql, id);

        if (linhas == 0) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(204).build();
        }
    }
}
