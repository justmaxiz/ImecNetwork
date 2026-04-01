package ru.justmaxiz.saransk.networklibrary.repository;

import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.justmaxiz.saransk.networklibrary.api.ApiService;
import ru.justmaxiz.saransk.networklibrary.common.NetworkCallback;
import ru.justmaxiz.saransk.networklibrary.common.NetworkClient;
import ru.justmaxiz.saransk.networklibrary.common.NetworkFactory;
import ru.justmaxiz.saransk.networklibrary.models.AuthRequest;
import ru.justmaxiz.saransk.networklibrary.models.AuthResponse;
import ru.justmaxiz.saransk.networklibrary.models.Combat;
import ru.justmaxiz.saransk.networklibrary.models.Profile;

/**
 * Описание назначения класса: Репозиторий для взаимодействия с сервером,
 * предназначен для использования готовых методов в Presentation слое
 * Дата создания: 01-04-2026
 * Автор создания: 15
 */
public class MainRepository {
    private ApiService apiService;
    private final String LOG_TAG = "MainRepository";

    // Конструктор репозитория
    public MainRepository(ApiService apiService) {
        this.apiService = NetworkClient.getInstance().create(ApiService.class);
    }

    // Метод авторизации
    public void login(String login, String password, Object data, NetworkCallback<AuthResponse> callback) {
        // Имплементация метода login из ApiService
        apiService.login(new AuthRequest(login, password, null)).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                // При успешном запросе сохраняем токен и вызываем onSuccess
                Log.d(LOG_TAG, "Статус авторизации: " + response.code());
                if (response.isSuccessful() && response.body() != null) {
                    NetworkFactory.getAuthInterceptor().setToken(response.body().getAccessToken());
                    callback.onSuccess(response.body());
                } else {
                    // Передаем ошибку в коллбэк
                    callback.onError("Ошибка " + response.code() + ": Неверный логин или пароль");
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable throwable) {
                // Если запрос не удался, вызываем onError
                Log.e(LOG_TAG, "Ошибка авторизации", throwable);
                callback.onError("Проблемы с сетью: " + throwable.getMessage());
            }
        });
    }

    // Метод регистрации
    public void register(String email, String password, Object data, NetworkCallback<AuthResponse> callback) {
        // Имплементация метода register из ApiService
        apiService.register(new AuthRequest(email, password, data)).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                // При успешной регистрации сохраняем токен и вызываем onSuccess
                Log.d(LOG_TAG, "Статус регистрации: " + response.code());
                if (response.isSuccessful() && response.body() != null) {
                    NetworkFactory.getAuthInterceptor().setToken(response.body().getAccessToken());
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Ошибка регистрации");
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable throwable) {
                // Если запрос не удался, вызываем onError
                Log.e(LOG_TAG, "Регистрация провалена", throwable);
                callback.onError(throwable.getMessage());
            }
        });
    }

    // Изменение профиля
    public void updateProfile(String userId, Profile profile, NetworkCallback<Void> callback) {
        // Имплементация метода updateProfile из ApiService
        apiService.updateProfile(userId, profile).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                // При успешном обновлении профиля вызываем onSuccess
                Log.d(LOG_TAG, "Статус обновления профиля: " + response.code());
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onError("Ошибка обновления профиля");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                // Если запрос не удался, вызываем onError
                Log.e(LOG_TAG, "Ошибка обновления профиля", throwable);
                callback.onError(throwable.getMessage());
            }
        });
    }

    // Получение информации о профиле
    public void getProfile(String userId, NetworkCallback<Profile> callback) {
        // Имплементация метода getProfile из ApiService
        apiService.getProfile("eq." + userId).enqueue(new Callback<List<Profile>>() {
            @Override
            public void onResponse(Call<List<Profile>> call, Response<List<Profile>> response) {
                // При удачном запросе вызываем onSuccess, в который передаем 1 профиль из листа response.body()
                // Если профиль не найден, вызываем onError
                Log.d(LOG_TAG, "Статус получения пользователя: " + response.code());
                if (response.isSuccessful() && response.body() != null) {
                    List<Profile> list = response.body();
                    if (!list.isEmpty()) {
                        callback.onSuccess(list.get(0));
                    } else {
                        callback.onError("Пользователь не найден");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Profile>> call, Throwable throwable) {
                // Если запрос не удался, вызываем onError
                Log.e(LOG_TAG, "Ошибка получения пользователя", throwable);
                callback.onError(throwable.getMessage());
            }
        });
    }

    // Метод выхода из аккаунта
    public void logout(NetworkCallback<Void> callback) {
        // Имплементация метода logout из ApiService
        apiService.logout().enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                // При удачном запросе вызываем onSuccess и обнуляем токен
                NetworkFactory.getAuthInterceptor().setToken(null);
                Log.d(LOG_TAG, "Выход выполнен, статус сервера: " + response.code());
                callback.onSuccess(null);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                // Если запрос не удался, вызываем onError, при этом обнуляем токен
                NetworkFactory.getAuthInterceptor().setToken(null);
                Log.e(LOG_TAG, "Ошибка выхода", throwable);
                callback.onSuccess(null);
            }
        });
    }

    // Метод создания игры
    public void createGame(Combat combat, NetworkCallback<Combat> callback) {
        // Имплементация метода createGame из ApiService
        apiService.createGame(combat).enqueue(new Callback<Combat>() {
            @Override
            public void onResponse(Call<Combat> call, Response<Combat> response) {
                // При удачном запросе возвращаем его тело в onSuccess
                // Если запрос не удался, вызываем onError
                Log.d(LOG_TAG, "Статус создания игры: " + response.code());
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Ошибка создания игры: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Combat> call, Throwable throwable) {
                // Если запрос не удался, вызываем onError
                Log.e(LOG_TAG, "Ошибка создания игры", throwable);
                callback.onError(throwable.getMessage());
            }
        });
    }

    // Метод получения доступных игр
    public void getAvailableGames(NetworkCallback<List<Combat>> callback) {
        // Имплементация метода getAvailableGames из ApiService
        apiService.getAvailableGames().enqueue(new Callback<List<Combat>>() {
            @Override
            public void onResponse(Call<List<Combat>> call, Response<List<Combat>> response) {
                // При успешном запросе передаем его тело (список игр) в onSuccess
                // Если запрос не удался, вызываем onError
                Log.d(LOG_TAG, "Список игр получен. Статус: " + response.code());
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Не удалось загрузить список доступных игр");
                }
            }

            @Override
            public void onFailure(Call<List<Combat>> call, Throwable t) {
                // Если запрос не удался, вызываем onError
                Log.e(LOG_TAG, "Ошибка при получении списка игр", t);
                callback.onError(t.getMessage());
            }
        });
    }

    // Метод получения деталей игры
    public void getGameDetails(String gameId, NetworkCallback<Combat> callback) {
        // Имплементация метода getGameDetails из ApiService
        apiService.getGameDetails(gameId).enqueue(new Callback<List<Combat>>() {
            @Override
            public void onResponse(Call<List<Combat>> call, Response<List<Combat>> response) {
                // При удачном запросе передаем его тело (объект игры) в onSuccess
                // Если запрос не удался, вызываем onError
                Log.d(LOG_TAG, "Детали игры получены. Статус: " + response.code());
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    // Распаковываем массив и отдаем один объект
                    callback.onSuccess(response.body().get(0));
                } else {
                    callback.onError("Игра не найдена");
                }
            }

            @Override
            public void onFailure(Call<List<Combat>> call, Throwable t) {
                // Если запрос не удался, вызываем onError
                Log.e(LOG_TAG, "Ошибка при получении деталей игры", t);
                callback.onError(t.getMessage());
            }
        });
    }

    // Метод получения таблицы лидеров
    public void getLeaderboard(NetworkCallback<List<Profile>> callback) {
        // Имплементация метода getLeaderboard из ApiService
        apiService.getLeaderboard().enqueue(new Callback<List<Profile>>() {
            @Override
            public void onResponse(Call<List<Profile>> call, Response<List<Profile>> response) {
                // При успешном запросе передаем его тело в onSuccess
                // Если запрос не удался, вызываем onError
                Log.d(LOG_TAG, "Таблица лидеров загружена. Статус: " + response.code());
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Не удалось загрузить статистику");
                }
            }

            @Override
            public void onFailure(Call<List<Profile>> call, Throwable t) {
                // Если запрос не удался, вызываем onError
                Log.e(LOG_TAG, "Ошибка загрузки статистики", t);
                callback.onError(t.getMessage());
            }
        });
    }

    // Метод сохранения результатов игры
    public void saveGameResult(String gameId, String winnerId, NetworkCallback<Void> callback) {
        // Сохраняем победителя и новый статус игры в HashMap
        Map<String, Object> resultData = new HashMap<>();
        resultData.put("winner", winnerId);
        resultData.put("status", "finished");

        // Имплементация метода saveGameResult из ApiService
        apiService.saveGameResult(gameId, resultData).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                // При успешном запросе вызываем onSuccess
                // Если запрос не удался, вызываем onError
                Log.d(LOG_TAG, "Результат игры сохранен. Статус: " + response.code());
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onError("Ошибка при сохранении результата");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Если запрос не удался, вызываем onError
                Log.e(LOG_TAG, "Сетевая ошибка при сохранении результата", t);
                callback.onError(t.getMessage());
            }
        });
    }

    // Метод подключения к игре
    public void joinCombat(String gameId, String myUserId, NetworkCallback<Void> callback) {
        // Сохраняем подключившегося игрока и новый статус игры в HashMap
        Map<String, Object> update = new HashMap<>();
        update.put("player_two", myUserId);
        update.put("status", "in_progress");

        // Имплементация метода joinGame из ApiService
        apiService.joinGame(gameId, update).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                // Если запрос удался, вызываем onSuccess
                // Если запрос не удался, вызываем onError
                Log.d(LOG_TAG, "Подключение к игре - статус: " + response.code());
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onError("Не удалось подключитсья к игре: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                // Если запрос не удался, вызываем onError
                callback.onError(throwable.getMessage());
            }
        });
    }
}
