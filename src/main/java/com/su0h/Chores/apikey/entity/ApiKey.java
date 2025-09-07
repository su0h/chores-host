package com.su0h.Chores.apikey.entity;

import java.time.LocalDateTime;

import com.su0h.Chores.common.enums.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "api_keys")
public class ApiKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 60) // BCrypt produces 60-char hashes
    private String keyHash;

    @Column(nullable = false)
    private String name;

    @Column(name = "owner_id", nullable = false)
    private String ownerId;

    private String description;

    @Column(nullable = false)
    private boolean active = true;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.READ_ONLY;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;
    
    @Column(name = "last_used")
    private LocalDateTime lastUsed;

    public ApiKey() {}

    public ApiKey(String keyHash, String name, Integer expirationInDays, String description, String ownerId) {
        this.keyHash = keyHash;
        this.name = name;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.expiresAt = this.createdAt.plusDays(expirationInDays);
        this.ownerId = ownerId;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getKeyValueHash() { return keyHash; }
    public void setKeyValueHash(String keyValueHash) { this.keyHash = keyValueHash; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getExpiresAt() { return expiresAt; }
    public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }
    
    public LocalDateTime getLastUsed() { return lastUsed; }
    public void setLastUsed(LocalDateTime lastUsed) { this.lastUsed = lastUsed; }

    public String getOwnerId() { return ownerId; }

    public void setOwnerId(String ownerId) { this.ownerId = ownerId; }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }
}