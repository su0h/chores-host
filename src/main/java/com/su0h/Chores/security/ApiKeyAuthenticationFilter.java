package com.su0h.Chores.security;

import com.su0h.Chores.entity.ApiKey;
import com.su0h.Chores.service.ApiKeyService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class ApiKeyAuthenticationFilter extends OncePerRequestFilter {

    private static final String API_KEY_HEADER = "X-API-KEY";

    private final ApiKeyService apiKeyService;

    public ApiKeyAuthenticationFilter(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String requestApiKey = request.getHeader(API_KEY_HEADER);
        System.out.println(requestApiKey);

        // No API key → skip (allows public endpoints to work)
        if (requestApiKey == null || requestApiKey.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }

        // Validate API key
        ApiKey apiKey = apiKeyService.getApiKey(requestApiKey);
        if (apiKey == null) {
            System.out.println("apiKey = " + apiKey);
            sendUnauthorized(response, "Invalid or expired API key");
            return;
        }

        System.out.println("apiKey = " + apiKey.toString());

        // Build authentication with details from DB
        PreAuthenticatedAuthenticationToken auth = new PreAuthenticatedAuthenticationToken(
            apiKey.getName(),
            requestApiKey,
            // Maybe add Authorities?
            Collections.emptyList()
        );
        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }

    private void sendUnauthorized(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"" + message + "\"}");
    }
}
