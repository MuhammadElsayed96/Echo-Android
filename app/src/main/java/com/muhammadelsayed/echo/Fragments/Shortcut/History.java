package com.muhammadelsayed.echo.Fragments.Shortcut;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.muhammadelsayed.echo.Adapters.NewsAdapter;
import com.muhammadelsayed.echo.Database.DatabaseHelper;
import com.muhammadelsayed.echo.Model.Article;
import com.muhammadelsayed.echo.R;

import java.util.List;


public class History extends Fragment {

    private static final String TAG = "History";

    private NewsAdapter mHistoryNewsAdapter;
    private RecyclerView mHistoryRecycler;
    private TextView mNoResults;

    private DatabaseHelper db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHelper(getActivity());

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.history_shortcuts_tab, container, false);

        mHistoryRecycler = rootView.findViewById(R.id.history_recycler);
        LinearLayoutManager mLinearLayoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mHistoryRecycler.setLayoutManager(mLinearLayoutManager);
        mHistoryRecycler.setDrawingCacheEnabled(true);
        mHistoryRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mHistoryRecycler.setItemViewCacheSize(100);
        mNoResults = rootView.findViewById(R.id.no_results);

        Log.wtf(TAG, "onCreateView: HISTORY = " + db.getAllHistoryArticles());

        List<Article> articles = db.getAllHistoryArticles();

        if (articles.isEmpty())
            mNoResults.setVisibility(View.VISIBLE);
        else {
            mHistoryNewsAdapter = new NewsAdapter(getContext(), articles);
            mHistoryRecycler.setAdapter(mHistoryNewsAdapter);
        }
        return rootView;
    }

}
