package ru.justmaxiz.saransk.networklibrary.models;

/**
 * Описание назначения класса: DTO класс статистики, предназначен
 * для удобной конвертации JSON в GSON
 * Дата создания: 02-04-2026
 * Автор создания: 15
 */
public class PlayerStatistics {
    public String thisWeekEarnings; // Сумма заработанных баллов
    public int playedGames;         // Количество выигранных игр
    public int scheduledGames;      // Количество созданных игр за неделю

    public PlayerStatistics(String thisWeekEarnings, int playedGames, int scheduledGames) {
        this.thisWeekEarnings = thisWeekEarnings;
        this.playedGames = playedGames;
        this.scheduledGames = scheduledGames;
    }
}