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


public class Lifestyle extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

  private static final String TAG = Lifestyle.class.getSimpleName();
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private RecyclerView mLifestyleRecycler;
  private NewsAdapter mLifestyleNewsAdapter;
  //  private static List<Article> mLifestyleArticleList = new ArrayList<>();

  public Lifestyle() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.wtf(TAG, "onCreate() has been instantiated");
    if (getArguments() != null) {}
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    Log.wtf(TAG, "onCreateView() has been instantiated");

    // Inflate the layout for this fragment
    View rootView = inflater.inflate(R.layout.lifestyle_home_tab, container, false);
    mLifestyleRecycler = rootView.findViewById(R.id.lifestyle_recycler);
    mLifestyleRecycler.setHasFixedSize(true);
    LinearLayoutManager mLinearLayoutManager =
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    mLifestyleRecycler.setLayoutManager(mLinearLayoutManager);
    mSwipeRefreshLayout = rootView.findViewById(R.id.lifestyle_swipe);
    mSwipeRefreshLayout.setOnRefreshListener(this);
    mSwipeRefreshLayout.setColorSchemeResources(
        R.color.colorPrimary,
        android.R.color.holo_green_dark,
        android.R.color.holo_orange_dark,
        android.R.color.holo_blue_dark);

    loadLifestyleData();

    return rootView;
  }

  @Override
  public void onRefresh() {
    loadLifestyleData();
  }

  private void loadLifestyleData() {
//    Log.wtf(TAG, "loadLifestyleData() has been instantiated");
//    mLifestyleNewsAdapter = new NewsAdapter(getActivity(), lifestyleList);
//    Log.wtf(TAG, "loadLifestyleData: lifestyleList = " + lifestyleList);
//    mLifestyleRecycler.setAdapter(mLifestyleNewsAdapter);
  }
}
