package com.lira.grupo.api.lira_api.service;

import com.lira.grupo.api.lira_api.config.security.TokenService;
import com.lira.grupo.api.lira_api.entity.Aluno;
import com.lira.grupo.api.lira_api.requestDto.LoginRequestDto;
import com.lira.grupo.api.lira_api.responseDto.AlunoResponseDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public LoginService(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public AlunoResponseDto autenticar(LoginRequestDto request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail().trim().toLowerCase(Locale.ROOT),
                        request.getSenha()
                )
        );

        Aluno aluno = (Aluno) authentication.getPrincipal();
        String token = tokenService.gerarToken(authentication);

        return AlunoResponseDto.from(aluno, token);
    }
}
