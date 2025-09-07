package com.su0h.Chores.apikey.dto;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String error;
    private String code;
    private LocalDateTime timestamp;

    public ErrorResponse(String error) {
        this.error = error;
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(String error, String code) {
        this.error = error;
        this.code = code;
        this.timestamp = LocalDateTime.now();
    }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
