package ru.justmaxiz.saransk.networklibrary.models;

/**
 * Описание назначения класса: DTO класс запроса на регистрацию
 * Дата создания: 02-04-2026
 * Автор создания: 15
 */
public class RegisterRequest {
    public String fullName;
    public String username;
    public String email;
    public String password;
    public String phone;

    public RegisterRequest(String fullName, String username, String email, String password, String phone) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }
}