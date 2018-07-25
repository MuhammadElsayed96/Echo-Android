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
import com.muhammadelsayed.echo.model.Article;

import java.util.ArrayList;
import java.util.List;

/** A simple {@link Fragment} subclass. create an instance of this fragment. */
public class World extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
  private static final String TAG = World.class.getSimpleName();
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private NewsAdapter mNewsAdapter;
  private List<Article> mArticleList = new ArrayList<>();

  public World() {
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
    View rootView = inflater.inflate(R.layout.world_home_tab, container, false);
    RecyclerView mWorldRecycler = rootView.findViewById(R.id.world_recycler);
    mWorldRecycler.setHasFixedSize(true);
    LinearLayoutManager mLinearLayoutManager =
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    mWorldRecycler.setLayoutManager(mLinearLayoutManager);
    mSwipeRefreshLayout = rootView.findViewById(R.id.world_swipe);
    mSwipeRefreshLayout.setOnRefreshListener(this);
    mSwipeRefreshLayout.setColorSchemeResources(
        R.color.colorPrimary,
        android.R.color.holo_green_dark,
        android.R.color.holo_orange_dark,
        android.R.color.holo_blue_dark);

    /*
     Showing Swipe Refresh animation on activity create As animation won't start on onCreate, post
     runnable is used
    */
    mSwipeRefreshLayout.post(
        new Runnable() {
          @Override
          public void run() {
            mSwipeRefreshLayout.setRefreshing(true);
            // Fetching data from server
            loadWorldData();
          }
        });
    return rootView;
  }

  @Override
  public void onRefresh() {
    loadWorldData();
  }

  private void loadWorldData() {}
}
