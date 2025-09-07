package com.su0h.Chores.apikey.dto;

import java.time.LocalDateTime;

public class MessageResponse {
    private String message;
    private LocalDateTime timestamp;

    public MessageResponse(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
