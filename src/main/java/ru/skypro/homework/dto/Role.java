package ru.skypro.homework.dto;

import org.springframework.security.core.GrantedAuthority;

/**
 * The class is a Role enum, represents the user's roles for access control in the application
 *
 * @see GrantedAuthority
 */
public enum Role implements GrantedAuthority {
    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
