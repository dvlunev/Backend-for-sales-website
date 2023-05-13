package ru.skypro.homework.dto;

import org.springframework.security.core.GrantedAuthority;

/**
 * Класс является перечислением Role, представляет из себя роли пользователя для управления доступом в приложении.
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
