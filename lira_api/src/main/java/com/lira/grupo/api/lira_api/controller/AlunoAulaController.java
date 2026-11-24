package com.lira.grupo.api.lira_api.controller;

import com.lira.grupo.api.lira_api.entity.Aluno;
import com.lira.grupo.api.lira_api.responseDto.AlunoAulaResponseDto;
import com.lira.grupo.api.lira_api.service.AlunoAulaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Marcação de aulas", description = "Operações para o aluno marcar e desmarcar aulas")
@RestController
public class AlunoAulaController {

    private final AlunoAulaService alunoAulaService;

    public AlunoAulaController(AlunoAulaService alunoAulaService) {
        this.alunoAulaService = alunoAulaService;
    }

    @Operation(
            summary = "Marca uma aula",
            description = "Marca a aula informada para o aluno autenticado (identificado pelo token)"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Aula marcada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlunoAulaResponseDto.class)
                    )
            ),
            @ApiResponse(responseCode = "401", description = "Aluno não autenticado"),
            @ApiResponse(responseCode = "404", description = "Aula ou aluno não encontrado"),
            @ApiResponse(responseCode = "409", description = "Aula já marcada, conflito de horário ou aula sem vagas"),
            @ApiResponse(responseCode = "422", description = "A aula já passou")
    })
    @PostMapping("/aulas/{idAula}/marcacoes")
    public ResponseEntity<AlunoAulaResponseDto> marcar(
            @Parameter(hidden = true) @AuthenticationPrincipal Aluno aluno,
            @Parameter(description = "Identificador da aula", example = "1")
            @PathVariable Integer idAula
    ) {
        AlunoAulaResponseDto marcacao = alunoAulaService.marcar(aluno.getIdAluno(), idAula);
        return ResponseEntity.status(HttpStatus.CREATED).body(marcacao);
    }

    @Operation(
            summary = "Desmarca uma aula",
            description = "Remove a marcação do aluno autenticado na aula informada"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Aula desmarcada com sucesso"),
            @ApiResponse(responseCode = "401", description = "Aluno não autenticado"),
            @ApiResponse(responseCode = "404", description = "Marcação não encontrada"),
            @ApiResponse(responseCode = "422", description = "A aula já passou")
    })
    @DeleteMapping("/aulas/{idAula}/marcacoes")
    public ResponseEntity<Void> desmarcar(
            @Parameter(hidden = true) @AuthenticationPrincipal Aluno aluno,
            @Parameter(description = "Identificador da aula", example = "1")
            @PathVariable Integer idAula
    ) {
        alunoAulaService.desmarcar(aluno.getIdAluno(), idAula);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Lista as aulas do aluno logado",
            description = "Lista todas as aulas marcadas pelo aluno autenticado"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Marcações encontradas (pode ser uma lista vazia)",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AlunoAulaResponseDto.class))
                    )
            ),
            @ApiResponse(responseCode = "401", description = "Aluno não autenticado")
    })
    @GetMapping("/alunos/me/aulas")
    public ResponseEntity<List<AlunoAulaResponseDto>> listarMinhasAulas(
            @Parameter(hidden = true) @AuthenticationPrincipal Aluno aluno
    ) {
        return ResponseEntity.ok(alunoAulaService.listarAulasDoAluno(aluno.getIdAluno()));
    }

    @Operation(
            summary = "Lista os alunos de uma aula",
            description = "Lista todos os alunos marcados na aula informada"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Marcações encontradas (pode ser uma lista vazia)",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AlunoAulaResponseDto.class))
                    )
            ),
            @ApiResponse(responseCode = "401", description = "Aluno não autenticado"),
            @ApiResponse(responseCode = "404", description = "Aula não encontrada")
    })
    @GetMapping("/aulas/{idAula}/alunos")
    public ResponseEntity<List<AlunoAulaResponseDto>> listarAlunosDaAula(
            @Parameter(description = "Identificador da aula", example = "1")
            @PathVariable Integer idAula
    ) {
        return ResponseEntity.ok(alunoAulaService.listarAlunosDaAula(idAula));
    }
}
