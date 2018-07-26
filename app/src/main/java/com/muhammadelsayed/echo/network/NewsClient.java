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
     * @param options the Query Strings that can be {
     *
     *     country  = The 2-letter ISO 3166-1 code of the country you want to get headlines for.
     *
     *     category = The category you want to get headlines for.
     *                { business, entertainment, general, health, science, sports, technology }
     *
     *     q        = Keywords or phrases to search for.
     *
     *     sources  = A comma-seperated string of identifiers (maximum 20)
     *               for the news sources or blogs you want headlines from.
     *
     *     pageSize = The number of results to return per page.
     *                20 is the default, 100 is the maximum.
     *
     *     apiKey   = Your API key. (REQUIRED)
     *
     * }
     *
     * @return an object containing an array of the top headline news
     */
    @GET("top-headlines")
    Call<ResultArticles> getTopHeadLines(@QueryMap Map<String, Object> options);

    /**
     * Retrieves all articles that matches the options
     * @param options the Query Strings that can be {
     *
     *     country  = The 2-letter ISO 3166-1 code of the country you want to get headlines for.
     *
     *     category = The category you want to get headlines for.
     *                { business, entertainment, general, health, science, sports, technology }
     *
     *     language = he 2-letter ISO-639-1 code of the language you want to get headlines for.
     *
     *     apiKey   = Your API key. (REQUIRED)
     * }
     * @return an object containing an array of all articles that matches the options
     */
    @GET("everything")
    Call<ResultArticles> getArticles(@QueryMap Map<String, Object> options);


    /**
     * Retrieves all sources that matches the options
     *
     * @param options the Query Strings that can be {
     *
     *     q        = Keywords or phrases to search for.
     *
     *     sources  = A comma-seperated string of identifiers (maximum 20)
     *               for the news sources or blogs you want headlines from.
     *
     *     from     = A date and optional time for the oldest article allowed.
     *
     *     to       = A date and optional time for the newest article allowed.
     *
     *     language = he 2-letter ISO-639-1 code of the language you want to get headlines for.
     *
     *     pageSize = The number of results to return per page.
     *                20 is the default, 100 is the maximum.
     *
     *     apiKey   = Your API key. (REQUIRED)
     * @return an object containing an array of all sources that matches the options
     */
    @GET("sources")
    Call<ResultSources> getSources(@QueryMap Map<String, String> options);
}
