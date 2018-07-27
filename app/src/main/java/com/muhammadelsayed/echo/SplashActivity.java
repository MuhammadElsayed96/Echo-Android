package com.muhammadelsayed.echo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.muhammadelsayed.echo.model.Source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplashActivity extends AppCompatActivity {

  private static final String TAG = "SplashActivity";

  public static List<Source> generalList = new ArrayList<>();
  public static List<Source> businessList = new ArrayList<>();
  public static List<Source> entertainmentList = new ArrayList<>();
  public static List<Source> healthList = new ArrayList<>();
  public static List<Source> sportsList = new ArrayList<>();
  public static List<Source> technologyList = new ArrayList<>();
  public static List<Source> scienceList = new ArrayList<>();
  private Map<String, Object> options = new HashMap<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.wtf(TAG, "onCreate: has been instantiated");

    // LEAD STORIES
    options.put("apiKey", getResources().getString(R.string.news_api_key1));
    options.put("language", "en");
    Utils.getSources(
        options,
        new Utils.retrofitCallbackSource() {
          @Override
          public void onSuccessSource(List<Source> source) {
            generalList = source;
            Log.wtf(TAG, "onSuccess: General = " + generalList);
          }
        });

    // BUSINESS
    options.clear();
    options.put("apiKey", getResources().getString(R.string.news_api_key2));
    options.put("language", "en");
    options.put("category", "business");

    Utils.getSources(
        options,
        new Utils.retrofitCallbackSource() {
          @Override
          public void onSuccessSource(List<Source> sources) {
            businessList = sources;
            Log.wtf(TAG, "onSuccess: BUSINESS = " + businessList);
          }
        });

    // ENTERTAINMENT
    options.clear();
    options.put("apiKey", getResources().getString(R.string.news_api_key6));
    options.put("language", "en");
    options.put("category", "entertainment");
    Utils.getSources(
        options,
        new Utils.retrofitCallbackSource() {
          @Override
          public void onSuccessSource(List<Source> source) {
            entertainmentList = source;
            Log.wtf(TAG, "onSuccess: ENTERTAINMENT = " + entertainmentList);
          }
        });

    // HEALTH
    options.clear();
    options.put("apiKey", getResources().getString(R.string.news_api_key8));
    options.put("language", "en");
    options.put("category", "health");
    Utils.getSources(
        options,
        new Utils.retrofitCallbackSource() {
          @Override
          public void onSuccessSource(List<Source> source) {
            healthList = source;
            Log.wtf(TAG, "onSuccess: HEALTH = " + healthList);
          }
        });

    // SPORT
    options.clear();
    options.put("apiKey", getResources().getString(R.string.news_api_key9));
    options.put("language", "en");
    options.put("category", "sports");
    Utils.getSources(
        options,
        new Utils.retrofitCallbackSource() {
          @Override
          public void onSuccessSource(List<Source> source) {
            sportsList = source;
            Log.wtf(TAG, "onSuccess: SPORTS = " + sportsList);
          }
        });

    // TECHNOLOGY
    options.clear();
    options.put("apiKey", getResources().getString(R.string.news_api_key10));
    options.put("language", "en");
    options.put("category", "technology");
    Utils.getSources(
        options,
        new Utils.retrofitCallbackSource() {
          @Override
          public void onSuccessSource(List<Source> source) {
            technologyList = source;
            Log.wtf(TAG, "onSuccess: TECHNOLOGY = " + technologyList);
          }
        });

    // SCIENCE
    options.clear();
    options.put("apiKey", getResources().getString(R.string.news_api_key11));
    options.put("language", "en");
    options.put("category", "science");
    Utils.getSources(
        options,
        new Utils.retrofitCallbackSource() {
          @Override
          public void onSuccessSource(List<Source> sources) {
            scienceList = sources;
            Log.wtf(TAG, "onSuccess: science = " + scienceList);

            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
          }
        });
  }
}
