package com.beasttailor.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Allow unauthenticated access to our health check endpoint
                .requestMatchers("/api/health").permitAll()
                // Secure all other endpoints
                .anyRequest().authenticated()
            )
            // Use default form login for now
            .formLogin(form -> form.permitAll());

        return http.build();
    }
}