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

import static com.muhammadelsayed.echo.SplashActivity.technologyList;

public class Technology extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
  private static final String TAG = Technology.class.getSimpleName();
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private RecyclerView mTechnologyRecycler;
  private NewsAdapter mTechnologyNewsAdapter;
  //  private List<Article> mArticleList = new ArrayList<>();

  public Technology() {
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
    View rootView = inflater.inflate(R.layout.technology_home_tab, container, false);
    mTechnologyRecycler = rootView.findViewById(R.id.technology_recycler);
    mTechnologyRecycler.setHasFixedSize(true);
    LinearLayoutManager mLinearLayoutManager =
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    mTechnologyRecycler.setLayoutManager(mLinearLayoutManager);
    mSwipeRefreshLayout = rootView.findViewById(R.id.technology_swipe);
    mSwipeRefreshLayout.setOnRefreshListener(this);
    mSwipeRefreshLayout.setColorSchemeResources(
        R.color.colorPrimary,
        android.R.color.holo_green_dark,
        android.R.color.holo_orange_dark,
        android.R.color.holo_blue_dark);

    loadTechnologyData();

    return rootView;
  }

  @Override
  public void onRefresh() {
    loadTechnologyData();
  }

  private void loadTechnologyData() {
    Log.wtf(TAG, "loadTechnologyData() has been instantiated");
    mTechnologyNewsAdapter = new NewsAdapter(getActivity(), technologyList);
    Log.wtf(TAG, "loadTechnologyData: technologyList = " + technologyList);
    mTechnologyRecycler.setAdapter(mTechnologyNewsAdapter);
  }
}
