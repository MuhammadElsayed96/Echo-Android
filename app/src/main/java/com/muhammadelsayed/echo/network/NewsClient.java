package com.muhammadelsayed.echo.network;

import com.muhammadelsayed.echo.model.ResultArticles;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface NewsClient {

    @GET("top-headlines")
        Call<ResultArticles> getTopHeadLines(@QueryMap Map<String, String> options);

}
