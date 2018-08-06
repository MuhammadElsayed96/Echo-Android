package com.muhammadelsayed.echo.Fragments.App;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.view.Menu;
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
    private NavigationView navDrawer;
    FragmentManager fragmentManager;
    Fragment fragment;

    public static HomeFragment homeFragmentInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.wtf(TAG, "onCreateView() has been instantiated");
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        mDrawerLayout = rootView.findViewById(R.id.drawer_layout);
        navDrawer = rootView.findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        fragmentManager = getChildFragmentManager();
        if (fragment == null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.settings_preferences), Context.MODE_PRIVATE);
            boolean internationalHeadline = sharedPreferences.getBoolean("international_headline", true);
            boolean usHeadline = sharedPreferences.getBoolean("us_headline", false);
            boolean ukHeadline = sharedPreferences.getBoolean("uk_headline", false);
            boolean australiaHeadline = sharedPreferences.getBoolean("australia_headline", false);
            if (internationalHeadline) {
                fragment = new InternationalHeadlines();
                getActivity().setTitle(getResources().getString(R.string.international_headlines));
            } else if (usHeadline) {
                fragment = new UsHeadlines();
                getActivity().setTitle(getResources().getString(R.string.us_headlines));
            } else if (ukHeadline) {
                fragment = new UkHeadlines();
                getActivity().setTitle(getResources().getString(R.string.uk_headlines));
            } else if (australiaHeadline) {
                fragment = new AustraliaHeadlines();
                getActivity().setTitle(getResources().getString(R.string.australia_headlines));
            }
            navDrawer.setCheckedItem(R.id.nav_news);
        }
        fragmentManager.beginTransaction().

                replace(R.id.content_frame, fragment).

                commit();
        setupDrawerItems();
        setupDrawerContent(navDrawer);
        return rootView;
    }

    private void setupDrawerItems() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.settings_preferences), Context.MODE_PRIVATE);
        boolean au = sharedPreferences.getBoolean("au_enabled", true);
        boolean uk = sharedPreferences.getBoolean("uk_enabled", true);
        boolean us = sharedPreferences.getBoolean("us_enabled", true);
        boolean international = sharedPreferences.getBoolean("international_enabled", true);

        boolean artdesign = sharedPreferences.getBoolean("artdesign_enabled", true);
        boolean books = sharedPreferences.getBoolean("books_enabled", true);
        boolean business = sharedPreferences.getBoolean("business_enabled", true);
        boolean culture = sharedPreferences.getBoolean("culture_enabled", true);
        boolean education = sharedPreferences.getBoolean("education_enabled", true);
        boolean environment = sharedPreferences.getBoolean("environment_enabled", true);
        boolean fashion = sharedPreferences.getBoolean("fashion_enabled", true);
        boolean film = sharedPreferences.getBoolean("film_enabled", true);
        boolean football = sharedPreferences.getBoolean("football_enabled", true);
        boolean law = sharedPreferences.getBoolean("law_enabled", true);
        boolean lifestyle = sharedPreferences.getBoolean("lifestyle_enabled", true);
        boolean media = sharedPreferences.getBoolean("media_enabled", true);
        boolean money = sharedPreferences.getBoolean("money_enabled", true);
        boolean music = sharedPreferences.getBoolean("music_enabled", true);
        boolean politics = sharedPreferences.getBoolean("politics_enabled", true);
        boolean science = sharedPreferences.getBoolean("science_enabled", true);
        boolean society = sharedPreferences.getBoolean("society_enabled", true);
        boolean sport = sharedPreferences.getBoolean("sport_enabled", true);
        boolean technology = sharedPreferences.getBoolean("technology_enabled", true);
        boolean travel = sharedPreferences.getBoolean("travel_enabled", true);
        boolean tvradio = sharedPreferences.getBoolean("tvradio_enabled", true);
        boolean weather = sharedPreferences.getBoolean("weather_enabled", true);
        Menu navMenu = navDrawer.getMenu();

        navMenu.findItem(R.id.nav_au_news).setVisible(au);
        navMenu.findItem(R.id.nav_uk_news).setVisible(uk);
        navMenu.findItem(R.id.nav_us_news).setVisible(us);
        navMenu.findItem(R.id.nav_news).setVisible(international);

        navMenu.findItem(R.id.nav_art_design).setVisible(artdesign);
        navMenu.findItem(R.id.nav_books).setVisible(books);
        navMenu.findItem(R.id.nav_business).setVisible(business);
        navMenu.findItem(R.id.nav_culture).setVisible(culture);
        navMenu.findItem(R.id.nav_education).setVisible(education);
        navMenu.findItem(R.id.nav_environment).setVisible(environment);
        navMenu.findItem(R.id.nav_fashion).setVisible(fashion);
        navMenu.findItem(R.id.nav_film).setVisible(film);
        navMenu.findItem(R.id.nav_football).setVisible(football);
        navMenu.findItem(R.id.nav_law).setVisible(law);
        navMenu.findItem(R.id.nav_lifestyle).setVisible(lifestyle);
        navMenu.findItem(R.id.nav_media).setVisible(media);
        navMenu.findItem(R.id.nav_money).setVisible(money);
        navMenu.findItem(R.id.nav_music).setVisible(music);
        navMenu.findItem(R.id.nav_politics).setVisible(politics);
        navMenu.findItem(R.id.nav_science).setVisible(science);
        navMenu.findItem(R.id.nav_society).setVisible(society);
        navMenu.findItem(R.id.nav_sport).setVisible(sport);
        navMenu.findItem(R.id.nav_technology).setVisible(technology);
        navMenu.findItem(R.id.nav_travel).setVisible(travel);
        navMenu.findItem(R.id.nav_tv_radio).setVisible(tvradio);
        navMenu.findItem(R.id.nav_weather).setVisible(weather);

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
            default: {
                fragment = new InternationalHeadlines();
            }
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
