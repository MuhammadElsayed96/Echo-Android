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
import com.muhammadelsayed.echo.model.Source;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.muhammadelsayed.echo.SplashActivity.generalList;
import static com.muhammadelsayed.echo.SplashActivity.mLeadStoriesArticleList;

public class LeadStories extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
  private static final String TAG = LeadStories.class.getSimpleName();
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private NewsAdapter mLeadStoriesNewsAdapter;
  private RecyclerView mLeadStoriesRecycler;
  private String leadStoriesSources;

  public LeadStories() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    Log.wtf(TAG, "onCreate() has been instantiated");
    super.onCreate(savedInstanceState);
    leadStoriesSources = "";
    if (getArguments() != null) {}
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    Log.wtf(TAG, "onCreateView() has been instantiated");
    // Inflate the layout for this fragment
    View rootView = inflater.inflate(R.layout.leadstories_home_tab, container, false);
    mLeadStoriesRecycler = rootView.findViewById(R.id.leadstories_recycler);
    mLeadStoriesRecycler.setHasFixedSize(true);
    LinearLayoutManager mLinearLayoutManager =
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    mLeadStoriesRecycler.setLayoutManager(mLinearLayoutManager);
    mLeadStoriesRecycler.setDrawingCacheEnabled(true);
    mLeadStoriesRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    mLeadStoriesRecycler.setItemViewCacheSize(20);
    mSwipeRefreshLayout = rootView.findViewById(R.id.leadstories_swipe);
    mSwipeRefreshLayout.setOnRefreshListener(this);
    mSwipeRefreshLayout.setColorSchemeResources(
        R.color.colorPrimary,
        android.R.color.holo_green_dark,
        android.R.color.holo_orange_dark,
        android.R.color.holo_blue_dark);

    loadLeadStoriesData();

    return rootView;
  }

  @Override
  public void onRefresh() {
    mSwipeRefreshLayout.setRefreshing(true);
    mLeadStoriesArticleList.clear();
    loadLeadStoriesData();
    mSwipeRefreshLayout.setRefreshing(false);
  }

  private void loadLeadStoriesData() {
    Log.wtf(TAG, "loadLeadStoriesData() has been instantiated");
    if (mLeadStoriesArticleList.isEmpty()) {
      loadLeadStoriesSources();
      Map<String, Object> options = new HashMap<>();
      options.put("apiKey", getResources().getString(R.string.news_api_key1));
      options.put("sources", leadStoriesSources);
      Utils.getTopHeadLines(
          options,
          new Utils.retrofitCallbackArticle() {
            @Override
            public void onSuccessArticle(List<Article> articles) {
              Log.wtf(TAG, "onSuccessArticle()::LeadStories");
              mLeadStoriesArticleList = articles;
              mLeadStoriesNewsAdapter = new NewsAdapter(getContext(), mLeadStoriesArticleList);
              mLeadStoriesRecycler.setAdapter(mLeadStoriesNewsAdapter);
            }
          });
    } else {
      mLeadStoriesNewsAdapter = new NewsAdapter(getContext(), mLeadStoriesArticleList);
      mLeadStoriesRecycler.setAdapter(mLeadStoriesNewsAdapter);
    }
  }

  private void loadLeadStoriesSources() {
    Log.wtf(TAG, "loadLeadStoriesSources() has been instantiated");
    for (Source source : generalList) {
      leadStoriesSources = leadStoriesSources.concat(source.getId() + ",");
    }
    leadStoriesSources.replaceAll(",$", "");
  }
}
