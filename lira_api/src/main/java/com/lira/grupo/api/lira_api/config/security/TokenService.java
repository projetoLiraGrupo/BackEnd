package com.lira.grupo.api.lira_api.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TokenService {

    private static final String ISSUER = "lira-api";

    private final String secret;
    private final long expirationSeconds;

    public TokenService(
            @Value("${api.security.token.secret}") String secret,
            @Value("${api.security.token.expiration-seconds:3600}") long expirationSeconds
    ) {
        this.secret = secret;
        this.expirationSeconds = expirationSeconds;
    }

    public String gerarToken(Authentication authentication) {
        try {
            Instant agora = Instant.now();

            List<String> authorities = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();

            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(authentication.getName())
                    .withClaim("authorities", authorities)
                    .withIssuedAt(agora)
                    .withExpiresAt(agora.plus(expirationSeconds, ChronoUnit.SECONDS))
                    .sign(algorithm());

        } catch (JWTCreationException exception) {
            throw new IllegalStateException("Não foi possível gerar o token de autenticação.", exception);
        }
    }

    public String validarToken(String token) {
        if (token == null || token.isBlank()) {
            return null;
        }

        try {
            return JWT.require(algorithm())
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException | IllegalArgumentException exception) {
            return null;
        }
    }

    private Algorithm algorithm() {
        return Algorithm.HMAC256(secret);
    }
}
