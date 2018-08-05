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
import android.widget.ProgressBar;

import com.muhammadelsayed.echo.Adapters.EndlessRecyclerOnScrollListener;
import com.muhammadelsayed.echo.Adapters.NewsAdapter;
import com.muhammadelsayed.echo.R;
import com.muhammadelsayed.echo.Utils;
import com.muhammadelsayed.echo.model.Article;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.muhammadelsayed.echo.SplashActivity.mEnvironmentArticleList;

public class Environment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = Environment.class.getSimpleName();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private NewsAdapter mEnvironmentNewsAdapter;
    private RecyclerView mEnvironmentRecycler;
    private ProgressBar mProgressBar;

    private final int PAGE_START = 1;
    private int currentPage = PAGE_START;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.wtf(TAG, "onCreate() has been instantiated");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.wtf(TAG, "onCreateView() has been instantiated");
        View rootView = inflater.inflate(R.layout.fragment_environment, container, false);
        mProgressBar = rootView.findViewById(R.id.progressBar);
        mEnvironmentRecycler = rootView.findViewById(R.id.recycler);
        LinearLayoutManager mLinearLayoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mEnvironmentRecycler.setLayoutManager(mLinearLayoutManager);
        mSwipeRefreshLayout = rootView.findViewById(R.id.swipe);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mEnvironmentRecycler.setDrawingCacheEnabled(true);
        mEnvironmentRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mEnvironmentRecycler.setItemViewCacheSize(100);

        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        loadEnvironmentData();
        mEnvironmentRecycler.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                currentPage++;
                addDataToList(currentPage);
            }
        });
        return rootView;
    }

    private void addDataToList(int page) {
        mProgressBar.setVisibility(View.VISIBLE);
        Map<String, Object> options = new HashMap<>();
        options.put("section", "environment");
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", page);
        options.put("page-size", 20);
        options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: Environment = " + articles);
                mEnvironmentArticleList.addAll(articles);
                mEnvironmentNewsAdapter.notifyDataSetChanged();
                mProgressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure(): Environment FAILED !!");
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }


    private void loadEnvironmentData() {
        Log.wtf(TAG, "loadEnvironmentData() has been instantiated");
        if (mEnvironmentArticleList.isEmpty()) {
            Map<String, Object> options = new HashMap<>();
            options.put("section", "environment");
            options.put("order-by", "newest");
            options.put("show-tags", "contributor");
            options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
            options.put("page", 1);
            options.put("page-size", 20);
            options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
            Utils.getNews(options, new Utils.retrofitCallback() {
                @Override
                public void onSuccess(List<Article> articles) {
                    Log.wtf(TAG, "onSuccess: Environment = " + articles);
                    mEnvironmentArticleList = articles;
                    mEnvironmentNewsAdapter = new NewsAdapter(getContext(), mEnvironmentArticleList);
                    mEnvironmentRecycler.setAdapter(mEnvironmentNewsAdapter);
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.wtf(TAG, "onFailure(): Environment FAILED !!");
                }
            });
        } else {
            mEnvironmentNewsAdapter = new NewsAdapter(getContext(), mEnvironmentArticleList);
        }
        mEnvironmentRecycler.setAdapter(mEnvironmentNewsAdapter);
    }

    @Override
    public void onRefresh() {
        Log.wtf(TAG, "onRefresh() has been instantiated");
        mSwipeRefreshLayout.setRefreshing(true);
        mEnvironmentArticleList.clear();
        EndlessRecyclerOnScrollListener.mPreviousTotal = 0;
        currentPage = PAGE_START;
        Log.wtf(TAG, "onRefresh()::mEnvironmentArticleList = " + mEnvironmentArticleList.toString());
        loadEnvironmentData();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onPause() {
        super.onPause();
        EndlessRecyclerOnScrollListener.mPreviousTotal = 0;
        Log.wtf(TAG, "onPause() has been instantiated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.wtf(TAG, "onStart() has been instantiated");
    }

    @Override
    public void onStop() {
        super.onStop();
        EndlessRecyclerOnScrollListener.mPreviousTotal = 0;
        Log.wtf(TAG, "onStop() has been instantiated");
    }

}
