package com.macbitsgoa.events.timeline;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Rest api calls.
 *
 * @author Rushikesh Jogdand.
 */
@SuppressWarnings("DuplicateStringLiteralInspection")
public interface WebService {
    @GET("helloWorld")
    Call<ResponseBody> testApi();

    @GET("events")
    Call<List<Event>> getEvents();

    @GET("sessionOfEvent/{eventName}")
    Call<List<Session>> getSessionsOfEvent(@Path("eventName") String eventName);

    @GET("allSessions")
    Call<List<Session>> getAllSessions();
}
