package com.lira.grupo.api.lira_api.service;

import com.lira.grupo.api.lira_api.entity.Aula;
import com.lira.grupo.api.lira_api.entity.Endereco;
import com.lira.grupo.api.lira_api.entity.Professor;
import com.lira.grupo.api.lira_api.repository.AulaRepository;
import com.lira.grupo.api.lira_api.repository.EnderecoRepository;
import com.lira.grupo.api.lira_api.repository.ProfessorRepository;
import com.lira.grupo.api.lira_api.requestDto.AulaRequestDto;
import com.lira.grupo.api.lira_api.responseDto.AulaResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AulaServiceTest {

    @Mock
    private AulaRepository aulaRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private ProfessorRepository professorRepository;

    @InjectMocks
    private AulaService aulaService;

    @Test
    @DisplayName("Deve Criar Aula com Sucesso")
    void deveCriarAulaComSucesso() {

        LocalDateTime diaHora = LocalDateTime.now().plusDays(1);

        Professor professor = new Professor();
        professor.setIdProfessor(1);

        Endereco endereco = new Endereco();
        endereco.setIdEndereco(2);

        AulaRequestDto request = new AulaRequestDto(diaHora, "Intermediario", endereco, professor);

        when(aulaRepository.save(any(Aula.class))).thenAnswer(invocacao -> {
            Aula aula = invocacao.getArgument(0);
            aula.setIdAula(10);
            return aula;
        });


        AulaResponseDto resposta = aulaService.criar(request);

        ArgumentCaptor<Aula> aulaArgumentCaptor = ArgumentCaptor.forClass(Aula.class);
        verify(aulaRepository, times(1)).save(aulaArgumentCaptor.capture());

        Aula salva = aulaArgumentCaptor.getValue();

        assertEquals(diaHora, salva.getDiaHora());
        assertEquals("Intermediario", salva.getNivel());
        assertSame(professor, salva.getFkProfessor());
        assertSame(endereco, salva.getFkEndereco());

        assertNotNull(resposta);
        assertEquals(10, resposta.getIdAula());
        assertEquals(diaHora, resposta.getDiaHora());
        assertEquals("Intermediario", resposta.getNivel());
    }
}