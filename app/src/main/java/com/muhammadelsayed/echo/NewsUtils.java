/*
 *  Copyright [2017] [Muhammad Elsayed]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
        Log.i(LOG_TAG, "TEST: fetchNewsData method has been triggered");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String jsonResponse = "";

        //  create URL object
        URL mUrl = createUrl(url);

        try {
            jsonResponse = makeHTTPRequest(mUrl);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        ArrayList<News> news = extractJSONNews(jsonResponse);
        return news;

    }

    /**
     * returns new URL object from the given String url .
     */
    public static URL createUrl(String url) {
        Log.i(LOG_TAG, "TEST: createUrl method has been triggered");
        URL mUrl;
        try {
            mUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "Error with creating the URL.", e);
            return null;
        }
        return mUrl;
    }

    /**
     * Make an Http request to the given url and returns a String as the response.
     */
    public static String makeHTTPRequest(URL url) throws IOException {
        Log.i(LOG_TAG, "TEST: makeHTTPRequest method has been triggered");
        String jsonResponse = "";

        // if the url is null, then return the Empty jsonResponse.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        // InputStream: It is the stream of bytes of the information we want. ("raw data")
        // InputStream: It represents a stream of bytes (small chuck of data)
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error with response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "Error retrieving the JSON results from the servers.", e);
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

    /**
     * Converts the InputStream into a String that holds the
     * whole JSON response from the server.
     */
    public static String readFromStream(InputStream inputStream) throws IOException {
        Log.i(LOG_TAG, "TEST: readFromStream method has been triggered");
        StringBuilder jsonOutput = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader streamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            // BufferedReader: it allows us to convert the raw data into human-readable text.
            // BufferedReader: it helps us to read the text from the inputStreamReader.
            BufferedReader reader = new BufferedReader(streamReader);
            String line = reader.readLine();
            while (line != null) {
                jsonOutput.append(line);
                line = reader.readLine();
            }
        }
        return jsonOutput.toString();
    }

    /**
     * Return a list of {@link News} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<News> extractJSONNews(String newsJson) {
        Log.i(LOG_TAG, "TEST: extractJSONNews method has been triggered");
        if (TextUtils.isEmpty(newsJson)) {
            return null;
        }

        // Create an empty ArrayList to start adding news to it.
        ArrayList<News> news = new ArrayList<>();

        try {
            // build up a list of News objects with the corresponding data.
            JSONObject root = new JSONObject(newsJson);
            JSONObject metaData = root.getJSONObject("response");
            JSONArray newsArray = metaData.getJSONArray("results");
            for (int i = 0; i < newsArray.length(); i++) {
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

        // Return the list of news.
        return news;
    }
}
