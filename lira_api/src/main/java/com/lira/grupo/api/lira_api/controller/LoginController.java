package com.lira.grupo.api.lira_api.controller;

import com.lira.grupo.api.lira_api.entity.dto.response.*;
import com.lira.grupo.api.lira_api.entity.dto.*;

//import com.lira.grupo.api.lira_api.entity.dto.response.TokenResponseDto;
//import com.lira.grupo.api.lira_api.entity.dto.LoginRequestDto;
import com.lira.grupo.api.lira_api.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(
            @RequestBody @Valid LoginRequestDto dados
    ) {
        return ResponseEntity.ok(new com.lira.grupo.api.lira_api.entity.dto.response.TokenResponseDto(loginService.autenticar(dados)));
        //NONGIEM AVAI LER ISSO MAS PRECISO MUITO RECLAMAR
        //ṔASSEI UM TEMPO ENORME PARA CONSERTAR UM ERRO DE REFERENCIA OCM TOKENRESPONSE
        // NADA NA INTERNET OU NADA QUE FAZIA O MENOR SENTIDO
        // QUEBREI A CABEÇA
        // MAS SÓ REINICIAR A DROGA DO NOTE CONCERTOU ISSO
        // EU NÃO ENTENDO
        // EU NAO QUERO ENTENDER
        // EU NÃO VOU ENTENDER

    }
}
