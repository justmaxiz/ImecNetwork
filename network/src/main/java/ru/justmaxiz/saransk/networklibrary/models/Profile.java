package ru.justmaxiz.saransk.networklibrary.models;

import com.google.gson.annotations.SerializedName;

public class Profile {
    public String id, username;
    @SerializedName("avatar_url") public String avatarUrl;
    public int wins, score;
}
