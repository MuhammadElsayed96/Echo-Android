package com.muhammadelsayed.echo.Fragments.HomeTabs;

import android.os.Bundle;
import android.support.annotation.NonNull;
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

import static com.muhammadelsayed.echo.SplashActivity.mTravelArticleList;


public class Travel extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = Travel.class.getSimpleName();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private NewsAdapter mTravelNewsAdapter;
    private RecyclerView mTravelRecycler;

    public Travel() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.wtf(TAG, "onCreateView() has been instantiated");
        View rootView = inflater.inflate(R.layout.fragment_travel, container, false);
        mTravelRecycler = rootView.findViewById(R.id.travel_recycler);
        LinearLayoutManager mLinearLayoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mTravelRecycler.setLayoutManager(mLinearLayoutManager);
        mSwipeRefreshLayout = rootView.findViewById(R.id.travel_swipe);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mTravelRecycler.setDrawingCacheEnabled(true);
        mTravelRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mTravelRecycler.setItemViewCacheSize(100);

        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        loadTravelData();
        return rootView;
    }

    private void loadTravelData() {
        Log.wtf(TAG, "loadTravelData() has been instantiated");
        if (mTravelArticleList.isEmpty()) {
            Map<String, Object> options = new HashMap<>();
            options.put("section", "travel");
            options.put("order-by", "newest");
            options.put("show-tags", "contributor");
            options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
            options.put("page", 1);
            options.put("page-size", 20);
            options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
            Utils.getNews(options, new Utils.retrofitCallback() {
                @Override
                public void onSuccess(List<Article> articles) {
                    Log.wtf(TAG, "onSuccess: Travel = " + articles);
                    mTravelArticleList = articles;
                    mTravelNewsAdapter = new NewsAdapter(getContext(), mTravelArticleList);
                    mTravelRecycler.setAdapter(mTravelNewsAdapter);
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.wtf(TAG, "onFailure(): Travel FAILED !!");
                }
            });
        } else {
            mTravelNewsAdapter = new NewsAdapter(getContext(), mTravelArticleList);
        }
        mTravelRecycler.setAdapter(mTravelNewsAdapter);
    }

    @Override
    public void onRefresh() {
        Log.wtf(TAG, "onRefresh() has been instantiated");
        mSwipeRefreshLayout.setRefreshing(true);
        mTravelArticleList.clear();
        Log.wtf(TAG, "onRefresh()::mTravelArticleList = " + mTravelArticleList.toString());
        loadTravelData();
        mSwipeRefreshLayout.setRefreshing(false);
    }

}
