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

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    public static final String LOG_TAG = NewsActivity.class.getName();
    private static final int NEWS_LOADER_ID = 1;
    public static final String THE_GUARDIAN_REQUEST_URL =
            "http://content.guardianapis.com/search?page-size=200&q=tech&api-key=c8133e91-2b02-42b7-9cc8-88ca8d73998a";

    private NewsAdapter newsAdapter;
    private TextView emptyStateTextView;
    private ProgressBar loadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(LOG_TAG, "TEST: onCreate method has been triggered");

        ListView newsListView = findViewById(R.id.news_list);

        newsAdapter = new NewsAdapter(this, new ArrayList<News>());
        newsListView.setAdapter(newsAdapter);
        emptyStateTextView = (TextView) findViewById(R.id.empty_state);
        newsListView.setEmptyView(emptyStateTextView);
        emptyStateTextView.setText(R.string.empty_string);

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News currentNews = newsAdapter.getItem(position);
                String url = currentNews.getmUrl();
                Uri uri = Uri.parse(url);
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(websiteIntent);
            }
        });

        // Checking the network connectivity.
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            Log.i(LOG_TAG, "TEST: loaderManager.initLoader method has been triggered");

            // Start the loader or create it to fetch the news data.
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {
            Log.i(LOG_TAG, "No Internet connection.");
            loadingIndicator = (ProgressBar) findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            emptyStateTextView.setText(R.string.no_internet_connection);
        }
    }


    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        Log.i(LOG_TAG, "TEST: onCreateLoader method has been triggered");

        // Create a new loader for the given URL
        return new NewsLoader(this, THE_GUARDIAN_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        Log.i(LOG_TAG, "TEST: onLoadFinished method has been triggered");


        // Hide loading indicator because the data has been loaded
        loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No earthquakes found."
        emptyStateTextView.setText(R.string.no_news);


        // Clear the adapter of previous news data
        newsAdapter.clear();

        // If there is a valid list of news, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (data != null || !data.isEmpty()) {
            newsAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        Log.i(LOG_TAG, "TEST: onLoaderReset method has been triggered");

        //Loader reset, so we can clear out our existing data.
        newsAdapter.clear();
    }


    @Override
    protected void onStart() {
        Log.i(LOG_TAG, "TEST: onStart method has been triggered");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i(LOG_TAG, "TEST: onResume method has been triggered");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i(LOG_TAG, "TEST: onPause method has been triggered");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(LOG_TAG, "TEST: onStop method has been triggered");
        super.onStop();
    }


    @Override
    protected void onRestart() {
        Log.i(LOG_TAG, "TEST: onRestart method has been triggered");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.i(LOG_TAG, "TEST: onDestroy method has been triggered");
        super.onDestroy();
    }

}