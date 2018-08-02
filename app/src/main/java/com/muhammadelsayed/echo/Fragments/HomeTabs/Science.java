package com.muhammadelsayed.echo.Fragments.HomeTabs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muhammadelsayed.echo.Adapters.NewsAdapter;

public class Science extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = Science.class.getSimpleName();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mScienceRecycler;
    private NewsAdapter mScienceNewsAdapter;
    private String scienceSources;

    public Science() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scienceSources = "";
        if (getArguments() != null) {
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onRefresh() {
    }

}
