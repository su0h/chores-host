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

@Service
public class ApiKeyService {
    @Autowired
    private ApiKeyRepository apiKeyRepository;
    
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
    private final SecureRandom secureRandom = new SecureRandom();

    public boolean validateApiKey(String keyValue) {
        List<ApiKey> activeKeys = apiKeyRepository.findAllByActiveTrue();

        for (ApiKey key : activeKeys) {
            if (passwordEncoder.matches(keyValue, key.getKeyValueHash())) {
                return key.getExpiresAt() == null || key.getExpiresAt().isAfter(LocalDateTime.now());
            }
        }

        return false;
    }


    public GeneratedApiKey generateApiKey(ApiKeyRequest request) {
        byte[] keyBytes = new byte[32];
        secureRandom.nextBytes(keyBytes);
        String keyValue = "ak_" + Base64.getUrlEncoder().withoutPadding().encodeToString(keyBytes);
        String hashedKey = passwordEncoder.encode(keyValue);
        ApiKey apiKey = new ApiKey(hashedKey, request.getName(), request.getExpirationDays(), request.getDescription());
        ApiKey savedKey = apiKeyRepository.save(apiKey);
        return new GeneratedApiKey(keyValue, savedKey);
    }

    public void revokeApiKey(String keyValue) {
        List<ApiKey> activeKeys = apiKeyRepository.findAllByActiveTrue();

        for (ApiKey key: activeKeys) {
            if (passwordEncoder.matches(keyValue, key.getKeyValueHash())) {
                key.setActive(false);
                apiKeyRepository.save(key);
                return;
            }
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
        System.out.println("getApiKeys() Start: " + LocalDateTime.now());
        List<ApiKey> result = apiKeyRepository.findAllByActiveTrue()
                .stream()
                .toList();

        System.out.println("getApiKeys() End: " + LocalDateTime.now());
        return result;
    }

    public record GeneratedApiKey(String plainKey, ApiKey apiKey) {}
}
