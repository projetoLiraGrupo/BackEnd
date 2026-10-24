package com.lira.grupo.api.lira_api.controller;

import com.lira.grupo.api.lira_api.requestDto.EnderecoRequestDto;
import com.lira.grupo.api.lira_api.responseDto.EnderecoResponseDto;
import com.lira.grupo.api.lira_api.service.EnderecoService;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.*;
import io.swagger.v3.oas.annotations.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Endereços", description = "Operações relacionadas a endereço")
@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @Operation(
            summary = "Cadastra um novo endereço",
            description = "Cadastra um novo endereço no sistema, apartir dos dados enviados"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Endereço cadastrado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EnderecoResponseDto.class)
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
                                              "message": "Requisição malFformada ou dados inválidos.",
                                              "path": "/enderecos",
                                              "timestamp": "2026-10-24T10:45:07.0040695"
                                            }
                                            """
                            )
                    )
            )
    })
    @PostMapping
    public ResponseEntity<EnderecoResponseDto> cadastrar(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto contendo os dados necessários para cadastrar o endereço.",
                    required = true
            )
            @Valid @RequestBody EnderecoRequestDto dto) {
        return ResponseEntity.status(201).body(enderecoService.cadastrar(dto));
    }

    @Operation(
            summary = "Busca todos os endereços",
            description = "Busca todos os endereços cadastrados no sistema"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Endereços encontrados",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = EnderecoResponseDto.class)
                            ),
                            examples = @ExampleObject(
                                    name = "Lista de endereços",
                                    summary = "Retorno com dois endereços",
                                    value = """
                    [
                      {
                        "idEndereco": 1,
                        "cep": "04105-061",
                        "logradouro": "Rua Topázio",
                        "numero": "258",
                        "complemento": "2° andar"
                      },
                      {
                        "idEndereco": 2,
                        "cep": "00000-000",
                        "logradouro": "Segundo endereço",
                        "numero": "001",
                        "complemento": ""
                      }
                    ]
                    """
                            )
                    )
            )
    })
    @GetMapping
    public ResponseEntity<List<EnderecoResponseDto>> listarTodos() {
        return ResponseEntity.ok(enderecoService.listarTodos());
    }


    @Operation(
            summary = "Busca o endereço específico",
            description = "Busca um endereço pelo identificador informado"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Endereço encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EnderecoResponseDto.class)
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
                                               "mensagem": "Não existe endereço com o identificador informado.",
                                               "caminho": "/enderecos/{id}"
                                             }
                                            """
                            )
                    )
            )
    }
    )
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponseDto> buscarPorId(
            @Parameter(description = "Identificador do endereço", example = "1")
            @PathVariable Integer id) {
        return ResponseEntity.ok(enderecoService.buscarPorId(id));
    }

    @Operation(
            summary = "Atualiza endereço",
            description = "Atualiza as informações do endereço especificado"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Endereço encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EnderecoResponseDto.class)
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
                                               "mensagem": "Não existe endereço com o identificador informado.",
                                               "caminho": "/enderecos/{id}"
                                             }
                                            """
                            )
                    )
            )
    }
    )
    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponseDto> atualizar(
            @Parameter(description = "Identificador do endereço", example = "1")
            @PathVariable Integer id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto contendo os dados necessários para atualização do endereço.",
                    required = true
            )
            @Valid @RequestBody EnderecoRequestDto dto
    ) {
        return ResponseEntity.ok(enderecoService.atualizar(id, dto));
    }

    @Operation(
            summary = "Deleta um endereço",
            description = "Deleta o endereço especificado pelo identificador"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "203",
                    description = "Endereço Deletado com sucesso",
                    content = @Content(
                            mediaType = "application/json"
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
                                               "mensagem": "Não existe endereço com o identificador informado.",
                                               "caminho": "/enderecos/{id}"
                                             }
                                            """
                            )
                    )
            )
    }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "Identificador do endereço", example = "1")
            @PathVariable Integer id) {
        enderecoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
