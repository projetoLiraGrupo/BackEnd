package com.lira.grupo.api.lira_api.controller;

import com.lira.grupo.api.lira_api.model.dto.AutenticacaoDto;
import com.lira.grupo.api.lira_api.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    private final LoginService loginService;

    public AutenticacaoController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AutenticacaoDto dados) {
        try {
            String tokenJwt = loginService.autenticar(dados);
            return ResponseEntity.ok(Map.of("token", tokenJwt));

        } catch (BadCredentialsException | UsernameNotFoundException e) {
            
            return ResponseEntity.status(401).body(Map.of("erro", "E-mail ou senha inválidos."));
        } catch (Exception e) {
            
            e.printStackTrace();

            
            return ResponseEntity.status(500).body(Map.of("erro", "Erro interno: " + e.getMessage()));
        }
    }
}
