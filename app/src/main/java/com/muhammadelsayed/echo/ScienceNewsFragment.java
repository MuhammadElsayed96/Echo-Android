package com.muhammadelsayed.echo;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScienceNewsFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<News>> {

    public static final String LOG_TAG = ScienceNewsFragment.class.getName();
    private static final int NEWS_LOADER_ID = 10;
    public static final String THE_GUARDIAN_REQUEST_URL =
            "http://content.guardianapis.com/search?section=science&order-by=newest&show-tags=contributor&show-fields=thumbnail&page=1&page-size=100&q=science&api-key=c8133e91-2b02-42b7-9cc8-88ca8d73998a";
    private View rootView;
    private NewsAdapter newsAdapter;
    private TextView emptyStateTextView;
    private ProgressBar loadingIndicator;

    public ScienceNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.news_list, container, false);


        Log.i(LOG_TAG, "TEST: onCreate method has been triggered");

        ListView newsListView = rootView.findViewById(R.id.news_list);

        newsAdapter = new NewsAdapter(getContext(), new ArrayList<News>());
        newsListView.setAdapter(newsAdapter);
        emptyStateTextView = (TextView) rootView.findViewById(R.id.empty_state);
        newsListView.setEmptyView(emptyStateTextView);
        emptyStateTextView.setText(R.string.empty_string);

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News currentNews = newsAdapter.getItem(position);
                String url = currentNews.getUrl();
                Uri uri = Uri.parse(url);
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(websiteIntent);
            }
        });

        // Checking the network connectivity.
        ConnectivityManager cm =
                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

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
            loadingIndicator = (ProgressBar) rootView.findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            emptyStateTextView.setText(R.string.no_internet_connection);
        }


        return rootView;
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        Log.i(LOG_TAG, "TEST: onCreateLoader method has been triggered");

        // Create a new loader for the given URL
        return new NewsLoader(getContext(), THE_GUARDIAN_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        Log.i(LOG_TAG, "TEST: onLoadFinished method has been triggered");


        // Hide loading indicator because the data has been loaded
        loadingIndicator = rootView.findViewById(R.id.loading_indicator);
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
}
