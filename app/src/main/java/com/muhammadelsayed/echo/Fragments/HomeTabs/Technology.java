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

import static com.muhammadelsayed.echo.SplashActivity.mTechnologyArticleList;
import static com.muhammadelsayed.echo.SplashActivity.technologyList;

public class Technology extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
  private static final String TAG = Technology.class.getSimpleName();
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private RecyclerView mTechnologyRecycler;
  private NewsAdapter mTechnologyNewsAdapter;
  private String technologySources;

  public Technology() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    technologySources = "";
    if (getArguments() != null) {}
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    Log.wtf(TAG, "onCreateView() has been instantiated");

    // Inflate the layout for this fragment
    View rootView = inflater.inflate(R.layout.technology_home_tab, container, false);
    mTechnologyRecycler = rootView.findViewById(R.id.technology_recycler);
    mTechnologyRecycler.setHasFixedSize(true);
    LinearLayoutManager mLinearLayoutManager =
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    mTechnologyRecycler.setLayoutManager(mLinearLayoutManager);
    mTechnologyRecycler.setDrawingCacheEnabled(true);
    mTechnologyRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    mTechnologyRecycler.setItemViewCacheSize(20);
    mSwipeRefreshLayout = rootView.findViewById(R.id.technology_swipe);
    mSwipeRefreshLayout.setOnRefreshListener(this);
    mSwipeRefreshLayout.setColorSchemeResources(
        R.color.colorPrimary,
        android.R.color.holo_green_dark,
        android.R.color.holo_orange_dark,
        android.R.color.holo_blue_dark);

    loadTechnologyData();

    return rootView;
  }

  @Override
  public void onRefresh() {
    mSwipeRefreshLayout.setRefreshing(true);
    mTechnologyArticleList.clear();
    loadTechnologyData();
    mSwipeRefreshLayout.setRefreshing(false);
  }

  private void loadTechnologyData() {
    Log.wtf(TAG, "loadTechnologyData() has been instantiated");
    if (mTechnologyArticleList.isEmpty()) {
      loadTechnologySources();
      Map<String, Object> options = new HashMap<>();
      options.put("apiKey", getResources().getString(R.string.news_api_key1));
      options.put("sources", technologySources);
      Utils.getTopHeadLines(
          options,
          new Utils.retrofitCallbackArticle() {
            @Override
            public void onSuccessArticle(List<Article> articles) {
              Log.wtf(TAG, "onSuccessArticle()::Technology");
              mTechnologyArticleList = articles;
              mTechnologyNewsAdapter = new NewsAdapter(getContext(), mTechnologyArticleList);
              mTechnologyRecycler.setAdapter(mTechnologyNewsAdapter);
            }
          });
    } else {
      mTechnologyNewsAdapter = new NewsAdapter(getContext(), mTechnologyArticleList);
      mTechnologyRecycler.setAdapter(mTechnologyNewsAdapter);
    }
  }

  private void loadTechnologySources() {
    Log.wtf(TAG, "loadTechnologySources() has been instantiated");
    for (Source source : technologyList) {
      technologySources = technologySources.concat(source.getId() + ",");
    }
    technologySources.replaceAll(",$", "");
  }
}
