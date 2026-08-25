package com.example.demo;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder; // <-- CORRIGIDO AQUI
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${SENHA_ADMIN:B988RINEDANIBO}")
    private String senhaAdmin;

    // 1. AS REGRAS DA PORTA
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    // 2. CRIAÇÃO DO USUÁRIO
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) { // <-- CORRIGIDO AQUI
        UserDetails admin = User.builder()
                .username("rian")
                .password(passwordEncoder.encode(senhaAdmin))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }

    // 3. CODIFICADOR DE SENHA
    @Bean
    public PasswordEncoder passwordEncoder() { // <-- CORRIGIDO AQUI
        return new BCryptPasswordEncoder();
    }

    // 4. CONFIGURAÇÃO GLOBAL DE CORS (Libera o DELETE para o navegador)
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*")); // Libera para qualquer site (seu GitHub Pages)
        configuration.setAllowedMethods(List.of("GET", "POST", "DELETE", "PUT", "OPTIONS")); // Libera o Apagar!
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}