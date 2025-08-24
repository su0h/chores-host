package com.su0h.Chores.controller;

import com.su0h.Chores.dto.request.ApiKeyRequest;
import com.su0h.Chores.dto.request.RevokeKeyRequest;
import com.su0h.Chores.dto.response.ApiKeyResponse;
import com.su0h.Chores.dto.response.ErrorResponse;
import com.su0h.Chores.dto.response.MessageResponse;
import com.su0h.Chores.exception.ApiKeyNotFoundException;
import com.su0h.Chores.service.ApiKeyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1.2/tokens")
public class ApiKeyController {
    @Autowired
    private ApiKeyService apiKeyService;

    @PostMapping("/generate")
    public ResponseEntity<?> generateApiKey(@Valid @RequestBody ApiKeyRequest request) {
        try {
            ApiKeyService.GeneratedApiKey apiKey = apiKeyService.generateApiKey(request);
            return ResponseEntity.ok(new ApiKeyResponse(
                    apiKey.plainKey(),
                    apiKey.apiKey(),
                    "API key generated successfully. Store this key securely - it won't be shown again!"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Failed to generate API key"));
        }
    }

    @DeleteMapping("/revoke")
    public ResponseEntity<?> revokeApiKey(@Valid @RequestBody RevokeKeyRequest request) {
        try {
            apiKeyService.revokeApiKey(request.getKeyValue());
            return ResponseEntity.ok(new MessageResponse("API key revoked successfully"));
        } catch (ApiKeyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage(), "TOKEN_NOT_FOUND"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("An unexpected error occurred", "INTERNAL_SERVER_ERROR"));
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getApiKeys() {
        try {
            return ResponseEntity.ok(apiKeyService.getApiKeys());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Failed to retrieve API keys"));
        }
    }
}
