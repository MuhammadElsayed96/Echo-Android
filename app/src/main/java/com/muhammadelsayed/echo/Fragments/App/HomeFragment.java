package com.muhammadelsayed.echo.Fragments.App;

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
import com.muhammadelsayed.echo.Fragments.HomeTabs.Business;
import com.muhammadelsayed.echo.Fragments.HomeTabs.Entertainment;
import com.muhammadelsayed.echo.Fragments.HomeTabs.Environment;
import com.muhammadelsayed.echo.Fragments.HomeTabs.GoodNews;
import com.muhammadelsayed.echo.Fragments.HomeTabs.Health;
import com.muhammadelsayed.echo.Fragments.HomeTabs.LeadStories;
import com.muhammadelsayed.echo.Fragments.HomeTabs.Lifestyle;
import com.muhammadelsayed.echo.Fragments.HomeTabs.National;
import com.muhammadelsayed.echo.Fragments.HomeTabs.OurPicks;
import com.muhammadelsayed.echo.Fragments.HomeTabs.Politics;
import com.muhammadelsayed.echo.Fragments.HomeTabs.Science;
import com.muhammadelsayed.echo.Fragments.HomeTabs.Sport;
import com.muhammadelsayed.echo.Fragments.HomeTabs.Technology;
import com.muhammadelsayed.echo.Fragments.HomeTabs.Travel;
import com.muhammadelsayed.echo.Fragments.HomeTabs.World;
import com.muhammadelsayed.echo.R;

/**
 * A simple {@link Fragment} subclass. Use the {@link HomeFragment#homeFragmentInstance} factory
 * method to create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
  private static final String TAG = "HomeFragment";
  private ViewPager mViewPager;

  public HomeFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of this fragment using the provided
   * parameters.
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
    }
  }

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
   *
   * @param viewPager the view that holds the fragments
   */
  private void setupViewPager(ViewPager viewPager) {
    Log.wtf(TAG, "setupViewPager: setting up viewPager...");
    HomePagerAdapter adapter = new HomePagerAdapter(getChildFragmentManager());
    adapter.addFragment(new LeadStories());
    adapter.addFragment(new OurPicks());
    adapter.addFragment(new GoodNews());
    adapter.addFragment(new Business());
    adapter.addFragment(new National());
    adapter.addFragment(new World());
    adapter.addFragment(new Politics());
    adapter.addFragment(new Entertainment());
    adapter.addFragment(new Lifestyle());
    adapter.addFragment(new Health());
    adapter.addFragment(new Sport());
    adapter.addFragment(new Technology());
    adapter.addFragment(new Science());
    adapter.addFragment(new Environment());
    adapter.addFragment(new Travel());
    viewPager.setAdapter(adapter);
  }
}
