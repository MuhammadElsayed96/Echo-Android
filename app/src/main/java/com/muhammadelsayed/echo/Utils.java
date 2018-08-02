package com.muhammadelsayed.echo;

import android.util.Log;

import com.muhammadelsayed.echo.model.Article;
import com.muhammadelsayed.echo.model.Retrosponse;
import com.muhammadelsayed.echo.network.NewsClient;
import com.muhammadelsayed.echo.network.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Utils {
    private static final String TAG = "Utils";

    public static void getNews(Map<String, Object> options,
                               final retrofitCallback callback) {

        NewsClient service = RetrofitClientInstance.getRetrofitInstance().create(NewsClient.class);
        Call<Retrosponse> call = service.search(options);

        call.enqueue(new Callback<Retrosponse>() {
            @Override
            public void onResponse(Call<Retrosponse> call, Response<Retrosponse> response) {
                if (response.body() != null) {
                    if (response.body().getResponse().getStatus().equals("ok")) {

                        Log.d(TAG, "onResponse: " + response.body());
                        List<Article> articles =
                                new ArrayList<>(Arrays.asList(response.body().getResponse().getArticles()));
                        callback.onSuccess(articles);


                    }
                }
            }

            @Override
            public void onFailure(Call<Retrosponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });

    }

    public interface retrofitCallback {
        void onSuccess(List<Article> articles);

        void onFailure(Throwable t);
    }

}
