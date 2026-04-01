package ru.justmaxiz.saransk.networklibrary.models;

import com.google.gson.annotations.SerializedName;

public class Combat {
    public String id, status;
    @SerializedName("player_one") public String playerOne;
    @SerializedName("player_two") public String playerTwo;
    @SerializedName("game_data") public String gameData; // Тут может быть JSON строкой
    public String winner;
}
