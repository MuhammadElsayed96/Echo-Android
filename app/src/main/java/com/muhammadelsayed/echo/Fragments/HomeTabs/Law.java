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

import static com.muhammadelsayed.echo.SplashActivity.mLawArticleList;

public class Law extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = Law.class.getSimpleName();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private NewsAdapter mLawNewsAdapter;
    private RecyclerView mLawRecycler;
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
        View rootView = inflater.inflate(R.layout.fragment_law, container, false);
        mProgressBar = rootView.findViewById(R.id.progressBar);
        mLawRecycler = rootView.findViewById(R.id.recycler);
        LinearLayoutManager mLinearLayoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mLawRecycler.setLayoutManager(mLinearLayoutManager);
        mSwipeRefreshLayout = rootView.findViewById(R.id.swipe);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mLawRecycler.setDrawingCacheEnabled(true);
        mLawRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mLawRecycler.setItemViewCacheSize(100);

        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        loadLawData();
        mLawRecycler.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
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
        options.put("section", "law");
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", page);
        options.put("page-size", 20);
        options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: Law = " + articles);
                mLawArticleList.addAll(articles);
                mLawNewsAdapter.notifyDataSetChanged();
                mProgressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure(): Law FAILED !!");
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }

    private void loadLawData() {
        Log.wtf(TAG, "loadLawData() has been instantiated");
        if (mLawArticleList.isEmpty()) {
            Map<String, Object> options = new HashMap<>();
            options.put("section", "law");
            options.put("order-by", "newest");
            options.put("show-tags", "contributor");
            options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
            options.put("page", 1);
            options.put("page-size", 20);
            options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
            Utils.getNews(options, new Utils.retrofitCallback() {
                @Override
                public void onSuccess(List<Article> articles) {
                    Log.wtf(TAG, "onSuccess: Law = " + articles);
                    mLawArticleList = articles;
                    mLawNewsAdapter = new NewsAdapter(getContext(), mLawArticleList);
                    mLawRecycler.setAdapter(mLawNewsAdapter);
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.wtf(TAG, "onFailure(): Law FAILED !!");
                }
            });
        } else {
            mLawNewsAdapter = new NewsAdapter(getContext(), mLawArticleList);
        }
        mLawRecycler.setAdapter(mLawNewsAdapter);
    }

    @Override
    public void onRefresh() {
        Log.wtf(TAG, "onRefresh() has been instantiated");
        mSwipeRefreshLayout.setRefreshing(true);
        mLawArticleList.clear();
        EndlessRecyclerOnScrollListener.mPreviousTotal = 0;
        currentPage = PAGE_START;
        Log.wtf(TAG, "onRefresh()::mLawArticleList = " + mLawArticleList.toString());
        loadLawData();
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
