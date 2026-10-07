package com.lira.grupo.api.lira_api.service;

import com.lira.grupo.api.lira_api.entity.Aluno;
import com.lira.grupo.api.lira_api.entity.Endereco;
import com.lira.grupo.api.lira_api.requestDto.AlunoRequestDto;
import com.lira.grupo.api.lira_api.responseDto.AlunoResponseDto;
import com.lira.grupo.api.lira_api.exception.ConflictException;
import com.lira.grupo.api.lira_api.exception.ResourceNotFoundException;
import com.lira.grupo.api.lira_api.repository.AlunoRepository;
import com.lira.grupo.api.lira_api.repository.EnderecoRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final EnderecoRepository enderecoRepository;
    private final PasswordEncoder passwordEncoder;

    public AlunoService(
            AlunoRepository alunoRepository,
            EnderecoRepository enderecoRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.alunoRepository = alunoRepository;
        this.enderecoRepository = enderecoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public AlunoResponseDto cadastrar(AlunoRequestDto dto) {
        validarConflitosDeCadastro(dto);

        Aluno aluno = new Aluno();
        aplicarDadosCompletos(aluno, dto);

        return AlunoResponseDto.from(alunoRepository.save(aluno));
    }

    @Transactional(readOnly = true)
    public List<AlunoResponseDto> listarTodos() {
        return alunoRepository.findAll()
                .stream()
                .map(AlunoResponseDto::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public AlunoResponseDto buscarPorId(Integer id) {
        return AlunoResponseDto.from(buscarAluno(id));
    }

    @Transactional
    public AlunoResponseDto atualizar(Integer id, AlunoRequestDto dto) {
        Aluno aluno = buscarAluno(id);
        validarConflitosDeAtualizacao(id, dto);
        aplicarDadosCompletos(aluno, dto);

        return AlunoResponseDto.from(alunoRepository.save(aluno));
    }

    @Transactional
    public AlunoResponseDto atualizarParcial(Integer id, AlunoRequestDto dto) {
        Aluno aluno = buscarAluno(id);
        validarConflitosDeAtualizacao(id, dto);

        if (dto.getAlunoNome() != null) {
            aluno.setAlunoNome(dto.getAlunoNome());
        }
        if (dto.getAlunoEmail() != null) {
            aluno.setAlunoEmail(normalizarEmail(dto.getAlunoEmail()));
        }
        if (dto.getAlunoCpf() != null) {
            aluno.setAlunoCpf(normalizarCpf(dto.getAlunoCpf()));
        }
        if (dto.getDataDeNascimento() != null) {
            aluno.setDataDeNascimento(dto.getDataDeNascimento());
        }
        if (dto.getAlunoPossuiResponsavel() != null) {
            aluno.setAlunoPossuiResponsavel(dto.getAlunoPossuiResponsavel());
        }
        if (dto.getAlunoSenha() != null && !dto.getAlunoSenha().isBlank()) {
            aluno.setAlunoSenha(passwordEncoder.encode(dto.getAlunoSenha()));
        }
        if (dto.getFkEndereco() != null) {
            aluno.setEndereco(buscarEndereco(dto.getFkEndereco()));
        }

        return AlunoResponseDto.from(alunoRepository.save(aluno));
    }

    @Transactional
    public void deletar(Integer id) {
        alunoRepository.delete(buscarAluno(id));
    }

    private void aplicarDadosCompletos(Aluno aluno, AlunoRequestDto dto) {
        aluno.setAlunoNome(dto.getAlunoNome());
        aluno.setAlunoEmail(normalizarEmail(dto.getAlunoEmail()));
        aluno.setAlunoCpf(normalizarCpf(dto.getAlunoCpf()));
        aluno.setDataDeNascimento(dto.getDataDeNascimento());
        aluno.setAlunoPossuiResponsavel(dto.getAlunoPossuiResponsavel());
        aluno.setAlunoSenha(passwordEncoder.encode(dto.getAlunoSenha()));
        aluno.setEndereco(buscarEndereco(dto.getFkEndereco()));
    }

    private void validarConflitosDeCadastro(AlunoRequestDto dto) {
        if (alunoRepository.existsByAlunoEmail(normalizarEmail(dto.getAlunoEmail()))) {
            throw new ConflictException("Já existe um aluno cadastrado com este e-mail.");
        }
        if (alunoRepository.existsByAlunoCpf(normalizarCpf(dto.getAlunoCpf()))) {
            throw new ConflictException("Já existe um aluno cadastrado com este CPF.");
        }
    }

    private void validarConflitosDeAtualizacao(Integer id, AlunoRequestDto dto) {
        if (dto.getAlunoEmail() != null
                && alunoRepository.existsByAlunoEmailAndIdAlunoNot(normalizarEmail(dto.getAlunoEmail()), id)) {
            throw new ConflictException("Já existe outro aluno cadastrado com este e-mail.");
        }

        if (dto.getAlunoCpf() != null
                && alunoRepository.existsByAlunoCpfAndIdAlunoNot(normalizarCpf(dto.getAlunoCpf()), id)) {
            throw new ConflictException("Já existe outro aluno cadastrado com este CPF.");
        }
    }

    private String normalizarEmail(String email) {
        return email.trim().toLowerCase(Locale.ROOT);
    }

    private String normalizarCpf(String cpf) {
        return cpf.replaceAll("\\D", "");
    }

    private Aluno buscarAluno(Integer id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno", id));
    }

    private Endereco buscarEndereco(Integer id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço", id));
    }
}
