package ru.justmaxiz.saransk.networklibrary.models;

/**
 * Описание назначения класса: DTO класс игрока, предназначен
 * для удобной конвертации JSON в GSON
 * Дата создания: 02-04-2026
 * Автор создания: 15
 */
public class Player {
    public String id;
    public String name;
    public String rank;
    public String status;
    public String categories;

    public Player(String id, String name, String rank, String status, String categories) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.status = status;
        this.categories = categories;
    }
}