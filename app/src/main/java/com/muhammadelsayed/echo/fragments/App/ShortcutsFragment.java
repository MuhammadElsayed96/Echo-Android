package com.muhammadelsayed.echo.fragments.App;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muhammadelsayed.echo.R;
import com.muhammadelsayed.echo.adapters.ShortcutsPagerAdapter;
import com.muhammadelsayed.echo.fragments.Shortcut.History;
import com.muhammadelsayed.echo.fragments.Shortcut.Saved;
import com.muhammadelsayed.echo.fragments.Shortcut.Topics;

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
        ShortcutsPagerAdapter adapter = new ShortcutsPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new Topics());
        adapter.addFragment(new Saved());
        adapter.addFragment(new History());
        viewPager.setAdapter(adapter);
    }
}
