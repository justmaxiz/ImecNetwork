package ru.justmaxiz.saransk.networklibrary;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.justmaxiz.saransk.networklibrary.api.ApiService;
import ru.justmaxiz.saransk.networklibrary.common.NetworkCallback;
import ru.justmaxiz.saransk.networklibrary.common.NetworkClient;
import ru.justmaxiz.saransk.networklibrary.common.NetworkFactory;
import ru.justmaxiz.saransk.networklibrary.models.*;

/**
 * Описание назначения класса: Сетевой менеджер, обрабатывающий все запросы к серверу,
 * предназначен для возврата результата операций вне библиотеки
 * Дата создания: 02-04-2026
 * Автор создания: 15
 */
public class NetworkManager {

    private final ApiService apiService;

    public NetworkManager() {
        this.apiService = NetworkClient.getInstance().create(ApiService.class);
    }

    // Метод авторизации
    public void login(LoginRequest request, NetworkCallback<AuthResponse> callback) {
        apiService.login(request).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    NetworkFactory.getAuthInterceptor().setToken(response.body().token);
                    callback.onSuccess(response.body());
                } else callback.onError("Ошибка авторизации: " + response.code());
            }
            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                callback.onError("Нет подключения к сети");
            }
        });
    }

    public void register(RegisterRequest request, NetworkCallback<AuthResponse> callback) {
        apiService.register(request).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    NetworkFactory.getAuthInterceptor().setToken(response.body().token);
                    callback.onSuccess(response.body());
                } else callback.onError("Ошибка регистрации: " + response.code());
            }
            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                callback.onError("Нет подключения к сети");
            }
        });
    }

    public void updateProfile(ProfileUpdateRequest request, NetworkCallback<Void> callback) {
        apiService.updateProfile(request).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) callback.onSuccess(null);
                else callback.onError("Ошибка обновления профиля: " + response.code());
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onError("Нет подключения к сети");
            }
        });
    }

    public void getProfile(String userId, NetworkCallback<UserProfile> callback) {
        apiService.getProfile(userId).enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful() && response.body() != null) callback.onSuccess(response.body());
                else callback.onError("Ошибка загрузки профиля: " + response.code());
            }
            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                callback.onError("Нет подключения к сети");
            }
        });
    }

    public void logout(NetworkCallback<Void> callback) {
        apiService.logout().enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                NetworkFactory.getAuthInterceptor().setToken(null);
                if (response.isSuccessful()) callback.onSuccess(null);
                else callback.onError("Ошибка выхода: " + response.code());
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onError("Нет подключения к сети");
            }
        });
    }

    public void createGame(GameCreateRequest request, NetworkCallback<Void> callback) {
        apiService.createGame(request).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) callback.onSuccess(null);
                else callback.onError("Ошибка создания игры: " + response.code());
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onError("Нет подключения к сети");
            }
        });
    }

    public void getTrendingGames(NetworkCallback<List<Game>> callback) {
        apiService.getGames("trending").enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                if (response.isSuccessful() && response.body() != null) callback.onSuccess(response.body());
                else callback.onError("Ошибка загрузки игр");
            }
            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                callback.onError("Нет подключения к сети");
            }
        });
    }

    public void getLatestGames(NetworkCallback<List<Game>> callback) {
        apiService.getGames("latest").enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                if (response.isSuccessful() && response.body() != null) callback.onSuccess(response.body());
                else callback.onError("Ошибка загрузки игр");
            }
            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                callback.onError("Нет подключения к сети");
            }
        });
    }

    public void getGameInfo(String gameId, NetworkCallback<Game> callback) {
        apiService.getGameInfo(gameId).enqueue(new Callback<Game>() {
            @Override
            public void onResponse(Call<Game> call, Response<Game> response) {
                if (response.isSuccessful() && response.body() != null) callback.onSuccess(response.body());
                else callback.onError("Ошибка загрузки информации об игре");
            }
            @Override
            public void onFailure(Call<Game> call, Throwable t) {
                callback.onError("Нет подключения к сети");
            }
        });
    }

    public void joinGame(String gameId, NetworkCallback<Void> callback) {
        apiService.joinGame(gameId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) callback.onSuccess(null);
                else callback.onError("Ошибка подключения к игре: " + response.code());
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onError("Нет подключения к сети");
            }
        });
    }

    public void getPlayerStatistics(String userId, NetworkCallback<PlayerStatistics> callback) {
        apiService.getPlayerStatistics(userId).enqueue(new Callback<PlayerStatistics>() {
            @Override
            public void onResponse(Call<PlayerStatistics> call, Response<PlayerStatistics> response) {
                if (response.isSuccessful() && response.body() != null) callback.onSuccess(response.body());
                else callback.onError("Ошибка загрузки статистики");
            }
            @Override
            public void onFailure(Call<PlayerStatistics> call, Throwable t) {
                callback.onError("Нет подключения к сети");
            }
        });
    }

    public void saveGameResult(GameResultRequest request, NetworkCallback<Void> callback) {
        apiService.saveGameResult(request).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) callback.onSuccess(null);
                else callback.onError("Ошибка сохранения результата");
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onError("Нет подключения к сети");
            }
        });
    }

    public void getPopularPlayers(NetworkCallback<List<Player>> callback) {
        apiService.getPopularPlayers().enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                if (response.isSuccessful() && response.body() != null) callback.onSuccess(response.body());
                else callback.onError("Ошибка загрузки игроков");
            }
            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                callback.onError("Нет подключения к сети");
            }
        });
    }
}