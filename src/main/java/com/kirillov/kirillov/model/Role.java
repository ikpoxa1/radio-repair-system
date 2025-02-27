package com.kirillov.kirillov.model;

public enum Role {
    ADMIN("Админ"),
    USER("Пользователь"),
    VIEWER("Зритель");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    // Метод для получения имени роли с префиксом "ROLE_", как ожидается в Spring Security
    public String getAuthorityName() {
        return "ROLE_" + this.name();
    }
}
