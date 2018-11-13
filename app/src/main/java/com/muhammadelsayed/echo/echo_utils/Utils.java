package com.muhammadelsayed.echo.echo_utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.util.Log;

import com.muhammadelsayed.echo.model.Article;
import com.muhammadelsayed.echo.model.Retrosponse;
import com.muhammadelsayed.echo.network.NewsClient;
import com.muhammadelsayed.echo.network.RetrofitClientInstance;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.thefinestartist.utils.content.ContextUtil.getSystemService;

public class Utils {
    private static final String TAG = "Utils";

    public static void getNews(Map<String, Object> options,
                               final retrofitCallback callback) {

        NewsClient service = RetrofitClientInstance.getRetrofitInstance().create(NewsClient.class);
        Call<Retrosponse> call = service.search(options);
        Log.wtf(TAG, call.request().url().toString());
        call.enqueue(new Callback<Retrosponse>() {
            @Override
            public void onResponse(@NonNull Call<Retrosponse> call, @NonNull Response<Retrosponse> response) {
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
            public void onFailure(@NonNull Call<Retrosponse> call, @NonNull Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public static void getSearchResults(Map<String, Object> options,
                                        final retrofitCallback callback) {

        NewsClient service = RetrofitClientInstance.getRetrofitInstance().create(NewsClient.class);
        Call<Retrosponse> call = service.search(options);
        Log.wtf(TAG, call.request().url().toString());
        call.enqueue(new Callback<Retrosponse>() {
            @Override
            public void onResponse(@NonNull Call<Retrosponse> call, @NonNull Response<Retrosponse> response) {
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
            public void onFailure(@NonNull Call<Retrosponse> call, @NonNull Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public static boolean isNetworkAvailable() {
        Log.wtf(TAG, "isNetworkAvailable(): has been instantiated");
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public interface retrofitCallback {
        void onSuccess(List<Article> articles);

        void onFailure(Throwable t);
    }

}