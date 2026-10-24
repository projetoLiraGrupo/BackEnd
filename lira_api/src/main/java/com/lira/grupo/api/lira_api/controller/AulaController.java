package com.lira.grupo.api.lira_api.controller;

import com.lira.grupo.api.lira_api.entity.Aula;
import com.lira.grupo.api.lira_api.mapper.AulaMapper;
import com.lira.grupo.api.lira_api.requestDto.AulaRequestDto;
import com.lira.grupo.api.lira_api.responseDto.AlunoResponseDto;
import com.lira.grupo.api.lira_api.responseDto.AulaResponseDto;
import com.lira.grupo.api.lira_api.responseDto.EnderecoResponseDto;
import com.lira.grupo.api.lira_api.service.AulaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Aulas", description = "Operações relacionadas as aulas")
@RestController
@RequestMapping("/aulas")
public class AulaController {

    private final AulaService aulaService;

    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }

    @Operation(
            summary = "Busca todas as aulas",
            description = "Busca todas as aulas cadastrados no sistema"
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
                                    name = "Lista de aulas",
                                    summary = "Retorno com dois valores",
                                    value = """
                                                                 [
                                                                    {
                                                                       "idAula": 1,
                                                                       "diaHora": "2026/10/28T15:32:58.758Z",
                                                                       "nivel": "Intermediario",
                                                                       "endereco": {
                                                                         "idEndereco": 1,
                                                                         "cep": "04105-061",
                                                                         "logradouro": "Rua Topázio",
                                                                         "numero": "258",
                                                                         "complemento": "2° andar"
                                                                       },
                                                                       "fkProfessor": {
                                                                         "idProfessor": 1,
                                                                         "nome": "David Luiz",
                                                                         "email": "davidluiz04@lira.prof.com",
                                                                         "token": "1",
                                                                         "telefone": "11911111111",
                                                                         "criadoEm": "2026-10-24",
                                                                         "fkEndereco": 2
                                                                       }
                                                                     },
                                                                     {
                                                                       "idAula": 2,
                                                                       "diaHora": "2026/10/28T18:30:00.758Z",
                                                                       "nivel": "Avançado",
                                                                       "endereco": {
                                                                         "idEndereco": 1,
                                                                         "cep": "04105-061",
                                                                         "logradouro": "Rua Topázio",
                                                                         "numero": "258",
                                                                         "complemento": "2° andar"
                                                                       },
                                                                       "fkProfessor": {
                                                                         "idProfessor": 2,
                                                                         "nome": "Thiago Silva",
                                                                         "email": "thiagosilva02@lira.prof.com",
                                                                         "token": "1",
                                                                         "telefone": "11911111111",
                                                                         "criadoEm": "2026-10-24",
                                                                         "fkEndereco": 2
                                                                       }
                                                                     }
                                                                 ]
                                            """
                            )
                    )
            )
    })
    @GetMapping
    public ResponseEntity<List<AulaResponseDto>> listar() {
        List<Aula> aulaResponseDtos = aulaService.listar();
        return ResponseEntity.ok(AulaMapper.toResponse(aulaResponseDtos));
    }

    @Operation(
            summary = "Busca uma aulas específica",
            description = "Busca uma aula pelo identificador informado"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "aula encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EnderecoResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Aula não encontrado encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    """
                                            {
                                               "timestamp": "2026-10-24T11:00:00-03:00",
                                               "status": 404,
                                               "erro": "Not Found",
                                               "mensagem": "Não existe aula com o identificador informado.",
                                               "caminho": "/aulas/{id}"
                                             }
                                            """
                            )
                    )
            )
    }
    )
    @GetMapping("/{id}")
    public ResponseEntity<AulaResponseDto> listarPorId(
            @Parameter(description = "Identificador da aula", example = "99")
            @PathVariable Integer id
    ) {
        Aula aula = aulaService.listarPorId(id);
        return ResponseEntity.ok(AulaMapper.toResponse(aula));
    }

    @Operation(
            summary = "Cadastra uma nova aula",
            description = "Cadastra uma nova aula no sistema, apartir dos dados enviados"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Aula cadastrada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EnderecoResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro ao cadastrar a aula",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value =
                                            """
                                                    {
                                                      "status": 400,
                                                      "error": "Bad Request",
                                                      "message": "Requisição malformada ou dados inválidos.",
                                                      "path": "/aulas",
                                                      "timestamp": "2026-10-24T10:45:07.0040695"
                                                    }
                                                    """
                            )
                    )
            )
    })
    @PostMapping
    public ResponseEntity<AulaResponseDto> criar(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto contendo os dados necessários para cadastrar a aula.",
                    required = true
            )
            @Valid @RequestBody AulaRequestDto aulaRequestDto) {
        AulaResponseDto aulaResponseDto = aulaService.criar(aulaRequestDto);
        return ResponseEntity.status(201).body(aulaResponseDto);
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<AulaResponseDto> atualizar(@PathVariable Integer id, @RequestBody AulaRequestDto aulaRequestDto){
//
//    }
}
