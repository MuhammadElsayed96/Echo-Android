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

    Map<String, Object> options = new HashMap<>();

    public static List<Article> mBusinessArticleList = new ArrayList<>();
    public static List<Article> mEnvironmentArticleList = new ArrayList<>();
    public static List<Article> mTechnologyArticleList = new ArrayList<>();
    public static List<Article> mSportArticleList = new ArrayList<>();
    public static List<Article> mScienceArticleList = new ArrayList<>();
    public static List<Article> mArtAndDesignArticleList = new ArrayList<>();
    public static List<Article> mAustraliaNewsArticleList = new ArrayList<>();
    public static List<Article> mUkNewsArticleList = new ArrayList<>();
    public static List<Article> mUsNewsArticleList = new ArrayList<>();
    public static List<Article> mInternationalArticleList = new ArrayList<>();
    public static List<Article> mBooksArticleList = new ArrayList<>();
    public static List<Article> mCultureArticleList = new ArrayList<>();
    public static List<Article> mEducationArticleList = new ArrayList<>();
    public static List<Article> mFashionArticleList = new ArrayList<>();
    public static List<Article> mFilmArticleList = new ArrayList<>();
    public static List<Article> mFootballArticleList = new ArrayList<>();
    public static List<Article> mLawArticleList = new ArrayList<>();
    public static List<Article> mLifestyleArticleList = new ArrayList<>();
    public static List<Article> mMediaArticleList = new ArrayList<>();
    public static List<Article> mMoneyArticleList = new ArrayList<>();
    public static List<Article> mMusicArticleList = new ArrayList<>();
    public static List<Article> mPoliticsArticleList = new ArrayList<>();
    public static List<Article> mSocietyArticleList = new ArrayList<>();
    public static List<Article> mTravelArticleList = new ArrayList<>();
    public static List<Article> mTvAndRadioArticleList = new ArrayList<>();
    public static List<Article> mWeatherArticleList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.wtf(TAG, "onCreate: has been instantiated");


        // BUSINESS
        options.put("section", "business");
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", 1);
        options.put("page-size", 20);
        options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.d(TAG, "onSuccess: BUSINESS = " + articles);
                mBusinessArticleList = articles;

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: BUSINESS FAILED !!");
            }
        });

        // CULTURE
        options.put("section", "culture");
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", 1);
        options.put("page-size", 20);
        options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.d(TAG, "onSuccess: CULTURE = " + articles);
                mCultureArticleList = articles;
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: CULTURE FAILED !!");
            }
        });

        // ART & DESIGN
        options.clear();
        options.put("section", "artanddesign");
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", 1);
        options.put("page-size", 20);
        options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.d(TAG, "onSuccess: ART & DESIGN = " + articles);
                mArtAndDesignArticleList = articles;
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: ART & DESIGN FAILED !!");
            }
        });


        // ENVIRONMENT
        options.clear();
        options.put("section", "environment");
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", 1);
        options.put("page-size", 20);
        options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.d(TAG, "onSuccess: ENVIRONMENT = " + articles);
                mEnvironmentArticleList = articles;
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: ENVIRONMENT FAILED !!");
            }
        });

        // BOOKS
        options.clear();
        options.put("section", "environment");
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", 1);
        options.put("page-size", 20);
        options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.d(TAG, "onSuccess: BOOKS = " + articles);
                mBooksArticleList = articles;
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: BOOKS FAILED !!");
            }
        });

        // AUSTRALIA NEWS
        options.clear();
        options.put("section", "australia-news");
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", 1);
        options.put("page-size", 20);
        options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.d(TAG, "onSuccess: AUSTRALIA NEWS = " + articles);
                mAustraliaNewsArticleList = articles;
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: AUSTRALIA NEWS FAILED !!");
            }
        });

        // UK NEWS
        options.clear();
        options.put("section", "uk-news");
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", 1);
        options.put("page-size", 20);
        options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.d(TAG, "onSuccess: UK NEWS = " + articles);
                mUkNewsArticleList = articles;
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: UK NEWS FAILED !!");
            }
        });

        // US NEWS
        options.clear();
        options.put("section", "us-news");
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", 1);
        options.put("page-size", 20);
        options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.d(TAG, "onSuccess: US NEWS = " + articles);
                mUsNewsArticleList = articles;
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: US NEWS FAILED !!");
            }
        });

        // NEWS
        options.clear();
        options.put("section", "news");
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", 1);
        options.put("page-size", 20);
        options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.d(TAG, "onSuccess: NEWS = " + articles);
                mInternationalArticleList = articles;
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: NEWS FAILED !!");
            }
        });

        // EDUCATION
        options.clear();
        options.put("section", "education");
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", 1);
        options.put("page-size", 20);
        options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.d(TAG, "onSuccess: EDUCATION = " + articles);
                mEducationArticleList = articles;
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: EDUCATION FAILED !!");
            }
        });

        // FASHION
        options.clear();
        options.put("section", "fashion");
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", 1);
        options.put("page-size", 20);
        options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.d(TAG, "onSuccess: FASHION = " + articles);
                mFashionArticleList = articles;
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: FASHION FAILED !!");
            }
        });

        // FILM
        options.clear();
        options.put("section", "film");
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", 1);
        options.put("page-size", 20);
        options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.d(TAG, "onSuccess: FILM = " + articles);
                mFilmArticleList = articles;
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: FILM FAILED !!");
            }
        });

        // FOOTBALL
        options.clear();
        options.put("section", "football");
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", 1);
        options.put("page-size", 20);
        options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.d(TAG, "onSuccess: FOOTBALL = " + articles);
                mFootballArticleList = articles;
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: FOOTBALL FAILED !!");
            }
        });

        // LAW
        options.clear();
        options.put("section", "law");
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", 1);
        options.put("page-size", 20);
        options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.d(TAG, "onSuccess: LAW = " + articles);
                mLawArticleList = articles;
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: LAW FAILED !!");
            }
        });

        // LIFESTYLE
        options.clear();
        options.put("section", "lifeandstyle");
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", 1);
        options.put("page-size", 20);
        options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.d(TAG, "onSuccess: LIFESTYLE = " + articles);
                mLifestyleArticleList = articles;
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: LIFESTYLE FAILED !!");
            }
        });

        // MEDIA
        options.clear();
        options.put("section", "media");
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", 1);
        options.put("page-size", 20);
        options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.d(TAG, "onSuccess: MEDIA = " + articles);
                mMediaArticleList = articles;
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: MEDIA FAILED !!");
            }
        });

        // MONEY
        options.clear();
        options.put("section", "money");
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", 1);
        options.put("page-size", 20);
        options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.d(TAG, "onSuccess: MONEY = " + articles);
                mMoneyArticleList = articles;
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: MONEY FAILED !!");
            }
        });

        // MUSIC
        options.clear();
        options.put("section", "music");
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", 1);
        options.put("page-size", 20);
        options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.d(TAG, "onSuccess: MUSIC = " + articles);
                mMusicArticleList = articles;
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: MUSIC FAILED !!");
            }
        });

        // POLITICS
        options.clear();
        options.put("section", "politics");
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", 1);
        options.put("page-size", 20);
        options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.d(TAG, "onSuccess: POLITICS = " + articles);
                mPoliticsArticleList = articles;
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: POLITICS FAILED !!");
            }
        });

        // SCIENCE
        options.clear();
        options.put("section", "science");
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", 1);
        options.put("page-size", 20);
        options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.d(TAG, "onSuccess: SCIENCE = " + articles);
                mScienceArticleList = articles;
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: SCIENCE FAILED !!");
            }
        });

        // SOCIETY
        options.clear();
        options.put("section", "society");
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", 1);
        options.put("page-size", 20);
        options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.d(TAG, "onSuccess: SOCIETY = " + articles);
                mSocietyArticleList = articles;
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: SOCIETY FAILED !!");
            }
        });

        // SPORT
        options.clear();
        options.put("section", "sport");
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", 1);
        options.put("page-size", 20);
        options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.d(TAG, "onSuccess: SPORT = " + articles);
                mSportArticleList = articles;
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: SPORT FAILED !!");
            }
        });

        // TECHNOLOGY
        options.clear();
        options.put("section", "technology");
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", 1);
        options.put("page-size", 20);
        options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.d(TAG, "onSuccess: TECHNOLOGY = " + articles);
                mTechnologyArticleList = articles;
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: TECHNOLOGY FAILED !!");
            }
        });

        // TRAVEL
        options.clear();
        options.put("section", "travel");
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", 1);
        options.put("page-size", 20);
        options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.d(TAG, "onSuccess: TRAVEL = " + articles);
                mTravelArticleList = articles;
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: TRAVEL FAILED !!");
            }
        });

        // TV & RADIO
        options.clear();
        options.put("section", "tv-and-radio");
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", 1);
        options.put("page-size", 20);
        options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.d(TAG, "onSuccess: TV & RADIO = " + articles);
                mTvAndRadioArticleList = articles;
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: TV & RADIO FAILED !!");
            }
        });

        // WEATHER
        options.clear();
        options.put("section", "weather");
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", 1);
        options.put("page-size", 20);
        options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.d(TAG, "onSuccess: WEATHER = " + articles);
                mWeatherArticleList = articles;
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: WEATHER FAILED !!");
            }
        });

    }

}

