package ru.justmaxiz.saransk.networklibrary.models;

/**
 * Описание назначения класса: DTO класс ответа авторизации
 * Дата создания: 02-04-2026
 * Автор создания: 15
 */
public class AuthResponse {
    public String token; // Токен для последующих запросов
    public String userId;

    public AuthResponse(String token, String userId) {
        this.token = token;
        this.userId = userId;
    }
}