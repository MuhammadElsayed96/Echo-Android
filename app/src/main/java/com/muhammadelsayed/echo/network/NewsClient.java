package com.muhammadelsayed.echo.network;

import com.muhammadelsayed.echo.model.Retrosponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface NewsClient {

    @GET("search")
    Call<Retrosponse> search(@QueryMap Map<String, Object> options);
}
