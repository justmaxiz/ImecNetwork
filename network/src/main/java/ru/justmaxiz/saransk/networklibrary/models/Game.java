package ru.justmaxiz.saransk.networklibrary.models;

/**
 * Описание назначения класса: DTO класс игры, предназначен
 * для удобной конвертации JSON в GSON
 * Дата создания: 02-04-2026
 * Автор создания: 15
 */
public class Game {
    public String id;
    public String hostName;
    public String guestName; // Может быть null, если соперника еще нет
    public int extraGuests;
    public String gameName;
    public String status;
    public String winningPrice;

    public Game(String id, String hostName, String guestName, int extraGuests,
                String gameName, String status, String winningPrice) {
        this.id = id;
        this.hostName = hostName;
        this.guestName = guestName;
        this.extraGuests = extraGuests;
        this.gameName = gameName;
        this.status = status;
        this.winningPrice = winningPrice;
    }
}