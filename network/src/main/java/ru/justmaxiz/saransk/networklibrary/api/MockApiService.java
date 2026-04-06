package ru.justmaxiz.saransk.networklibrary.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okio.Timeout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import ru.justmaxiz.saransk.networklibrary.models.*;

/**
 * Описание назначения класса: Класс-реализация ApiService для тестирования приложения
 * с использованием фейковых операций
 * Дата создания: 02-04-2026
 * Автор создания: 15
 */
public class MockApiService implements ApiService {

    // Вспомогательный метод для быстрой генерации успешного Retrofit Call
    private <T> Call<T> createMockCall(T body) {
        return new Call<T>() {
            @Override public Response<T> execute() throws IOException { return Response.success(body); }
            @Override public void enqueue(Callback<T> callback) { callback.onResponse(this, Response.success(body)); }
            @Override public boolean isExecuted() { return false; }
            @Override public void cancel() {}
            @Override public boolean isCanceled() { return false; }
            @Override public Call<T> clone() { return this; }
            @Override public Request request() { return new Request.Builder().url("http://mock.local").build(); }
            @Override public Timeout timeout() { return Timeout.NONE; }
        };
    }

    @Override
    public Call<AuthResponse> login(LoginRequest request) {
        return createMockCall(new AuthResponse("mock_token_123", "user_1"));
    }

    @Override
    public Call<AuthResponse> register(RegisterRequest request) {
        return createMockCall(new AuthResponse("mock_token_123", "user_1"));
    }

    @Override
    public Call<Void> updateProfile(ProfileUpdateRequest request) {
        return createMockCall(null);
    }

    @Override
    public Call<UserProfile> getProfile(String userId) {
        return createMockCall(new UserProfile("user_1", "Scott Brown", "scottB", "name@domenname.ru", "1234567890", ""));
    }

    @Override
    public Call<Void> logout() {
        return createMockCall(null);
    }

    @Override
    public Call<Void> createGame(GameCreateRequest request) {
        return createMockCall(null);
    }

    @Override
    public Call<List<Game>> getGames(String filter) {
        List<Game> list = new ArrayList<>();
        if ("trending".equals(filter) || "latest".equals(filter)) {
            list.add(new Game("1", "Scott Brown", "Stone Stella", 0, "Halo 5", "Open", "$4,000"));
            list.add(new Game("2", "Alex Mercer", null, 0, "FIFA 24", "Open", "$1,000"));
        } else {
            list.add(new Game("3", "Shedrac Bety", "Usman Terik", 3, "Mortal Kombat", "Cancelled", "$7,000"));
            list.add(new Game("4", "Jully Paga", "Dada Awuri", 0, "NFS(Rivals 2)", "Active", "$4,000"));
        }
        return createMockCall(list);
    }

    @Override
    public Call<Game> getGameInfo(String gameId) {
        return createMockCall(new Game(gameId, "Scott Brown", "Stone Stella", 0, "NFS(Rivals 2)", "Open", "$4,000"));
    }

    @Override
    public Call<Void> joinGame(String gameId) {
        return createMockCall(null);
    }

    @Override
    public Call<PlayerStatistics> getPlayerStatistics(String userId) {
        return createMockCall(new PlayerStatistics("$12,500", 42, 15));
    }

    @Override
    public Call<Void> saveGameResult(GameResultRequest request) {
        return createMockCall(null);
    }

    @Override
    public Call<List<Player>> getPopularPlayers() {
        List<Player> list = new ArrayList<>();
        list.add(new Player("user_1", "Scott Brown", "Gold Player", "Online", "Action, Soccer..."));
        list.add(new Player("user_2", "Teslar fullar", "Silver Player", "Away", "Action, Soccer..."));
        return createMockCall(list);
    }
}