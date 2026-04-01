package ru.justmaxiz.saransk.networklibrary.models;

public class LoginRequest {
    private String login, password;

    public LoginRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
