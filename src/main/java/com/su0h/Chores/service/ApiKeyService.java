package com.su0h.Chores.service;

import com.su0h.Chores.dto.request.ApiKeyRequest;
import com.su0h.Chores.entity.ApiKey;
import com.su0h.Chores.exception.ApiKeyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.su0h.Chores.repository.ApiKeyRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class ApiKeyService {
    @Autowired
    private ApiKeyRepository apiKeyRepository;
    
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
    private final SecureRandom secureRandom = new SecureRandom();

    public Optional<ApiKey> findValidApiKey(String keyValue) {
        List<ApiKey> activeKeys = apiKeyRepository.findAllByActiveTrue();

        activeKeys.stream()
                .filter(this::isExpired)
                .forEach(key -> {
                    key.setActive(false);
                    apiKeyRepository.save(key);
                });

        return activeKeys.stream()
                .filter(key -> passwordEncoder.matches(keyValue, key.getKeyValueHash()))
                .filter(key -> !isExpired(key))
                .findFirst();
    }

    public GeneratedApiKey generateApiKey(ApiKeyRequest request) {
        byte[] keyBytes = new byte[32];
        secureRandom.nextBytes(keyBytes);
        String keyValue = "ak_" + Base64.getUrlEncoder().withoutPadding().encodeToString(keyBytes);
        String hashedKey = passwordEncoder.encode(keyValue);
        ApiKey apiKey = new ApiKey(hashedKey, request.getName(), request.getExpirationDays(), request.getDescription(), request.getOwnerId());
        ApiKey savedKey = apiKeyRepository.save(apiKey);
        return new GeneratedApiKey(keyValue, savedKey);
    }

    public void revokeApiKey(String keyValue) {
        ApiKey key = this.getApiKey(keyValue);
        if(key != null) {
            key.setActive(false);
            apiKeyRepository.save(key);
            return;
        }

        // If we reach here, the API key wasn't found among active keys
        throw new ApiKeyNotFoundException("API key not found or already inactive");
    }

    public ApiKey getApiKey(String apiKey) {
        List<ApiKey> apiKeys = this.getApiKeys();

        for (ApiKey key: apiKeys) {
            if (passwordEncoder.matches(apiKey, key.getKeyValueHash())) {
                return key;
            }
        }

        return null;
    }

    public List<ApiKey> getApiKeys() {
        return apiKeyRepository.findAllByActiveTrue()
                .stream()
                .toList();
    }

    public record GeneratedApiKey(String plainKey, ApiKey apiKey) {}

    private boolean isExpired(ApiKey key) {
        return key.getExpiresAt() == null || key.getExpiresAt().isBefore(LocalDateTime.now());
    }
}
