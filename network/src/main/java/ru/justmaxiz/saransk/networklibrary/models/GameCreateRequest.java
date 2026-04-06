package ru.justmaxiz.saransk.networklibrary.models;

/**
 * Описание назначения класса: DTO класс запроса создания игры, предназначен
 * для удобной конвертации JSON в GSON
 * Дата создания: 02-04-2026
 * Автор создания: 15
 */
public class GameCreateRequest {
    public String name;
    public String category;
    public String winningPrice;
    public String fromDate;
    public String fromTime;
    public String toDate;
    public String toTime;
    public String description;
    public boolean isReminderOn; // Из Спринт 4 пункт 1 (уведомления)

    public GameCreateRequest(String name, String category, String winningPrice, String fromDate,
                             String fromTime, String toDate, String toTime, String description, boolean isReminderOn) {
        this.name = name;
        this.category = category;
        this.winningPrice = winningPrice;
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;
        this.description = description;
        this.isReminderOn = isReminderOn;
    }
}