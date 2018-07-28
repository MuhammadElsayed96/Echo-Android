package com.muhammadelsayed.echo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.muhammadelsayed.echo.model.Article;
import com.muhammadelsayed.echo.model.Source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplashActivity extends AppCompatActivity {

  private static final String TAG = "SplashActivity";
  public static List<Source> mSourcesList = new ArrayList<>();
  public static List<Source> generalList = new ArrayList<>();
  public static List<Source> businessList = new ArrayList<>();
  public static List<Source> entertainmentList = new ArrayList<>();
  public static List<Source> healthList = new ArrayList<>();
  public static List<Source> sportsList = new ArrayList<>();
  public static List<Source> technologyList = new ArrayList<>();
  public static List<Source> scienceList = new ArrayList<>();
  private Map<String, Object> options = new HashMap<>();
  public static List<Article> mBusinessArticleList = new ArrayList<>();
  public static List<Article> mTechnologyArticleList = new ArrayList<>();
  public static List<Article> mSportArticleList = new ArrayList<>();
  public static List<Article> mScienceArticleList = new ArrayList<>();
  public static List<Article> mLeadStoriesArticleList = new ArrayList<>();
  public static List<Article> mHealthArticleList = new ArrayList<>();
  public static List<Article> mEntertainmentArticleList = new ArrayList<>();
  private String entertainmentSources;
  private String healthSources;
  private String leadStoriesSources;
  private String scienceSources;
  private String sportSources;
  private String businessSources;
  private String technologySources;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.wtf(TAG, "onCreate: has been instantiated");
    healthSources = "";
    businessSources = "";
    entertainmentSources = "";
    leadStoriesSources = "";
    scienceSources = "";
    technologySources = "";
    sportSources = "";
    // LEAD STORIES
    options.put("apiKey", getResources().getString(R.string.news_api_key1));
    options.put("language", "en");
    options.put("category", "general");
    Utils.getSources(
        options,
        new Utils.retrofitCallbackSource() {
          @Override
          public void onSuccessSource(List<Source> source) {
            generalList = source;
            Log.wtf(TAG, "onSuccess: General = " + generalList);
            mSourcesList.addAll(generalList);
            loadLeadStoriesSources();
            Map<String, Object> options = new HashMap<>();
            options.put("apiKey", getResources().getString(R.string.news_api_key2));
            options.put("sources", leadStoriesSources);
            Utils.getTopHeadLines(
                options,
                new Utils.retrofitCallbackArticle() {
                  @Override
                  public void onSuccessArticle(List<Article> articles) {
                    Log.wtf(TAG, "onSuccessArticle()::LeadStories");
                    mLeadStoriesArticleList = articles;
                  }
                });
          }
        });

    // BUSINESS
    options.clear();
    options.put("apiKey", getResources().getString(R.string.news_api_key3));
    options.put("language", "en");
    options.put("category", "business");
    Utils.getSources(
        options,
        new Utils.retrofitCallbackSource() {
          @Override
          public void onSuccessSource(List<Source> sources) {
            businessList = sources;
            Log.wtf(TAG, "onSuccess: BUSINESS = " + businessList);
            mSourcesList.addAll(businessList);
            loadBusinessSources();
            Map<String, Object> options = new HashMap<>();
            options.put("apiKey", getResources().getString(R.string.news_api_key4));
            options.put("sources", businessSources);
            Utils.getTopHeadLines(
                options,
                new Utils.retrofitCallbackArticle() {
                  @Override
                  public void onSuccessArticle(List<Article> articles) {
                    Log.wtf(TAG, "onSuccessArticle()::Business");
                    mBusinessArticleList = articles;
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                  }
                });
          }
        });

    // TECHNOLOGY
    options.clear();
    options.put("apiKey", getResources().getString(R.string.news_api_key5));
    options.put("language", "en");
    options.put("category", "technology");
    Utils.getSources(
        options,
        new Utils.retrofitCallbackSource() {
          @Override
          public void onSuccessSource(List<Source> source) {
            technologyList = source;
            Log.wtf(TAG, "onSuccess: TECHNOLOGY = " + technologyList);
            mSourcesList.addAll(technologyList);
            loadTechnologySources();
            Map<String, Object> options = new HashMap<>();
            options.put("apiKey", getResources().getString(R.string.news_api_key6));
            options.put("sources", technologySources);
            Utils.getTopHeadLines(
                options,
                new Utils.retrofitCallbackArticle() {
                  @Override
                  public void onSuccessArticle(List<Article> articles) {
                    Log.wtf(TAG, "onSuccessArticle()::Technology");
                    mTechnologyArticleList = articles;
                  }
                });
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
            mSourcesList.addAll(entertainmentList);
            loadEntertainmentSources();
            Map<String, Object> options = new HashMap<>();
            options.put("apiKey", getResources().getString(R.string.news_api_key7));
            options.put("sources", entertainmentSources);
            Utils.getTopHeadLines(
                options,
                new Utils.retrofitCallbackArticle() {
                  @Override
                  public void onSuccessArticle(List<Article> articles) {
                    Log.wtf(TAG, "onSuccessArticle()::Entertainment");
                    mEntertainmentArticleList = articles;
                  }
                });
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
            mSourcesList.addAll(healthList);
            loadHealthSources();
            Map<String, Object> options = new HashMap<>();
            options.put("apiKey", getResources().getString(R.string.news_api_key9));
            options.put("sources", healthSources);
            Utils.getTopHeadLines(
                options,
                new Utils.retrofitCallbackArticle() {
                  @Override
                  public void onSuccessArticle(List<Article> articles) {
                    Log.wtf(TAG, "onSuccessArticle()::Health");
                    mHealthArticleList = articles;
                  }
                });
          }
        });

    // SPORT
    options.clear();
    options.put("apiKey", getResources().getString(R.string.news_api_key10));
    options.put("language", "en");
    options.put("category", "sports");
    Utils.getSources(
        options,
        new Utils.retrofitCallbackSource() {
          @Override
          public void onSuccessSource(List<Source> source) {
            sportsList = source;
            Log.wtf(TAG, "onSuccess: SPORTS = " + sportsList);
            mSourcesList.addAll(sportsList);
            loadSportSources();
            Map<String, Object> options = new HashMap<>();
            options.put("apiKey", getResources().getString(R.string.news_api_key11));
            options.put("sources", sportSources);
            Utils.getTopHeadLines(
                options,
                new Utils.retrofitCallbackArticle() {
                  @Override
                  public void onSuccessArticle(List<Article> articles) {
                    Log.wtf(TAG, "onSuccessArticle()::Sport");
                    mSportArticleList = articles;
                  }
                });
          }
        });

    // SCIENCE
    options.clear();
    options.put("apiKey", getResources().getString(R.string.news_api_key12));
    options.put("language", "en");
    options.put("category", "science");
    Utils.getSources(
        options,
        new Utils.retrofitCallbackSource() {
          @Override
          public void onSuccessSource(List<Source> sources) {
            scienceList = sources;
            Log.wtf(TAG, "onSuccess: science = " + scienceList);
            mSourcesList.addAll(scienceList);
            loadScienceSources();
            Map<String, Object> options = new HashMap<>();
            options.put("apiKey", getResources().getString(R.string.news_api_key13));
            options.put("sources", scienceSources);
            Utils.getTopHeadLines(
                options,
                new Utils.retrofitCallbackArticle() {
                  @Override
                  public void onSuccessArticle(List<Article> articles) {
                    Log.wtf(TAG, "onSuccessArticle()::Science");
                    mScienceArticleList = articles;
                  }
                });
          }
        });
  }

  private void loadBusinessSources() {
    Log.wtf(TAG, "loadBusinessSources() has been instantiated");
    for (Source source : businessList) {
      businessSources = businessSources.concat(source.getId() + ",");
    }
    businessSources.replaceAll(",$", "");
  }

  private void loadTechnologySources() {
    Log.wtf(TAG, "loadTechnologySources() has been instantiated");
    for (Source source : technologyList) {
      technologySources = technologySources.concat(source.getId() + ",");
    }
    technologySources.replaceAll(",$", "");
  }

  private void loadSportSources() {
    Log.wtf(TAG, "loadSportSources() has been instantiated");
    for (Source source : sportsList) {
      sportSources = sportSources.concat(source.getId() + ",");
    }
    sportSources.replaceAll(",$", "");
  }

  private void loadScienceSources() {
    Log.wtf(TAG, "loadScienceSources() has been instantiated");
    for (Source source : scienceList) {
      scienceSources = scienceSources.concat(source.getId() + ",");
    }
    scienceSources.replaceAll(",$", "");
  }

  private void loadLeadStoriesSources() {
    Log.wtf(TAG, "loadLeadStoriesSources() has been instantiated");
    for (Source source : generalList) {
      leadStoriesSources = leadStoriesSources.concat(source.getId() + ",");
    }
    leadStoriesSources.replaceAll(",$", "");
  }

  private void loadHealthSources() {
    Log.wtf(TAG, "loadHealthSources() has been instantiated");
    for (Source source : healthList) {
      healthSources = healthSources.concat(source.getId() + ",");
    }
    healthSources.replaceAll(",$", "");
  }

  private void loadEntertainmentSources() {
    Log.wtf(TAG, "loadEntertainmentSources() has been instantiated");
    for (Source source : entertainmentList) {
      entertainmentSources = entertainmentSources.concat(source.getId() + ",");
    }
    entertainmentSources.replaceAll(",$", "");
  }
}
