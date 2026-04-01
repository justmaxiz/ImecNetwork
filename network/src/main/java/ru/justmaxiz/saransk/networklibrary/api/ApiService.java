package ru.justmaxiz.saransk.networklibrary.api;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ru.justmaxiz.saransk.networklibrary.models.AuthRequest;
import ru.justmaxiz.saransk.networklibrary.models.AuthResponse;
import ru.justmaxiz.saransk.networklibrary.models.Combat;
import ru.justmaxiz.saransk.networklibrary.models.Profile;

public interface ApiService {
    // 1. Авторизация
    @POST("auth/v1/token?grant_type=password")
    Call<AuthResponse> login(@Body AuthRequest request);

    // 2. Регистрация
    @POST("auth/v1/signup")
    Call<AuthResponse> register(@Body AuthRequest request);

    // 3. Изменение профиля (PATCH — обновляет только присланные поля)
    @PATCH("rest/v1/profiles")
    Call<Void> updateProfile(@Query("id") String userId, @Body Profile profile);

    // 4. Получение информации о профиле
    @GET("rest/v1/profiles")
    Call<List<Profile>> getProfile(@Query("id") String userId);

    // 5. Выход (В Supabase это часто просто очистка токена на клиенте)
    @POST("auth/v1/logout")
    Call<Void> logout();

    // 6. Создание игры
    @POST("rest/v1/combats")
    Call<Combat> createGame(@Body Combat combat);

    // 7. Список игр (все игры со статусом "ожидание")
    @GET("rest/v1/combats?status=eq.waiting")
    Call<List<Combat>> getAvailableGames();

    // 8. Информация об игре (детали по ID)
    @GET("rest/v1/combats")
    Call<List<Combat>> getGameDetails(@Query("id") String gameId);

    // 9. Подключение к игре (записываем себя в player_two и меняем статус)
    @PATCH("rest/v1/combats")
    Call<Void> joinGame(@Query("id") String gameId, @Body Map<String, Object> updateData);

    // 10. Статистика игрока (сортировка по очкам)
    @GET("rest/v1/profiles?order=score.desc")
    Call<List<Profile>> getLeaderboard();

    // 11. Сохранение результата игры (запись победителя и статуса finished)
    @PATCH("rest/v1/combats")
    Call<Void> saveGameResult(@Query("id") String gameId, @Body Map<String, Object> resultData);
}
