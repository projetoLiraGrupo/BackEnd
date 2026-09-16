package com.lira.grupo.api.lira_api.service;

import com.lira.grupo.api.lira_api.config.security.TokenService;
import com.lira.grupo.api.lira_api.model.dto.AutenticacaoDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public LoginService(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public String autenticar(AutenticacaoDto dados) {
        
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.getEmail(), dados.getSenha());

        
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        
        return tokenService.gerarToken(dados.getEmail());
    }
}