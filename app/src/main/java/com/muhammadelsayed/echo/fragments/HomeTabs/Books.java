package com.muhammadelsayed.echo.fragments.HomeTabs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.muhammadelsayed.echo.R;
import com.muhammadelsayed.echo.adapters.EndlessRecyclerOnScrollListener;
import com.muhammadelsayed.echo.adapters.NewsAdapter;
import com.muhammadelsayed.echo.echo_utils.Constants;
import com.muhammadelsayed.echo.echo_utils.Utils;
import com.muhammadelsayed.echo.model.Article;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.muhammadelsayed.echo.activities.SplashActivity.mBooksArticleList;
import static com.muhammadelsayed.echo.echo_utils.Utils.isNetworkAvailable;

public class Books extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = Books.class.getSimpleName();
    private final int PAGE_START = 1;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private NewsAdapter mBooksNewsAdapter;
    private RecyclerView mBooksRecycler;
    private ProgressBar mProgressBar;
    private SweetAlertDialog noInternet;
    private int currentPage = PAGE_START;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.wtf(TAG, "onCreateView() has been instantiated");
        View rootView = inflater.inflate(R.layout.fragment_books, container, false);
        mProgressBar = rootView.findViewById(R.id.progressBar);
        mBooksRecycler = rootView.findViewById(R.id.recycler);
        LinearLayoutManager mLinearLayoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mBooksRecycler.setLayoutManager(mLinearLayoutManager);
        mSwipeRefreshLayout = rootView.findViewById(R.id.swipe);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mBooksRecycler.setDrawingCacheEnabled(true);
        mBooksRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mBooksRecycler.setItemViewCacheSize(100);

        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        loadBooksData();
        mBooksRecycler.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
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
        options.put("section", "books");
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", page);
        options.put("page-size", 20);
        options.put("api-key", Constants.GUARDIAN_API_KEY);
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: Books = " + articles);
                mBooksArticleList.addAll(articles);
                mBooksNewsAdapter.notifyDataSetChanged();
                mProgressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure(): Books FAILED !!");
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }

    private void loadBooksData() {
        Log.wtf(TAG, "loadBooksData() has been instantiated");
        if (mBooksArticleList.isEmpty()) {
            if (isNetworkAvailable()) {
                Map<String, Object> options = new HashMap<>();
                options.put("section", "books");
                options.put("order-by", "newest");
                options.put("show-tags", "contributor");
                options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
                options.put("page", 1);
                options.put("page-size", 20);
                options.put("api-key", Constants.GUARDIAN_API_KEY);
                Utils.getNews(options, new Utils.retrofitCallback() {
                    @Override
                    public void onSuccess(List<Article> articles) {
                        Log.wtf(TAG, "onSuccess: Books = " + articles);
                        mBooksArticleList = articles;
                        mBooksNewsAdapter = new NewsAdapter(getContext(), mBooksArticleList);
                        mBooksRecycler.setAdapter(mBooksNewsAdapter);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.wtf(TAG, "onFailure(): Books FAILED !!");
                    }
                });
            } else
                noInternetConnection();
        } else {
            mBooksNewsAdapter = new NewsAdapter(getContext(), mBooksArticleList);
        }
        mBooksRecycler.setAdapter(mBooksNewsAdapter);
    }

    @Override
    public void onRefresh() {
        Log.wtf(TAG, "onRefresh() has been instantiated");
        if (isNetworkAvailable()) {
            mSwipeRefreshLayout.setRefreshing(true);
            mBooksArticleList.clear();
            EndlessRecyclerOnScrollListener.mPreviousTotal = 0;
            currentPage = PAGE_START;
            Log.wtf(TAG, "onRefresh()::mBooksArticleList = " + mBooksArticleList.toString());
            loadBooksData();
            mSwipeRefreshLayout.setRefreshing(false);
        } else {
            mSwipeRefreshLayout.setRefreshing(false);
            noInternetConnection();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.wtf(TAG, "onPause() has been instantiated");
        EndlessRecyclerOnScrollListener.mPreviousTotal = 0;
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.wtf(TAG, "onStop() has been instantiated");
        EndlessRecyclerOnScrollListener.mPreviousTotal = 0;
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
