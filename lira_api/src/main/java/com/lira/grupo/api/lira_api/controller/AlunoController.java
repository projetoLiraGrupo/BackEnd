package com.lira.grupo.api.lira_api.controller;

import com.lira.grupo.api.lira_api.requestDto.AlunoRequestDto;
import com.lira.grupo.api.lira_api.responseDto.AlunoResponseDto;
import com.lira.grupo.api.lira_api.responseDto.EnderecoResponseDto;
import com.lira.grupo.api.lira_api.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Alunos", description = "Operações relacionadas ao Aluno")
@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @Operation(
            summary = "Cadastra um novo aluno",
            description = "Cadastra um novo aluno no sistema, apartir dos dados enviados"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Endereço cadastrado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlunoResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro ao cadastrar o endereço",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value =
                                            """
                                                    {
                                                      "status": 400,
                                                      "error": "Bad Request",
                                                      "message": "Requisição malformada ou dados inválidos.",
                                                      "path": "/alunos",
                                                      "timestamp": "2026-10-24T10:45:07.0040695"
                                                    }
                                                    """
                            )
                    )
            )
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<AlunoResponseDto> cadastrar(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto contendo os dados necessários para cadastrar o aluno.",
                    required = true
            )
            @Valid @RequestBody AlunoRequestDto alunoRequestDto
    ) {
        return ResponseEntity.status(201).body(alunoService.cadastrar(alunoRequestDto));
    }

    @Operation(
            summary = "Busca os alunos cadastrados",
            description = "Busca os alunos cadastrados no sistema"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Alunos encontrados",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """ 
                                               [
                                               {
                                                 "idAluno": 1,
                                                 "alunoNome": "Clark Kent",
                                                 "alunoEmail": "superKentClark@Lira.com",
                                                 "alunoCpf": "33333333333",
                                                 "alunoPossuiResponsavel": false,
                                                 "dataDeNascimento": "1999-12-02",
                                                 "fkEndereco": 1,
                                                 "token": "7f3a9c2e8b1d4f6a0c5e9d2b8a7f1c4e6d0b3a9f5e2c8d1a"
                                               },
                                               {
                                                  "idAluno": 2,
                                                  "alunoNome": "Bruce Wayne",
                                                  "alunoEmail": "batwayne@Lira.com",
                                                  "alunoCpf": "11111111111",
                                                  "alunoPossuiResponsavel": false,
                                                  "dataDeNascimento": "1985-02-19",
                                                  "fkEndereco": 1,
                                                  "token": "8a2f6c1d9e4b7a3f0c5d8e2b1a6f9c4d"
                                                }
                                               ]
                                            """
                            )
                    )
            )
    }
    )
    @GetMapping
    public ResponseEntity<List<AlunoResponseDto>> listarTodos() {
        return ResponseEntity.ok(alunoService.listarTodos());
    }

    @Operation(
            summary = "Busca o aluno específico",
            description = "Busca um aluno pelo identificador informado"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Endereço encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlunoResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Endereço não encontrado encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    """
                                            {
                                               "timestamp": "2026-10-24T11:00:00-03:00",
                                               "status": 404,
                                               "erro": "Not Found",
                                               "mensagem": "Não existe aluno com o identificador informado.",
                                               "caminho": "/alunos/{id}"
                                             }
                                            """
                            )
                    )
            )
    }
    )
    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDto> buscarPorId(
            @Parameter(description = "Identificador do aluno", example = "1")
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(alunoService.buscarPorId(id));
    }

    @Operation(
            summary = "Atualiza aluno",
            description = "Atualiza as informações do aluno especificado"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Aluno encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlunoResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Aluno não encontrado encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    """
                                            {
                                               "timestamp": "2026-10-24T11:00:00-03:00",
                                               "status": 404,
                                               "erro": "Not Found",
                                               "mensagem": "Não existe aluno com o identificador informado.",
                                               "caminho": "/alunos/{id}"
                                             }
                                            """
                            )
                    )
            )
    }
    )
    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponseDto> atualizar(
            @Parameter(description = "Identificador do aluno", example = "1")
            @PathVariable Integer id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto contendo os dados necessários para atualização do aluno.",
                    required = true
            )
            @Valid @RequestBody AlunoRequestDto alunoRequestDto
    ) {
        return ResponseEntity.ok(alunoService.atualizar(id, alunoRequestDto));
    }

    @Operation(
            summary = "Atualiza parcial aluno",
            description = "Atualiza parcialmente as informações do aluno especificado"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Aluno encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlunoResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Aluno não encontrado encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    """
                                            {
                                               "timestamp": "2026-10-24T11:00:00-03:00",
                                               "status": 404,
                                               "erro": "Not Found",
                                               "mensagem": "Não existe aluno com o identificador informado.",
                                               "caminho": "/alunos/{id}"
                                             }
                                            """
                            )
                    )
            )
    }
    )
    @PatchMapping("/{id}")
    public ResponseEntity<AlunoResponseDto> atualizarParcial(
            @Parameter(description = "Identificador do aluno", example = "1")
            @PathVariable Integer id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto contendo os dados necessários para atualização do aluno.",
                    required = true
            )
            @RequestBody AlunoRequestDto alunoRequestDto
    ) {
        return ResponseEntity.ok(alunoService.atualizarParcial(id, alunoRequestDto));
    }

    @Operation(
            summary = "Deleta um aluno",
            description = "Deleta o aluno especificado pelo identificador"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "203",
                    description = "Aluno Deletado com sucesso",
                    content = @Content(
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Aluno não encontrado encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    """
                                            {
                                               "timestamp": "2026-10-24T11:00:00-03:00",
                                               "status": 404,
                                               "erro": "Not Found",
                                               "mensagem": "Não existe aluno com o identificador informado.",
                                               "caminho": "/alunos/{id}"
                                             }
                                            """
                            )
                    )
            )
    }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "Identificador do aluno", example = "1")
            @PathVariable Integer id
    ) {
        alunoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
