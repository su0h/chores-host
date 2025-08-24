package com.su0h.Chores.entity;

import com.su0h.Chores.common.enums.Role;

import java.security.Principal;

public record ApiKeyPrincipal(
        Long apiKeyId,
        String name,
        String ownerId,
        Role role
) implements Principal {

    @Override
    public String getName() {
        return name;
    }

    public String getUniqueIdentifier() {
        return ownerId + ":" + name;
    }
}
