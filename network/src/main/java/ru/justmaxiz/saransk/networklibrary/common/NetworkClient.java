package ru.justmaxiz.saransk.networklibrary.common;

import retrofit2.Retrofit;

/**
 * NetworkClient
 * Ответственность: Создание и хранение единого Retorfit клиента
 * Дата создания: 30-03-2026
 * Автор: Участник № 209327
 */
public class NetworkClient {
    private static Retrofit instance = null;

    // Получение singleton клиента Retrofit
    public static Retrofit getInstance() {
        if (instance == null) {
            // Вот тут магия: Client просит Factory собрать объект
            instance = NetworkFactory.createRetrofit();
        }
        return instance;
    }
}
