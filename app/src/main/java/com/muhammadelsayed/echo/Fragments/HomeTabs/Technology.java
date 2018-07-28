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

import static com.muhammadelsayed.echo.SplashActivity.mTechnologyArticleList;
import static com.muhammadelsayed.echo.SplashActivity.technologyList;

public class Technology extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
  private static final String TAG = Technology.class.getSimpleName();
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private RecyclerView mTechnologyRecycler;
  private NewsAdapter mTechnologyNewsAdapter;
  private String technologySources;

  public Technology() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    technologySources = "";
    if (getArguments() != null) {}
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    Log.wtf(TAG, "onCreateView() has been instantiated");

    // Inflate the layout for this fragment
    View rootView = inflater.inflate(R.layout.technology_home_tab, container, false);
    mTechnologyRecycler = rootView.findViewById(R.id.technology_recycler);
    mTechnologyRecycler.setHasFixedSize(true);
    LinearLayoutManager mLinearLayoutManager =
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    mTechnologyRecycler.setLayoutManager(mLinearLayoutManager);
    mTechnologyRecycler.setDrawingCacheEnabled(true);
    mTechnologyRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    mTechnologyRecycler.setItemViewCacheSize(20);
    mSwipeRefreshLayout = rootView.findViewById(R.id.technology_swipe);
    mSwipeRefreshLayout.setOnRefreshListener(this);
    mSwipeRefreshLayout.setColorSchemeResources(
        R.color.colorPrimary,
        android.R.color.holo_green_dark,
        android.R.color.holo_orange_dark,
        android.R.color.holo_blue_dark);

    loadTechnologyData();

    return rootView;
  }

  @Override
  public void onRefresh() {
    mSwipeRefreshLayout.setRefreshing(true);
    mTechnologyArticleList.clear();
    loadTechnologyData();
    mSwipeRefreshLayout.setRefreshing(false);
  }

  private void loadTechnologyData() {
    Log.wtf(TAG, "loadTechnologyData() has been instantiated");
    if (mTechnologyArticleList.isEmpty()) {
      loadTechnologySources();
      Map<String, Object> options = new HashMap<>();
      options.put("apiKey", getResources().getString(R.string.news_api_key1));
      options.put("sources", technologySources);
      Utils.getTopHeadLines(
          options,
          new Utils.retrofitCallbackArticle() {
            @Override
            public void onSuccessArticle(List<Article> articles) {
              Log.wtf(TAG, "onSuccessArticle()::Technology");
              mTechnologyArticleList = articles;
              mTechnologyNewsAdapter =
                  new NewsAdapter(
                      getContext(),
                      mTechnologyArticleList,
                      new NewsAdapter.ItemClickListener() {
                        @Override
                        public void onItemClick(View v, int position) {

                          Article currentArticle = mTechnologyArticleList.get(position);
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
              mTechnologyRecycler.setAdapter(mTechnologyNewsAdapter);
            }
          });
    } else {
      mTechnologyNewsAdapter =
          new NewsAdapter(
              getContext(),
              mTechnologyArticleList,
              new NewsAdapter.ItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {

                  Article currentArticle = mTechnologyArticleList.get(position);
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
      mTechnologyRecycler.setAdapter(mTechnologyNewsAdapter);
    }
  }

  private void loadTechnologySources() {
    Log.wtf(TAG, "loadTechnologySources() has been instantiated");
    for (Source source : technologyList) {
      technologySources = technologySources.concat(source.getId() + ",");
    }
    technologySources.replaceAll(",$", "");
  }
}
