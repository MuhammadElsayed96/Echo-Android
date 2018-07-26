package com.muhammadelsayed.echo;

import android.util.Log;

import com.muhammadelsayed.echo.model.Article;
import com.muhammadelsayed.echo.model.ResultArticles;
import com.muhammadelsayed.echo.network.NewsClient;
import com.muhammadelsayed.echo.network.RetrofitClientInstance;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Utils {
  private static final String TAG = "Utils";

//  private static List<Article> articles = new ArrayList<>();

  public static void getTopHeadLines(Map<String, Object> options, final retrofitCallback callback) {
    NewsClient service = RetrofitClientInstance.getRetrofitInstance().create(NewsClient.class);

    Call<ResultArticles> call = service.getTopHeadLines(options);
    call.enqueue(
        new Callback<ResultArticles>() {
          @Override
          public void onResponse(Call<ResultArticles> call, Response<ResultArticles> response) {
            Log.wtf(TAG, "getTopHeadLines()::onResponse(): Has been instantiated");
            if (response.body().getStatus().equals("ok")) {
                List<Article> articles = Arrays.asList(response.body().getArticles());
              callback.onSuccess(articles);
            }
          }

          @Override
          public void onFailure(Call<ResultArticles> call, Throwable t) {
            Log.wtf(TAG, "getTopHeadLines()::onFailure(): Has been instantiated");
          }
        });
  }

  public static void getEverything(Map<String, Object> options, final retrofitCallback callback) {
    NewsClient service = RetrofitClientInstance.getRetrofitInstance().create(NewsClient.class);

    Call<ResultArticles> call = service.getArticles(options);
    call.enqueue(
        new Callback<ResultArticles>() {
          @Override
          public void onResponse(Call<ResultArticles> call, Response<ResultArticles> response) {
            Log.wtf(TAG, "getEverything()::onResponse(): Has been instantiated");
            if (response.body().getStatus().equals("ok")) {
                List<Article> articles = Arrays.asList(response.body().getArticles());
              callback.onSuccess(articles);
            }
          }

          @Override
          public void onFailure(Call<ResultArticles> call, Throwable t) {
            Log.wtf(TAG, "getEverything()::onFailure(): Has been instantiated");
          }
        });
  }

  public interface retrofitCallback {
    void onSuccess(List<Article> articles);
  }
}
