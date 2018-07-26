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

/** A simple {@link Fragment} subclass. create an instance of this fragment. */
public class World extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
  private static final String TAG = World.class.getSimpleName();
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private NewsAdapter mWorldNewsAdapter;
  private RecyclerView mWorldRecycler;
  //  private List<Article> mArticleList = new ArrayList<>();

  public World() {
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
    View rootView = inflater.inflate(R.layout.world_home_tab, container, false);
    mWorldRecycler = rootView.findViewById(R.id.world_recycler);
    mWorldRecycler.setHasFixedSize(true);
    LinearLayoutManager mLinearLayoutManager =
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    mWorldRecycler.setLayoutManager(mLinearLayoutManager);
    mSwipeRefreshLayout = rootView.findViewById(R.id.world_swipe);
    mSwipeRefreshLayout.setOnRefreshListener(this);
    mSwipeRefreshLayout.setColorSchemeResources(
        R.color.colorPrimary,
        android.R.color.holo_green_dark,
        android.R.color.holo_orange_dark,
        android.R.color.holo_blue_dark);

    loadWorldData();

    return rootView;
  }

  @Override
  public void onRefresh() {
    loadWorldData();
  }

  private void loadWorldData() {
    //    Log.wtf(TAG, "loadWorldData() has been instantiated");
    //    mWorldNewsAdapter = new NewsAdapter(getActivity(), worldList);
    //    Log.wtf(TAG, "loadWorldData: worldList = " + worldList);
    //    mWorldRecycler.setAdapter(mWorldNewsAdapter);
  }
}
