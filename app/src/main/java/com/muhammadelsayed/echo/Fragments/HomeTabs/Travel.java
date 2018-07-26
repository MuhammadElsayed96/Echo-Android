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

import static com.muhammadelsayed.echo.SplashActivity.travelList;

public class Travel extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
  private static final String TAG = Travel.class.getSimpleName();
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private NewsAdapter mTravelNewsAdapter;
  private RecyclerView mTravelRecycler;
  //  private List<Article> mArticleList = new ArrayList<>();

  public Travel() {
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
    View rootView = inflater.inflate(R.layout.travel_home_tab, container, false);
    mTravelRecycler = rootView.findViewById(R.id.travel_recycler);
    mTravelRecycler.setHasFixedSize(true);
    LinearLayoutManager mLinearLayoutManager =
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    mTravelRecycler.setLayoutManager(mLinearLayoutManager);
    mSwipeRefreshLayout = rootView.findViewById(R.id.travel_swipe);
    mSwipeRefreshLayout.setOnRefreshListener(this);
    mSwipeRefreshLayout.setColorSchemeResources(
        R.color.colorPrimary,
        android.R.color.holo_green_dark,
        android.R.color.holo_orange_dark,
        android.R.color.holo_blue_dark);

    loadTravelData();

    return rootView;
  }

  @Override
  public void onRefresh() {
    loadTravelData();
  }

  private void loadTravelData() {
    Log.wtf(TAG, "loadTravelData() has been instantiated");
    mTravelNewsAdapter = new NewsAdapter(getActivity(), travelList);
    Log.wtf(TAG, "loadTravelData: travelList = " + travelList);
    mTravelRecycler.setAdapter(mTravelNewsAdapter);
  }
}
