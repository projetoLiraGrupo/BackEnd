package com.lira.grupo.api.lira_api.service;

import com.lira.grupo.api.lira_api.entity.Aula;
import com.lira.grupo.api.lira_api.entity.Endereco;
import com.lira.grupo.api.lira_api.entity.Professor;
import com.lira.grupo.api.lira_api.exception.BusinessRuleException;
import com.lira.grupo.api.lira_api.exception.ConflictException;
import com.lira.grupo.api.lira_api.exception.ResourceNotFoundException;
import com.lira.grupo.api.lira_api.repository.AulaRepository;
import com.lira.grupo.api.lira_api.repository.EnderecoRepository;
import com.lira.grupo.api.lira_api.repository.ProfessorRepository;
import com.lira.grupo.api.lira_api.requestDto.AulaRequestDto;
import com.lira.grupo.api.lira_api.responseDto.AulaResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AulaServiceTest {

    private static final Integer ID_AULA = 10;
    private static final Integer ID_PROFESSOR = 1;
    private static final Integer ID_ENDERECO = 2;

    @Mock
    private AulaRepository aulaRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private ProfessorRepository professorRepository;

    @InjectMocks
    private AulaService aulaService;

    private LocalDateTime diaHora;
    private Professor professorDoBanco;
    private Endereco enderecoDoBanco;
    private AulaRequestDto request;

    @BeforeEach
    void setUp() {
        diaHora = LocalDateTime.now().plusDays(1);


        professorDoBanco = new Professor();
        professorDoBanco.setIdProfessor(ID_PROFESSOR);

        enderecoDoBanco = new Endereco();
        enderecoDoBanco.setIdEndereco(ID_ENDERECO);


        request = new AulaRequestDto(diaHora, "Intermediario", ID_ENDERECO, ID_PROFESSOR);
    }



    @Test
    @DisplayName("Deve criar aula com sucesso")
    void deveCriarAulaComSucesso() {
        when(professorRepository.findById(ID_PROFESSOR)).thenReturn(Optional.of(professorDoBanco));
        when(enderecoRepository.findById(ID_ENDERECO)).thenReturn(Optional.of(enderecoDoBanco));
        when(aulaRepository.existsByFkProfessorIdProfessorAndDiaHora(ID_PROFESSOR, diaHora)).thenReturn(false);
        when(aulaRepository.save(any(Aula.class))).thenAnswer(invocacao -> {
            Aula aula = invocacao.getArgument(0);
            aula.setIdAula(ID_AULA);
            return aula;
        });

        AulaResponseDto resposta = aulaService.criar(request);

        ArgumentCaptor<Aula> captor = ArgumentCaptor.forClass(Aula.class);
        verify(aulaRepository, times(1)).save(captor.capture());

        Aula salva = captor.getValue();
        assertEquals(diaHora, salva.getDiaHora());
        assertEquals("Intermediario", salva.getNivel());
        assertSame(professorDoBanco, salva.getFkProfessor());
        assertSame(enderecoDoBanco, salva.getFkEndereco());

        assertNotNull(resposta);
        assertEquals(ID_AULA, resposta.getIdAula());
        assertEquals(diaHora, resposta.getDiaHora());
        assertEquals("Intermediario", resposta.getNivel());
    }

    @Test
    @DisplayName("Não deve criar aula com professor inexistente")
    void naoDeveCriarAulaComProfessorInexistente() {
        when(professorRepository.findById(ID_PROFESSOR)).thenReturn(Optional.empty());

        ResourceNotFoundException erro = assertThrows(
                ResourceNotFoundException.class,
                () -> aulaService.criar(request)
        );

        assertEquals("Professor não encontrado(a): 1", erro.getMessage());
        verify(aulaRepository, never()).save(any(Aula.class));
    }

    @Test
    @DisplayName("Não deve criar aula com endereço inexistente")
    void naoDeveCriarAulaComEnderecoInexistente() {
        when(professorRepository.findById(ID_PROFESSOR)).thenReturn(Optional.of(professorDoBanco));
        when(enderecoRepository.findById(ID_ENDERECO)).thenReturn(Optional.empty());

        ResourceNotFoundException erro = assertThrows(
                ResourceNotFoundException.class,
                () -> aulaService.criar(request)
        );

        assertEquals("Endereço não encontrado(a): 2", erro.getMessage());
        verify(aulaRepository, never()).save(any(Aula.class));
    }

    @Test
    @DisplayName("Não deve criar aula no passado")
    void naoDeveCriarAulaNoPassado() {
        AulaRequestDto requestNoPassado = new AulaRequestDto(
                LocalDateTime.now().minusDays(1), "Intermediario",
                ID_ENDERECO, ID_PROFESSOR);

        BusinessRuleException erro = assertThrows(
                BusinessRuleException.class,
                () -> aulaService.criar(requestNoPassado)
        );

        assertEquals("A aula precisa ser marcada para uma data futura.", erro.getMessage());
        verify(aulaRepository, never()).save(any(Aula.class));
        verifyNoInteractions(professorRepository, enderecoRepository);
    }

    @Test
    @DisplayName("Não deve criar aula quando o professor já tem aula no mesmo horário")
    void naoDeveCriarAulaComProfessorOcupadoNoHorario() {
        when(professorRepository.findById(ID_PROFESSOR)).thenReturn(Optional.of(professorDoBanco));
        when(enderecoRepository.findById(ID_ENDERECO)).thenReturn(Optional.of(enderecoDoBanco));
        when(aulaRepository.existsByFkProfessorIdProfessorAndDiaHora(ID_PROFESSOR, diaHora)).thenReturn(true);

        ConflictException erro = assertThrows(
                ConflictException.class,
                () -> aulaService.criar(request)
        );

        assertEquals("O professor já tem uma aula neste horário.", erro.getMessage());
        verify(aulaRepository, never()).save(any(Aula.class));
    }



    @Test
    @DisplayName("Deve buscar aula por id")
    void deveBuscarAulaPorId() {
        Aula aula = new Aula(ID_AULA, diaHora, "Intermediario", enderecoDoBanco, professorDoBanco);
        when(aulaRepository.findById(ID_AULA)).thenReturn(Optional.of(aula));

        Aula encontrada = aulaService.listarPorId(ID_AULA);

        assertSame(aula, encontrada);
    }

    @Test
    @DisplayName("Não deve buscar aula inexistente")
    void naoDeveBuscarAulaInexistente() {
        when(aulaRepository.findById(99)).thenReturn(Optional.empty());

        ResourceNotFoundException erro = assertThrows(
                ResourceNotFoundException.class,
                () -> aulaService.listarPorId(99)
        );

        assertEquals("Aula não encontrado(a): 99", erro.getMessage());
    }



    @Test
    @DisplayName("Deve atualizar aula com sucesso")
    void deveAtualizarAulaComSucesso() {
        when(aulaRepository.existsById(ID_AULA)).thenReturn(true);
        when(professorRepository.findById(ID_PROFESSOR)).thenReturn(Optional.of(professorDoBanco));
        when(enderecoRepository.findById(ID_ENDERECO)).thenReturn(Optional.of(enderecoDoBanco));
        when(aulaRepository.existsByFkProfessorIdProfessorAndDiaHoraAndIdAulaNot(ID_PROFESSOR, diaHora, ID_AULA))
                .thenReturn(false);
        when(aulaRepository.save(any(Aula.class))).thenAnswer(invocacao -> invocacao.getArgument(0));

        AulaResponseDto resposta = aulaService.atualizar(ID_AULA, request);

        ArgumentCaptor<Aula> captor = ArgumentCaptor.forClass(Aula.class);
        verify(aulaRepository, times(1)).save(captor.capture());

        Aula salva = captor.getValue();
        assertEquals(ID_AULA, salva.getIdAula());
        assertEquals(diaHora, salva.getDiaHora());
        assertSame(professorDoBanco, salva.getFkProfessor());
        assertSame(enderecoDoBanco, salva.getFkEndereco());

        assertEquals(ID_AULA, resposta.getIdAula());
    }

    @Test
    @DisplayName("Não deve atualizar aula inexistente")
    void naoDeveAtualizarAulaInexistente() {
        when(aulaRepository.existsById(99)).thenReturn(false);

        ResourceNotFoundException erro = assertThrows(
                ResourceNotFoundException.class,
                () -> aulaService.atualizar(99, request)
        );

        assertEquals("Aula não encontrado(a): 99", erro.getMessage());
        verify(aulaRepository, never()).save(any(Aula.class));
        verifyNoInteractions(professorRepository, enderecoRepository);
    }
}
