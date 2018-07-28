package com.muhammadelsayed.echo;

import android.util.Log;

import com.muhammadelsayed.echo.model.Article;
import com.muhammadelsayed.echo.model.ResultArticles;
import com.muhammadelsayed.echo.model.ResultSources;
import com.muhammadelsayed.echo.model.Source;
import com.muhammadelsayed.echo.network.NewsClient;
import com.muhammadelsayed.echo.network.RetrofitClientInstance;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Utils {
  private static final String TAG = "Utils";

  //  private static List<Article> articles = new ArrayList<>();
  public static Map<String, Integer> sourcesMap = new HashMap<>();

  public static void getTopHeadLines(
      Map<String, Object> options, final retrofitCallbackArticle callback) {
    NewsClient service = RetrofitClientInstance.getRetrofitInstance().create(NewsClient.class);

    Call<ResultArticles> call = service.getTopHeadLines(options);
    call.enqueue(
        new Callback<ResultArticles>() {
          @Override
          public void onResponse(Call<ResultArticles> call, Response<ResultArticles> response) {
            Log.wtf(TAG, "getTopHeadLines()::onResponse(): Has been instantiated");
            if (response.body().getStatus().equals("ok")) {
              List<Article> articles =
                  new LinkedList<Article>(Arrays.asList(response.body().getArticles()));

              for (Article article : articles) {
                if (sourcesMap.get(article.getSource().getId()) != null) {
                  article
                      .getSource()
                      .setImageResourceID(sourcesMap.get(article.getSource().getId()));
                }
              }

              callback.onSuccessArticle(articles);
            }
          }

          @Override
          public void onFailure(Call<ResultArticles> call, Throwable t) {
            Log.wtf(TAG, "getTopHeadLines()::onFailure(): " + t.getMessage());
          }
        });
  }

  public static void getEverything(
      Map<String, Object> options, final retrofitCallbackArticle callback) {
    NewsClient service = RetrofitClientInstance.getRetrofitInstance().create(NewsClient.class);

    Call<ResultArticles> call = service.getArticles(options);
    call.enqueue(
        new Callback<ResultArticles>() {
          @Override
          public void onResponse(Call<ResultArticles> call, Response<ResultArticles> response) {
            Log.wtf(TAG, "getEverything()::onResponse(): Has been instantiated");
            if (response.body().getStatus().equals("ok")) {
              List<Article> articles =
                  new LinkedList<Article>(Arrays.asList(response.body().getArticles()));
              callback.onSuccessArticle(articles);
            }
          }

          @Override
          public void onFailure(Call<ResultArticles> call, Throwable t) {
              Log.wtf(TAG, "getEverything()::onFailure(): " + t.getMessage());
          }
        });
  }

  public static void getSources(
      Map<String, Object> options, final retrofitCallbackSource callback) {
    NewsClient service = RetrofitClientInstance.getRetrofitInstance().create(NewsClient.class);

    Call<ResultSources> call = service.getSources(options);
    call.enqueue(
        new Callback<ResultSources>() {
          @Override
          public void onResponse(Call<ResultSources> call, Response<ResultSources> response) {
            Log.wtf(TAG, "getEverything()::onResponse(): Has been instantiated");
            if (response.body().getStatus().equals("ok")) {
              List<Source> sources =
                  new LinkedList<Source>(Arrays.asList(response.body().getSources()));

              instantiateSourcesMap();
              for (Source source : sources) {

                if (sourcesMap.get(source.getId()) != null)
                  source.setImageResourceID(sourcesMap.get(source.getId()));
              }
              callback.onSuccessSource(sources);
            }
          }

          @Override
          public void onFailure(Call<ResultSources> call, Throwable t) {
            Log.wtf(TAG, "getSources()::onFailure(): " + t.getMessage());
          }
        });
  }

  public interface retrofitCallbackArticle {
    void onSuccessArticle(List<Article> articles);
  }

  public interface retrofitCallbackSource {
    void onSuccessSource(List<Source> sources);
  }

    public static void instantiateSourcesMap() {
        Log.wtf(TAG, "instantiateSourcesMap: instantiating sourcesMap...");
        sourcesMap.put("abc-news", R.drawable.abc_news);
        sourcesMap.put("abc-news-au", R.drawable.abc_news_au);
        sourcesMap.put("al-jazeera-english", R.drawable.al_jazeera_english);
        sourcesMap.put("ars-technica", R.drawable.ars_technica);
        sourcesMap.put("associated-press", R.drawable.associated_press);
        sourcesMap.put("australian-financial-review", R.drawable.australian_financial_review);
        sourcesMap.put("axios", R.drawable.axios);
        sourcesMap.put("bbc-news", R.drawable.bbc_news);
        sourcesMap.put("bbc-sport", R.drawable.bbc_sport);
        sourcesMap.put("bleacher-report", R.drawable.bleacher_report);
        sourcesMap.put("bloomberg", R.drawable.bloomberg);
        sourcesMap.put("breitbart-news", R.drawable.breitbart_news);
        sourcesMap.put("business-insider", R.drawable.business_insider);
        sourcesMap.put("business-insider-uk", R.drawable.business_insider_uk);
        sourcesMap.put("buzzfeed", R.drawable.buzzfeed);
        sourcesMap.put("cbc-news", R.drawable.cbc_news);
        sourcesMap.put("cbs-news", R.drawable.cbs_news);
        sourcesMap.put("cnbc", R.drawable.cnbc);
        sourcesMap.put("cnn", R.drawable.cnn);
        sourcesMap.put("crypto-coins-news", R.drawable.crypto_coin_news);
        sourcesMap.put("daily-mail", R.drawable.daily_mail);
        sourcesMap.put("engadget", R.drawable.engadget);
        sourcesMap.put("entertainment-weekly", R.drawable.entertainment_weekly);
        sourcesMap.put("espn", R.drawable.espn);
        sourcesMap.put("espn-cric-info", R.drawable.espn_cric_info);
        sourcesMap.put("financial-post", R.drawable.financial_post);
        sourcesMap.put("financial-times", R.drawable.financial_times);
        sourcesMap.put("football-italia", R.drawable.football_italia);
        sourcesMap.put("fortune", R.drawable.fortune);
        sourcesMap.put("four-four-two", R.drawable.four_four_two);
        sourcesMap.put("fox-news", R.drawable.fox_news);
        sourcesMap.put("fox-sports", R.drawable.fox_sports);
        sourcesMap.put("google-news", R.drawable.google_news);
        sourcesMap.put("google-news-au", R.drawable.google_news);
        sourcesMap.put("google-news-ca", R.drawable.google_news);
        sourcesMap.put("google-news-in", R.drawable.google_news);
        sourcesMap.put("google-news-uk", R.drawable.google_news);
        sourcesMap.put("hacker-news", R.drawable.hacker_news);
        sourcesMap.put("ign", R.drawable.ign);
        sourcesMap.put("independent", R.drawable.independent);
        sourcesMap.put("mashable", R.drawable.mashable);
        sourcesMap.put("medical-news-today", R.drawable.medical_news_today);
        sourcesMap.put("metro", R.drawable.metro);
        sourcesMap.put("mirror", R.drawable.mirror);
        sourcesMap.put("msnbc", R.drawable.msnbc);
        sourcesMap.put("mtv-news", R.drawable.mtv_news);
        sourcesMap.put("mtv-news-uk", R.drawable.mtv_news_uk);
        sourcesMap.put("national-geographic", R.drawable.national_geographic);
        sourcesMap.put("national-review", R.drawable.national_review);
        sourcesMap.put("nbc-news", R.drawable.nbc_news);
        sourcesMap.put("news24", R.drawable.news24);
        sourcesMap.put("new-scientist", R.drawable.new_scientist);
        sourcesMap.put("news-com-au", R.drawable.news_com_au);
        sourcesMap.put("newsweek", R.drawable.newsweek);
        sourcesMap.put("new-york-magazine", R.drawable.new_york_magazine);
//        sourcesMap.put("next-big-future", R.drawable.next_big_future);
        sourcesMap.put("nfl-news", R.drawable.nfl_news);
        sourcesMap.put("nhl-news", R.drawable.nhl_news);
        sourcesMap.put("politico", R.drawable.politico);
        sourcesMap.put("polygon", R.drawable.polygon);
        sourcesMap.put("recode", R.drawable.recode);
        sourcesMap.put("reddit-r-all", R.drawable.reddit_r_all);
        sourcesMap.put("reuters", R.drawable.reuters);
        sourcesMap.put("rte", R.drawable.rte);
        sourcesMap.put("talksport", R.drawable.talksport);
        sourcesMap.put("techcrunch", R.drawable.techcrunch);
        sourcesMap.put("techradar", R.drawable.techradar);
        sourcesMap.put("the-american-conservative", R.drawable.the_american_conservative);
        sourcesMap.put("the-economist", R.drawable.the_economist);
        sourcesMap.put("the-globe-and-mail", R.drawable.the_globe_and_mail);
        sourcesMap.put("the-guardian-au", R.drawable.the_guardian);
        sourcesMap.put("the-guardian-uk", R.drawable.the_guardian);
        sourcesMap.put("the-hill", R.drawable.the_hill);
        sourcesMap.put("the-hindu", R.drawable.the_hindu);
        sourcesMap.put("the-huffington-post", R.drawable.the_huffington_post);
        sourcesMap.put("the-irish-times", R.drawable.the_irish_times);
        sourcesMap.put("the-jerusalem-post", R.drawable.the_jerusalem_post);
        sourcesMap.put("the-lad-bible", R.drawable.the_lad_bible);
        sourcesMap.put("the-new-york-times", R.drawable.the_new_york_times);
        sourcesMap.put("the-next-web", R.drawable.the_next_web);
        sourcesMap.put("the-sport-bible", R.drawable.the_sport_bible);
        sourcesMap.put("the-telegraph", R.drawable.the_telegraph);
        sourcesMap.put("the-times-of-india", R.drawable.the_times_of_india);
        sourcesMap.put("the-verge", R.drawable.the_verge);
        sourcesMap.put("the-wall-street-journal", R.drawable.the_wall_street_journal);
        sourcesMap.put("the-washington-post", R.drawable.the_washington_post);
        sourcesMap.put("the-washington-times", R.drawable.the_washington_times);
        sourcesMap.put("time", R.drawable.time);
        sourcesMap.put("vice-news", R.drawable.vice_news);
        sourcesMap.put("usa-today", R.drawable.usa_today);
        sourcesMap.put("wired", R.drawable.wired);
    }
}
