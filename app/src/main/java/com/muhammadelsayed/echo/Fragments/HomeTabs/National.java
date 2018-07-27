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


public class National extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
  private static final String TAG = National.class.getSimpleName();
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private NewsAdapter mNationalNewsAdapter;
  private RecyclerView mNationalRecycler;
  //  private static List<Article> mNationalArticleList = new ArrayList<>();

  public National() {
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
    View rootView = inflater.inflate(R.layout.national_home_tab, container, false);
    mNationalRecycler = rootView.findViewById(R.id.national_recycler);
    mNationalRecycler.setHasFixedSize(true);
    LinearLayoutManager mLinearLayoutManager =
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    mNationalRecycler.setLayoutManager(mLinearLayoutManager);
    mSwipeRefreshLayout = rootView.findViewById(R.id.national_swipe);
    mSwipeRefreshLayout.setOnRefreshListener(this);
    mSwipeRefreshLayout.setColorSchemeResources(
        R.color.colorPrimary,
        android.R.color.holo_green_dark,
        android.R.color.holo_orange_dark,
        android.R.color.holo_blue_dark);

    loadNationalData();

    return rootView;
  }

  @Override
  public void onRefresh() {
    loadNationalData();
  }

  private void loadNationalData() {
//    Log.wtf(TAG, "loadNationalData() has been instantiated");
//    mNationalNewsAdapter = new NewsAdapter(getActivity(), nationalList);
//    Log.wtf(TAG, "loadNationalData: nationalList = " + nationalList);
//    mNationalRecycler.setAdapter(mNationalNewsAdapter);
  }
}
