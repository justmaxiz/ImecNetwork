package ru.justmaxiz.saransk.networklibrary.common;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.justmaxiz.saransk.networklibrary.interceptors.AuthInterceptor;

/**
 * NetworkFactory
 * Ответственность: Создание Retrofit клиента для передачи в NetworkClient
 * Дата создания: 30-03-2026
 * Автор: Участник № 209327
 */
public class NetworkFactory {
    private static AuthInterceptor authInterceptor = new AuthInterceptor();

    // Конструктор
    public static AuthInterceptor getAuthInterceptor() {
        return authInterceptor;
    }

    // Метод создания Retrofit клиента
    public static Retrofit createRetrofit() {
        // Настройка логирования запросов в Logcat
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Настраиваем клиент OkHttp для передачи в аргумент конструктора Retrofit
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(authInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();

        // Возвращаем Retrofit клиент с заданными параметрами (url RestAPI, конвертер Gson, клиент OkHttp)
        return new Retrofit.Builder()
                .baseUrl("https://imec-api.ru/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
}
