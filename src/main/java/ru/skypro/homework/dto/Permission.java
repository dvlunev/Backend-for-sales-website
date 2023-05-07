package ru.skypro.homework.dto;

public enum Permission {
    USER_GET_AD_LIST("users: get ad list"),
    USER_GET_AD("users: get ad");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
