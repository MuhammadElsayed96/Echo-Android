package com.muhammadelsayed.echo.network;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface NewsClient {

    @GET("top-headlines")
    Call<Void> getTopHeadLines(@QueryMap Map<String, String> options);

}
