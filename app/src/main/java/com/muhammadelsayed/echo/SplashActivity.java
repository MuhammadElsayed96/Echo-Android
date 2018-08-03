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
    Map<String, Object> options = new HashMap<>();
    boolean bool, f1 = false, f2 = false, f3 = false, f4 = false, f5 = false, f6 = false, f7 = false, f8 = false,
            f9 = false, f10 = false, f11 = false, f12 = false, f13 = false, f14 = false, f15 = false, f16 = false,
            f17 = false, f18 = false, f19 = false, f20 = false, f21 = false, f22 = false, f23 = false, f24 = false,
            f25 = false, f26 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.wtf(TAG, "onCreate: has been instantiated");
        bool = false;

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
                Log.wtf(TAG, "onSuccess: BUSINESS = " + articles);
                mBusinessArticleList = articles;
                f1 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure: BUSINESS FAILED !!");
            }
        });

        // CULTURE
        options.put("section", "culture");

        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: CULTURE = " + articles);
                mCultureArticleList = articles;
                f2 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure: CULTURE FAILED !!");
            }
        });

        // ART & DESIGN
        options.put("section", "artanddesign");

        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: ART & DESIGN = " + articles);
                mArtAndDesignArticleList = articles;
                f3 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure: ART & DESIGN FAILED !!");
            }
        });


        // ENVIRONMENT
        options.put("section", "environment");

        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: ENVIRONMENT = " + articles);
                mEnvironmentArticleList = articles;
                f4 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure: ENVIRONMENT FAILED !!");
            }
        });

        // BOOKS
        options.put("section", "environment");

        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: BOOKS = " + articles);
                mBooksArticleList = articles;
                f5 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure: BOOKS FAILED !!");
            }
        });

        // AUSTRALIA NEWS
        options.put("section", "australia-news");

        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: AUSTRALIA NEWS = " + articles);
                mAustraliaNewsArticleList = articles;
                f6 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure: AUSTRALIA NEWS FAILED !!");
            }
        });

        // UK NEWS
        options.put("section", "uk-news");

        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: UK NEWS = " + articles);
                mUkNewsArticleList = articles;
                f7 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure: UK NEWS FAILED !!");
            }
        });

        // US NEWS
        options.put("section", "us-news");

        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: US NEWS = " + articles);
                mUsNewsArticleList = articles;
                f8 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure: US NEWS FAILED !!");
            }
        });

        // NEWS
        options.put("section", "news");

        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: NEWS = " + articles);
                mInternationalArticleList = articles;
                f9 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure: NEWS FAILED !!");
            }
        });

        // EDUCATION
        options.put("section", "education");

        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: EDUCATION = " + articles);
                mEducationArticleList = articles;
                f10 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure: EDUCATION FAILED !!");
            }
        });

        // FASHION
        options.put("section", "fashion");

        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: FASHION = " + articles);
                mFashionArticleList = articles;
                f11 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure: FASHION FAILED !!");
            }
        });

        // FILM
        options.put("section", "film");

        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: FILM = " + articles);
                mFilmArticleList = articles;
                f12 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure: FILM FAILED !!");
            }
        });

        // FOOTBALL
        options.put("section", "football");

        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: FOOTBALL = " + articles);
                mFootballArticleList = articles;
                f13 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure: FOOTBALL FAILED !!");
            }
        });

        // LAW
        options.put("section", "law");

        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: LAW = " + articles);
                mLawArticleList = articles;
                f14 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure: LAW FAILED !!");
            }
        });

        // LIFESTYLE
        options.put("section", "lifeandstyle");

        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: LIFESTYLE = " + articles);
                mLifestyleArticleList = articles;
                f15 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure: LIFESTYLE FAILED !!");
            }
        });

        // MEDIA
        options.put("section", "media");

        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: MEDIA = " + articles);
                mMediaArticleList = articles;
                f16 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure: MEDIA FAILED !!");
            }
        });

        // MONEY
        options.put("section", "money");

        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: MONEY = " + articles);
                mMoneyArticleList = articles;
                f17 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure: MONEY FAILED !!");
            }
        });

        // MUSIC
        options.put("section", "music");

        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: MUSIC = " + articles);
                mMusicArticleList = articles;
                f18 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure: MUSIC FAILED !!");
            }
        });

        // POLITICS
        options.put("section", "politics");

        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: POLITICS = " + articles);
                mPoliticsArticleList = articles;
                f19 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure: POLITICS FAILED !!");
            }
        });

        // SCIENCE
        options.put("section", "science");

        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: SCIENCE = " + articles);
                mScienceArticleList = articles;
                f20 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure: SCIENCE FAILED !!");
            }
        });

        // SOCIETY
        options.put("section", "society");

        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: SOCIETY = " + articles);
                mSocietyArticleList = articles;
                f21 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure: SOCIETY FAILED !!");
            }
        });

        // SPORT
        options.put("section", "sport");

        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: SPORT = " + articles);
                mSportArticleList = articles;
                f22 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure: SPORT FAILED !!");
            }
        });

        // TECHNOLOGY
        options.put("section", "technology");

        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: TECHNOLOGY = " + articles);
                mTechnologyArticleList = articles;
                f23 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure: TECHNOLOGY FAILED !!");
            }
        });

        // TRAVEL
        options.put("section", "travel");

        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: TRAVEL = " + articles);
                mTravelArticleList = articles;
                f24 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure: TRAVEL FAILED !!");
            }
        });

        // TV & RADIO
        options.put("section", "tv-and-radio");

        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: TV & RADIO = " + articles);
                mTvAndRadioArticleList = articles;
                f25 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure: TV & RADIO FAILED !!");
            }
        });

        // WEATHER
        options.put("section", "weather");

        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: WEATHER = " + articles);
                mWeatherArticleList = articles;
                f26 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure: WEATHER FAILED !!");
            }
        });
    }

    private void moveToMain() {
        Log.wtf(TAG, "moveToMain() has been instantiated");
        bool = f1 && f2 && f3 && f4 && f5 && f6 && f7 && f8 && f9 && f10
                && f11 && f12 && f13 && f14 && f15 && f16 && f17 && f18 && f19 && f20
                && f21 && f22 && f23 && f24 && f25 && f26;
        if (bool) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
