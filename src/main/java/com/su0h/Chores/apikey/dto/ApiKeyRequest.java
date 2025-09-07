package com.su0h.Chores.apikey.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ApiKeyRequest {
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    private String description;

    @NotNull(message = "Days to expiration value is required")
    @Positive
    private Integer expirationDays;

    @NotNull(message = "Owner ID is required")
    private String ownerId;

    public ApiKeyRequest() {}

    public ApiKeyRequest(String name, Integer expirationDays, String ownerId) {
        this.name = name;
        this.expirationDays = expirationDays;
        this.ownerId = ownerId;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getExpirationDays() { return expirationDays; }
    public void setExpirationDays(Integer expirationDays) { this.expirationDays = expirationDays; }

    public String getOwnerId() { return ownerId; }

    public void setOwnerId(String ownerId) { this.ownerId = ownerId; }
}
