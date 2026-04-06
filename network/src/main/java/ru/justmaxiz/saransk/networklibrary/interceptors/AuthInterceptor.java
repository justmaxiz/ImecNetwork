package ru.justmaxiz.saransk.networklibrary.interceptors;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Класс: AuthInterceptor
 * Ответственность: Сетевой перехватчик для автоматизации процесса авторизации
 * Дата создания: 30-03-2026
 * Автор: Участник № 209327
 */
public class AuthInterceptor implements Interceptor {
    private String token;
    private final String apiKey = "ТВОЙ_ANON_KEY_ИЗ_ТЗ"; // Выдадут на конкурсе

    public void setToken(String token) {
        this.token = token;
    }

    // Модификация исходящего запроса путем добавления токена авторизации
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder builder = original.newBuilder()
                .header("apikey", apiKey); // Supabase требует это всегда

        if (token != null && !token.isEmpty()) {
            builder.header("Authorization", "Bearer " + token);
        }

        return chain.proceed(builder.build());
    }
}
