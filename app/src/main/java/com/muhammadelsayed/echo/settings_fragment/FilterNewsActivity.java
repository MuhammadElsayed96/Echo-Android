package com.muhammadelsayed.echo.settings_fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.muhammadelsayed.echo.R;
import com.muhammadelsayed.echo.adapters.ShortcutsPagerAdapter;
import com.muhammadelsayed.echo.settings_fragment.FilterNewsTabs.HeadlinesTab;
import com.muhammadelsayed.echo.settings_fragment.FilterNewsTabs.SectionsTab;

public class FilterNewsActivity extends AppCompatActivity {

    private static final String TAG = "FilterNewsActivity";

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_news);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Filter News");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ViewPager mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        // adding home tabs' titles
        tabLayout.getTabAt(0).setText("SECTIONS");
        tabLayout.getTabAt(1).setText("HEADLINES");


    }

    /**
     * Adds fragments to the viewPager
     *
     * @param viewPager the view that holds the fragments
     */
    private void setupViewPager(ViewPager viewPager) {
        Log.wtf(TAG, "setupViewPager() has been instantiated");
        ShortcutsPagerAdapter adapter = new ShortcutsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SectionsTab());
        adapter.addFragment(new HeadlinesTab());
        viewPager.setAdapter(adapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home)
            NavUtils.navigateUpFromSameTask(this);
        return super.onOptionsItemSelected(item);
    }
}
