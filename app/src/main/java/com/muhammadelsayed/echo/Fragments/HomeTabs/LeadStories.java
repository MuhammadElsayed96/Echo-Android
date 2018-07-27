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


public class LeadStories extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
  private static final String TAG = LeadStories.class.getSimpleName();
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private NewsAdapter mLeadStoriesNewsAdapter;
  private RecyclerView mLeadStoriesRecycler;
  //  private static List<Article> mLeadStoriesArticleList = new ArrayList<>();

  public LeadStories() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    Log.wtf(TAG, "onCreate() has been instantiated");

    super.onCreate(savedInstanceState);
    if (getArguments() != null) {}
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    Log.wtf(TAG, "onCreateView() has been instantiated");

    // Inflate the layout for this fragment
    View rootView = inflater.inflate(R.layout.leadstories_home_tab, container, false);
    mLeadStoriesRecycler = rootView.findViewById(R.id.leadstories_recycler);
    mLeadStoriesRecycler.setHasFixedSize(true);
    LinearLayoutManager mLinearLayoutManager =
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    mLeadStoriesRecycler.setLayoutManager(mLinearLayoutManager);
    mSwipeRefreshLayout = rootView.findViewById(R.id.leadstories_swipe);
    mSwipeRefreshLayout.setOnRefreshListener(this);
    mSwipeRefreshLayout.setColorSchemeResources(
        R.color.colorPrimary,
        android.R.color.holo_green_dark,
        android.R.color.holo_orange_dark,
        android.R.color.holo_blue_dark);

    loadLeadStoriesData();

    return rootView;
  }

  @Override
  public void onRefresh() {
    loadLeadStoriesData();
  }

  private void loadLeadStoriesData() {
//    Log.wtf(TAG, "loadLeadStoriesData() has been instantiated");
//    mLeadStoriesNewsAdapter = new NewsAdapter(getActivity(), leadStoriesList);
//    Log.wtf(TAG, "loadLeadStoriesData: leadStoriesList = " + leadStoriesList);
//    mLeadStoriesRecycler.setAdapter(mLeadStoriesNewsAdapter);
  }
}
