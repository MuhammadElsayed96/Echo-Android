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

import static com.muhammadelsayed.echo.SplashActivity.scienceList;

public class Science extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
  private static final String TAG = Science.class.getSimpleName();
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private RecyclerView mScienceRecycler;
  private NewsAdapter mScienceNewsAdapter;
  //  private List<Article> mArticleList = new ArrayList<>();

  public Science() {
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
    View rootView = inflater.inflate(R.layout.science_home_tab, container, false);
    mScienceRecycler = rootView.findViewById(R.id.science_recycler);
    mScienceRecycler.setHasFixedSize(true);
    LinearLayoutManager mLinearLayoutManager =
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    mScienceRecycler.setLayoutManager(mLinearLayoutManager);
    mSwipeRefreshLayout = rootView.findViewById(R.id.science_swipe);
    mSwipeRefreshLayout.setOnRefreshListener(this);
    mSwipeRefreshLayout.setColorSchemeResources(
        R.color.colorPrimary,
        android.R.color.holo_green_dark,
        android.R.color.holo_orange_dark,
        android.R.color.holo_blue_dark);

    loadScienceData();

    return rootView;
  }

  @Override
  public void onRefresh() {
    loadScienceData();
  }

  private void loadScienceData() {
    Log.wtf(TAG, "loadScienceData() has been instantiated");
    mScienceNewsAdapter = new NewsAdapter(getActivity(), scienceList);
    Log.wtf(TAG, "loadScienceData: scienceList = " + scienceList);
    mScienceRecycler.setAdapter(mScienceNewsAdapter);
  }
}
