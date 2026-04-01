package ru.justmaxiz.saransk.networklibrary.common;

/**
 * NetworkCallback
 * Ответственность: Интерфейс коллбэка для передачи результата запроса
 * Дата создания: 30-03-2026
 * Автор: Участник № 209327
 */
public interface NetworkCallback<T> {
    void onSuccess(T result);
    void onError(String errorMessage);
}
