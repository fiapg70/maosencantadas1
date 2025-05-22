package com.maosencantadas.model.domain.user;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private final String role;

    UserRole(String role){
        this.role = role;
    }

    public static UserRole getUserRole(String role) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.name().equals(role)) {
                return userRole;
            }
        }
        return null;
    }
}
