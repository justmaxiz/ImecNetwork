package ru.justmaxiz.saransk.networklibrary.models;

import com.google.gson.annotations.SerializedName;

public class AuthResponse {
    @SerializedName("access_token") public String accessToken;
    @SerializedName("user") public User user;

    public static class User {
        public String id;
        public String email;
    }

    public String getAccessToken() {
        return accessToken;
    }
}

