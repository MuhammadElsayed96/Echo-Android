package com.muhammadelsayed.echo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.muhammadelsayed.echo.model.Article;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplashActivity extends AppCompatActivity {

  private static final String TAG = "SplashActivity";
  private BoolVariable bool = new BoolVariable();
  private boolean f1 = false,
      f2 = false,
      f3 = false,
      f4 = false,
      f5 = false,
      f6 = false,
      f7 = false;
  //      f8 = false,
  //      f9 = false,
  //      f10 = false,
  //      f11 = false,
  //      f12 = false,
  //      f13 = false;
  public static List<Article> leadStoriesList = new ArrayList<>();
  public static List<Article> businessList = new ArrayList<>();
  //  public static List<Article> nationalList = new ArrayList<>();
  //  public static List<Article> worldList = new ArrayList<>();
  //  public static List<Article> politicsList = new ArrayList<>();
  public static List<Article> entertainmentList = new ArrayList<>();
  //  public static List<Article> lifestyleList = new ArrayList<>();
  public static List<Article> healthList = new ArrayList<>();
  public static List<Article> sportList = new ArrayList<>();
  public static List<Article> technologyList = new ArrayList<>();
  public static List<Article> scienceList = new ArrayList<>();
  //  public static List<Article> environmentList = new ArrayList<>();
  //  public static List<Article> travelList = new ArrayList<>();
  private Map<String, Object> options = new HashMap<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.wtf(TAG, "onCreate: has been instantiated");

    bool.setListener(
        new BoolVariable.BoolChangeListener() {
          @Override
          public void onChange() {
            Log.wtf(TAG, "Moving to MainActivity");
            if (bool.aBoolean) {
              Intent intent = new Intent(SplashActivity.this, MainActivity.class);
              startActivity(intent);
              finish();
            }
          }
        });

    // LEAD STORIES
    options.put("apiKey", getResources().getString(R.string.news_api_key1));
    options.put("category", "general");
    //    options.put("pageSize", 50);
    Utils.getTopHeadLines(
        options,
        new Utils.retrofitCallback() {
          @Override
          public void onSuccess(List<Article> articles) {
            leadStoriesList = articles;
            Log.wtf(TAG, "onSuccess: LEAD STORIES = " + leadStoriesList);
            f1 = true;
            bool.aBoolean = f1 && f2 && f3 && f4 && f5 && f6;
          }
        });

    // BUSINESS
    options.clear();
    options.put("apiKey", getResources().getString(R.string.news_api_key1));
    //    options.put("language", "en");
    //    options.put("pageSize", 50);
    options.put("category", "business");

    Utils.getTopHeadLines(
        options,
        new Utils.retrofitCallback() {
          @Override
          public void onSuccess(List<Article> articles) {
            businessList = articles;
            Log.wtf(TAG, "onSuccess: BUSINESS = " + businessList);
            f2 = true;
            bool.aBoolean = f1 && f2 && f3 && f4 && f5 && f6;
          }
        });

    //    // NATIONAL
    //    options.clear();
    //    options.put("apiKey", getResources().getString(R.string.news_api_key1));
    //    options.put("language", "en");
    //    options.put("q", "national");
    //    //    options.put("pageSize", 50);
    //    Utils.getEverything(
    //        options,
    //        new Utils.retrofitCallback() {
    //          @Override
    //          public void onSuccess(List<Article> articles) {
    //            nationalList = articles;
    //            Log.wtf(TAG, "onSuccess: NATIONAL = " + nationalList);
    //            f3 = true;
    //            bool.aBoolean =
    //                f1 && f2 && f3 && f4 && f5 && f6 && f7 && f8 && f9 && f10 && f11 && f12 &&
    // f13;
    //          }
    //        });

    //    // WORLD
    //    options.clear();
    //    options.put("apiKey", getResources().getString(R.string.news_api_key1));
    //    options.put("language", "en");
    //    options.put("q", "world");
    //    //    options.put("pageSize", 50);
    //    Utils.getEverything(
    //        options,
    //        new Utils.retrofitCallback() {
    //          @Override
    //          public void onSuccess(List<Article> articles) {
    //            worldList = articles;
    //            Log.wtf(TAG, "onSuccess: WORLD = " + worldList);
    //            f4 = true;
    //            bool.aBoolean =
    //                f1 && f2 && f3 && f4 && f5 && f6 && f7 && f8 && f9 && f10 && f11 && f12 &&
    // f13;
    //          }
    //        });

    //    // POLITICS
    //    options.clear();
    //    options.put("apiKey", getResources().getString(R.string.news_api_key1));
    //    options.put("language", "en");
    //    options.put("q", "politics");
    //    //    options.put("pageSize", 50);
    //    Utils.getEverything(
    //        options,
    //        new Utils.retrofitCallback() {
    //          @Override
    //          public void onSuccess(List<Article> articles) {
    //            politicsList = articles;
    //            Log.wtf(TAG, "onSuccess: POLITICS = " + politicsList);
    //            f5 = true;
    //            bool.aBoolean =
    //                f1 && f2 && f3 && f4 && f5 && f6 && f7 && f8 && f9 && f10 && f11 && f12 &&
    // f13;
    //          }
    //        });

    // ENTERTAINMENT
    options.clear();
    options.put("apiKey", getResources().getString(R.string.news_api_key1));
    //    options.put("language", "en");
    options.put("category", "entertainment");
    //    options.put("pageSize", 50);
    Utils.getTopHeadLines(
        options,
        new Utils.retrofitCallback() {
          @Override
          public void onSuccess(List<Article> articles) {
            entertainmentList = articles;
            Log.wtf(TAG, "onSuccess: ENTERTAINMENT = " + entertainmentList);
            f3 = true;
            bool.aBoolean = f1 && f2 && f3 && f4 && f5 && f6;
          }
        });

    //    // LIFESTYLE
    //    options.clear();
    //    options.put("apiKey", getResources().getString(R.string.news_api_key1));
    //    options.put("language", "en");
    //    options.put("q", "lifestyle");
    //    //    options.put("pageSize", 50);
    //    Utils.getEverything(
    //        options,
    //        new Utils.retrofitCallback() {
    //          @Override
    //          public void onSuccess(List<Article> articles) {
    //            lifestyleList = articles;
    //            Log.wtf(TAG, "onSuccess: LIFESTYLE = " + lifestyleList);
    //            f7 = true;
    //            bool.aBoolean =
    //                f1 && f2 && f3 && f4 && f5 && f6 && f7 && f8 && f9 && f10 && f11 && f12 &&
    // f13;
    //          }
    //        });

    // HEALTH
    options.clear();
    options.put("apiKey", getResources().getString(R.string.news_api_key1));
    options.put("language", "en");
    options.put("category", "health");
    //    options.put("pageSize", 50);
    Utils.getTopHeadLines(
        options,
        new Utils.retrofitCallback() {
          @Override
          public void onSuccess(List<Article> articles) {
            healthList = articles;
            Log.wtf(TAG, "onSuccess: HEALTH = " + healthList);
            f4 = true;
            bool.aBoolean = f1 && f2 && f3 && f4 && f5 && f6;
          }
        });

    // SPORT
    options.clear();
    options.put("apiKey", getResources().getString(R.string.news_api_key1));
    options.put("language", "en");
    options.put("category", "sports");
    //    options.put("pageSize", 50);
    Utils.getTopHeadLines(
        options,
        new Utils.retrofitCallback() {
          @Override
          public void onSuccess(List<Article> articles) {
            sportList = articles;
            Log.wtf(TAG, "onSuccess: SPORT = " + sportList);
            f5 = true;
            bool.aBoolean = f1 && f2 && f3 && f4 && f5 && f6;
          }
        });

    // TECHNOLOGY
    options.clear();
    options.put("apiKey", getResources().getString(R.string.news_api_key1));
    //    options.put("language", "en");
    options.put("category", "technology");
    //    options.put("pageSize", 50);
    Utils.getTopHeadLines(
        options,
        new Utils.retrofitCallback() {
          @Override
          public void onSuccess(List<Article> articles) {
            technologyList = articles;
            Log.wtf(TAG, "onSuccess: TECHNOLOGY = " + technologyList);
            f6 = true;
            bool.aBoolean = f1 && f2 && f3 && f4 && f5 && f6 && f7;
          }
        });

    // SCIENCE
    options.clear();
    options.put("apiKey", getResources().getString(R.string.news_api_key1));
    options.put("language", "en");
    options.put("category", "science");
    //    options.put("pageSize", 50);
    Utils.getTopHeadLines(
        options,
        new Utils.retrofitCallback() {
          @Override
          public void onSuccess(List<Article> articles) {
            scienceList = articles;
            Log.wtf(TAG, "onSuccess: SCIENCE = " + scienceList);
            f7 = true;
            bool.aBoolean = f1 && f2 && f3 && f4 && f5 && f6 && f7;
          }
        });

    //    // ENVIRONMENT
    //    options.clear();
    //    options.put("apiKey", getResources().getString(R.string.news_api_key1));
    //    options.put("language", "en");
    //    options.put("q", "environment");
    //    //    options.put("pageSize", 50);
    //    Utils.getEverything(
    //        options,
    //        new Utils.retrofitCallback() {
    //          @Override
    //          public void onSuccess(List<Article> articles) {
    //            environmentList = articles;
    //            Log.wtf(TAG, "onSuccess: ENVIRONMENT = " + environmentList);
    //            f12 = true;
    //            bool.aBoolean =
    //                f1 && f2 && f3 && f4 && f5 && f6 && f7 && f8 && f9 && f10 && f11 && f12 &&
    // f13;
    //          }
    //        });
    //
    //    // TRAVEL
    //    options.clear();
    //    options.put("apiKey", getResources().getString(R.string.news_api_key1));
    //    options.put("language", "en");
    //    options.put("q", "travel");
    //    //    options.put("pageSize", 50);
    //    Utils.getEverything(
    //        options,
    //        new Utils.retrofitCallback() {
    //          @Override
    //          public void onSuccess(List<Article> articles) {
    //            travelList = articles;
    //            Log.wtf(TAG, "onSuccess: TRAVEL = " + travelList);
    //            f13 = true;
    //            bool.aBoolean =
    //                f1 && f2 && f3 && f4 && f5 && f6 && f7 && f8 && f9 && f10 && f11 && f12 &&
    // f13;
    //          }
    //        });
  }

  private static class BoolVariable {
    private boolean aBoolean = false;
    private BoolChangeListener listener;

    public boolean isBoolean() {
      return aBoolean;
    }

    public void setBoolean(boolean aBoolean) {
      this.aBoolean = aBoolean;
    }

    public BoolChangeListener getListener() {
      return listener;
    }

    public void setListener(BoolChangeListener listener) {
      this.listener = listener;
    }

    public interface BoolChangeListener {
      void onChange();
    }
  }
}
