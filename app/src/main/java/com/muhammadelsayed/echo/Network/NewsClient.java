package com.muhammadelsayed.echo.Network;

import com.muhammadelsayed.echo.Model.Retrosponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface NewsClient {

    @GET("search")
    Call<Retrosponse> search(@QueryMap Map<String, Object> options);
}
