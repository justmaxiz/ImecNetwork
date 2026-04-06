package ru.justmaxiz.saransk.networklibrary.models;

/**
 * Описание назначения класса: DTO класс профиля пользователя
 * Дата создания: 02-04-2026
 * Автор создания: 15
 */
public class UserProfile {
    public String id;
    public String fullName;
    public String username;
    public String email;
    public String phone;
    public String photoUrl; // Для аватарки

    public UserProfile(String id, String fullName, String username, String email, String phone, String photoUrl) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.photoUrl = photoUrl;
    }
}