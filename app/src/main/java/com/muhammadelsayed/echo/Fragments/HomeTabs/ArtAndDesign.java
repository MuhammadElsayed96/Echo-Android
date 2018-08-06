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
import android.widget.ProgressBar;

import com.muhammadelsayed.echo.Adapters.EndlessRecyclerOnScrollListener;
import com.muhammadelsayed.echo.Adapters.NewsAdapter;
import com.muhammadelsayed.echo.R;
import com.muhammadelsayed.echo.Utils;
import com.muhammadelsayed.echo.model.Article;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.muhammadelsayed.echo.SplashActivity.mArtAndDesignArticleList;
import static com.muhammadelsayed.echo.Utils.isNetworkAvailable;


public class ArtAndDesign extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = ArtAndDesign.class.getSimpleName();
    private final int PAGE_START = 1;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private NewsAdapter mArtAndDesignAdapter;
    private RecyclerView mArtAndDesignRecycler;
    private ProgressBar mProgressBar;
    private SweetAlertDialog noInternet;
    private int currentPage = PAGE_START;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.wtf(TAG, "onCreate() has been instantiated");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.wtf(TAG, "onCreate() has been instantiated");
        View rootView = inflater.inflate(R.layout.fragment_art_design, container, false);

        mProgressBar = rootView.findViewById(R.id.progressBar);
        mArtAndDesignRecycler = rootView.findViewById(R.id.recycler);
        LinearLayoutManager mLinearLayoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mArtAndDesignRecycler.setLayoutManager(mLinearLayoutManager);
        mSwipeRefreshLayout = rootView.findViewById(R.id.swipe);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mArtAndDesignRecycler.setDrawingCacheEnabled(true);
        mArtAndDesignRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mArtAndDesignRecycler.setItemViewCacheSize(100);

        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        loadArtAndDesignData();

        mArtAndDesignRecycler.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                currentPage++;
                addDataToList(currentPage);
            }
        });
        return rootView;
    }

    private void addDataToList(int page) {
        mProgressBar.setVisibility(View.VISIBLE);
        Map<String, Object> options = new HashMap<>();
        options.put("section", "artanddesign");
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", page);
        options.put("page-size", 20);
        options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: AustraliaHeadlines = " + articles);
                mArtAndDesignArticleList.addAll(articles);
                mArtAndDesignAdapter.notifyDataSetChanged();
                mProgressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure(): AustraliaHeadlines FAILED !!");
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }

    private void loadArtAndDesignData() {
        Log.wtf(TAG, "loadArtAndDesignData() has been instantiated");
        if (mArtAndDesignArticleList.isEmpty()) {
            if (isNetworkAvailable()) {
                Map<String, Object> options = new HashMap<>();
                options.put("section", "artanddesign");
                options.put("order-by", "newest");
                options.put("show-tags", "contributor");
                options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
                options.put("page", 1);
                options.put("page-size", 20);
                options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
                Utils.getNews(options, new Utils.retrofitCallback() {
                    @Override
                    public void onSuccess(List<Article> articles) {
                        Log.wtf(TAG, "onSuccess: Art & Design = " + articles);
                        mArtAndDesignArticleList = articles;
                        mArtAndDesignAdapter = new NewsAdapter(getContext(), mArtAndDesignArticleList);
                        mArtAndDesignRecycler.setAdapter(mArtAndDesignAdapter);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.wtf(TAG, "onFailure(): Art & Design FAILED !!");
                    }
                });
            } else
                noInternetConnection();
        } else {
            mArtAndDesignAdapter = new NewsAdapter(getContext(), mArtAndDesignArticleList);
        }
        mArtAndDesignRecycler.setAdapter(mArtAndDesignAdapter);
    }

    @Override
    public void onRefresh() {
        Log.wtf(TAG, "onRefresh() has been instantiated");
        if (isNetworkAvailable()) {
            mSwipeRefreshLayout.setRefreshing(true);
            mArtAndDesignArticleList.clear();
            EndlessRecyclerOnScrollListener.mPreviousTotal = 0;
            currentPage = PAGE_START;
            Log.wtf(TAG, "onRefresh()::mArtAndDesignArticleList = " + mArtAndDesignArticleList.toString());
            loadArtAndDesignData();
            mSwipeRefreshLayout.setRefreshing(false);
        } else {
            mSwipeRefreshLayout.setRefreshing(false);
            noInternetConnection();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        EndlessRecyclerOnScrollListener.mPreviousTotal = 0;
        Log.wtf(TAG, "onPause() has been instantiated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.wtf(TAG, "onStart() has been instantiated");
    }

    @Override
    public void onStop() {
        super.onStop();
        EndlessRecyclerOnScrollListener.mPreviousTotal = 0;
        Log.wtf(TAG, "onStop() has been instantiated");
    }

    private void noInternetConnection() {
        Log.wtf(TAG, "tryToConnectOrExit(): has been instantiated");
        if (noInternet != null)
            noInternet = null;
        noInternet = new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE);
        noInternet.setCancelable(false);
        noInternet.setTitleText("No Internet Connection")
                .setContentText("Connect to WI-FI or Cellular")
                .setConfirmText("OK")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                }).show();
    }
}
