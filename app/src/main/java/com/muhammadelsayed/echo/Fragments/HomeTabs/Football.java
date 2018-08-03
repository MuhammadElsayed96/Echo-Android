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

import static com.muhammadelsayed.echo.SplashActivity.mFootballArticleList;

public class Football extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = Football.class.getSimpleName();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private NewsAdapter mFootballNewsAdapter;
    private RecyclerView mFootballRecycler;

    public Football() {
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
        View rootView = inflater.inflate(R.layout.fragment_football, container, false);

        mFootballRecycler = rootView.findViewById(R.id.football_recycler);
        LinearLayoutManager mLinearLayoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mFootballRecycler.setLayoutManager(mLinearLayoutManager);
        mSwipeRefreshLayout = rootView.findViewById(R.id.football_swipe);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mFootballRecycler.setDrawingCacheEnabled(true);
        mFootballRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mFootballRecycler.setItemViewCacheSize(100);

        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        loadFootballData();

        return rootView;
    }

    private void loadFootballData() {
        Log.wtf(TAG, "loadFootballData() has been instantiated");
        if (mFootballArticleList.isEmpty()) {
            Map<String, Object> options = new HashMap<>();
            options.put("section", "football");
            options.put("order-by", "newest");
            options.put("show-tags", "contributor");
            options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
            options.put("page", 1);
            options.put("page-size", 20);
            options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
            Utils.getNews(options, new Utils.retrofitCallback() {
                @Override
                public void onSuccess(List<Article> articles) {
                    Log.wtf(TAG, "onSuccess: Football = " + articles);
                    mFootballArticleList = articles;
                    mFootballNewsAdapter = new NewsAdapter(getContext(), mFootballArticleList);
                    mFootballRecycler.setAdapter(mFootballNewsAdapter);
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.wtf(TAG, "onFailure(): Football FAILED !!");
                }
            });
        } else {
            mFootballNewsAdapter = new NewsAdapter(getContext(), mFootballArticleList);
        }
        mFootballRecycler.setAdapter(mFootballNewsAdapter);
    }

    @Override
    public void onRefresh() {
        Log.wtf(TAG, "onRefresh() has been instantiated");
        mSwipeRefreshLayout.setRefreshing(true);
        mFootballArticleList.clear();
        Log.wtf(TAG, "onRefresh()::mFootballArticleList = " + mFootballArticleList.toString());
        loadFootballData();
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
