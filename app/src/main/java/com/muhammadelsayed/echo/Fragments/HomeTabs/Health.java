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


public class Health extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
  private static final String TAG = Health.class.getSimpleName();
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private NewsAdapter mHealthNewsAdapter;
  private RecyclerView mHealthRecycler;
  //  private static List<Article> mHealthArticleList = new ArrayList<>();

  public Health() {
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
    View rootView = inflater.inflate(R.layout.health_home_tab, container, false);
    mHealthRecycler = rootView.findViewById(R.id.health_recycler);
    mHealthRecycler.setHasFixedSize(true);
    LinearLayoutManager mLinearLayoutManager =
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    mHealthRecycler.setLayoutManager(mLinearLayoutManager);
    mSwipeRefreshLayout = rootView.findViewById(R.id.health_swipe);
    mSwipeRefreshLayout.setOnRefreshListener(this);
    mSwipeRefreshLayout.setColorSchemeResources(
        R.color.colorPrimary,
        android.R.color.holo_green_dark,
        android.R.color.holo_orange_dark,
        android.R.color.holo_blue_dark);

    loadHealthData();

    return rootView;
  }

  @Override
  public void onRefresh() {
    loadHealthData();
  }

  private void loadHealthData() {
//    Log.wtf(TAG, "loadHealthData() has been instantiated");
//    mHealthNewsAdapter = new NewsAdapter(getActivity(), healthList);
//    Log.wtf(TAG, "loadHealthData: healthList = " + healthList);
//    mHealthRecycler.setAdapter(mHealthNewsAdapter);
  }
}
