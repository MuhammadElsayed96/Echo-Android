package com.muhammadelsayed.echo.fragments.App;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.muhammadelsayed.echo.R;
import com.muhammadelsayed.echo.fragments.HomeTabs.ArtAndDesign;
import com.muhammadelsayed.echo.fragments.HomeTabs.AustraliaHeadlines;
import com.muhammadelsayed.echo.fragments.HomeTabs.Books;
import com.muhammadelsayed.echo.fragments.HomeTabs.Business;
import com.muhammadelsayed.echo.fragments.HomeTabs.Culture;
import com.muhammadelsayed.echo.fragments.HomeTabs.Education;
import com.muhammadelsayed.echo.fragments.HomeTabs.Environment;
import com.muhammadelsayed.echo.fragments.HomeTabs.Fashion;
import com.muhammadelsayed.echo.fragments.HomeTabs.Film;
import com.muhammadelsayed.echo.fragments.HomeTabs.Football;
import com.muhammadelsayed.echo.fragments.HomeTabs.InternationalHeadlines;
import com.muhammadelsayed.echo.fragments.HomeTabs.Law;
import com.muhammadelsayed.echo.fragments.HomeTabs.Lifestyle;
import com.muhammadelsayed.echo.fragments.HomeTabs.Media;
import com.muhammadelsayed.echo.fragments.HomeTabs.Money;
import com.muhammadelsayed.echo.fragments.HomeTabs.Music;
import com.muhammadelsayed.echo.fragments.HomeTabs.Politics;
import com.muhammadelsayed.echo.fragments.HomeTabs.Science;
import com.muhammadelsayed.echo.fragments.HomeTabs.Society;
import com.muhammadelsayed.echo.fragments.HomeTabs.Sport;
import com.muhammadelsayed.echo.fragments.HomeTabs.Technology;
import com.muhammadelsayed.echo.fragments.HomeTabs.Travel;
import com.muhammadelsayed.echo.fragments.HomeTabs.TvAndRadio;
import com.muhammadelsayed.echo.fragments.HomeTabs.UkHeadlines;
import com.muhammadelsayed.echo.fragments.HomeTabs.UsHeadlines;
import com.muhammadelsayed.echo.fragments.HomeTabs.Weather;
import com.muhammadelsayed.echo.model.Section;

import java.util.ArrayList;
import java.util.List;

import static com.muhammadelsayed.echo.settings_fragment.ReorderSectionsActivity.initSectionsList;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "HomeFragment";
    public static Fragment fragment;
    public static List<Section> sortedSections;
    FragmentManager fragmentManager;
    private DrawerLayout mDrawerLayout;
    private NavigationView navDrawer;

    public static HomeFragment homeFragmentInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.wtf(TAG, "onCreateView() has been instantiated");
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

