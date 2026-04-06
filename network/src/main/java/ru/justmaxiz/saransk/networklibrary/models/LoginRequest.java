package ru.justmaxiz.saransk.networklibrary.models;

/**
 * Описание назначения класса: DTO класс запроса на авторизацию
 * Дата создания: 02-04-2026
 * Автор создания: 15
 */
public class LoginRequest {
    public String email;
    public String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}