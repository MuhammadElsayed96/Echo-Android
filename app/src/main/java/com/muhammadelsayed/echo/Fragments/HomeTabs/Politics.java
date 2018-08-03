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

import static com.muhammadelsayed.echo.SplashActivity.mPoliticsArticleList;


public class Politics extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = Politics.class.getSimpleName();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private NewsAdapter mPoliticsNewsAdapter;
    private RecyclerView mPoliticsRecycler;

    public Politics() {
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
        View rootView = inflater.inflate(R.layout.fragment_politics, container, false);
        mPoliticsRecycler = rootView.findViewById(R.id.politics_recycler);
        LinearLayoutManager mLinearLayoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mPoliticsRecycler.setLayoutManager(mLinearLayoutManager);
        mSwipeRefreshLayout = rootView.findViewById(R.id.politics_swipe);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mPoliticsRecycler.setDrawingCacheEnabled(true);
        mPoliticsRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mPoliticsRecycler.setItemViewCacheSize(100);

        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        loadPoliticsData();
        return rootView;
    }

    private void loadPoliticsData() {
        Log.wtf(TAG, "loadPoliticsData() has been instantiated");
        if (mPoliticsArticleList.isEmpty()) {
            Map<String, Object> options = new HashMap<>();
            options.put("section", "politics");
            options.put("order-by", "newest");
            options.put("show-tags", "contributor");
            options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
            options.put("page", 1);
            options.put("page-size", 20);
            options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
            Utils.getNews(options, new Utils.retrofitCallback() {
                @Override
                public void onSuccess(List<Article> articles) {
                    Log.wtf(TAG, "onSuccess: POLITICS = " + articles);
                    mPoliticsArticleList = articles;
                    mPoliticsNewsAdapter = new NewsAdapter(getContext(), mPoliticsArticleList);
                    mPoliticsRecycler.setAdapter(mPoliticsNewsAdapter);
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.wtf(TAG, "onFailure(): POLITICS FAILED !!");
                }
            });
        } else {
            mPoliticsNewsAdapter = new NewsAdapter(getContext(), mPoliticsArticleList);
        }
        mPoliticsRecycler.setAdapter(mPoliticsNewsAdapter);
    }

    @Override
    public void onRefresh() {
        Log.wtf(TAG, "onRefresh() has been instantiated");
        mSwipeRefreshLayout.setRefreshing(true);
        mPoliticsArticleList.clear();
        Log.wtf(TAG, "onRefresh()::mPoliticsArticleList = " + mPoliticsArticleList.toString());
        loadPoliticsData();
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