//        Intent intent = getActivity().getIntent();
//        if (intent != null) {
//            String articleUrl = intent.getStringExtra("article_url");
//            SharedPreferences preferences = getSharedPreferences(getString(R.string.settings_preferences), Context.MODE_PRIVATE);
//            boolean inAppBrowser = preferences.getBoolean("in_app_browser", true);
//            if (inAppBrowser) {
//                new FinestWebView.Builder(getActivity().getApplicationContext())
//                        .theme(R.style.FinestWebViewTheme)
//                        .titleDefault(getString(R.string.the_guardian))
//                        .showUrl(false)
//                        .statusBarColorRes(R.color.bluePrimaryDark)
//                        .toolbarColorRes(R.color.bluePrimary)
//                        .titleColorRes(R.color.finestWhite)
//                        .urlColorRes(R.color.bluePrimaryLight)
//                        .iconDefaultColorRes(R.color.finestWhite)
//                        .progressBarColorRes(R.color.finestWhite)
//                        .stringResCopiedToClipboard(R.string.copied_to_clipboard)
//                        .stringResCopiedToClipboard(R.string.copied_to_clipboard)
//                        .stringResCopiedToClipboard(R.string.copied_to_clipboard)
//                        .showSwipeRefreshLayout(true)
//                        .swipeRefreshColorRes(R.color.bluePrimaryDark)
//                        .menuSelector(R.drawable.selector_light_theme)
//                        .menuTextGravity(Gravity.CENTER)
//                        .menuTextPaddingRightRes(R.dimen.defaultMenuTextPaddingLeft)
//                        .dividerHeight(0)
//                        .gradientDivider(false)
//                        .setCustomAnimations(
//                                R.anim.slide_up, R.anim.hold, R.anim.hold, R.anim.slide_down)
//                        .show(articleUrl);
//            } else {
//                openWebPage(articleUrl);
//            }
//        }

        Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        mDrawerLayout = rootView.findViewById(R.id.drawer_layout);
        navDrawer = rootView.findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        fragmentManager = getChildFragmentManager();

        if (getActivity().getIntent().hasExtra("from_notification")) {
            if (getActivity().getIntent().getBooleanExtra("from_notification", false)) {
                getActivity().getIntent().removeExtra("from_notification");
                fragment = null;
                Log.d(TAG, "onCreateView: fragment = " + fragment);
            }
        }

        Log.d(TAG, "onCreateView: fragment = " + fragment);
        if (fragment == null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.settings_preferences), Context.MODE_PRIVATE);
            boolean internationalHeadline = sharedPreferences.getBoolean("international_headline", true);
            boolean usHeadline = sharedPreferences.getBoolean("us_headline", false);
            boolean ukHeadline = sharedPreferences.getBoolean("uk_headline", false);
            boolean australiaHeadline = sharedPreferences.getBoolean("australia_headline", false);
            if (internationalHeadline) {
                navDrawer.setCheckedItem(R.id.nav_news);
                fragment = new InternationalHeadlines();
                getActivity().setTitle(getResources().getString(R.string.international_headlines));
            } else if (usHeadline) {
                navDrawer.setCheckedItem(R.id.nav_us_news);
                fragment = new UsHeadlines();
                getActivity().setTitle(getResources().getString(R.string.us_headlines));
            } else if (ukHeadline) {
                navDrawer.setCheckedItem(R.id.nav_uk_news);
                fragment = new UkHeadlines();
                getActivity().setTitle(getResources().getString(R.string.uk_headlines));
                Log.d(TAG, "onCreateView: UKKKKKKKKKKKKKKKKK");
            } else if (australiaHeadline) {
                navDrawer.setCheckedItem(R.id.nav_au_news);
                fragment = new AustraliaHeadlines();
                getActivity().setTitle(getResources().getString(R.string.australia_headlines));
            }
            Log.d(TAG, "onCreateView: NULLLLLLLLLLLLLLLLLLLLLL");
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

        sortedSections = getSortedSections();
        for (int i = 0; i < sortedSections.size(); i++) {
            MenuItem item = navMenu.add(R.id.group_settings, sortedSections.get(i).getResId(), i, sortedSections.get(i).getTitle()).setCheckable(true);
            item.setIcon(R.drawable.ic_label_outline_black_24dp);
        }

        navMenu.findItem(R.id.nav_au_news).setVisible(au);
        navMenu.findItem(R.id.nav_uk_news).setVisible(uk);
        navMenu.findItem(R.id.nav_us_news).setVisible(us);
        navMenu.findItem(R.id.nav_news).setVisible(international);
        navMenu.findItem(R.string.nav_art_design).setVisible(artdesign);
        navMenu.findItem(R.string.nav_books).setVisible(books);
        navMenu.findItem(R.string.nav_business).setVisible(business);
        navMenu.findItem(R.string.nav_culture).setVisible(culture);
        navMenu.findItem(R.string.nav_education).setVisible(education);
        navMenu.findItem(R.string.nav_environment).setVisible(environment);
        navMenu.findItem(R.string.nav_fashion).setVisible(fashion);
        navMenu.findItem(R.string.nav_film).setVisible(film);
        navMenu.findItem(R.string.nav_football).setVisible(football);
        navMenu.findItem(R.string.nav_law).setVisible(law);
        navMenu.findItem(R.string.nav_lifestyle).setVisible(lifestyle);
        navMenu.findItem(R.string.nav_media).setVisible(media);
        navMenu.findItem(R.string.nav_money).setVisible(money);
        navMenu.findItem(R.string.nav_music).setVisible(music);
        navMenu.findItem(R.string.nav_politics).setVisible(politics);
        navMenu.findItem(R.string.nav_science).setVisible(science);
        navMenu.findItem(R.string.nav_society).setVisible(society);
        navMenu.findItem(R.string.nav_sport).setVisible(sport);
        navMenu.findItem(R.string.nav_technology).setVisible(technology);
        navMenu.findItem(R.string.nav_travel).setVisible(travel);
        navMenu.findItem(R.string.nav_tv_radio).setVisible(tvradio);
        navMenu.findItem(R.string.nav_weather).setVisible(weather);

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        menuItem.setChecked(true);
        getActivity().setTitle(menuItem.getTitle());
        mDrawerLayout.closeDrawers();

        Log.d(TAG, "selectDrawerItem: getItemId = " + menuItem.getItemId());
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
            case R.string.nav_art_design:
                fragment = new ArtAndDesign();
                break;
            case R.string.nav_books:
                fragment = new Books();
                break;
            case R.string.nav_business:
                fragment = new Business();
                break;
            case R.string.nav_culture:
                fragment = new Culture();
                break;
            case R.string.nav_education:
                fragment = new Education();
                break;
            case R.string.nav_environment:
                fragment = new Environment();
                break;
            case R.string.nav_fashion:
                fragment = new Fashion();
                break;
            case R.string.nav_film:
                fragment = new Film();
                break;
            case R.string.nav_football:
                fragment = new Football();
                break;
            case R.string.nav_law:
                fragment = new Law();
                break;
            case R.string.nav_lifestyle:
                fragment = new Lifestyle();
                break;
            case R.string.nav_media:
                fragment = new Media();
                break;
            case R.string.nav_money:
                fragment = new Money();
                break;
            case R.string.nav_music:
                fragment = new Music();
                break;
            case R.string.nav_politics:
                fragment = new Politics();
                break;
            case R.string.nav_science:
                fragment = new Science();
                break;
            case R.string.nav_society:
                fragment = new Society();
                break;
            case R.string.nav_sport:
                fragment = new Sport();
                break;
            case R.string.nav_technology:
                fragment = new Technology();
                break;
            case R.string.nav_travel:
                fragment = new Travel();
                break;
            case R.string.nav_tv_radio:
                fragment = new TvAndRadio();
                break;
            case R.string.nav_weather:
                fragment = new Weather();
                break;
            default: {
                fragment = new InternationalHeadlines();
            }
        }
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
    }

    private List<Section> getSortedSections() {
        List<Section> sectionsList = initSectionsList();
        List<Section> sortedSections = new ArrayList<>();
        SharedPreferences mSharedPreferences;
        mSharedPreferences = getActivity().getSharedPreferences(getString(R.string.settings_preferences), Context.MODE_PRIVATE);
        String jsonListOfSortedSectionsIds = mSharedPreferences.getString("list_of_sorted_sections_ids", "");

        if (!jsonListOfSortedSectionsIds.isEmpty()) {

            Gson gson = new Gson();
            List<String> listOfSortedSectionsId = gson.fromJson(jsonListOfSortedSectionsIds, new TypeToken<List<String>>() {
            }.getType());
            Log.d(TAG, "getSortedSections: listOfSortedSectionsId = " + listOfSortedSectionsId);
            if (listOfSortedSectionsId != null && listOfSortedSectionsId.size() > 0) {

                for (String id : listOfSortedSectionsId) {
                    for (Section section : sectionsList) {
                        if (String.valueOf(section.getResId()).equals(id)) {
                            sortedSections.add(section);
                            sectionsList.remove(section);
                            break;
                        }
                    }
                }

            }

            if (sectionsList.size() > 0) {
                sortedSections.addAll(sectionsList);
            }

            return sortedSections;

        } else {
            return sectionsList;
        }

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

    private void openWebPage(String url) {
        Log.wtf(TAG, "openWebPage() has been instantiated");
        try {
            Uri webPage = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(webPage);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getActivity().getApplicationContext(), "No application can handle this request. Please install a web browser or check your URL.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
