package com.muhammadelsayed.echo.Fragments.App;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muhammadelsayed.echo.Adapters.SourcesAdapter;
import com.muhammadelsayed.echo.R;
import com.muhammadelsayed.echo.model.ResultSources;
import com.muhammadelsayed.echo.model.Source;
import com.muhammadelsayed.echo.network.NewsClient;
import com.muhammadelsayed.echo.network.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SourcesFragment#sourcesFragmentInstance} factory method to
 * create an instance of this fragment.
 */
public class SourcesFragment extends Fragment {

    private static final String TAG = "SourcesFragment";

    private Map<String, Integer> sourcesMap = new HashMap<>();

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

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mSourcesRecyclerView.setHasFixedSize(true);
        mSourcesRecyclerView.setLayoutManager(mLayoutManager);
        mSourcesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mSourcesRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL));

        if (mSourcesList.isEmpty()) {
            prepareSourcesData();
        } else {
            mSourcesAdapter = new SourcesAdapter(getActivity(), mSourcesList);
            mSourcesRecyclerView.setAdapter(mSourcesAdapter);
        }

        return rootView;
    }

    private void prepareSourcesData() {
        Log.d(TAG, "prepareSourcesData: Loading sources data into RecyclerView...");
        instantiateSourcesMap();
        mSourcesList.clear();
        NewsClient service = RetrofitClientInstance.getRetrofitInstance()
                .create(NewsClient.class);

        Map<String, String> options = new HashMap<>();
        options.put("apiKey", getResources().getString(R.string.news_api_key));

        Call<ResultSources> call = service.getSources(options);
        call.enqueue(new Callback<ResultSources>() {
            @Override
            public void onResponse(@NonNull Call<ResultSources> call, @NonNull Response<ResultSources> response) {
                if (response.body() != null) {
                    ResultSources res = response.body();
                    for (Source source : res.getSources()) {
                        if (sourcesMap.get(source.getId()) != null && !mSourcesList.contains(source)) {

                            source.setImageResourceID(sourcesMap.get(source.getId()));
                            mSourcesList.add(source);
                        }
                    }
                    mSourcesAdapter = new SourcesAdapter(getActivity(), mSourcesList);
                    mSourcesRecyclerView.setAdapter(mSourcesAdapter);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResultSources> call, @NonNull Throwable t) {
                Log.wtf(TAG, "onFailure: FAILED !!!");
            }
        });

    }

    private void instantiateSourcesMap() {
        Log.wtf(TAG, "instantiateSourcesMap: instantiating sourcesMap...");
        sourcesMap.put("abc-news", R.drawable.abc_news);
        sourcesMap.put("aftenposten", R.drawable.aftenposten);
        sourcesMap.put("al-jazeera-english", R.drawable.al_jazeera_english);
        sourcesMap.put("bbc-news", R.drawable.bbc_news);
        sourcesMap.put("bbc-sport", R.drawable.bbc_sport);
        sourcesMap.put("bloomberg", R.drawable.bloomberg);
        sourcesMap.put("business-insider", R.drawable.business_insider);
        sourcesMap.put("buzzfeed", R.drawable.buzzfeed);
        sourcesMap.put("cbc-news", R.drawable.cbc_news);
        sourcesMap.put("cbs-news", R.drawable.cbs_news);
        sourcesMap.put("cnbc", R.drawable.cnbc);
        sourcesMap.put("cnn", R.drawable.cnn);
        sourcesMap.put("cnn-es", R.drawable.cnn_es);
        sourcesMap.put("daily-mail", R.drawable.daily_mail);
        sourcesMap.put("engadget", R.drawable.engadget);
        sourcesMap.put("entertainment-weekly", R.drawable.entertainment_weekly);
        sourcesMap.put("financial-post", R.drawable.financial_post);
        sourcesMap.put("football-italia", R.drawable.football_italia);
        sourcesMap.put("fortune", R.drawable.fortune);
        sourcesMap.put("four-four-two", R.drawable.four_four_two);
        sourcesMap.put("fox-news", R.drawable.fox_news);
        sourcesMap.put("fox_sports", R.drawable.fox_sports);
        sourcesMap.put("google-news", R.drawable.google_news);
        sourcesMap.put("hacker-news", R.drawable.hacker_news);
        sourcesMap.put("le-monde", R.drawable.le_monde);
        sourcesMap.put("medical-news-today", R.drawable.medical_news_today);
        sourcesMap.put("metro", R.drawable.metro);
        sourcesMap.put("msnbc", R.drawable.msnbc);
        sourcesMap.put("mtv-news", R.drawable.mtv_news);
        sourcesMap.put("mtv-news-uk", R.drawable.mtv_news_uk);
        sourcesMap.put("national-geographic", R.drawable.national_geographic);
        sourcesMap.put("nbc-news", R.drawable.nbc_news);
        sourcesMap.put("new-scientist", R.drawable.new_scientist);
        sourcesMap.put("new-york-magazine", R.drawable.new_york_magazine);
        sourcesMap.put("next-big-future", R.drawable.next_big_future);
        sourcesMap.put("politico", R.drawable.politico);
        sourcesMap.put("polygon", R.drawable.polygon);
        sourcesMap.put("reddit-r-all", R.drawable.reddit_r_all);
        sourcesMap.put("techcrunch", R.drawable.techcrunch);
        sourcesMap.put("techcrunch-cn", R.drawable.techcrunch_cn);
        sourcesMap.put("techradar", R.drawable.techradar);
        sourcesMap.put("the-economist", R.drawable.the_economist);
        sourcesMap.put("the-guardian", R.drawable.the_guardian);
        sourcesMap.put("the-irish-times", R.drawable.the_irish_times);
        sourcesMap.put("the-jerusalem-post", R.drawable.the_jerusalem_post);
        sourcesMap.put("the-new-york-times", R.drawable.the_new_york_times);
        sourcesMap.put("the-next-web", R.drawable.the_next_web);
        sourcesMap.put("the-telegraph", R.drawable.the_telegraph);
        sourcesMap.put("the-verge", R.drawable.the_verge);
        sourcesMap.put("the-wall-street-journal", R.drawable.the_wall_street_journal);
        sourcesMap.put("the-washington-post", R.drawable.the_washington_post);
        sourcesMap.put("the-washington-times", R.drawable.the_washington_times);
        sourcesMap.put("time", R.drawable.time);
        sourcesMap.put("vice-news", R.drawable.vice_news);
        sourcesMap.put("wired", R.drawable.wired);
        sourcesMap.put("wired-de", R.drawable.wired_de);
        sourcesMap.put("xinhua-net", R.drawable.xinhua_net);
        sourcesMap.put("ynet", R.drawable.ynet);

    }
}
