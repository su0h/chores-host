package com.su0h.Chores.common.enums;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public enum Role {
    ADMIN(Set.of(
            Permission.READ,
            Permission.WRITE,
            Permission.DELETE
    )),
    READ_WRITE(Set.of(
            Permission.READ,
            Permission.WRITE
    )),
    READ_ONLY(Set.of(
            Permission.READ
    ));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Collection<GrantedAuthority> getAuthorities() {

        // add permissions
        List<GrantedAuthority> authorities = new ArrayList<>(permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .toList());

        // add role itself (with ROLE_ prefix)
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return authorities;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}
