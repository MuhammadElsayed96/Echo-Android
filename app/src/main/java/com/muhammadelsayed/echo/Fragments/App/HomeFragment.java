package com.muhammadelsayed.echo.Fragments.App;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.muhammadelsayed.echo.Fragments.HomeTabs.ArtAndDesign;
import com.muhammadelsayed.echo.Fragments.HomeTabs.AustraliaHeadlines;
import com.muhammadelsayed.echo.Fragments.HomeTabs.Books;
import com.muhammadelsayed.echo.Fragments.HomeTabs.Business;
import com.muhammadelsayed.echo.Fragments.HomeTabs.Culture;
import com.muhammadelsayed.echo.Fragments.HomeTabs.Education;
import com.muhammadelsayed.echo.Fragments.HomeTabs.Environment;
import com.muhammadelsayed.echo.Fragments.HomeTabs.Fashion;
import com.muhammadelsayed.echo.Fragments.HomeTabs.Film;
import com.muhammadelsayed.echo.Fragments.HomeTabs.Football;
import com.muhammadelsayed.echo.Fragments.HomeTabs.InternationalHeadlines;
import com.muhammadelsayed.echo.Fragments.HomeTabs.Law;
import com.muhammadelsayed.echo.Fragments.HomeTabs.Lifestyle;
import com.muhammadelsayed.echo.Fragments.HomeTabs.Media;
import com.muhammadelsayed.echo.Fragments.HomeTabs.Money;
import com.muhammadelsayed.echo.Fragments.HomeTabs.Music;
import com.muhammadelsayed.echo.Fragments.HomeTabs.Politics;
import com.muhammadelsayed.echo.Fragments.HomeTabs.Science;
import com.muhammadelsayed.echo.Fragments.HomeTabs.Society;
import com.muhammadelsayed.echo.Fragments.HomeTabs.Sport;
import com.muhammadelsayed.echo.Fragments.HomeTabs.Technology;
import com.muhammadelsayed.echo.Fragments.HomeTabs.Travel;
import com.muhammadelsayed.echo.Fragments.HomeTabs.TvAndRadio;
import com.muhammadelsayed.echo.Fragments.HomeTabs.UkHeadlines;
import com.muhammadelsayed.echo.Fragments.HomeTabs.UsHeadlines;
import com.muhammadelsayed.echo.Fragments.HomeTabs.Weather;
import com.muhammadelsayed.echo.R;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "HomeFragment";
    private DrawerLayout mDrawerLayout;
    FragmentManager fragmentManager;
    Fragment fragment;

    public static HomeFragment homeFragmentInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.wtf(TAG, "onCreate() has been instantiated");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.wtf(TAG, "onCreateView() has been instantiated");
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        mDrawerLayout = rootView.findViewById(R.id.drawer_layout);
        NavigationView navDrawer = rootView.findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        fragmentManager = getChildFragmentManager();
        if (fragment == null) {
            fragment = new InternationalHeadlines();
            getActivity().setTitle(getResources().getString(R.string.international_headlines));
            navDrawer.setCheckedItem(R.id.nav_news);
        }
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        setupDrawerContent(navDrawer);
        return rootView;
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        menuItem.setChecked(true);
        getActivity().setTitle(menuItem.getTitle());
        mDrawerLayout.closeDrawers();

        switch (menuItem.getItemId()) {
            case R.id.nav_au_news:
                fragment = new AustraliaHeadlines();
                break;
            case R.id.nav_uk_news:
                fragment = new UkHeadlines();
                break;
            case R.id.nav_us_news:
                fragment = new UsHeadlines();
                break;
            case R.id.nav_news:
                fragment = new InternationalHeadlines();
                break;
            case R.id.nav_art_design:
                fragment = new ArtAndDesign();
                break;
            case R.id.nav_books:
                fragment = new Books();
                break;
            case R.id.nav_business:
                fragment = new Business();
                break;
            case R.id.nav_culture:
                fragment = new Culture();
                break;
            case R.id.nav_education:
                fragment = new Education();
                break;
            case R.id.nav_environment:
                fragment = new Environment();
                break;
            case R.id.nav_fashion:
                fragment = new Fashion();
                break;
            case R.id.nav_film:
                fragment = new Film();
                break;
            case R.id.nav_football:
                fragment = new Football();
                break;
            case R.id.nav_law:
                fragment = new Law();
                break;
            case R.id.nav_lifestyle:
                fragment = new Lifestyle();
                break;
            case R.id.nav_media:
                fragment = new Media();
                break;
            case R.id.nav_money:
                fragment = new Money();
                break;
            case R.id.nav_music:
                fragment = new Music();
                break;
            case R.id.nav_politics:
                fragment = new Politics();
                break;
            case R.id.nav_science:
                fragment = new Science();
                break;
            case R.id.nav_society:
                fragment = new Society();
                break;
            case R.id.nav_sport:
                fragment = new Sport();
                break;
            case R.id.nav_technology:
                fragment = new Technology();
                break;
            case R.id.nav_travel:
                fragment = new Travel();
                break;
            case R.id.nav_tv_radio:
                fragment = new TvAndRadio();
                break;
            case R.id.nav_weather:
                fragment = new Weather();
                break;
            default:
                fragment = new InternationalHeadlines();
        }
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        Log.wtf(TAG, "onRefresh() has been instantiated");
    }
}
