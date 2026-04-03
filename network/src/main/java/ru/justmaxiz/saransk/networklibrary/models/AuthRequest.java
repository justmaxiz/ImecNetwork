package ru.justmaxiz.saransk.networklibrary.models;

public class AuthRequest {
    public String fullname, username, email, password, phone;

    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AuthRequest(String fullname, String username, String email, String password, String phone) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }
}
