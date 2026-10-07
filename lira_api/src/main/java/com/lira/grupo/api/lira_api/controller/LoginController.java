package com.lira.grupo.api.lira_api.controller;

import com.lira.grupo.api.lira_api.config.security.SecurityFilter;
import com.lira.grupo.api.lira_api.entity.dto.LoginRequestDto;
import com.lira.grupo.api.lira_api.entity.dto.response.AlunoResponseDto;
import com.lira.grupo.api.lira_api.service.LoginService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final LoginService loginService;
    private final boolean secureCookie;
    private final long expirationSeconds;

    public LoginController(
            LoginService loginService,
            @Value("${api.security.cookie.secure:false}") boolean secureCookie,
            @Value("${api.security.token.expiration-seconds:3600}") long expirationSeconds
    ) {
        this.loginService = loginService;
        this.secureCookie = secureCookie;
        this.expirationSeconds = expirationSeconds;
    }

    @PostMapping("/login")
    public ResponseEntity<AlunoResponseDto> login(
            @Valid @RequestBody LoginRequestDto request,
            HttpServletResponse response
    ) {
        AlunoResponseDto autenticado = loginService.autenticar(request);

        ResponseCookie cookie = ResponseCookie.from(SecurityFilter.COOKIE_NAME, autenticado.getToken())
                .httpOnly(true)
                .secure(secureCookie)
                .sameSite("Strict")
                .path("/")
                .maxAge(Duration.ofSeconds(expirationSeconds))
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return ResponseEntity.ok(autenticado);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from(SecurityFilter.COOKIE_NAME, "")
                .httpOnly(true)
                .secure(secureCookie)
                .sameSite("Strict")
                .path("/")
                .maxAge(Duration.ZERO)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return ResponseEntity.noContent().build();
    }
}
