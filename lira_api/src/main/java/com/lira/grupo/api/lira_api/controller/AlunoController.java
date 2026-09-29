package com.lira.grupo.api.lira_api.controller;

import com.lira.grupo.api.lira_api.entity.dto.AlunoRequestDto;
import com.lira.grupo.api.lira_api.entity.dto.response.AlunoResponseDto;
import com.lira.grupo.api.lira_api.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<AlunoResponseDto> cadastrar(
            @Valid @RequestBody AlunoRequestDto alunoRequestDto
    ) {
        return ResponseEntity.status(201).body(alunoService.cadastrar(alunoRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponseDto>> listarTodos() {
        return ResponseEntity.ok(alunoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(alunoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponseDto> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody AlunoRequestDto alunoRequestDto
    ) {
        return ResponseEntity.ok(alunoService.atualizar(id, alunoRequestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AlunoResponseDto> atualizarParcial(
            @PathVariable Integer id,
            @RequestBody AlunoRequestDto alunoRequestDto
    ) {
        return ResponseEntity.ok(alunoService.atualizarParcial(id, alunoRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        alunoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
