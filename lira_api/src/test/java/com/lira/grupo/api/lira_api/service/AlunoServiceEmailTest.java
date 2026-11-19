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
class AlunoServiceEmailTest {

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
    void naoDeveCadastrarEmailDuplicado() {
        String email = "Davi.Paulista@Lira.com";
        String emailNormalizado = "davi.paulista@lira.com";

        AlunoRequestDto dto = mock(AlunoRequestDto.class);

        when(dto.getAlunoEmail()).thenReturn(email);

        when(alunoRepository.existsByAlunoEmail(emailNormalizado))
                .thenReturn(true);

        ConflictException erro = assertThrows(
                ConflictException.class,
                () -> alunoService.cadastrar(dto)
        );

        assertEquals(
                "Já existe um aluno cadastrado com este e-mail.",
                erro.getMessage()
        );

        verify(alunoRepository, never())
                .save(any(Aluno.class));
    }

    @Test
    void naoDeveAtualizarParaEmailDeOutroAluno() {
        Integer id = 1;

        String email = "Davi.Paulista@Lira.com";
        String emailNormalizado = "davi.paulista@lira.com";

        AlunoRequestDto dto = mock(AlunoRequestDto.class);

        when(dto.getAlunoEmail()).thenReturn(email);
        when(alunoRepository.findById(id))
                .thenReturn(Optional.of(new Aluno()));

        when(alunoRepository.existsByAlunoEmailAndIdAlunoNot(
                emailNormalizado, id
        )).thenReturn(true);

        ConflictException erro = assertThrows(
                ConflictException.class,
                () -> alunoService.atualizar(id, dto)
        );

        assertEquals(
                "Já existe outro aluno cadastrado com este e-mail.",
                erro.getMessage()
        );

        verify(alunoRepository, never())
                .save(any(Aluno.class));
    }
}