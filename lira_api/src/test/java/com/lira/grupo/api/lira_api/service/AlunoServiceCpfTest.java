
package com.lira.grupo.api.lira_api.service;

import com.lira.grupo.api.lira_api.entity.Aluno;
import com.lira.grupo.api.lira_api.requestDto.AlunoRequestDto;
import com.lira.grupo.api.lira_api.exception.ConflictException;
import com.lira.grupo.api.lira_api.mapper.*;
import com.lira.grupo.api.lira_api.repository.AlunoRepository;
import com.lira.grupo.api.lira_api.repository.EnderecoRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlunoServiceCpfTest {

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AlunoMapper alunoMapper;

    @InjectMocks
    private AlunoService alunoService;

    @Test
    void naoDeveCadastrarCpfDuplicado() {
        Integer id = 1;
        String cpf = "52998224725";
        String email = "Davi.Paulista@Lira.com";

        AlunoRequestDto dto = mock(AlunoRequestDto.class);

        when(dto.getAlunoCpf()).thenReturn(cpf);
        when(dto.getAlunoEmail()).thenReturn(email);

        when(alunoRepository.existsByAlunoCpf(cpf))
                .thenReturn(true);

        ConflictException erro = assertThrows(
                ConflictException.class,
                () -> alunoService.cadastrar(dto)
        );

        assertEquals(
                "Já existe um aluno cadastrado com este CPF.",
                erro.getMessage()
        );

        verify(alunoRepository, never())
                .save(any(Aluno.class));
    }

    @Test
    void naoDeveAtualizarParaCpfDeOutroAluno() {
        Integer id = 1;

        String cpf = "52998224725";
        String email = "Davi.Paulista@Lira.com";

        AlunoRequestDto dto = mock(AlunoRequestDto.class);

        when(dto.getAlunoCpf()).thenReturn(cpf);
        when(dto.getAlunoEmail()).thenReturn(email);

        when(alunoRepository.findById(id))
                .thenReturn(Optional.of(new Aluno()));

        when(alunoRepository.existsByAlunoCpfAndIdAlunoNot(
                cpf, id
        )).thenReturn(true);

        assertThrows(
                ConflictException.class,
                () -> alunoService.atualizar(id, dto)
        );

        verify(alunoRepository, never())
                .save(any(Aluno.class));
    }
}
