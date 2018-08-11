package com.muhammadelsayed.echo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import com.muhammadelsayed.echo.Model.Article;
import com.thefinestartist.Base;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.muhammadelsayed.echo.Utils.isNetworkAvailable;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";

    public static AlarmManager alarmManager;

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
    public static List<Article> mWorldArticleList = new ArrayList<>();
    public static Map<String, List<Article>> sections = new HashMap<>();
    Map<String, Object> options = new HashMap<>();
    boolean bool, f1 = false, f2 = false, f3 = false, f4 = false, f5 = false, f6 = false, f7 = false, f8 = false,
            f9 = false, f10 = false, f11 = false, f12 = false, f13 = false, f14 = false, f15 = false, f16 = false,
            f17 = false, f18 = false, f19 = false, f20 = false, f21 = false, f22 = false, f23 = false, f24 = false,
            f25 = false, f26 = false, f28 = false;
    private SweetAlertDialog exitOrRetry;

    private static List<Article> filterArticles(List<Article> articles) {
        Log.wtf(TAG, "filterArticles() has been instantiated");
        List<Article> filtered = new ArrayList<>();
        for (Article article : articles) {
            if (!TextUtils.isEmpty(article.getFields().getThumbnail()))
                filtered.add(article);
        }
        return filtered;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.wtf(TAG, "onCreate(): has been instantiated");
        Base.initialize(this);

        if (isNetworkAvailable()) {
            getData();

            sections.put("Business", mBusinessArticleList);
            sections.put("Culture", mCultureArticleList);
            sections.put("Art and design", mArtAndDesignArticleList);
            sections.put("Environment", mEnvironmentArticleList);
            sections.put("Books", mBooksArticleList);
            sections.put("Australia news", mAustraliaNewsArticleList);
            sections.put("UK news", mUkNewsArticleList);
            sections.put("US news", mUsNewsArticleList);
            sections.put("News", mInternationalArticleList);
            sections.put("Education", mEducationArticleList);
            sections.put("Fashion", mFashionArticleList);
            sections.put("Film", mFilmArticleList);
            sections.put("Football", mFootballArticleList);
            sections.put("Law", mLawArticleList);
            sections.put("Life and style", mLifestyleArticleList);
            sections.put("Media", mMediaArticleList);
            sections.put("Money", mMoneyArticleList);
            sections.put("Music", mMusicArticleList);
            sections.put("Politics", mPoliticsArticleList);
            sections.put("Science", mScienceArticleList);
            sections.put("Society", mSocietyArticleList);
            sections.put("Sport", mSportArticleList);
            sections.put("Technology", mTechnologyArticleList);
            sections.put("Television & radio", mTvAndRadioArticleList);
            sections.put("Travel", mTravelArticleList);
            sections.put("Weather", mWeatherArticleList);
            sections.put("World", mWorldArticleList);
        } else
            tryToConnectOrExit();

        setNotificationAlarm();

    }

    private void getData() {
        Log.wtf(TAG, "getData(): has been instantiated");

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
                mBusinessArticleList = filterArticles(articles);
                f1 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure(): BUSINESS -> " + t.getMessage());
            }
        });

        // CULTURE
        options.put("section", "culture");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: CULTURE FAILED -> " + articles);
                mCultureArticleList = filterArticles(articles);
                f2 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure(): CULTURE FAILED -> " + t.getMessage());
            }
        });

        // ART & DESIGN
        options.put("section", "artanddesign");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: ART & DESIGN = " + articles);
                mArtAndDesignArticleList = filterArticles(articles);
                f3 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure: ART & DESIGN FAILED -> " + t.getMessage());
            }
        });

        // ENVIRONMENT
        options.put("section", "environment");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: ENVIRONMENT = " + articles);
                mEnvironmentArticleList = filterArticles(articles);
                f4 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure(): ENVIRONMENT FAILED -> " + t.getMessage());
            }
        });

        // BOOKS
        options.put("section", "books");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: BOOKS = " + articles);
                mBooksArticleList = filterArticles(articles);
                f5 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure(): BOOKS FAILED -> " + t.getMessage());
            }
        });

        // AUSTRALIA NEWS
        options.put("section", "australia-news");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: AUSTRALIA NEWS = " + articles);
                mAustraliaNewsArticleList = filterArticles(articles);
                f6 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure(): AUSTRALIA NEWS FAILED -> " + t.getMessage());
            }
        });

        // UK NEWS
        options.put("section", "uk-news");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: UK NEWS = " + articles);
                mUkNewsArticleList = filterArticles(articles);
                f7 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure(): UK NEWS FAILED -> " + t.getMessage());
            }
        });

        // US NEWS
        options.put("section", "us-news");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: US NEWS = " + articles);
                mUsNewsArticleList = filterArticles(articles);
                f8 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure(): US NEWS FAILED -> " + t.getMessage());
            }
        });

        // NEWS
        options.put("section", "news");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: NEWS = " + articles);
                mInternationalArticleList = filterArticles(articles);
                f9 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure(): NEWS FAILED -> " + t.getMessage());
            }
        });

        // EDUCATION
        options.put("section", "education");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: EDUCATION = " + articles);
                mEducationArticleList = filterArticles(articles);
                f10 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure(): EDUCATION FAILED -> " + t.getMessage());
            }
        });

        // FASHION
        options.put("section", "fashion");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: FASHION = " + articles);
                mFashionArticleList = filterArticles(articles);
                f11 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure(): FASHION FAILED -> " + t.getMessage());
            }
        });

        // FILM
        options.put("section", "film");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: FILM = " + articles);
                mFilmArticleList = filterArticles(articles);
                f12 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure(): FILM FAILED -> " + t.getMessage());
            }
        });

        // FOOTBALL
        options.put("section", "football");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: FOOTBALL = " + articles);
                mFootballArticleList = filterArticles(articles);
                f13 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure(): FOOTBALL FAILED -> " + t.getMessage());
            }
        });

        // LAW
        options.put("section", "law");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: LAW = " + articles);
                mLawArticleList = filterArticles(articles);
                f14 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure(): LAW FAILED -> " + t.getMessage());
            }
        });

        // LIFESTYLE
        options.put("section", "lifeandstyle");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: LIFESTYLE = " + articles);
                mLifestyleArticleList = filterArticles(articles);
                f15 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure(): LIFESTYLE FAILED -> " + t.getMessage());
            }
        });

        // MEDIA
        options.put("section", "media");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: MEDIA = " + articles);
                mMediaArticleList = filterArticles(articles);
                f16 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure(): MEDIA FAILED -> " + t.getMessage());
            }
        });

        // MONEY
        options.put("section", "money");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: MONEY = " + articles);
                mMoneyArticleList = filterArticles(articles);
                f17 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure(): MONEY FAILED -> " + t.getMessage());
            }
        });

        // MUSIC
        options.put("section", "music");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: MUSIC = " + articles);
                mMusicArticleList = filterArticles(articles);
                f18 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure(): MUSIC FAILED -> " + t.getMessage());
            }
        });

        // POLITICS
        options.put("section", "politics");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: POLITICS = " + articles);
                mPoliticsArticleList = filterArticles(articles);
                f19 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure(): POLITICS FAILED -> " + t.getMessage());
            }
        });

        // SCIENCE
        options.put("section", "science");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: SCIENCE = " + articles);
                mScienceArticleList = filterArticles(articles);
                f20 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure(): SCIENCE FAILED -> " + t.getMessage());
            }
        });

        // SOCIETY
        options.put("section", "society");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: SOCIETY = " + articles);
                mSocietyArticleList = filterArticles(articles);
                f21 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure(): SOCIETY FAILED -> " + t.getMessage());
            }
        });

        // SPORT
        options.put("section", "sport");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: SPORT = " + articles);
                mSportArticleList = filterArticles(articles);
                f22 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure(): SPORT FAILED -> " + t.getMessage());
            }
        });

        // TECHNOLOGY
        options.put("section", "technology");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: TECHNOLOGY = " + articles);
                mTechnologyArticleList = filterArticles(articles);
                f23 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure(): TECHNOLOGY FAILED -> " + t.getMessage());
            }
        });

        // TRAVEL
        options.put("section", "travel");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: TRAVEL = " + articles);
                mTravelArticleList = filterArticles(articles);
                f24 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure(): TRAVEL FAILED -> " + t.getMessage());
            }
        });

        // TV & RADIO
        options.put("section", "tv-and-radio");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: TV & RADIO = " + articles);
                mTvAndRadioArticleList = filterArticles(articles);
                f25 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure(): TV & RADIO FAILED -> " + t.getMessage());
            }
        });

        // WEATHER
        options.put("section", "weather");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: WEATHER = " + articles);
                mWeatherArticleList = filterArticles(articles);
                f26 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure(): WEATHER FAILED-> " + t.getMessage());
            }
        });

        // WORLD
        options.put("section", "world");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: WORLD = " + articles);
                mWorldArticleList = filterArticles(articles);
                f28 = true;
                moveToMain();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure(): WORLD FAILED -> " + t.getMessage());
            }
        });
    }

    private void moveToMain() {
        Log.wtf(TAG, "moveToMain() has been instantiated");
        bool = f1 && f2 && f3 && f4 && f5 && f6 && f7 && f8 && f9 && f10
                && f11 && f12 && f13 && f14 && f15 && f16 && f17 && f18 && f19 && f20
                && f21 && f22 && f23 && f24 && f25 && f26 && f28;
        if (bool) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void tryToConnectOrExit() {
        Log.wtf(TAG, "tryToConnectOrExit(): has been instantiated");
        if (exitOrRetry != null)
            exitOrRetry = null;
        exitOrRetry = new SweetAlertDialog(SplashActivity.this, SweetAlertDialog.WARNING_TYPE);
        exitOrRetry.setCancelable(false);
        exitOrRetry.setTitleText("No Internet Connection")
                .setContentText("Connect to WI-FI or Cellular")
                .setConfirmText("RETRY")
                .setCancelText("EXIT")
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                        finish();
                    }
                }).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                getData();
            }
        }).show();
    }

    private void setNotificationAlarm() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 3);
        calendar.set(Calendar.MINUTE, 50);
        calendar.set(Calendar.SECOND, 0);

        Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);
        intent.setAction("MY_NOTIFICATION_MESSAGE");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);

    }

}
