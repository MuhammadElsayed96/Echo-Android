package com.muhammadelsayed.echo.SettingsFragment.FilterNewsTabs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.muhammadelsayed.echo.R;

public class SectionsTab extends Fragment {
    private static final String TAG = "SectionsTab";

    private SharedPreferences sharedpreferences;

    private RelativeLayout mArtDesign, mBooks, mBusiness, mCulture, mEducation, mEnvironment, mFashion, mFilm, mFootball, mLaw,
            mLifestyle, mMedia, mMoney, mMusic, mPolitics, mScience, mSociety, mSport, mTechnology, mTravel, mTvRadio, mWeather;

    private CheckBox chkArtDesign, chkBooks, chkBusiness, chkCulture, chkEducation, chkEnvironment,
            chkFashion, chkFilm, chkFootball, chkLaw, chkLifestyle, chkMedia, chkMoney, chkMusic,
            chkPolitics, chkScience, chkSociety, chkSport, chkTechnology, chkTravel, chkTvRadio, chkWeather;

    private boolean artdesign, books, business, culture, education, environment, fashion, film, football, law, lifestyle, media,
                    money, music, politics, science, society, sport, technology, travel, tvradio, weather;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_sections_tab, container, false);

        initViews(rootView);
        initCheckBoxes();

        final SharedPreferences.Editor editor = sharedpreferences.edit();


        chkArtDesign.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("artdesign_enabled", chkArtDesign.isChecked());
                editor.apply();
            }
        });

        chkBooks.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("books_enabled", chkBooks.isChecked());
                editor.apply();
            }
        });

        chkBusiness.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("business_enabled", chkBusiness.isChecked());
                editor.apply();
            }
        });

        chkCulture.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("culture_enabled", chkCulture.isChecked());
                editor.apply();
            }
        });

        chkEducation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("education_enabled", chkEducation.isChecked());
                editor.apply();
            }
        });

        chkEnvironment.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("environment_enabled", chkEnvironment.isChecked());
                editor.apply();
            }
        });

        chkFashion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("fashion_enabled", chkFashion.isChecked());
                editor.apply();
            }
        });

        chkFilm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("film_enabled", chkFilm.isChecked());
                editor.apply();
            }
        });

        chkFootball.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("football_enabled", chkFootball.isChecked());
                editor.apply();
            }
        });

        chkLaw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("law_enabled", chkLaw.isChecked());
                editor.apply();
            }
        });

        chkLifestyle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("lifestyle_enabled", chkLifestyle.isChecked());
                editor.apply();
            }
        });

        chkMedia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("media_enabled", chkMedia.isChecked());
                editor.apply();
            }
        });

        chkMoney.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("money_enabled", chkMoney.isChecked());
                editor.apply();
            }
        });

        chkMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("music_enabled", chkMusic.isChecked());
                editor.apply();
            }
        });

        chkPolitics.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("politics_enabled", chkPolitics.isChecked());
                editor.apply();
            }
        });

        chkScience.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("science_enabled", chkScience.isChecked());
                editor.apply();
            }
        });

        chkSociety.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("society_enabled", chkSociety.isChecked());
                editor.apply();
            }
        });

        chkSport.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("sport_enabled", chkSport.isChecked());
                editor.apply();
            }
        });

        chkTechnology.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("technology_enabled", chkTechnology.isChecked());
                editor.apply();
            }
        });

        chkTravel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("travel_enabled", chkTravel.isChecked());
                editor.apply();
            }
        });

        chkTvRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("tvradio_enabled", chkTvRadio.isChecked());
                editor.apply();
            }
        });

        chkWeather.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("weather_enabled", chkWeather.isChecked());
                editor.apply();
            }
        });
        return rootView;
    }

    private void initViews(View rootView) {
        mArtDesign = rootView.findViewById(R.id.art_design);
        mBooks = rootView.findViewById(R.id.books);
        mBusiness = rootView.findViewById(R.id.business);
        mCulture = rootView.findViewById(R.id.culture);
        mEducation = rootView.findViewById(R.id.education);
        mEnvironment = rootView.findViewById(R.id.environment);
        mFashion = rootView.findViewById(R.id.fashion);
        mFilm = rootView.findViewById(R.id.film);
        mFootball = rootView.findViewById(R.id.football);
        mLaw = rootView.findViewById(R.id.law);
        mLifestyle = rootView.findViewById(R.id.lifestyle);
        mMedia = rootView.findViewById(R.id.media);
        mMoney = rootView.findViewById(R.id.money);
        mMusic = rootView.findViewById(R.id.music);
        mPolitics = rootView.findViewById(R.id.politics);
        mScience = rootView.findViewById(R.id.science);
        mSociety = rootView.findViewById(R.id.society);
        mSport = rootView.findViewById(R.id.sport);
        mTechnology = rootView.findViewById(R.id.technology);
        mTravel = rootView.findViewById(R.id.travel);
        mTvRadio = rootView.findViewById(R.id.tvradio);
        mWeather = rootView.findViewById(R.id.weather);

        chkArtDesign = rootView.findViewById(R.id.check_artanddesign);
        chkBooks = rootView.findViewById(R.id.check_books);
        chkBusiness = rootView.findViewById(R.id.check_business);
        chkCulture = rootView.findViewById(R.id.check_culture);
        chkEducation = rootView.findViewById(R.id.check_education);
        chkEnvironment = rootView.findViewById(R.id.check_environment);
        chkFashion = rootView.findViewById(R.id.check_fashion);
        chkFilm = rootView.findViewById(R.id.check_film);
        chkFootball = rootView.findViewById(R.id.check_football);
        chkLaw = rootView.findViewById(R.id.check_law);
        chkLifestyle = rootView.findViewById(R.id.check_lifestyle);
        chkMedia = rootView.findViewById(R.id.check_media);
        chkMoney = rootView.findViewById(R.id.check_money);
        chkMusic = rootView.findViewById(R.id.check_music);
        chkPolitics = rootView.findViewById(R.id.check_politics);
        chkScience = rootView.findViewById(R.id.check_science);
        chkSociety = rootView.findViewById(R.id.check_society);
        chkSport = rootView.findViewById(R.id.check_sport);
        chkTechnology = rootView.findViewById(R.id.check_technology);
        chkTravel = rootView.findViewById(R.id.check_travel);
        chkTvRadio = rootView.findViewById(R.id.check_tvradio);
        chkWeather = rootView.findViewById(R.id.check_weather);
    }

    private void initCheckBoxes() {
        sharedpreferences = getActivity().getSharedPreferences(getString(R.string.settings_preferences), Context.MODE_PRIVATE);

        artdesign = sharedpreferences.getBoolean("artdesign_enabled", true);
        books = sharedpreferences.getBoolean("books_enabled", true);
        business = sharedpreferences.getBoolean("business_enabled", true);
        culture = sharedpreferences.getBoolean("culture_enabled", true);
        education = sharedpreferences.getBoolean("education_enabled", true);
        environment = sharedpreferences.getBoolean("environment_enabled", true);
        fashion = sharedpreferences.getBoolean("fashion_enabled", true);
        film = sharedpreferences.getBoolean("film_enabled", true);
        football = sharedpreferences.getBoolean("football_enabled", true);
        law = sharedpreferences.getBoolean("law_enabled", true);
        lifestyle = sharedpreferences.getBoolean("lifestyle_enabled", true);
        media = sharedpreferences.getBoolean("media_enabled", true);
        money = sharedpreferences.getBoolean("money_enabled", true);
        music = sharedpreferences.getBoolean("music_enabled", true);
        politics = sharedpreferences.getBoolean("politics_enabled", true);
        science = sharedpreferences.getBoolean("science_enabled", true);
        society = sharedpreferences.getBoolean("society_enabled", true);
        sport = sharedpreferences.getBoolean("sport_enabled", true);
        technology = sharedpreferences.getBoolean("technology_enabled", true);
        travel = sharedpreferences.getBoolean("travel_enabled", true);
        tvradio = sharedpreferences.getBoolean("tvradio_enabled", true);
        weather = sharedpreferences.getBoolean("weather_enabled", true);

        if (artdesign)
            chkArtDesign.setChecked(true);
        if (books)
            chkBooks.setChecked(true);
        if (business)
            chkBusiness.setChecked(true);
        if (culture)
            chkCulture.setChecked(true);
        if (education)
            chkEducation.setChecked(true);
        if (environment)
            chkEnvironment.setChecked(true);
        if (fashion)
            chkFashion.setChecked(true);
        if (film)
            chkFilm.setChecked(true);
        if (football)
            chkFootball.setChecked(true);
        if (law)
            chkLaw.setChecked(true);
        if (lifestyle)
            chkLifestyle.setChecked(true);
        if (media)
            chkMedia.setChecked(true);
        if (money)
            chkMoney.setChecked(true);
        if (music)
            chkMusic.setChecked(true);
        if (politics)
            chkPolitics.setChecked(true);
        if (science)
            chkScience.setChecked(true);
        if (society)
            chkSociety.setChecked(true);
        if (sport)
            chkSport.setChecked(true);
        if (technology)
            chkTechnology.setChecked(true);
        if (travel)
            chkTravel.setChecked(true);
        if (tvradio)
            chkTvRadio.setChecked(true);
        if (weather)
            chkWeather.setChecked(true);
    }

}
