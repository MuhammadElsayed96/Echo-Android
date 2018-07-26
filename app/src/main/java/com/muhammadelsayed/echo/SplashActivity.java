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

  public static List<Article> leadStoriesList = new ArrayList<>();
  public static List<Article> businessList = new ArrayList<>();
  public static List<Article> nationalList = new ArrayList<>();
  public static List<Article> worldList = new ArrayList<>();
  public static List<Article> politicsList = new ArrayList<>();
  public static List<Article> entertainmentList = new ArrayList<>();
  public static List<Article> lifestyleList = new ArrayList<>();
  public static List<Article> healthList = new ArrayList<>();
  public static List<Article> sportList = new ArrayList<>();
  public static List<Article> technologyList = new ArrayList<>();
  public static List<Article> scienceList = new ArrayList<>();
  public static List<Article> environmentList = new ArrayList<>();
  public static List<Article> travelList = new ArrayList<>();
  private Map<String, Object> options = new HashMap<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.wtf(TAG, "onCreate: has been instantiated");

    // LEAD STORIES
    options.put("apiKey", getResources().getString(R.string.news_api_key1));
    options.put("language", "en");
      options.put("pageSize", 100);
    Utils.getTopHeadLines(
        options,
        new Utils.retrofitCallback() {
          @Override
          public void onSuccess(List<Article> articles) {
            leadStoriesList = articles;
            Log.wtf(TAG, "onSuccess: LEAD STORIES = " + leadStoriesList);
          }
        });

    // BUSINESS
    options.clear();
    options.put("apiKey", getResources().getString(R.string.news_api_key2));
    options.put("language", "en");
      options.put("pageSize", 100);
    options.put("category", "business");

    Utils.getTopHeadLines(
        options,
        new Utils.retrofitCallback() {
          @Override
          public void onSuccess(List<Article> articles) {
            businessList = articles;
            Log.wtf(TAG, "onSuccess: BUSINESS = " + businessList);
          }
        });

    // NATIONAL
    options.clear();
    options.put("apiKey", getResources().getString(R.string.news_api_key3));
    options.put("language", "en");
    options.put("q", "national");
      options.put("pageSize", 100);
    Utils.getEverything(
        options,
        new Utils.retrofitCallback() {
          @Override
          public void onSuccess(List<Article> articles) {
            nationalList = articles;
            Log.wtf(TAG, "onSuccess: NATIONAL = " + nationalList);
          }
        });

    // WORLD
    options.clear();
    options.put("apiKey", getResources().getString(R.string.news_api_key4));
    options.put("language", "en");
    options.put("q", "world");
      options.put("pageSize", 100);
    Utils.getEverything(
        options,
        new Utils.retrofitCallback() {
          @Override
          public void onSuccess(List<Article> articles) {
            worldList = articles;
            Log.wtf(TAG, "onSuccess: WORLD = " + worldList);
          }
        });

    // POLITICS
    options.clear();
    options.put("apiKey", getResources().getString(R.string.news_api_key5));
    options.put("language", "en");
    options.put("q", "politics");
      options.put("pageSize", 100);
    Utils.getEverything(
        options,
        new Utils.retrofitCallback() {
          @Override
          public void onSuccess(List<Article> articles) {
            politicsList = articles;
            Log.wtf(TAG, "onSuccess: POLITICS = " + politicsList);
          }
        });

    // ENTERTAINMENT
    options.clear();
    options.put("apiKey", getResources().getString(R.string.news_api_key6));
    options.put("language", "en");
    options.put("category", "entertainment");
      options.put("pageSize", 100);
    Utils.getTopHeadLines(
        options,
        new Utils.retrofitCallback() {
          @Override
          public void onSuccess(List<Article> articles) {
            entertainmentList = articles;
            Log.wtf(TAG, "onSuccess: ENTERTAINMENT = " + entertainmentList);
          }
        });

    // LIFESTYLE
    options.clear();
    options.put("apiKey", getResources().getString(R.string.news_api_key7));
    options.put("language", "en");
    options.put("q", "lifestyle");
      options.put("pageSize", 100);
    Utils.getTopHeadLines(
        options,
        new Utils.retrofitCallback() {
          @Override
          public void onSuccess(List<Article> articles) {
            lifestyleList = articles;
            Log.wtf(TAG, "onSuccess: LIFESTYLE = " + lifestyleList);
          }
        });

    // HEALTH
    options.clear();
    options.put("apiKey", getResources().getString(R.string.news_api_key8));
    options.put("language", "en");
    options.put("category", "health");
      options.put("pageSize", 100);
    Utils.getTopHeadLines(
        options,
        new Utils.retrofitCallback() {
          @Override
          public void onSuccess(List<Article> articles) {
            healthList = articles;
            Log.wtf(TAG, "onSuccess: HEALTH = " + healthList);
          }
        });

    // SPORT
    options.clear();
    options.put("apiKey", getResources().getString(R.string.news_api_key9));
    options.put("language", "en");
    options.put("category", "sports");
      options.put("pageSize", 100);
    Utils.getTopHeadLines(
        options,
        new Utils.retrofitCallback() {
          @Override
          public void onSuccess(List<Article> articles) {
            sportList = articles;
            Log.wtf(TAG, "onSuccess: SPORT = " + sportList);
          }
        });

    // TECHNOLOGY
    options.clear();
    options.put("apiKey", getResources().getString(R.string.news_api_key10));
    options.put("language", "en");
    options.put("category", "technology");
      options.put("pageSize", 100);
    Utils.getTopHeadLines(
        options,
        new Utils.retrofitCallback() {
          @Override
          public void onSuccess(List<Article> articles) {
            technologyList = articles;
            Log.wtf(TAG, "onSuccess: TECHNOLOGY = " + technologyList);
          }
        });

    // SCIENCE
    options.clear();
    options.put("apiKey", getResources().getString(R.string.news_api_key11));
    options.put("language", "en");
    options.put("category", "science");
      options.put("pageSize", 100);
    Utils.getTopHeadLines(
        options,
        new Utils.retrofitCallback() {
          @Override
          public void onSuccess(List<Article> articles) {
            scienceList = articles;
            Log.wtf(TAG, "onSuccess: SCIENCE = " + scienceList);
          }
        });

    // ENVIRONMENT
    options.clear();
    options.put("apiKey", getResources().getString(R.string.news_api_key12));
    options.put("language", "en");
    options.put("q", "environment");
      options.put("pageSize", 100);
    Utils.getEverything(
        options,
        new Utils.retrofitCallback() {
          @Override
          public void onSuccess(List<Article> articles) {
            environmentList = articles;
            Log.wtf(TAG, "onSuccess: ENVIRONMENT = " + environmentList);
          }
        });

    // TRAVEL
    options.clear();
    options.put("apiKey", getResources().getString(R.string.news_api_key13));
    options.put("language", "en");
    options.put("q", "travel");
    options.put("pageSize", 100);
    Utils.getEverything(
        options,
        new Utils.retrofitCallback() {
          @Override
          public void onSuccess(List<Article> articles) {
            travelList = articles;
            Log.wtf(TAG, "onSuccess: TRAVEL = " + travelList);
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
          }
        });
  }
}
