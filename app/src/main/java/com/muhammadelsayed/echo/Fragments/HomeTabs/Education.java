package com.muhammadelsayed.echo.Fragments.HomeTabs;

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

import com.muhammadelsayed.echo.Adapters.EndlessRecyclerOnScrollListener;
import com.muhammadelsayed.echo.Adapters.NewsAdapter;
import com.muhammadelsayed.echo.Model.Article;
import com.muhammadelsayed.echo.R;
import com.muhammadelsayed.echo.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.muhammadelsayed.echo.SplashActivity.mEducationArticleList;
import static com.muhammadelsayed.echo.Utils.isNetworkAvailable;

public class Education extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = Education.class.getSimpleName();
    private final int PAGE_START = 1;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private NewsAdapter mEducationNewsAdapter;
    private RecyclerView mEducationRecycler;
    private ProgressBar mProgressBar;
    private SweetAlertDialog noInternet;
    private int currentPage = PAGE_START;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.wtf(TAG, "onCreateView() has been instantiated");
        View rootView = inflater.inflate(R.layout.fragment_education, container, false);
        mProgressBar = rootView.findViewById(R.id.progressBar);
        mEducationRecycler = rootView.findViewById(R.id.recycler);
        LinearLayoutManager mLinearLayoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mEducationRecycler.setLayoutManager(mLinearLayoutManager);
        mSwipeRefreshLayout = rootView.findViewById(R.id.swipe);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mEducationRecycler.setDrawingCacheEnabled(true);
        mEducationRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mEducationRecycler.setItemViewCacheSize(100);

        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        loadEducationData();
        mEducationRecycler.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
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
        options.put("section", "education");
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", page);
        options.put("page-size", 20);
        options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess: Education = " + articles);
                mEducationArticleList.addAll(articles);
                mEducationNewsAdapter.notifyDataSetChanged();
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure(): Education FAILED !!");
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }

    private void loadEducationData() {
        Log.wtf(TAG, "loadEducationData() has been instantiated");
        if (mEducationArticleList.isEmpty()) {
            if (isNetworkAvailable()) {
                Map<String, Object> options = new HashMap<>();
                options.put("section", "education");
                options.put("order-by", "newest");
                options.put("show-tags", "contributor");
                options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
                options.put("page", 1);
                options.put("page-size", 20);
                options.put("api-key", "c8133e91-2b02-42b7-9cc8-88ca8d73998a");
                Utils.getNews(options, new Utils.retrofitCallback() {
                    @Override
                    public void onSuccess(List<Article> articles) {
                        Log.wtf(TAG, "onSuccess: Education = " + articles);
                        mEducationArticleList = articles;
                        mEducationNewsAdapter = new NewsAdapter(getContext(), mEducationArticleList);
                        mEducationRecycler.setAdapter(mEducationNewsAdapter);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.wtf(TAG, "onFailure(): Education FAILED !!");
                    }
                });
            } else
                noInternetConnection();
        } else {
            mEducationNewsAdapter = new NewsAdapter(getContext(), mEducationArticleList);
        }
        mEducationRecycler.setAdapter(mEducationNewsAdapter);
    }

    @Override
    public void onRefresh() {
        Log.wtf(TAG, "onRefresh() has been instantiated");
        if (isNetworkAvailable()) {
            mSwipeRefreshLayout.setRefreshing(true);
            mEducationArticleList.clear();
            EndlessRecyclerOnScrollListener.mPreviousTotal = 0;
            currentPage = PAGE_START;
            Log.wtf(TAG, "onRefresh()::mEducationArticleList = " + mEducationArticleList.toString());
            loadEducationData();
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
