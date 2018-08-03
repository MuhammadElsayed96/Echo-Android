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
import com.thefinestartist.finestwebview.FinestWebView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.muhammadelsayed.echo.SplashActivity.mArtAndDesignArticleList;


public class ArtAndDesign extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = ArtAndDesign.class.getSimpleName();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private NewsAdapter mArtAndDesignAdapter;
    private RecyclerView mArtAndDesignRecycler;

    public ArtAndDesign() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.wtf(TAG, "onCreate() has been instantiated");

        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.wtf(TAG, "onCreate() has been instantiated");
        View rootView = inflater.inflate(R.layout.fragment_art_design, container, false);
        mArtAndDesignRecycler = rootView.findViewById(R.id.art_and_design_recycler);
        LinearLayoutManager mLinearLayoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mArtAndDesignRecycler.setLayoutManager(mLinearLayoutManager);
        mSwipeRefreshLayout = rootView.findViewById(R.id.art_and_design_swipe);
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
        return rootView;
    }

    private void loadArtAndDesignData() {
        Log.wtf(TAG, "loadArtAndDesignData() has been instantiated");
        if (mArtAndDesignArticleList.isEmpty()) {
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
                    mArtAndDesignAdapter = new NewsAdapter(getContext(), mArtAndDesignArticleList, new NewsAdapter.ItemClickListener() {
                        @Override
                        public void onItemClick(View v, int position) {
                            Article currentArticle = mArtAndDesignArticleList.get(position);
                            String articleUrl = currentArticle.getWebUrl();
                            new FinestWebView.Builder(getContext())
                                    .theme(R.style.FinestWebViewTheme)
                                    .titleDefault(getString(R.string.the_guardian))
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
                                    .show(articleUrl);
                        }
                    });
                    mArtAndDesignRecycler.setAdapter(mArtAndDesignAdapter);
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.wtf(TAG, "onFailure(): Art & Design FAILED !!");
                }
            });
        } else {
            mArtAndDesignAdapter =
                    new NewsAdapter(
                            getContext(),
                            mArtAndDesignArticleList,
                            new NewsAdapter.ItemClickListener() {
                                @Override
                                public void onItemClick(View v, int position) {
                                    Article currentArticle = mArtAndDesignArticleList.get(position);
                                    String articleUrl = currentArticle.getWebUrl();
                                    new FinestWebView.Builder(getContext())
                                            .theme(R.style.FinestWebViewTheme)
                                            .titleDefault(getString(R.string.the_guardian))
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
                                            .show(articleUrl);
                                }
                            });
        }
        mArtAndDesignRecycler.setAdapter(mArtAndDesignAdapter);
    }

    @Override
    public void onRefresh() {
        Log.wtf(TAG, "onRefresh() has been instantiated");
        mSwipeRefreshLayout.setRefreshing(true);
        mArtAndDesignArticleList.clear();
        Log.wtf(TAG, "onRefresh()::mArtAndDesignArticleList = " + mArtAndDesignArticleList.toString());
        loadArtAndDesignData();
        mSwipeRefreshLayout.setRefreshing(false);
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
