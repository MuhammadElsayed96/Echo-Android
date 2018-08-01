package com.muhammadelsayed.echo.Fragments.HomeTabs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muhammadelsayed.echo.Adapters.NewsAdapter;

public class Business extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
  private static final String TAG = Business.class.getSimpleName();
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private NewsAdapter mBusinessNewsAdapter;
  private RecyclerView mBusinessRecycler;
  private String businessSources;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.wtf(TAG, "onCreate() has been instantiated");
    businessSources = "";
    if (getArguments() != null) {}
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return super.onCreateView(inflater, container, savedInstanceState);
  }

  public Business() {
    // Required empty public constructor
  }

  @Override
  public void onRefresh() {
    Log.wtf(TAG, "onRefresh() has been instantiated");
  }

  @Override
  public void onPause() {
    super.onPause();
    Log.wtf(TAG, "onPause() has been instantiated");
  }

  @Override
  public void onStart() {
    super.onStart();
    Log.wtf(TAG, "onStart() has been instantiated");
  }

  @Override
  public void onStop() {
    super.onStop();
    Log.wtf(TAG, "onStop() has been instantiated");
  }
}
