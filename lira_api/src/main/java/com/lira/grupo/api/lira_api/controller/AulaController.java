package com.lira.grupo.api.lira_api.controller;

import com.lira.grupo.api.lira_api.entity.Aula;
import com.lira.grupo.api.lira_api.mapper.AulaMapper;
import com.lira.grupo.api.lira_api.requestDto.AulaRequestDto;
import com.lira.grupo.api.lira_api.responseDto.AlunoResponseDto;
import com.lira.grupo.api.lira_api.responseDto.AulaResponseDto;
import com.lira.grupo.api.lira_api.service.AulaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aulas")
public class AulaController {

    private final AulaService aulaService;

    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }

    @GetMapping
    public ResponseEntity<List<AulaResponseDto>> listar(){
        List<Aula> aulaResponseDtos = aulaService.listar();
        return ResponseEntity.ok(AulaMapper.toResponse(aulaResponseDtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AulaResponseDto> listarPorId(@PathVariable Integer id){
        Aula aula = aulaService.listarPorId(id);
        return ResponseEntity.ok(AulaMapper.toResponse(aula));
    }

    @PostMapping
    public ResponseEntity<AulaResponseDto> criar(@Valid @RequestBody AulaRequestDto aulaRequestDto){
        AulaResponseDto aulaResponseDto = aulaService.criar(aulaRequestDto);
        return ResponseEntity.status(201).body(aulaResponseDto);
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<AulaResponseDto> atualizar(@PathVariable Integer id, @RequestBody AulaRequestDto aulaRequestDto){
//
//    }
}
