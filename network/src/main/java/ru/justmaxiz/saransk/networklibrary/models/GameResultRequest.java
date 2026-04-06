package ru.justmaxiz.saransk.networklibrary.models;

/**
 * Описание назначения класса: DTO класс результата игры после
 * окончания, предназначен для удобной конвертации JSON в GSON
 * Дата создания: 02-04-2026
 * Автор создания: 15
 */
public class GameResultRequest {
    public String gameId;
    public long completionTimeMs; // Время сборки пазла
    public boolean isWinner; // Выиграл или проиграл (закрыл игру)

    public GameResultRequest(String gameId, long completionTimeMs, boolean isWinner) {
        this.gameId = gameId;
        this.completionTimeMs = completionTimeMs;
        this.isWinner = isWinner;
    }
}