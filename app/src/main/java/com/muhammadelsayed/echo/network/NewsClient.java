package com.muhammadelsayed.echo.network;

import com.muhammadelsayed.echo.model.ResultArticles;
import com.muhammadelsayed.echo.model.ResultSources;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface NewsClient {

  /**
   * Retrieves the top headline news
   *
   * @param options the Query Strings that can be {
   *     <p>country = The 2-letter ISO 3166-1 code of the country you want to get headlines for.
   *     <p>category = The category you want to get headlines for. { business, entertainment,
   *     general, health, science, sports, technology }
   *     <p>q = Keywords or phrases to search for.
   *     <p>sources = A comma-seperated string of identifiers (maximum 20) for the news sources or
   *     blogs you want headlines from.
   *     <p>pageSize = The number of results to return per page. 20 is the default, 100 is the
   *     maximum.
   *     <p>apiKey = Your API key. (REQUIRED)
   *     <p>}
   * @return an object containing an array of the top headline news
   */
  @GET("top-headlines")
  Call<ResultArticles> getTopHeadLines(@QueryMap Map<String, Object> options);

  /**
   * Retrieves all articles that matches the options
   *
   * @param options the Query Strings that can be {
   *     <p>country = The 2-letter ISO 3166-1 code of the country you want to get headlines for.
   *     <p>category = The category you want to get headlines for. { business, entertainment,
   *     general, health, science, sports, technology }
   *     <p>language = he 2-letter ISO-639-1 code of the language you want to get headlines for.
   *     <p>apiKey = Your API key. (REQUIRED) }
   * @return an object containing an array of all articles that matches the options
   */
  @GET("everything")
  Call<ResultArticles> getArticles(@QueryMap Map<String, Object> options);

  /**
   * Retrieves all sources that matches the options
   *
   * @param options the Query Strings that can be {
   *     <p>q = Keywords or phrases to search for.
   *     <p>sources = A comma-seperated string of identifiers (maximum 20) for the news sources or
   *     blogs you want headlines from.
   *     <p>from = A date and optional time for the oldest article allowed.
   *     <p>to = A date and optional time for the newest article allowed.
   *     <p>language = he 2-letter ISO-639-1 code of the language you want to get headlines for.
   *     <p>pageSize = The number of results to return per page. 20 is the default, 100 is the
   *     maximum.
   *     <p>apiKey = Your API key. (REQUIRED)
   * @return an object containing an array of all sources that matches the options
   */
  @GET("sources")
  Call<ResultSources> getSources(@QueryMap Map<String, Object> options);
}
