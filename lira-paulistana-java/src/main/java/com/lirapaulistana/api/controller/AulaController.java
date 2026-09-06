package com.lirapaulistana.api.controller;

import com.lirapaulistana.api.model.Aula;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aulas")
public class AulaController {

    private final JdbcTemplate jdbcTemplate;

    public AulaController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public List<Aula> listar() {
        String sql = "SELECT idAula, diaHora, nivel, Endereco_idEndereco AS enderecoIdEndereco, Professor_idProfessor AS professorIdProfessor, Aluno_idAluno AS alunoIdAluno, qtdAlunos FROM Aula ORDER BY idAula";

        return jdbcTemplate.query(sql, new DataClassRowMapper<>(Aula.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aula> buscarPorId(@PathVariable Integer id) {
        String sql = "SELECT idAula, diaHora, nivel, Endereco_idEndereco AS enderecoIdEndereco, Professor_idProfessor AS professorIdProfessor, Aluno_idAluno AS alunoIdAluno, qtdAlunos FROM Aula WHERE idAula = ?";

        List<Aula> resultado =
                jdbcTemplate.query(sql, new DataClassRowMapper<>(Aula.class), id);

        if (resultado.isEmpty()) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(200).body(resultado.getFirst());
        }
    }

    @GetMapping("/professor/{professorId}")
    public List<Aula> listarPorProfessor(@PathVariable Integer professorId) {
        String sql = """
                SELECT idAula, diaHora, nivel, Endereco_idEndereco AS enderecoIdEndereco, Professor_idProfessor AS professorIdProfessor, Aluno_idAluno AS alunoIdAluno, qtdAlunos
                FROM Aula
                WHERE Professor_idProfessor = ?
                ORDER BY diaHora
                """;

        return jdbcTemplate.query(sql, new DataClassRowMapper<>(Aula.class), professorId);
    }

    @GetMapping("/aluno/{alunoId}")
    public List<Aula> listarPorAluno(@PathVariable Integer alunoId) {
        String sql = """
                SELECT idAula, diaHora, nivel, Endereco_idEndereco AS enderecoIdEndereco, Professor_idProfessor AS professorIdProfessor, Aluno_idAluno AS alunoIdAluno, qtdAlunos
                FROM Aula
                WHERE Aluno_idAluno = ?
                ORDER BY diaHora
                """;

        return jdbcTemplate.query(sql, new DataClassRowMapper<>(Aula.class), alunoId);
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody Aula body) {
        String sql = """
                INSERT INTO Aula
                    (diaHora, nivel, Endereco_idEndereco, Professor_idProfessor, Aluno_idAluno, qtdAlunos)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        jdbcTemplate.update(
                sql,
                body.getDiaHora(),
                body.getNivel(),
                body.getEnderecoIdEndereco(),
                body.getProfessorIdProfessor(),
                body.getAlunoIdAluno(),
                body.getQtdAlunos()
        );

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable Integer id,
            @RequestBody Aula body
    ) {
        String sql = """
                UPDATE Aula
                SET diaHora = ?,
                    nivel = ?,
                    Endereco_idEndereco = ?,
                    Professor_idProfessor = ?,
                    Aluno_idAluno = ?,
                    qtdAlunos = ?
                WHERE idAula = ?
                """;

        int linhas = jdbcTemplate.update(
                sql,
                body.getDiaHora(),
                body.getNivel(),
                body.getEnderecoIdEndereco(),
                body.getProfessorIdProfessor(),
                body.getAlunoIdAluno(),
                body.getQtdAlunos(),
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
        String sql = "DELETE FROM Aula WHERE idAula = ?";

        int linhas = jdbcTemplate.update(sql, id);

        if (linhas == 0) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(204).build();
        }
    }
}
