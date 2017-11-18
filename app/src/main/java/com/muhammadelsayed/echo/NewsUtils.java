package com.muhammadelsayed.echo;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Muhammad Elsayed on 11/18/2017.
 */

public class NewsUtils {

    public static final String LOG_TAG = NewsUtils.class.getName();

    public NewsUtils() {
    }

    public static ArrayList<News> fetchNewsData(String url) {

        String jsonResponse = "";


        URL mUrl = createUrl(url);


        try {
            jsonResponse = makeHTTPRequest(mUrl);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        ArrayList<News> news = extractJSONEarthquakes(jsonResponse);
        return news;

    }


    public static URL createUrl(String url) {
        URL mUrl;
        try {
            mUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        return mUrl;
    }


    public static String makeHTTPRequest(URL url) throws IOException {

        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error with response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    public static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder jsonOutput = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader streamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(streamReader);
            String line = reader.readLine();
            while (line != null) {
                jsonOutput.append(line);
                line = reader.readLine();
            }
        }
        return jsonOutput.toString();
    }


    public static ArrayList<News> extractJSONEarthquakes(String newsJson) {
        if (TextUtils.isEmpty(newsJson)) {
            return null;
        }

        ArrayList<News> news = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(newsJson);
            JSONObject metaData = root.getJSONObject("response");
            JSONArray newsArray = metaData.getJSONArray("results");
            for (int i = 0; i < 150; i++) {
                JSONObject currentNews = newsArray.getJSONObject(i);
                String title = currentNews.getString("webTitle");
                String date = currentNews.getString("webPublicationDate");
                String url = currentNews.getString("webUrl");
                news.add(new News(title, date, url));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "Problem parsing the news JSON results", e);
        }
        return news;
    }
}
