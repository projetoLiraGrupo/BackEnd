package com.lira.grupo.api.lira_api.service;

import com.lira.grupo.api.lira_api.entity.Aluno;
import com.lira.grupo.api.lira_api.entity.AlunoAula;
import com.lira.grupo.api.lira_api.entity.Aula;
import com.lira.grupo.api.lira_api.entity.AulaGrupo;
import com.lira.grupo.api.lira_api.exception.BusinessRuleException;
import com.lira.grupo.api.lira_api.exception.ConflictException;
import com.lira.grupo.api.lira_api.exception.ResourceNotFoundException;
import com.lira.grupo.api.lira_api.repository.AlunoAulaRepository;
import com.lira.grupo.api.lira_api.repository.AlunoRepository;
import com.lira.grupo.api.lira_api.repository.AulaGrupoRepository;
import com.lira.grupo.api.lira_api.repository.AulaRepository;
import com.lira.grupo.api.lira_api.responseDto.AlunoAulaResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlunoAulaServiceTest {

    private static final Integer ID_ALUNO = 1;
    private static final Integer ID_AULA = 10;
    private static final Integer ID_MARCACAO = 100;
    private static final int QTD_ALUNO_MAX = 5;

    @Mock
    private AlunoAulaRepository alunoAulaRepository;

    @Mock
    private AulaRepository aulaRepository;

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private AulaGrupoRepository aulaGrupoRepository;

    @InjectMocks
    private AlunoAulaService alunoAulaService;

    private Aluno aluno;
    private Aula aulaFutura;
    private Aula aulaPassada;
    private AulaGrupo aulaGrupo;

    @BeforeEach
    void setUp() {
        aluno = new Aluno();
        aluno.setIdAluno(ID_ALUNO);
        aluno.setAlunoNome("Clark Kent");

        aulaFutura = new Aula(ID_AULA, LocalDateTime.now().plusDays(1), "Intermediario", null, null);
        aulaPassada = new Aula(ID_AULA, LocalDateTime.now().minusDays(1), "Intermediario", null, null);

        aulaGrupo = new AulaGrupo(1, aulaFutura, null, null, QTD_ALUNO_MAX);
    }

    

    @Test
    @DisplayName("Deve marcar aula com sucesso")
    void deveMarcarAulaComSucesso() {
        prepararMarcacaoValida(2L, Optional.of(aulaGrupo));
        when(alunoAulaRepository.save(any(AlunoAula.class))).thenAnswer(invocacao -> {
            AlunoAula marcacao = invocacao.getArgument(0);
            marcacao.setIdAlunoAula(ID_MARCACAO);
            return marcacao;
        });

        AlunoAulaResponseDto resposta = alunoAulaService.marcar(ID_ALUNO, ID_AULA);

        ArgumentCaptor<AlunoAula> captor = ArgumentCaptor.forClass(AlunoAula.class);
        verify(alunoAulaRepository, times(1)).save(captor.capture());
        assertSame(aluno, captor.getValue().getFkAluno());
        assertSame(aulaFutura, captor.getValue().getFkAula());

        assertEquals(ID_MARCACAO, resposta.getIdAlunoAula());
        assertEquals(ID_ALUNO, resposta.getIdAluno());
        assertEquals("Clark Kent", resposta.getAlunoNome());
        assertEquals(ID_AULA, resposta.getAula().getIdAula());
    }

    @Test
    @DisplayName("Não deve marcar quando a aula não existe")
    void naoDeveMarcarQuandoAulaNaoExiste() {
        when(aulaRepository.findById(ID_AULA)).thenReturn(Optional.empty());

        ResourceNotFoundException erro = assertThrows(
                ResourceNotFoundException.class,
                () -> alunoAulaService.marcar(ID_ALUNO, ID_AULA)
        );

        assertEquals("Aula não encontrado(a): 10", erro.getMessage());
        verify(alunoAulaRepository, never()).save(any(AlunoAula.class));
    }

    @Test
    @DisplayName("Não deve marcar quando o aluno não existe")
    void naoDeveMarcarQuandoAlunoNaoExiste() {
        when(aulaRepository.findById(ID_AULA)).thenReturn(Optional.of(aulaFutura));
        when(alunoRepository.findById(ID_ALUNO)).thenReturn(Optional.empty());

        ResourceNotFoundException erro = assertThrows(
                ResourceNotFoundException.class,
                () -> alunoAulaService.marcar(ID_ALUNO, ID_AULA)
        );

        assertEquals("Aluno não encontrado(a): 1", erro.getMessage());
        verify(alunoAulaRepository, never()).save(any(AlunoAula.class));
    }

    @Test
    @DisplayName("Não deve marcar aula que já passou")
    void naoDeveMarcarAulaQueJaPassou() {
        when(aulaRepository.findById(ID_AULA)).thenReturn(Optional.of(aulaPassada));
        when(alunoRepository.findById(ID_ALUNO)).thenReturn(Optional.of(aluno));

        BusinessRuleException erro = assertThrows(
                BusinessRuleException.class,
                () -> alunoAulaService.marcar(ID_ALUNO, ID_AULA)
        );

        assertEquals("Não é possível marcar uma aula que já passou.", erro.getMessage());
        verify(alunoAulaRepository, never()).save(any(AlunoAula.class));
    }

    @Test
    @DisplayName("Não deve marcar aula já marcada pelo aluno")
    void naoDeveMarcarAulaJaMarcadaPeloAluno() {
        when(aulaRepository.findById(ID_AULA)).thenReturn(Optional.of(aulaFutura));
        when(alunoRepository.findById(ID_ALUNO)).thenReturn(Optional.of(aluno));
        when(alunoAulaRepository.existsByFkAlunoIdAlunoAndFkAulaIdAula(ID_ALUNO, ID_AULA)).thenReturn(true);

        ConflictException erro = assertThrows(
                ConflictException.class,
                () -> alunoAulaService.marcar(ID_ALUNO, ID_AULA)
        );

        assertEquals("O aluno já está marcado nesta aula.", erro.getMessage());
        verify(alunoAulaRepository, never()).save(any(AlunoAula.class));
    }

    @Test
    @DisplayName("Não deve marcar aula no mesmo horário de outra aula do aluno")
    void naoDeveMarcarAulaNoMesmoHorarioDeOutraAula() {
        when(aulaRepository.findById(ID_AULA)).thenReturn(Optional.of(aulaFutura));
        when(alunoRepository.findById(ID_ALUNO)).thenReturn(Optional.of(aluno));
        when(alunoAulaRepository.existsByFkAlunoIdAlunoAndFkAulaIdAula(ID_ALUNO, ID_AULA)).thenReturn(false);
        when(alunoAulaRepository.existsByFkAlunoIdAlunoAndFkAulaDiaHora(ID_ALUNO, aulaFutura.getDiaHora()))
                .thenReturn(true);

        ConflictException erro = assertThrows(
                ConflictException.class,
                () -> alunoAulaService.marcar(ID_ALUNO, ID_AULA)
        );

        assertEquals("O aluno já tem outra aula marcada neste horário.", erro.getMessage());
        verify(alunoAulaRepository, never()).save(any(AlunoAula.class));
    }

    @Test
    @DisplayName("Não deve marcar aula lotada")
    void naoDeveMarcarAulaLotada() {
        prepararMarcacaoValida((long) QTD_ALUNO_MAX, Optional.of(aulaGrupo));

        ConflictException erro = assertThrows(
                ConflictException.class,
                () -> alunoAulaService.marcar(ID_ALUNO, ID_AULA)
        );

        assertEquals("A aula não tem mais vagas.", erro.getMessage());
        verify(alunoAulaRepository, never()).save(any(AlunoAula.class));
    }

    @Test
    @DisplayName("Deve marcar quando resta uma vaga")
    void deveMarcarQuandoRestaUmaVaga() {
        prepararMarcacaoValida((long) QTD_ALUNO_MAX - 1, Optional.of(aulaGrupo));
        when(alunoAulaRepository.save(any(AlunoAula.class))).thenAnswer(invocacao -> invocacao.getArgument(0));

        assertDoesNotThrow(() -> alunoAulaService.marcar(ID_ALUNO, ID_AULA));

        verify(alunoAulaRepository, times(1)).save(any(AlunoAula.class));
    }

    @Test
    @DisplayName("Não deve marcar aula individual já ocupada")
    void naoDeveMarcarAulaIndividualJaOcupada() {
        prepararMarcacaoValida(1L, Optional.empty());

        ConflictException erro = assertThrows(
                ConflictException.class,
                () -> alunoAulaService.marcar(ID_ALUNO, ID_AULA)
        );

        assertEquals("A aula não tem mais vagas.", erro.getMessage());
        verify(alunoAulaRepository, never()).save(any(AlunoAula.class));
    }

    

    @Test
    @DisplayName("Deve desmarcar aula com sucesso")
    void deveDesmarcarAulaComSucesso() {
        AlunoAula marcacao = new AlunoAula(aluno, aulaFutura);
        when(alunoAulaRepository.findByFkAlunoIdAlunoAndFkAulaIdAula(ID_ALUNO, ID_AULA))
                .thenReturn(Optional.of(marcacao));

        alunoAulaService.desmarcar(ID_ALUNO, ID_AULA);

        verify(alunoAulaRepository, times(1)).delete(marcacao);
    }

    @Test
    @DisplayName("Não deve desmarcar quando a marcação não existe")
    void naoDeveDesmarcarQuandoMarcacaoNaoExiste() {
        when(alunoAulaRepository.findByFkAlunoIdAlunoAndFkAulaIdAula(ID_ALUNO, ID_AULA))
                .thenReturn(Optional.empty());

        ResourceNotFoundException erro = assertThrows(
                ResourceNotFoundException.class,
                () -> alunoAulaService.desmarcar(ID_ALUNO, ID_AULA)
        );

        assertEquals("Marcação não encontrado(a): aluno 1, aula 10", erro.getMessage());
        verify(alunoAulaRepository, never()).delete(any(AlunoAula.class));
    }

    @Test
    @DisplayName("Não deve desmarcar aula que já passou")
    void naoDeveDesmarcarAulaQueJaPassou() {
        AlunoAula marcacao = new AlunoAula(aluno, aulaPassada);
        when(alunoAulaRepository.findByFkAlunoIdAlunoAndFkAulaIdAula(ID_ALUNO, ID_AULA))
                .thenReturn(Optional.of(marcacao));

        BusinessRuleException erro = assertThrows(
                BusinessRuleException.class,
                () -> alunoAulaService.desmarcar(ID_ALUNO, ID_AULA)
        );

        assertEquals("Não é possível desmarcar uma aula que já passou.", erro.getMessage());
        verify(alunoAulaRepository, never()).delete(any(AlunoAula.class));
    }

    

    @Test
    @DisplayName("Deve listar as aulas do aluno")
    void deveListarAulasDoAluno() {
        Aula outraAula = new Aula(11, LocalDateTime.now().plusDays(2), "Iniciante", null, null);
        when(alunoAulaRepository.findByFkAlunoIdAluno(ID_ALUNO)).thenReturn(List.of(
                new AlunoAula(aluno, aulaFutura),
                new AlunoAula(aluno, outraAula)
        ));

        List<AlunoAulaResponseDto> resposta = alunoAulaService.listarAulasDoAluno(ID_ALUNO);

        assertEquals(2, resposta.size());
        assertEquals(ID_AULA, resposta.get(0).getAula().getIdAula());
        assertEquals(11, resposta.get(1).getAula().getIdAula());
        assertTrue(resposta.stream().allMatch(m -> ID_ALUNO.equals(m.getIdAluno())));
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando o aluno não tem aulas")
    void deveRetornarListaVaziaQuandoAlunoNaoTemAulas() {
        when(alunoAulaRepository.findByFkAlunoIdAluno(ID_ALUNO)).thenReturn(List.of());

        List<AlunoAulaResponseDto> resposta = alunoAulaService.listarAulasDoAluno(ID_ALUNO);

        assertNotNull(resposta);
        assertTrue(resposta.isEmpty());
    }

    @Test
    @DisplayName("Não deve listar alunos de aula inexistente")
    void naoDeveListarAlunosDeAulaInexistente() {
        when(aulaRepository.existsById(99)).thenReturn(false);

        ResourceNotFoundException erro = assertThrows(
                ResourceNotFoundException.class,
                () -> alunoAulaService.listarAlunosDaAula(99)
        );

        assertEquals("Aula não encontrado(a): 99", erro.getMessage());
        verify(alunoAulaRepository, never()).findByFkAulaIdAula(anyInt());
    }

    

    /** Stubs comuns a uma marcação que passa por todas as validações até a checagem de vagas. */
    private void prepararMarcacaoValida(long marcados, Optional<AulaGrupo> grupo) {
        when(aulaRepository.findById(ID_AULA)).thenReturn(Optional.of(aulaFutura));
        when(alunoRepository.findById(ID_ALUNO)).thenReturn(Optional.of(aluno));
        when(alunoAulaRepository.existsByFkAlunoIdAlunoAndFkAulaIdAula(ID_ALUNO, ID_AULA)).thenReturn(false);
        when(alunoAulaRepository.existsByFkAlunoIdAlunoAndFkAulaDiaHora(ID_ALUNO, aulaFutura.getDiaHora()))
                .thenReturn(false);
        when(alunoAulaRepository.countByFkAulaIdAula(ID_AULA)).thenReturn(marcados);
        when(aulaGrupoRepository.findByFkAulaIdAula(ID_AULA)).thenReturn(grupo);
    }
}
