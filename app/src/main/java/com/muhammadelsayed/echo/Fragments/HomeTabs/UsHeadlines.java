package com.muhammadelsayed.echo.Fragments.HomeTabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muhammadelsayed.echo.Adapters.NewsAdapter;
import com.muhammadelsayed.echo.R;
import com.muhammadelsayed.echo.Utils;
import com.muhammadelsayed.echo.model.Article;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.muhammadelsayed.echo.SplashActivity.mUkNewsArticleList;

public class UsHeadlines extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = UsHeadlines.class.getSimpleName();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private NewsAdapter mUsHeadlinesNewsAdapter;
    private RecyclerView mUsHeadlinesRecycler;

    public UsHeadlines() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.wtf(TAG, "onCreate() has been instantiated");
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.wtf(TAG, "onCreateView() has been instantiated");
        View rootView = inflater.inflate(R.layout.fragment_us_headlines, container, false);
        mUsHeadlinesRecycler = rootView.findViewById(R.id.us_recycler);
        LinearLayoutManager mLinearLayoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mUsHeadlinesRecycler.setLayoutManager(mLinearLayoutManager);
        mSwipeRefreshLayout = rootView.findViewById(R.id.us_swipe);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mUsHeadlinesRecycler.setDrawingCacheEnabled(true);
        mUsHeadlinesRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mUsHeadlinesRecycler.setItemViewCacheSize(100);

        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        loadUsHeadlinesData();
        return rootView;
    }


    private void loadUsHeadlinesData() {
        Log.wtf(TAG, "loadUsHeadlinesData() has been instantiated");
        if (mUkNewsArticleList.isEmpty()) {
            Map<String, Object> options = new HashMap<>();
            options.put("section", "us-news");
            options.put("order-by", "newest");
            options.put("show-tags", "contributor");
            options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
            options.put("page", 1);
            options.put("page-size", 20);
            options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
            Utils.getNews(options, new Utils.retrofitCallback() {
                @Override
                public void onSuccess(List<Article> articles) {
                    Log.wtf(TAG, "onSuccess: US NEWS = " + articles);
                    mUkNewsArticleList = articles;
                    mUsHeadlinesNewsAdapter = new NewsAdapter(getContext(), mUkNewsArticleList);
                    mUsHeadlinesRecycler.setAdapter(mUsHeadlinesNewsAdapter);
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.wtf(TAG, "onFailure(): US NEWS FAILED !!");
                }
            });
        } else {
            mUsHeadlinesNewsAdapter = new NewsAdapter(getContext(), mUkNewsArticleList);
        }
        mUsHeadlinesRecycler.setAdapter(mUsHeadlinesNewsAdapter);
    }

    @Override
    public void onRefresh() {
        Log.wtf(TAG, "onRefresh() has been instantiated");
        mSwipeRefreshLayout.setRefreshing(true);
        mUkNewsArticleList.clear();
        Log.wtf(TAG, "onRefresh()::mUkNewsArticleList = " + mUkNewsArticleList.toString());
        loadUsHeadlinesData();
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
