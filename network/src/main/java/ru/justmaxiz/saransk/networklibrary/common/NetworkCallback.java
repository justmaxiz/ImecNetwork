package ru.justmaxiz.saransk.networklibrary.common;

/**
 * NetworkCallback
 * Описание назначения класса: Интерфейс коллбэка для передачи результата запроса
 * Дата создания: 30-03-2026
 * Автор: Участник 15
 */
public interface NetworkCallback<T> {
    void onSuccess(T result);
    void onError(String errorMessage);
}
