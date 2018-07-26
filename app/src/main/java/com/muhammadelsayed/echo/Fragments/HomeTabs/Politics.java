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

public class Politics extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
  private static final String TAG = Politics.class.getSimpleName();
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private NewsAdapter mPoliticsNewsAdapter;
  private RecyclerView mPoliticsRecycler;
  //  private List<Article> mArticleList = new ArrayList<>();

  public Politics() {
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
    View rootView = inflater.inflate(R.layout.politics_home_tab, container, false);
    mPoliticsRecycler = rootView.findViewById(R.id.politics_recycler);
    mPoliticsRecycler.setHasFixedSize(true);
    LinearLayoutManager mLinearLayoutManager =
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    mPoliticsRecycler.setLayoutManager(mLinearLayoutManager);
    mSwipeRefreshLayout = rootView.findViewById(R.id.politics_swipe);
    mSwipeRefreshLayout.setOnRefreshListener(this);
    mSwipeRefreshLayout.setColorSchemeResources(
        R.color.colorPrimary,
        android.R.color.holo_green_dark,
        android.R.color.holo_orange_dark,
        android.R.color.holo_blue_dark);

    loadPoliticsData();

    return rootView;
  }

  @Override
  public void onRefresh() {
    loadPoliticsData();
  }

  private void loadPoliticsData() {
    //    Log.wtf(TAG, "loadPoliticsData() has been instantiated");
    //    mPoliticsNewsAdapter = new NewsAdapter(getActivity(), politicsList);
    //    Log.wtf(TAG, "loadPoliticsData: politicsList = " + politicsList);
    //    mPoliticsRecycler.setAdapter(mPoliticsNewsAdapter);
  }
}
