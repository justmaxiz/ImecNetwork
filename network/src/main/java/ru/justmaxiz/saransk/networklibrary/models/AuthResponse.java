package ru.justmaxiz.saransk.networklibrary.models;

import com.google.gson.annotations.SerializedName;

public class AuthResponse {
    @SerializedName("access_token") public String accessToken;

    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}

