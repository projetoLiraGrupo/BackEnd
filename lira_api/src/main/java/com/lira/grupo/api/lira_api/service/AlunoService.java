package com.lira.grupo.api.lira_api.service;

import com.lira.grupo.api.lira_api.entity.Aluno;
import com.lira.grupo.api.lira_api.entity.Endereco;
import com.lira.grupo.api.lira_api.entity.dto.AlunoRequestDto;
import com.lira.grupo.api.lira_api.entity.dto.response.AlunoResponseDto;
import com.lira.grupo.api.lira_api.exception.ConflictException;
import com.lira.grupo.api.lira_api.exception.ResourceNotFoundException;
import com.lira.grupo.api.lira_api.mapper.AlunoMapper;
import com.lira.grupo.api.lira_api.repository.AlunoRepository;
import com.lira.grupo.api.lira_api.repository.EnderecoRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final EnderecoRepository enderecoRepository;
    private final PasswordEncoder passwordEncoder;
    private final AlunoMapper alunoMapper;

    public AlunoService(
            AlunoRepository alunoRepository,
            EnderecoRepository enderecoRepository,
            PasswordEncoder passwordEncoder,
            AlunoMapper alunoMapper
    ) {
        this.alunoRepository = alunoRepository;
        this.enderecoRepository = enderecoRepository;
        this.passwordEncoder = passwordEncoder;
        this.alunoMapper = alunoMapper;
    }

    
    public AlunoResponseDto cadastrar(AlunoRequestDto dto) {
        validarConflitosDeCadastro(dto);
        Endereco endereco = buscarEndereco(dto.getFkEndereco());

        Aluno aluno = alunoMapper.toEntity(dto);
        aluno.setAlunoSenha(passwordEncoder.encode(dto.getAlunoSenha()));
        aluno.setEndereco(endereco);

        return alunoMapper.toResponseDto(alunoRepository.save(aluno));
    }

    
    public List<AlunoResponseDto> listarTodos() {
        return alunoRepository.findAll()
                .stream()
                .map(alunoMapper::toResponseDto)
                .toList();
    }

    
    public AlunoResponseDto buscarPorId(Integer id) {
        return alunoMapper.toResponseDto(buscarAluno(id));
    }

    
    public AlunoResponseDto atualizar(Integer id, AlunoRequestDto dto) {
        Aluno aluno = buscarAluno(id);
        validarConflitosDeAtualizacao(id, dto);
        Endereco endereco = buscarEndereco(dto.getFkEndereco());

        alunoMapper.updateEntity(dto, aluno);
        aluno.setAlunoSenha(passwordEncoder.encode(dto.getAlunoSenha()));
        aluno.setEndereco(endereco);

        return alunoMapper.toResponseDto(alunoRepository.save(aluno));
    }

    
    public AlunoResponseDto atualizarParcial(Integer id, AlunoRequestDto dto) {
        Aluno aluno = buscarAluno(id);
        validarConflitosDeAtualizacao(id, dto);

        alunoMapper.updatePartialEntity(dto, aluno);

        if (dto.getAlunoSenha() != null) {
            aluno.setAlunoSenha(passwordEncoder.encode(dto.getAlunoSenha()));
        }

        if (dto.getFkEndereco() != null) {
            aluno.setEndereco(buscarEndereco(dto.getFkEndereco()));
        }

        return alunoMapper.toResponseDto(alunoRepository.save(aluno));
    }

    
    public void deletar(Integer id) {
        Aluno aluno = buscarAluno(id);
        alunoRepository.delete(aluno);
    }

    private void validarConflitosDeCadastro(AlunoRequestDto dto) {
        if (alunoRepository.existsByAlunoEmail(dto.getAlunoEmail())) {
            throw new ConflictException("Já existe um aluno cadastrado com este e-mail.");
        }

        if (alunoRepository.existsByAlunoCpf(dto.getAlunoCpf())) {
            throw new ConflictException("Já existe um aluno cadastrado com este CPF.");
        }
    }

    private void validarConflitosDeAtualizacao(Integer id, AlunoRequestDto dto) {
        if (dto.getAlunoEmail() != null
                && alunoRepository.existsByAlunoEmailAndIdAlunoNot(dto.getAlunoEmail(), id)) {
            throw new ConflictException("Já existe outro aluno cadastrado com este e-mail.");
        }

        if (dto.getAlunoCpf() != null
                && alunoRepository.existsByAlunoCpfAndIdAlunoNot(dto.getAlunoCpf(), id)) {
            throw new ConflictException("Já existe outro aluno cadastrado com este CPF.");
        }
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
