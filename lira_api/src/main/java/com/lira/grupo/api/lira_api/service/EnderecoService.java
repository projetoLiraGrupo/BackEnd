package com.lira.grupo.api.lira_api.service;

import com.lira.grupo.api.lira_api.entity.Endereco;
import com.lira.grupo.api.lira_api.requestDto.EnderecoRequestDto;
import com.lira.grupo.api.lira_api.responseDto.EnderecoResponseDto;
import com.lira.grupo.api.lira_api.exception.ResourceNotFoundException;
import com.lira.grupo.api.lira_api.repository.EnderecoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional
    public EnderecoResponseDto cadastrar(EnderecoRequestDto dto) {
        Endereco endereco = new Endereco();
        aplicarDados(endereco, dto);
        return EnderecoResponseDto.from(enderecoRepository.save(endereco));
    }

    @Transactional(readOnly = true)
    public List<EnderecoResponseDto> listarTodos() {
        return enderecoRepository.findAll().stream()
                .map(EnderecoResponseDto::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public EnderecoResponseDto buscarPorId(Integer id) {
        return EnderecoResponseDto.from(buscarEndereco(id));
    }

    @Transactional
    public EnderecoResponseDto atualizar(Integer id, EnderecoRequestDto dto) {
        Endereco endereco = buscarEndereco(id);
        aplicarDados(endereco, dto);
        return EnderecoResponseDto.from(enderecoRepository.save(endereco));
    }

    @Transactional
    public void deletar(Integer id) {
        enderecoRepository.delete(buscarEndereco(id));
    }

    private Endereco buscarEndereco(Integer id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço", id));
    }

    private void aplicarDados(Endereco endereco, EnderecoRequestDto dto) {
        endereco.setCep(dto.getCep().replace("-", ""));
        endereco.setLogradouro(dto.getLogradouro());
        endereco.setNumero(dto.getNumero());
        endereco.setComplemento(dto.getComplemento());
    }
}
