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

import static com.muhammadelsayed.echo.SplashActivity.entertainmentList;

public class Entertainment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
  private static final String TAG = Entertainment.class.getSimpleName();
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private NewsAdapter mEntertainmentNewsAdapter;
  //  private static List<Article> mEntertainmentArticleList = new ArrayList<>();
  private RecyclerView mEntertainmentRecycler;

  public Entertainment() {
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
    View rootView = inflater.inflate(R.layout.entertainment_home_tab, container, false);
    mEntertainmentRecycler = rootView.findViewById(R.id.entertainment_recycler);
    mEntertainmentRecycler.setHasFixedSize(true);
    LinearLayoutManager mLinearLayoutManager =
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    mEntertainmentRecycler.setLayoutManager(mLinearLayoutManager);
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
    loadEntertainmentData();
    mSwipeRefreshLayout.setRefreshing(false);
  }

  private void loadEntertainmentData() {
    Log.wtf(TAG, "loadEntertainmentData() has been instantiated");
    mEntertainmentNewsAdapter = new NewsAdapter(getActivity(), entertainmentList);
    Log.wtf(TAG, "loadEntertainmentData: entertainmentList = " + entertainmentList);
    mEntertainmentRecycler.setAdapter(mEntertainmentNewsAdapter);
  }
}
