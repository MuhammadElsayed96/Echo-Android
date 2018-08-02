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

/**
 * A simple {@link Fragment} subclass. Use the {@link ShortcutsFragment#shortcutsFragmentInstance}
 * factory method to create an instance of this fragment.
 */
public class ShortcutsFragment extends Fragment {
    private static final String TAG = "ShortcutsFragment";
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private ViewPager mViewPager;

    public ShortcutsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment using the provided
     * parameters.
     *
     * @return A new instance of fragment ShortcutsFragment.
     */
    public static ShortcutsFragment shortcutsFragmentInstance() {
        ShortcutsFragment fragment = new ShortcutsFragment();
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
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_shortcuts, container, false);

        mViewPager = rootView.findViewById(R.id.container);
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
        Log.wtf(TAG, "setupViewPager: setting up viewPager...");
        HomePagerAdapter adapter = new HomePagerAdapter(getChildFragmentManager());
        adapter.addFragment(new Topics());
        adapter.addFragment(new Saved());
        adapter.addFragment(new History());
        viewPager.setAdapter(adapter);
    }
}
