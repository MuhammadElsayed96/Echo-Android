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

import static com.muhammadelsayed.echo.SplashActivity.environmentList;

public class Environment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
  private static final String TAG = Environment.class.getSimpleName();
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private NewsAdapter mEnvironmentNewsAdapter;
  //  private static List<Article> mEnvironmentArticleList = new ArrayList<>();
  private RecyclerView mEnvironmentRecycler;

  public Environment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {}
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    Log.wtf(TAG, "onCreateView() has been instantiated");

    // Inflate the layout for this fragment
    View rootView = inflater.inflate(R.layout.environment_home_tab, container, false);
    mEnvironmentRecycler = rootView.findViewById(R.id.environment_recycler);
    mEnvironmentRecycler.setHasFixedSize(true);
    LinearLayoutManager mLinearLayoutManager =
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    mEnvironmentRecycler.setLayoutManager(mLinearLayoutManager);
    mSwipeRefreshLayout = rootView.findViewById(R.id.environment_swipe);
    mSwipeRefreshLayout.setOnRefreshListener(this);
    mSwipeRefreshLayout.setColorSchemeResources(
        R.color.colorPrimary,
        android.R.color.holo_green_dark,
        android.R.color.holo_orange_dark,
        android.R.color.holo_blue_dark);

    loadEnvironmentData();
    return rootView;
  }

  @Override
  public void onRefresh() {
    loadEnvironmentData();
  }

  private void loadEnvironmentData() {
    Log.wtf(TAG, "loadEnvironmentData() has been instantiated");
    mEnvironmentNewsAdapter = new NewsAdapter(getActivity(), environmentList);
    Log.wtf(TAG, "loadEnvironmentData: environmentList = " + environmentList);
    mEnvironmentRecycler.setAdapter(mEnvironmentNewsAdapter);
  }
}
