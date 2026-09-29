package com.lira.grupo.api.lira_api.config.security;

import com.lira.grupo.api.lira_api.repository.AlunoRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final AlunoRepository alunoRepository;

    public SecurityFilter(TokenService tokenService, AlunoRepository alunoRepository) {
        this.tokenService = tokenService;
        this.alunoRepository = alunoRepository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String token = recuperarToken(request);

        if (token != null) {
            String email = tokenService.validarToken(token);

            if (email != null) {
                alunoRepository.findByAlunoEmail(email).ifPresent(aluno -> {
                    var authentication = new UsernamePasswordAuthenticationToken(
                            aluno,
                            null,
                            aluno.getAuthorities()
                    );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                });
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }

        return authHeader.substring(7);
    }
}
