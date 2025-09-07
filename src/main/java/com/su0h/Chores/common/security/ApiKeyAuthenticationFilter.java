package com.su0h.Chores.common.security;

import com.su0h.Chores.common.enums.Role;
import com.su0h.Chores.apikey.entity.ApiKey;
import com.su0h.Chores.apikey.entity.ApiKeyPrincipal;
import com.su0h.Chores.apikey.service.ApiKeyService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

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

        if (requestApiKey == null || requestApiKey.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }

        Optional<ApiKey> apiKeyOptional = apiKeyService.findValidApiKey(requestApiKey);
        if (apiKeyOptional.isEmpty()) {
            sendUnauthorized(response, "Invalid or expired API key");
            return;
        }

        ApiKey apiKey = apiKeyOptional.get();
        ApiKeyPrincipal principal = new ApiKeyPrincipal(
            apiKey.getId(),
            apiKey.getName(),
            apiKey.getOwnerId(),
            apiKey.getRole()
        );

        Collection<GrantedAuthority> authorities = getAuthoritiesForRole(apiKey.getRole());
        PreAuthenticatedAuthenticationToken auth = new PreAuthenticatedAuthenticationToken(
            principal,
            requestApiKey,
            authorities
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

    private Collection<GrantedAuthority> getAuthoritiesForRole(Role role) {
        return role.getAuthorities();
    }
}
