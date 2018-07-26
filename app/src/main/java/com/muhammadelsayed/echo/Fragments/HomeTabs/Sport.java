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

import static com.muhammadelsayed.echo.SplashActivity.sportList;

public class Sport extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
  private static final String TAG = Sport.class.getSimpleName();
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private NewsAdapter mSportNewsAdapter;
  private RecyclerView mSportRecycler;
  //  private List<Article> mArticleList = new ArrayList<>();

  public Sport() {
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
    View rootView = inflater.inflate(R.layout.sport_home_tab, container, false);
    mSportRecycler = rootView.findViewById(R.id.sport_recycler);
    mSportRecycler.setHasFixedSize(true);
    LinearLayoutManager mLinearLayoutManager =
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    mSportRecycler.setLayoutManager(mLinearLayoutManager);
    mSwipeRefreshLayout = rootView.findViewById(R.id.sport_swipe);
    mSwipeRefreshLayout.setOnRefreshListener(this);
    mSwipeRefreshLayout.setColorSchemeResources(
        R.color.colorPrimary,
        android.R.color.holo_green_dark,
        android.R.color.holo_orange_dark,
        android.R.color.holo_blue_dark);

    loadSportData();

    return rootView;
  }

  @Override
  public void onRefresh() {
    loadSportData();
  }

  private void loadSportData() {
    Log.wtf(TAG, "loadSportData() has been instantiated");
    mSportNewsAdapter = new NewsAdapter(getActivity(), sportList);
    Log.wtf(TAG, "loadSportData: sportList = " + sportList);
    mSportRecycler.setAdapter(mSportNewsAdapter);
  }
}
