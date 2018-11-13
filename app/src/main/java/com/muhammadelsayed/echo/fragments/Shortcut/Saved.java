package com.muhammadelsayed.echo.fragments.Shortcut;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.muhammadelsayed.echo.R;
import com.muhammadelsayed.echo.adapters.NewsAdapter;
import com.muhammadelsayed.echo.database.DatabaseHelper;
import com.muhammadelsayed.echo.model.Article;

import java.util.List;

public class Saved extends Fragment {
    private static final String TAG = "Saved";

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
        View rootView = inflater.inflate(R.layout.saved_shortcuts_tab, container, false);

        RecyclerView mSavedRecycler = rootView.findViewById(R.id.saved_recycler);
        LinearLayoutManager mLinearLayoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mSavedRecycler.setLayoutManager(mLinearLayoutManager);
        mSavedRecycler.setDrawingCacheEnabled(true);
        mSavedRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mSavedRecycler.setItemViewCacheSize(100);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mSavedRecycler.getContext(),
                mLinearLayoutManager.getOrientation());
        mSavedRecycler.addItemDecoration(dividerItemDecoration);
        TextView mNoResults = rootView.findViewById(R.id.no_results);

        Log.wtf(TAG, "onCreateView: SAVED = " + db.getAllSavedArticles());
        List<Article> articles = db.getAllSavedArticles();
        if (articles.isEmpty())
            mNoResults.setVisibility(View.VISIBLE);
        else {
            NewsAdapter mSavedNewsAdapter = new NewsAdapter(getContext(), articles);
            mSavedRecycler.setAdapter(mSavedNewsAdapter);
        }
        return rootView;
    }
}
