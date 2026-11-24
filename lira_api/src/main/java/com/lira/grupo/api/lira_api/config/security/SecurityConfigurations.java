package com.lira.grupo.api.lira_api.config.security;

import com.lira.grupo.api.lira_api.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    private final SecurityFilter securityFilter;

    public SecurityConfigurations(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/v3/api-docs.yaml",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/h2-console/**",
                                "/h2-console/"
                        ).permitAll()
                        
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/v3/api-docs.yaml",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/h2-console/**"
                        ).permitAll()

                        
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/logout").permitAll()

                        
                        .requestMatchers(HttpMethod.POST,   "/alunos/cadastrar").permitAll()
                        .requestMatchers(HttpMethod.GET,    "/alunos/me/aulas").authenticated()
                        .requestMatchers(HttpMethod.GET,    "/alunos").authenticated()
                        .requestMatchers(HttpMethod.GET,    "/alunos/{id}").authenticated()
                        .requestMatchers(HttpMethod.PUT,    "/alunos/{id}").authenticated()
                        .requestMatchers(HttpMethod.PATCH,  "/alunos/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/alunos/{id}").authenticated()

                        
                        .requestMatchers(HttpMethod.POST,   "/enderecos").permitAll()   
                        .requestMatchers(HttpMethod.GET,    "/enderecos").authenticated()
                        .requestMatchers(HttpMethod.GET,    "/enderecos/{id}").authenticated()
                        .requestMatchers(HttpMethod.PUT,    "/enderecos/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/enderecos/{id}").authenticated()

                        
                        .requestMatchers(HttpMethod.GET,    "/aulas").authenticated()
                        .requestMatchers(HttpMethod.GET,    "/aulas/{id}").authenticated()
                        .requestMatchers(HttpMethod.POST,   "/aulas").authenticated()       
                        .requestMatchers(HttpMethod.PUT,    "/aulas/{id}").authenticated()  

                        
                        .requestMatchers(HttpMethod.POST,   "/aulas/{idAula}/marcacoes").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/aulas/{idAula}/marcacoes").authenticated()
                        .requestMatchers(HttpMethod.GET,    "/aulas/{idAula}/alunos").authenticated()

                        
                        .requestMatchers("/professores/**").authenticated()
                        .requestMatchers("/instrumentos/**").authenticated()

                        
                        .anyRequest().denyAll()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(AlunoRepository alunoRepository) {
        return email -> alunoRepository
                .findByAlunoEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("E-mail ou senha inválidos."));
    }

    @Bean
    public AuthenticationProvider authenticationProvider(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    ) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider) {
        return new ProviderManager(List.of(authenticationProvider));
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(
            @Value("${app.cors.allowed-origins:http://localhost:3000,http://localhost:5173}") String origins
    ) {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(
                Arrays.stream(origins.split(","))
                        .map(String::trim)
                        .filter(value -> !value.isBlank())
                        .toList()
        );
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
