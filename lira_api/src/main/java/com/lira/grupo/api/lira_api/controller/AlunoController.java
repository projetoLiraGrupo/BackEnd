package com.lira.grupo.api.lira_api.controller;

import com.lira.grupo.api.lira_api.dto.AlunoDto;
import com.lira.grupo.api.lira_api.model.Aluno;
import com.lira.grupo.api.lira_api.model.Endereco;
import com.lira.grupo.api.lira_api.repository.AlunoRepository;
import com.lira.grupo.api.lira_api.repository.EnderecoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoRepository alunoRepository;
    private final EnderecoRepository enderecoRepository;

    public AlunoController(
            AlunoRepository alunoRepository,
            EnderecoRepository enderecoRepository
    ) {
        this.alunoRepository = alunoRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @PostMapping
    public ResponseEntity<Aluno> cadastrar(
            @RequestBody AlunoDto alunoDto
    ) {

        Endereco endereco = enderecoRepository
                .findById(alunoDto.getFkEndereco())
                .orElse(null);

        if (endereco == null) {
            return ResponseEntity
                    .status(400)
                    .build();
        }

        Aluno aluno = new Aluno();

        aluno.setAlunoNome(alunoDto.getAlunoNome());
        aluno.setAlunoEmail(alunoDto.getAlunoEmail());
        aluno.setAlunoSenha(alunoDto.getAlunoSenha());
        aluno.setAlunoCpf(alunoDto.getAlunoCpf());
        aluno.setDataDeNascimento(alunoDto.getDataDeNascimento());
        aluno.setAlunoPossuiResponsavel(
                alunoDto.getAlunoPossuiResponsavel()
        );

        aluno.setEndereco(endereco);

        Aluno alunoSalvo = alunoRepository.save(aluno);

        return ResponseEntity
                .status(201)
                .body(alunoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listarTodos() {

        List<Aluno> alunos = alunoRepository.findAll();

        return ResponseEntity
                .status(200)
                .body(alunos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarPorId(
            @PathVariable Integer id
    ) {

        Optional<Aluno> aluno = alunoRepository.findById(id);

        return aluno.map(value -> ResponseEntity
                .status(200)
                .body(value)).orElseGet(() -> ResponseEntity
                .status(404)
                .build());

    }


    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(
            @PathVariable Integer id,
            @RequestBody AlunoDto alunoDto
    ) {

        Optional<Aluno> alunoEncontrado =
                alunoRepository.findById(id);

        if (alunoEncontrado.isEmpty()) {
            return ResponseEntity
                    .status(404)
                    .build();
        }

        Endereco endereco = enderecoRepository
                .findById(alunoDto.getFkEndereco())
                .orElse(null);

        if (endereco == null) {
            return ResponseEntity
                    .status(400)
                    .build();
        }

        Aluno aluno = alunoEncontrado.get();

        aluno.setAlunoNome(alunoDto.getAlunoNome());
        aluno.setAlunoEmail(alunoDto.getAlunoEmail());
        aluno.setAlunoSenha(alunoDto.getAlunoSenha());
        aluno.setAlunoCpf(alunoDto.getAlunoCpf());
        aluno.setDataDeNascimento(
                alunoDto.getDataDeNascimento()
        );
        aluno.setAlunoPossuiResponsavel(
                alunoDto.getAlunoPossuiResponsavel()
        );

        aluno.setEndereco(endereco);

        Aluno alunoAtualizado =
                alunoRepository.save(aluno);

        return ResponseEntity
                .status(200)
                .body(alunoAtualizado);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Aluno> atualizarParcial(
            @PathVariable Integer id,
            @RequestBody AlunoDto alunoDto
    ) {

        Optional<Aluno> alunoEncontrado =
                alunoRepository.findById(id);

        if (alunoEncontrado.isEmpty()) {
            return ResponseEntity
                    .status(404)
                    .build();
        }

        Aluno aluno = alunoEncontrado.get();


        if (alunoDto.getAlunoNome() != null) {

            aluno.setAlunoNome(
                    alunoDto.getAlunoNome()
            );
        }




        if (alunoDto.getAlunoEmail() != null) {

            aluno.setAlunoEmail(
                    alunoDto.getAlunoEmail()
            );
        }




        if (alunoDto.getAlunoSenha() != null) {

            aluno.setAlunoSenha(
                    alunoDto.getAlunoSenha()
            );
        }




        if (alunoDto.getAlunoCpf() != null) {

            aluno.setAlunoCpf(
                    alunoDto.getAlunoCpf()
            );
        }




        if (alunoDto.getDataDeNascimento() != null) {

            aluno.setDataDeNascimento(
                    alunoDto.getDataDeNascimento()
            );
        }




        if (alunoDto.getAlunoPossuiResponsavel() != null) {

            aluno.setAlunoPossuiResponsavel(
                    alunoDto.getAlunoPossuiResponsavel()
            );
        }




        if (alunoDto.getFkEndereco() != null) {

            Endereco endereco = enderecoRepository
                    .findById(alunoDto.getFkEndereco())
                    .orElse(null);

            if (endereco == null) {

                return ResponseEntity
                        .status(400)
                        .build();
            }

            aluno.setEndereco(endereco);
        }


        Aluno alunoAtualizado =
                alunoRepository.save(aluno);

        return ResponseEntity
                .status(200)
                .body(alunoAtualizado);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Aluno> deletar(
            @PathVariable Integer id
    ) {

        Optional<Aluno> aluno =
                alunoRepository.findById(id);

        if (aluno.isEmpty()) {

            return ResponseEntity
                    .status(404)
                    .build();
        }

        try {

            alunoRepository.deleteById(id);

            return ResponseEntity
                    .status(204)
                    .build();

        } catch (DataIntegrityViolationException erro) {

            return ResponseEntity
                    .status(409)
                    .build();
        }
    }
}