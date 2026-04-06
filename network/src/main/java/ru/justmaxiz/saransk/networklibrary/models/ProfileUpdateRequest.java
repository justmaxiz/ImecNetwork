package ru.justmaxiz.saransk.networklibrary.models;

/**
 * Описание назначения класса: DTO класс обновления информации в профиле
 * пользователя, предназначен для удобной конвертации JSON в GSON
 * Дата создания: 02-04-2026
 * Автор создания: 15
 */
public class ProfileUpdateRequest {
    public String fullName;
    public String username;
    public String email;
    public String phone;
    public String photoBase64; // Передача фото на сервер (согласно Модулю Д)

    public ProfileUpdateRequest(String fullName, String username, String email, String phone, String photoBase64) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.photoBase64 = photoBase64;
    }
}