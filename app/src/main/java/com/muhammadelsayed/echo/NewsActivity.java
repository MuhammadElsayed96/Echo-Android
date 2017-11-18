package com.muhammadelsayed.echo;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    public static final String LOG_TAG = NewsActivity.class.getName();
    private static final int NEWS_LOADER_ID = 1;
    public static final String THE_GUARDIAN_REQUEST_URL =
            "http://content.guardianapis.com/search?page-size=200&q=tech&api-key=c8133e91-2b02-42b7-9cc8-88ca8d73998a";

    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(LOG_TAG, "TEST: onCreate method has been triggered");

        ListView newsListView = findViewById(R.id.news_list);

        newsAdapter = new NewsAdapter(this, new ArrayList<News>());
        newsListView.setAdapter(newsAdapter);

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

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(NEWS_LOADER_ID, null, this);
    }


    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        Log.i(LOG_TAG, "TEST: onCreateLoader method has been triggered");
        return new NewsLoader(this, THE_GUARDIAN_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        Log.i(LOG_TAG, "TEST: onLoadFinished method has been triggered");
        newsAdapter.clear();
        if (data != null || !data.isEmpty()) {
            newsAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        Log.i(LOG_TAG, "TEST: onLoaderReset method has been triggered");
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
