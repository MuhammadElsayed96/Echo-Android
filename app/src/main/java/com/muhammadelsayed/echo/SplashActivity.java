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
        Log.wtf(TAG, "onCreate: started !!");




        // BUSINESS
        options.put("apiKey", getResources().getString(R.string.news_api_key));
//        options.put("language", "en");
        options.put("category", "business");

        Utils.getTopHeadLines(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                businessList = articles;
                Log.wtf(TAG, "onSuccess: BUSINESS = " + businessList);
            }
        });

        // LEAD STORIES
        options.clear();
        options.put("apiKey", getResources().getString(R.string.news_api_key));
        options.put("language", "en");

        Utils.getTopHeadLines(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                leadStoriesList = articles;

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



        // NATIONAL
        options.clear();
        options.put("apiKey", getResources().getString(R.string.news_api_key));
        options.put("language", "en");
        options.put("q", "national");

        Utils.getEverything(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                nationalList = articles;
            }
        });


        // WORLD
        options.clear();
        options.put("apiKey", getResources().getString(R.string.news_api_key));
        options.put("language", "en");
        options.put("q", "world");

        Utils.getEverything(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                worldList = articles;
            }
        });

        // POLITICS
        options.clear();
        options.put("apiKey", getResources().getString(R.string.news_api_key));
        options.put("language", "en");
        options.put("q", "politics");

        Utils.getEverything(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                politicsList = articles;
            }
        });

        // ENTERTAINMENT
        options.clear();
        options.put("apiKey", getResources().getString(R.string.news_api_key));
        options.put("language", "en");
        options.put("category", "entertainment");

        Utils.getTopHeadLines(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                entertainmentList = articles;
            }
        });

        // LIFESTYLE
        options.clear();
        options.put("apiKey", getResources().getString(R.string.news_api_key));
        options.put("language", "en");
        options.put("q", "lifestyle");

        Utils.getTopHeadLines(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                lifestyleList = articles;
            }
        });

        // HEALTH
        options.clear();
        options.put("apiKey", getResources().getString(R.string.news_api_key));
        options.put("language", "en");
        options.put("category", "health");

        Utils.getTopHeadLines(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                healthList = articles;
            }
        });

        // SPORT
        options.clear();
        options.put("apiKey", getResources().getString(R.string.news_api_key));
        options.put("language", "en");
        options.put("category", "sports");

        Utils.getTopHeadLines(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                sportList = articles;
            }
        });

        // TECHNOLOGY
        options.clear();
        options.put("apiKey", getResources().getString(R.string.news_api_key));
        options.put("language", "en");
        options.put("category", "technology");

        Utils.getTopHeadLines(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                technologyList = articles;
            }
        });

        // SCIENCE
        options.clear();
        options.put("apiKey", getResources().getString(R.string.news_api_key));
        options.put("language", "en");
        options.put("category", "science");

        Utils.getTopHeadLines(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                scienceList = articles;
            }
        });

        // ENVIRONMENT
        options.clear();
        options.put("apiKey", getResources().getString(R.string.news_api_key));
        options.put("language", "en");
        options.put("q", "environment");

        Utils.getEverything(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                environmentList = articles;
            }
        });

        // TRAVEL
        options.clear();
        options.put("apiKey", getResources().getString(R.string.news_api_key));
        options.put("language", "en");
        options.put("q", "travel");

        Utils.getEverything(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                travelList = articles;
            }
        });



    }

}
