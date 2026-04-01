package ru.justmaxiz.saransk.networklibrary.models;

public class AuthRequest {
    public String email, password;
    public Object data;

    public AuthRequest(String email, String password, Object data) {
        this.email = email;
        this.password = password;
        this.data = data;
    }


}
