package com.muhammadelsayed.echo.Fragments.App;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muhammadelsayed.echo.Adapters.HomePagerAdapter;
import com.muhammadelsayed.echo.Fragments.Shortcut.History;
import com.muhammadelsayed.echo.Fragments.Shortcut.Saved;
import com.muhammadelsayed.echo.Fragments.Shortcut.Topics;
import com.muhammadelsayed.echo.R;

public class ShortcutsFragment extends Fragment {
    private static final String TAG = "ShortcutsFragment";

    public ShortcutsFragment() {
        // Required empty public constructor
    }

    public static ShortcutsFragment shortcutsFragmentInstance() {
        return new ShortcutsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.wtf(TAG, "onCreate() has been instantiated");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.wtf(TAG, "onCreateView() has been instantiated");
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_shortcuts, container, false);

        ViewPager mViewPager = rootView.findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        // adding home tabs' titles
        tabLayout.getTabAt(0).setText("TOPICS");
        tabLayout.getTabAt(1).setText("SAVED");
        tabLayout.getTabAt(2).setText("HISTORY");

        return rootView;
    }

    /**
     * Adds fragments to the viewPager
     *
     * @param viewPager the view that holds the fragments
     */
    private void setupViewPager(ViewPager viewPager) {
        Log.wtf(TAG, "setupViewPager() has been instantiated");
        HomePagerAdapter adapter = new HomePagerAdapter(getChildFragmentManager());
        adapter.addFragment(new Topics());
        adapter.addFragment(new Saved());
        adapter.addFragment(new History());
        viewPager.setAdapter(adapter);
    }
}
