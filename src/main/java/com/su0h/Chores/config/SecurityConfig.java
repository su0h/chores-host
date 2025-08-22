package com.su0h.Chores.config;

import com.su0h.Chores.security.ApiKeyAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final ApiKeyAuthenticationFilter apiKeyAuthenticationFilter;

    public SecurityConfig(ApiKeyAuthenticationFilter apiKeyAuthenticationFilter) {
        this.apiKeyAuthenticationFilter = apiKeyAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable) // disable CSRF for API
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**", "/health").permitAll() // no auth
                        // ak_93Orhl1-8T1SJwTCu6ClJ3dERREIuSBLvcImUcImE7M
                        .requestMatchers("/api/admin/**").permitAll()
                        .requestMatchers("/api/**").authenticated() // requires API key
                        .anyRequest().permitAll() // everything else is open
                )
                .addFilterBefore(apiKeyAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
