package com.muhammadelsayed.echo.Fragments.HomeTabs;

import android.databinding.DataBindingUtil;
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
import com.muhammadelsayed.echo.databinding.FragmentInternationalHeadlinesBinding;
import com.muhammadelsayed.echo.model.Article;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.muhammadelsayed.echo.SplashActivity.mInternationalArticleList;

public class InternationalHeadlines extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = InternationalHeadlines.class.getSimpleName();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private NewsAdapter mInternationalNewsAdapter;
    private RecyclerView mInternationalRecycler;

    private int mLoadedItems = 0;
    private FragmentInternationalHeadlinesBinding mFragmentInternationalHeadlinesBinding;

    public InternationalHeadlines() {
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

        mFragmentInternationalHeadlinesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_international_headlines, container, false);

        View rootView = mFragmentInternationalHeadlinesBinding.getRoot();


        mInternationalRecycler = rootView.findViewById(R.id.international_recycler);
        LinearLayoutManager mLinearLayoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mInternationalRecycler.setLayoutManager(mLinearLayoutManager);
        mSwipeRefreshLayout = rootView.findViewById(R.id.international_swipe);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mInternationalRecycler.setDrawingCacheEnabled(true);
        mInternationalRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mInternationalRecycler.setItemViewCacheSize(100);

        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        loadInternationalData();


        return rootView;
    }

    private void loadInternationalData() {
        Log.wtf(TAG, "loadInternationalData() has been instantiated");
        if (mInternationalArticleList.isEmpty()) {
            Map<String, Object> options = new HashMap<>();
            options.put("section", "news");
            options.put("order-by", "newest");
            options.put("show-tags", "contributor");
            options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
            options.put("page", 1);
            options.put("page-size", 20);
            options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
            Utils.getNews(options, new Utils.retrofitCallback() {
                @Override
                public void onSuccess(List<Article> articles) {
                    Log.wtf(TAG, "onSuccess: International = " + articles);
                    mInternationalArticleList = articles;
                    mInternationalNewsAdapter = new NewsAdapter(getContext(), mInternationalArticleList);
                    mInternationalRecycler.setAdapter(mInternationalNewsAdapter);
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.wtf(TAG, "onFailure(): International FAILED !!");
                }
            });
        } else {
            mInternationalNewsAdapter = new NewsAdapter(getContext(), mInternationalArticleList);
        }
        mInternationalRecycler.setAdapter(mInternationalNewsAdapter);
    }

    @Override
    public void onRefresh() {
        Log.wtf(TAG, "onRefresh() has been instantiated");
        mSwipeRefreshLayout.setRefreshing(true);
        mInternationalArticleList.clear();
        Log.wtf(TAG, "onRefresh()::mInternationalArticleList = " + mInternationalArticleList.toString());
        loadInternationalData();
        mSwipeRefreshLayout.setRefreshing(false);
    }

}
