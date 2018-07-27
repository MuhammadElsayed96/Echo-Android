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

import static com.muhammadelsayed.echo.SplashActivity.entertainmentList;
import static com.muhammadelsayed.echo.SplashActivity.mEntertainmentArticleList;

public class Entertainment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
  private static final String TAG = Entertainment.class.getSimpleName();
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private NewsAdapter mEntertainmentNewsAdapter;
  private RecyclerView mEntertainmentRecycler;
  private String entertainmentSources;

  public Entertainment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    entertainmentSources = "";
    if (getArguments() != null) {}
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    Log.wtf(TAG, "onCreateView() has been instantiated");

    // Inflate the layout for this fragment
    View rootView = inflater.inflate(R.layout.entertainment_home_tab, container, false);
    mEntertainmentRecycler = rootView.findViewById(R.id.entertainment_recycler);
    mEntertainmentRecycler.setHasFixedSize(true);
    LinearLayoutManager mLinearLayoutManager =
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    mEntertainmentRecycler.setLayoutManager(mLinearLayoutManager);
    mEntertainmentRecycler.setDrawingCacheEnabled(true);
    mEntertainmentRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    mEntertainmentRecycler.setItemViewCacheSize(20);
    mSwipeRefreshLayout = rootView.findViewById(R.id.entertainment_swipe);
    mSwipeRefreshLayout.setOnRefreshListener(this);
    mSwipeRefreshLayout.setColorSchemeResources(
        R.color.colorPrimary,
        android.R.color.holo_green_dark,
        android.R.color.holo_orange_dark,
        android.R.color.holo_blue_dark);

    loadEntertainmentData();

    return rootView;
  }

  @Override
  public void onRefresh() {
    mSwipeRefreshLayout.setRefreshing(true);
    mEntertainmentArticleList.clear();
    loadEntertainmentData();
    mSwipeRefreshLayout.setRefreshing(false);
  }

  private void loadEntertainmentData() {
    Log.wtf(TAG, "loadEntertainmentData() has been instantiated");
    if (mEntertainmentArticleList.isEmpty()) {
      loadEntertainmentSources();
      Map<String, Object> options = new HashMap<>();
      options.put("apiKey", getResources().getString(R.string.news_api_key1));
      options.put("sources", entertainmentSources);
      Utils.getTopHeadLines(
          options,
          new Utils.retrofitCallbackArticle() {
            @Override
            public void onSuccessArticle(List<Article> articles) {
              Log.wtf(TAG, "onSuccessArticle()::Entertainment");
              mEntertainmentArticleList = articles;
              mEntertainmentNewsAdapter = new NewsAdapter(getContext(), mEntertainmentArticleList);
              mEntertainmentRecycler.setAdapter(mEntertainmentNewsAdapter);
            }
          });
    } else {
      mEntertainmentNewsAdapter = new NewsAdapter(getContext(), mEntertainmentArticleList);
      mEntertainmentRecycler.setAdapter(mEntertainmentNewsAdapter);
    }
  }

  private void loadEntertainmentSources() {
    Log.wtf(TAG, "loadEntertainmentSources() has been instantiated");
    for (Source source : entertainmentList) {
      entertainmentSources = entertainmentSources.concat(source.getId() + ",");
    }
    entertainmentSources.replaceAll(",$", "");
  }
}
