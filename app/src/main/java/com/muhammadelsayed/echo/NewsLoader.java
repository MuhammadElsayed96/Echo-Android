package com.muhammadelsayed.echo;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhammad Elsayed on 11/19/2017.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    public static final String LOG_TAG = NewsLoader.class.getName();
    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {

        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {

        if (mUrl == null) {
            return null;
        }

        ArrayList<News> news = NewsUtils.fetchNewsData(mUrl);
        return news;
    }
}
