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

import static com.muhammadelsayed.echo.SplashActivity.businessList;

public class Business extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
  private static final String TAG = Business.class.getSimpleName();
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private NewsAdapter mBusinessNewsAdapter;
  private RecyclerView mBusinessRecycler;
  //  private static List<Article> mBusinessArticleList = new ArrayList<>();

  public Business() {
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
    View rootView = inflater.inflate(R.layout.business_home_tab, container, false);
    mBusinessRecycler = rootView.findViewById(R.id.business_recycler);
    mBusinessRecycler.setHasFixedSize(true);
    LinearLayoutManager mLinearLayoutManager =
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    mBusinessRecycler.setLayoutManager(mLinearLayoutManager);
    mSwipeRefreshLayout = rootView.findViewById(R.id.business_swipe);
    mSwipeRefreshLayout.setOnRefreshListener(this);
    mSwipeRefreshLayout.setColorSchemeResources(
        android.R.color.holo_red_light,
        android.R.color.holo_green_dark,
        android.R.color.holo_orange_dark,
        android.R.color.holo_blue_dark);

    loadBusinessData();
    /*
     Showing Swipe Refresh animation on activity create As animation won't start on onCreate, post
     runnable is used
    */
    //    mSwipeRefreshLayout.post(
    //        new Runnable() {
    //          @Override
    //          public void run() {
    //
    //            mSwipeRefreshLayout.setRefreshing(true);
    //
    //            // Fetching data from server
    //            loadBusinessData();
    //            mSwipeRefreshLayout.setRefreshing(false);
    //          }
    //        });
    return rootView;
  }

  @Override
  public void onRefresh() {
    mSwipeRefreshLayout.setRefreshing(true);
    loadBusinessData();
    mSwipeRefreshLayout.setRefreshing(false);
  }

  private void loadBusinessData() {
    Log.wtf(TAG, "loadBusinessData() has been instantiated");
    mBusinessNewsAdapter = new NewsAdapter(getActivity(), businessList);
    Log.wtf(TAG, "loadBusinessData: businessList = " + businessList);
    mBusinessRecycler.setAdapter(mBusinessNewsAdapter);
  }
}
