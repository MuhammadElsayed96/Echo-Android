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

import static com.muhammadelsayed.echo.SplashActivity.mSportArticleList;
import static com.muhammadelsayed.echo.SplashActivity.sportsList;

public class Sport extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
  private static final String TAG = Sport.class.getSimpleName();
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private NewsAdapter mSportNewsAdapter;
  private RecyclerView mSportRecycler;
  private String sportSources;

  public Sport() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    sportSources = "";
    if (getArguments() != null) {}
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    Log.wtf(TAG, "onCreateView() has been instantiated");

    // Inflate the layout for this fragment
    View rootView = inflater.inflate(R.layout.sport_home_tab, container, false);
    mSportRecycler = rootView.findViewById(R.id.sport_recycler);
    mSportRecycler.setHasFixedSize(true);
    LinearLayoutManager mLinearLayoutManager =
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    mSportRecycler.setLayoutManager(mLinearLayoutManager);
    mSportRecycler.setDrawingCacheEnabled(true);
    mSportRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    mSportRecycler.setItemViewCacheSize(20);
    mSwipeRefreshLayout = rootView.findViewById(R.id.sport_swipe);
    mSwipeRefreshLayout.setOnRefreshListener(this);
    mSwipeRefreshLayout.setColorSchemeResources(
        R.color.colorPrimary,
        android.R.color.holo_green_dark,
        android.R.color.holo_orange_dark,
        android.R.color.holo_blue_dark);

    loadSportsData();

    return rootView;
  }

  @Override
  public void onRefresh() {
    mSwipeRefreshLayout.setRefreshing(true);
    mSportArticleList.clear();
    loadSportsData();
    mSwipeRefreshLayout.setRefreshing(false);
  }

  private void loadSportsData() {
    Log.wtf(TAG, "loadSportData() has been instantiated");
    if (mSportArticleList.isEmpty()) {
      loadSportSources();
      Map<String, Object> options = new HashMap<>();
      options.put("apiKey", getResources().getString(R.string.news_api_key1));
      options.put("sources", sportSources);
      Utils.getTopHeadLines(
          options,
          new Utils.retrofitCallbackArticle() {
            @Override
            public void onSuccessArticle(List<Article> articles) {
              Log.wtf(TAG, "onSuccessArticle()::Sport");
              mSportArticleList = articles;
              mSportNewsAdapter = new NewsAdapter(getContext(), mSportArticleList);
              mSportRecycler.setAdapter(mSportNewsAdapter);
            }
          });
    } else {
      mSportNewsAdapter = new NewsAdapter(getContext(), mSportArticleList);
      mSportRecycler.setAdapter(mSportNewsAdapter);
    }
  }

  private void loadSportSources() {
    Log.wtf(TAG, "loadSportSources() has been instantiated");
    for (Source source : sportsList) {
      sportSources = sportSources.concat(source.getId() + ",");
    }
    sportSources.replaceAll(",$", "");
  }
}
