package com.su0h.Chores.config;

import com.su0h.Chores.security.ApiKeyAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
                        .requestMatchers("/public/**", "/health").permitAll()
                        .requestMatchers("/api/v1.2/tokens").hasRole("ADMIN")
                        .requestMatchers("/api/v1.2/tokens/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/**").hasAuthority("WRITE")
                        .requestMatchers(HttpMethod.PUT, "/api/**").hasAuthority("WRITE")
                        .requestMatchers(HttpMethod.PATCH, "/api/**").hasAuthority("WRITE")
                        .requestMatchers(HttpMethod.DELETE, "/api/**").hasAuthority("DELETE")
                        .requestMatchers(HttpMethod.GET, "/api/**").hasAuthority("READ")
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll()
                )
                .addFilterBefore(apiKeyAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
