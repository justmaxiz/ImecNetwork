package ru.justmaxiz.saransk.networklibrary.api;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.justmaxiz.saransk.networklibrary.models.*;

/**
 * Описание назначения класса: Интерфейс API для работы с RestAPI
 * Дата создания: 02-04-2026
 * Автор создания: 15
 */
public interface ApiService {
    // 1. Авторизация
    @POST("auth/login")
    Call<AuthResponse> login(@Body LoginRequest request);

    // 2. Регистрация
    @POST("auth/register")
    Call<AuthResponse> register(@Body RegisterRequest request);

    // 3. Изменение профиля
    @PUT("profile")
    Call<Void> updateProfile(@Body ProfileUpdateRequest request);

    // 4. Получение информации о профиле
    @GET("profile/{userId}")
    Call<UserProfile> getProfile(@Path("userId") String userId);

    // 5. Выход
    @POST("auth/logout")
    Call<Void> logout();

    // 6. Создание игры
    @POST("games")
    Call<Void> createGame(@Body GameCreateRequest request);

    // 7. Список игр (trending, latest, etc.)
    @GET("games")
    Call<List<Game>> getGames(@Query("filter") String filter);

    // 8. Информация об игре
    @GET("games/{gameId}")
    Call<Game> getGameInfo(@Path("gameId") String gameId);

    // 9. Подключение к игре
    @POST("games/{gameId}/join")
    Call<Void> joinGame(@Path("gameId") String gameId);

    // 10. Статистика игрока
    @GET("statistics/{userId}")
    Call<PlayerStatistics> getPlayerStatistics(@Path("userId") String userId);

    // 11. Сохранение результата игры
    @POST("games/result")
    Call<Void> saveGameResult(@Body GameResultRequest request);

    // Экран Discover (Популярные игроки)
    @GET("players/popular")
    Call<List<Player>> getPopularPlayers();
}