package com.su0h.Chores.apikey.dto;

import jakarta.validation.constraints.NotBlank;

public class RevokeKeyRequest {
    @NotBlank(message = "Key value is required")
    private String keyValue;

    public RevokeKeyRequest() {}

    public RevokeKeyRequest(String keyValue) {
        this.keyValue = keyValue;
    }

    public String getKeyValue() { return keyValue; }
    public void setKeyValue(String keyValue) { this.keyValue = keyValue; }
}
