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

import static com.muhammadelsayed.echo.SplashActivity.mScienceArticleList;
import static com.muhammadelsayed.echo.SplashActivity.scienceList;

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
    if (getArguments() != null) {}
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    Log.wtf(TAG, "onCreateView() has been instantiated");

    // Inflate the layout for this fragment
    View rootView = inflater.inflate(R.layout.science_home_tab, container, false);
    mScienceRecycler = rootView.findViewById(R.id.science_recycler);
    mScienceRecycler.setHasFixedSize(true);
    LinearLayoutManager mLinearLayoutManager =
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    mScienceRecycler.setLayoutManager(mLinearLayoutManager);
    mScienceRecycler.setDrawingCacheEnabled(true);
    mScienceRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    mScienceRecycler.setItemViewCacheSize(20);
    mSwipeRefreshLayout = rootView.findViewById(R.id.science_swipe);
    mSwipeRefreshLayout.setOnRefreshListener(this);
    mSwipeRefreshLayout.setColorSchemeResources(
        R.color.colorPrimary,
        android.R.color.holo_green_dark,
        android.R.color.holo_orange_dark,
        android.R.color.holo_blue_dark);

    loadScienceData();

    return rootView;
  }

  @Override
  public void onRefresh() {
    mSwipeRefreshLayout.setRefreshing(true);
    mScienceArticleList.clear();
    loadScienceData();
    mSwipeRefreshLayout.setRefreshing(false);
  }

  private void loadScienceData() {
    Log.wtf(TAG, "loadScienceData() has been instantiated");
    if (mScienceArticleList.isEmpty()) {
      loadScienceSources();
      Map<String, Object> options = new HashMap<>();
      options.put("apiKey", getResources().getString(R.string.news_api_key1));
      options.put("sources", scienceSources);
      Utils.getTopHeadLines(
          options,
          new Utils.retrofitCallbackArticle() {
            @Override
            public void onSuccessArticle(List<Article> articles) {
              Log.wtf(TAG, "onSuccessArticle()::Science");
              mScienceArticleList = articles;
              mScienceNewsAdapter =
                  new NewsAdapter(
                      getActivity(),
                      mScienceArticleList,
                      new NewsAdapter.ItemClickListener() {
                        @Override
                        public void onItemClick(View v, int position) {

                          Article currentArticle = mScienceArticleList.get(position);
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
              mScienceRecycler.setAdapter(mScienceNewsAdapter);
            }
          });
    } else {
      mScienceNewsAdapter =
          new NewsAdapter(
              getActivity(),
              mScienceArticleList,
              new NewsAdapter.ItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {

                  Article currentArticle = mScienceArticleList.get(position);
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
      mScienceRecycler.setAdapter(mScienceNewsAdapter);
    }
  }

  private void loadScienceSources() {
    Log.wtf(TAG, "loadScienceSources() has been instantiated");
    for (Source source : scienceList) {
      scienceSources = scienceSources.concat(source.getId() + ",");
    }
    scienceSources.replaceAll(",$", "");
  }
}
