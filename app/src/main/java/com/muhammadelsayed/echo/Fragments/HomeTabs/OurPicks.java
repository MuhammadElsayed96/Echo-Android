package com.muhammadelsayed.echo.Fragments.HomeTabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muhammadelsayed.echo.Adapters.NewsAdapter;
import com.muhammadelsayed.echo.R;
import com.muhammadelsayed.echo.model.Article;

import java.util.ArrayList;
import java.util.List;

public class OurPicks extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
  private static final String TAG = OurPicks.class.getSimpleName();
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private List<Article> mArticleList = new ArrayList<>();
  private NewsAdapter mNewsAdapter;

  public OurPicks() {
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
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.ourpicks_home_tab, container, false);
  }

  @Override
  public void onRefresh() {}
}
