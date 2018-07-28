package com.muhammadelsayed.echo.Fragments.HomeTabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muhammadelsayed.echo.Adapters.NewsAdapter;
import com.muhammadelsayed.echo.R;
import com.muhammadelsayed.echo.Utils;
import com.muhammadelsayed.echo.model.Article;
import com.muhammadelsayed.echo.model.Source;
import com.thefinestartist.finestwebview.FinestWebView;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.muhammadelsayed.echo.SplashActivity.businessList;
import static com.muhammadelsayed.echo.SplashActivity.mBusinessArticleList;

public class Business extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
  private static final String TAG = Business.class.getSimpleName();
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private NewsAdapter mBusinessNewsAdapter;
  private RecyclerView mBusinessRecycler;
  private String businessSources;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.wtf(TAG, "onCreate() has been instantiated");
    businessSources = "";
    if (getArguments() != null) {}
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    Log.wtf(TAG, "onCreateView() has been instantiated");
    // Inflate the layout for this fragment
    View rootView = inflater.inflate(R.layout.business_home_tab, container, false);
    mBusinessRecycler = rootView.findViewById(R.id.business_recycler);
    LinearLayoutManager mLinearLayoutManager =
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    mBusinessRecycler.setLayoutManager(mLinearLayoutManager);
    mSwipeRefreshLayout = rootView.findViewById(R.id.business_swipe);
    mSwipeRefreshLayout.setOnRefreshListener(this);
    mBusinessRecycler.setDrawingCacheEnabled(true);
    mBusinessRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    mBusinessRecycler.setItemViewCacheSize(20);
    mSwipeRefreshLayout.setColorSchemeResources(
        android.R.color.holo_red_light,
        android.R.color.holo_green_dark,
        android.R.color.holo_orange_dark,
        android.R.color.holo_blue_dark);

    loadBusinessData();
    /*
     Showing Swipe Refresh animation on activity create As animation won't start on onCreate, post
     runnable is used
    */
    //    mSwipeRefreshLayout.post(
    //        new Runnable() {
    //          @Override
    //          public void run() {
    //
    //            mSwipeRefreshLayout.setRefreshing(true);
    //
    //            // Fetching data from server
    //            loadBusinessData();
    //            mSwipeRefreshLayout.setRefreshing(false);
    //          }
    //        });
    return rootView;
  }

  public Business() {
    // Required empty public constructor
  }

  @Override
  public void onRefresh() {
    Log.wtf(TAG, "onRefresh() has been instantiated");
    mSwipeRefreshLayout.setRefreshing(true);
    mBusinessArticleList.clear();
    Log.wtf(TAG, "onRefresh()::mBusinessArticleList = " + mBusinessArticleList.toString());
    loadBusinessData();
    mSwipeRefreshLayout.setRefreshing(false);
  }

  private void loadBusinessData() {
    Log.wtf(TAG, "loadBusinessData() has been instantiated");
    if (mBusinessArticleList.isEmpty()) {
      loadBusinessSources();
      Map<String, Object> options = new HashMap<>();
      options.put("apiKey", getResources().getString(R.string.news_api_key1));
      options.put("sources", businessSources);
      Utils.getTopHeadLines(
          options,
          new Utils.retrofitCallbackArticle() {
            @Override
            public void onSuccessArticle(List<Article> articles) {
              Log.wtf(TAG, "onSuccessArticle()::Business");
              mBusinessArticleList = articles;
              mBusinessNewsAdapter =
                  new NewsAdapter(
                      getContext(),
                      mBusinessArticleList,
                      new NewsAdapter.ItemClickListener() {
                        @Override
                        public void onItemClick(View v, int position) {
                          Article currentArticle = mBusinessArticleList.get(position);
                          URL articleUrl = currentArticle.getUrl();
                          String articleSourceName = currentArticle.getSource().getName();
                          new FinestWebView.Builder(getContext())
                              .theme(R.style.FinestWebViewTheme)
                              .titleDefault(articleSourceName)
                              .showUrl(false)
                              .statusBarColorRes(R.color.bluePrimaryDark)
                              .toolbarColorRes(R.color.bluePrimary)
                              .titleColorRes(R.color.finestWhite)
                              .urlColorRes(R.color.bluePrimaryLight)
                              .iconDefaultColorRes(R.color.finestWhite)
                              .progressBarColorRes(R.color.finestWhite)
                              .stringResCopiedToClipboard(R.string.copied_to_clipboard)
                              .stringResCopiedToClipboard(R.string.copied_to_clipboard)
                              .stringResCopiedToClipboard(R.string.copied_to_clipboard)
                              .showSwipeRefreshLayout(true)
                              .swipeRefreshColorRes(R.color.bluePrimaryDark)
                              .menuSelector(R.drawable.selector_light_theme)
                              .menuTextGravity(Gravity.CENTER)
                              .menuTextPaddingRightRes(R.dimen.defaultMenuTextPaddingLeft)
                              .dividerHeight(0)
                              .gradientDivider(false)
                              .setCustomAnimations(
                                  R.anim.slide_up, R.anim.hold, R.anim.hold, R.anim.slide_down)
                              .show(articleUrl.toString());
                        }
                      });
              mBusinessRecycler.setAdapter(mBusinessNewsAdapter);
            }
          });
    } else {
      mBusinessNewsAdapter =
          new NewsAdapter(
              getContext(),
              mBusinessArticleList,
              new NewsAdapter.ItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                  Article currentArticle = mBusinessArticleList.get(position);
                  String articleSourceName = currentArticle.getSource().getName();
                  URL articleUrl = currentArticle.getUrl();
                  new FinestWebView.Builder(getContext())
                      .theme(R.style.FinestWebViewTheme)
                      .titleDefault(articleSourceName)
                      .showUrl(false)
                      .statusBarColorRes(R.color.bluePrimaryDark)
                      .toolbarColorRes(R.color.bluePrimary)
                      .titleColorRes(R.color.finestWhite)
                      .urlColorRes(R.color.bluePrimaryLight)
                      .iconDefaultColorRes(R.color.finestWhite)
                      .progressBarColorRes(R.color.finestWhite)
                      .stringResCopiedToClipboard(R.string.copied_to_clipboard)
                      .stringResCopiedToClipboard(R.string.copied_to_clipboard)
                      .stringResCopiedToClipboard(R.string.copied_to_clipboard)
                      .showSwipeRefreshLayout(true)
                      .swipeRefreshColorRes(R.color.bluePrimaryDark)
                      .menuSelector(R.drawable.selector_light_theme)
                      .menuTextGravity(Gravity.CENTER)
                      .menuTextPaddingRightRes(R.dimen.defaultMenuTextPaddingLeft)
                      .dividerHeight(0)
                      .gradientDivider(false)
                      .setCustomAnimations(
                          R.anim.slide_up, R.anim.hold, R.anim.hold, R.anim.slide_down)
                      .show(articleUrl.toString());
                }
              });
    }
    mBusinessRecycler.setAdapter(mBusinessNewsAdapter);
  }

  private void loadBusinessSources() {
    Log.wtf(TAG, "loadBusinessSources() has been instantiated");
    for (Source source : businessList) {
      businessSources = businessSources.concat(source.getId() + ",");
    }
    businessSources.replaceAll(",$", "");
  }

  @Override
  public void onPause() {
    super.onPause();
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
    Log.wtf(TAG, "onStop() has been instantiated");
  }
}
