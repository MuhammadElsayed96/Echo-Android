package com.muhammadelsayed.echo.Fragments.HomeTabs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import static com.muhammadelsayed.echo.SplashActivity.mWorldArticleList;


/**
 * A simple {@link Fragment} subclass. create an instance of this fragment.
 */
public class World extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = World.class.getSimpleName();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private NewsAdapter mWorldNewsAdapter;
    private RecyclerView mWorldRecycler;

    public World() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_world, container, false);
        mWorldRecycler = rootView.findViewById(R.id.world_recycler);
        LinearLayoutManager mLinearLayoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mWorldRecycler.setLayoutManager(mLinearLayoutManager);
        mSwipeRefreshLayout = rootView.findViewById(R.id.world_swipe);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mWorldRecycler.setDrawingCacheEnabled(true);
        mWorldRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mWorldRecycler.setItemViewCacheSize(100);

        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        loadWorldData();
        return rootView;
    }

    private void loadWorldData() {
        Log.wtf(TAG, "loadWorldData() has been instantiated");
        if (mWorldArticleList.isEmpty()) {
            Map<String, Object> options = new HashMap<>();
            options.put("section", "world");
            options.put("order-by", "newest");
            options.put("show-tags", "contributor");
            options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
            options.put("page", 1);
            options.put("page-size", 20);
            options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
            Utils.getNews(options, new Utils.retrofitCallback() {
                @Override
                public void onSuccess(List<Article> articles) {
                    Log.wtf(TAG, "onSuccess: World = " + articles);
                    mWorldArticleList = articles;
                    mWorldNewsAdapter = new NewsAdapter(getContext(), mWorldArticleList);
                    mWorldRecycler.setAdapter(mWorldNewsAdapter);
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.wtf(TAG, "onFailure(): World FAILED !!");
                }
            });
        } else {
            mWorldNewsAdapter = new NewsAdapter(getContext(), mWorldArticleList);
        }
        mWorldRecycler.setAdapter(mWorldNewsAdapter);
    }

    @Override
    public void onRefresh() {
        Log.wtf(TAG, "onRefresh() has been instantiated");
        mSwipeRefreshLayout.setRefreshing(true);
        mWorldArticleList.clear();
        Log.wtf(TAG, "onRefresh()::mWorldArticleList = " + mWorldArticleList.toString());
        loadWorldData();
        mSwipeRefreshLayout.setRefreshing(false);
    }

}
