package com.muhammadelsayed.echo.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muhammadelsayed.echo.Adapters.HomePagerAdapter;
import com.muhammadelsayed.echo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#homeFragmentInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ViewPager mViewPager;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeFragment.
     */
    public static HomeFragment homeFragmentInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        mViewPager = rootView.findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        // adding home tabs' titles
        tabLayout.getTabAt(0).setText("LEAD STORIES");
        tabLayout.getTabAt(1).setText("OUR PICKS");
        tabLayout.getTabAt(2).setText("GOOD NEWS");
        tabLayout.getTabAt(3).setText("BUSINESS");
        tabLayout.getTabAt(4).setText("NATIONAL");
        tabLayout.getTabAt(5).setText("WORLD");
        tabLayout.getTabAt(6).setText("POLITICS");
        tabLayout.getTabAt(7).setText("ENTERTAINMENT");
        tabLayout.getTabAt(8).setText("LIFESTYLE");
        tabLayout.getTabAt(9).setText("HEALTH");
        tabLayout.getTabAt(10).setText("SPORT");
        tabLayout.getTabAt(11).setText("TECHNOLOGY");
        tabLayout.getTabAt(12).setText("SCIENCE");
        tabLayout.getTabAt(13).setText("ENVIRONMENT");
        tabLayout.getTabAt(14).setText("TRAVEL");

        return rootView;
    }

    /**
     * Adds fragments to the viewPager
     * @param viewPager the view that holds the fragments
     */
    private void setupViewPager(ViewPager viewPager) {
        Log.wtf(TAG, "setupViewPager: setting up viewPager...");
        HomePagerAdapter adapter = new HomePagerAdapter(getChildFragmentManager());
        adapter.addFragment(new HomeFragment1());
        adapter.addFragment(new HomeFragment2());
        adapter.addFragment(new HomeFragment3());
        adapter.addFragment(new HomeFragment4());
        adapter.addFragment(new HomeFragment5());
        adapter.addFragment(new HomeFragment6());
        adapter.addFragment(new HomeFragment7());
        adapter.addFragment(new HomeFragment8());
        adapter.addFragment(new HomeFragment9());
        adapter.addFragment(new HomeFragment10());
        adapter.addFragment(new HomeFragment11());
        adapter.addFragment(new HomeFragment12());
        adapter.addFragment(new HomeFragment13());
        adapter.addFragment(new HomeFragment14());
        adapter.addFragment(new HomeFragment15());
        viewPager.setAdapter(adapter);
    }
}
