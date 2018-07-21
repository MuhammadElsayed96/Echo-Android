package com.muhammadelsayed.echo.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muhammadelsayed.echo.Adapters.SourcesAdapter;
import com.muhammadelsayed.echo.R;
import com.muhammadelsayed.echo.model.Source;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SourcesFragment#sourcesFragmentInstance} factory method to
 * create an instance of this fragment.
 */
public class SourcesFragment extends Fragment {

    private View rootView;
    private List<Source> mSourcesList = new ArrayList<>();
    private RecyclerView mSourcesRecyclerView;
    private SourcesAdapter mSourcesAdapter;

    public SourcesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SourcesFragment.
     */
    public static SourcesFragment sourcesFragmentInstance() {
        SourcesFragment fragment = new SourcesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_sources, container, false);
        mSourcesRecyclerView = rootView.findViewById(R.id.recycler_view);
        mSourcesAdapter = new SourcesAdapter(mSourcesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mSourcesRecyclerView.setHasFixedSize(true);
        mSourcesRecyclerView.setLayoutManager(mLayoutManager);
        mSourcesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mSourcesRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL));
        mSourcesRecyclerView.setAdapter(mSourcesAdapter);

        prepareSourcesData();

        return rootView;
    }

    private void prepareSourcesData() {
    }

}
