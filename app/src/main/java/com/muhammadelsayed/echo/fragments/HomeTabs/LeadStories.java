package com.muhammadelsayed.echo.fragments.HomeTabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muhammadelsayed.echo.R;
import com.muhammadelsayed.echo.adapters.NewsAdapter;

public class LeadStories extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = LeadStories.class.getSimpleName();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private NewsAdapter mLeadStoriesNewsAdapter;
    private RecyclerView mLeadStoriesRecycler;

    public LeadStories() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.wtf(TAG, "onCreate() has been instantiated");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.wtf(TAG, "onCreateView() has been instantiated");
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onRefresh() {
    }

}
