package com.su0h.Chores.dto.response;

import com.su0h.Chores.entity.ApiKey;

import java.time.LocalDateTime;

public class ApiKeyResponse {
    private String keyValue;
    private String name;
    private String description;
    private String message;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;

    // For new key generation (shows the actual key)
    public ApiKeyResponse(String plainKeyValue, ApiKey apiKey, String message) {
        this.keyValue = plainKeyValue;
        this.name = apiKey.getName();
        this.message = message;
        this.description = apiKey.getDescription();
        this.expiresAt = apiKey.getExpiresAt();
        this.createdAt = apiKey.getCreatedAt();
    }

    // For listing existing keys (hides the actual key)
    public static ApiKeyResponse fromEntity(ApiKey apiKey) {
        ApiKeyResponse response = new ApiKeyResponse();
        response.keyValue = "ak_****"; // Masked for security
        response.name = apiKey.getName();
        response.createdAt = apiKey.getCreatedAt();
        response.expiresAt = apiKey.getExpiresAt();
        return response;
    }

    // Private constructor for fromEntity
    private ApiKeyResponse() {}

    // Getters and Setters
    public String getKeyValue() { return keyValue; }
    public void setKeyValue(String keyValue) { this.keyValue = keyValue; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getExpiresAt() { return expiresAt; }
    public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }
}
