package com.muhammadelsayed.echo.SettingsFragment.FilterNewsTabs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.muhammadelsayed.echo.R;

import static com.muhammadelsayed.echo.SettingsFragment.FilterNewsTabs.HeadlinesTab.au;
import static com.muhammadelsayed.echo.SettingsFragment.FilterNewsTabs.HeadlinesTab.international;
import static com.muhammadelsayed.echo.SettingsFragment.FilterNewsTabs.HeadlinesTab.uk;
import static com.muhammadelsayed.echo.SettingsFragment.FilterNewsTabs.HeadlinesTab.us;

public class SectionsTab extends Fragment {
    private static final String TAG = "SectionsTab";

    private SharedPreferences sharedpreferences;

    private int count;

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
        View rootView = inflater.inflate(R.layout.fragment_sections_tab, container, false);

        initViews(rootView);
        initCheckBoxes();

        final SharedPreferences.Editor editor = sharedpreferences.edit();


        chkArtDesign.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("artdesign_enabled", chkArtDesign.isChecked());
                editor.apply();
                countCheckedBox(b);
                validateCheckBoxes();
            }
        });

        chkBooks.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("books_enabled", chkBooks.isChecked());
                editor.apply();

                countCheckedBox(b);
                validateCheckBoxes();

            }
        });

        chkBusiness.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    editor.putBoolean("business_enabled", chkBusiness.isChecked());
                    editor.apply();
                countCheckedBox(b);
                validateCheckBoxes();

                }
        });

        chkCulture.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    editor.putBoolean("culture_enabled", chkCulture.isChecked());
                    editor.apply();
                countCheckedBox(b);
                validateCheckBoxes();

            }
        });

        chkEducation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    editor.putBoolean("education_enabled", chkEducation.isChecked());
                    editor.apply();
                countCheckedBox(b);
                validateCheckBoxes();

                }
        });

        chkEnvironment.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    editor.putBoolean("environment_enabled", chkEnvironment.isChecked());
                    editor.apply();
                countCheckedBox(b);
                validateCheckBoxes();

            }
        });

        chkFashion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    editor.putBoolean("fashion_enabled", chkFashion.isChecked());
                    editor.apply();
                countCheckedBox(b);
                validateCheckBoxes();

            }
        });

        chkFilm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    editor.putBoolean("film_enabled", chkFilm.isChecked());
                    editor.apply();
                countCheckedBox(b);
                validateCheckBoxes();

            }
        });

        chkFootball.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    editor.putBoolean("football_enabled", chkFootball.isChecked());
                    editor.apply();
                countCheckedBox(b);
                validateCheckBoxes();

                }
        });

        chkLaw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("law_enabled", chkLaw.isChecked());
                editor.apply();
                countCheckedBox(b);
                validateCheckBoxes();

            }
        });

        chkLifestyle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("lifestyle_enabled", chkLifestyle.isChecked());
                editor.apply();
                countCheckedBox(b);
                validateCheckBoxes();

            }
        });

        chkMedia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("media_enabled", chkMedia.isChecked());
                editor.apply();
                countCheckedBox(b);
                validateCheckBoxes();
            }
        });

        chkMoney.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("money_enabled", chkMoney.isChecked());
                editor.apply();
                countCheckedBox(b);
                validateCheckBoxes();
            }
        });

        chkMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("music_enabled", chkMusic.isChecked());
                editor.apply();
                countCheckedBox(b);
                validateCheckBoxes();
            }
        });

        chkPolitics.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("politics_enabled", chkPolitics.isChecked());
                editor.apply();
                countCheckedBox(b);
                validateCheckBoxes();
            }
        });

        chkScience.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("science_enabled", chkScience.isChecked());
                editor.apply();
                countCheckedBox(b);
                validateCheckBoxes();
            }
        });

        chkSociety.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("society_enabled", chkSociety.isChecked());
                editor.apply();
                countCheckedBox(b);
                validateCheckBoxes();
            }
        });

        chkSport.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("sport_enabled", chkSport.isChecked());
                editor.apply();
                countCheckedBox(b);
                validateCheckBoxes();
            }
        });

        chkTechnology.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("technology_enabled", chkTechnology.isChecked());
                editor.apply();
                countCheckedBox(b);
                validateCheckBoxes();
            }
        });

        chkTravel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("travel_enabled", chkTravel.isChecked());
                editor.apply();
                countCheckedBox(b);
                validateCheckBoxes();
            }
        });

        chkTvRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("tvradio_enabled", chkTvRadio.isChecked());
                editor.apply();
                countCheckedBox(b);
                validateCheckBoxes();
            }
        });

        chkWeather.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("weather_enabled", chkWeather.isChecked());
                editor.apply();
                countCheckedBox(b);
                validateCheckBoxes();
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
        count = 0;
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
        Log.d(TAG, "initCheckBoxes: artdesign = " + artdesign);

        initCount(artdesign);
        initCount(books);
        initCount(business);
        initCount(culture);
        initCount(education);
        initCount(environment);
        initCount(fashion);
        initCount(film);
        initCount(football);
        initCount(law);
        initCount(lifestyle);
        initCount(media);
        initCount(money);
        initCount(music);
        initCount(politics);
        initCount(science);
        initCount(society);
        initCount(sport);
        initCount(technology);
        initCount(travel);
        initCount(tvradio);
        initCount(weather);

        chkArtDesign.setChecked(artdesign);
        chkBooks.setChecked(books);
        chkBusiness.setChecked(business);
        chkCulture.setChecked(culture);
        chkEducation.setChecked(education);
        chkEnvironment.setChecked(environment);
        chkFashion.setChecked(fashion);
        chkFilm.setChecked(film);
        chkFootball.setChecked(football);
        chkLaw.setChecked(law);
        chkLifestyle.setChecked(lifestyle);
        chkMedia.setChecked(media);
        chkMoney.setChecked(money);
        chkMusic.setChecked(music);
        chkPolitics.setChecked(politics);
        chkScience.setChecked(science);
        chkSociety.setChecked(society);
        chkSport.setChecked(sport);
        chkTechnology.setChecked(technology);
        chkTravel.setChecked(travel);
        chkTvRadio.setChecked(tvradio);
        chkWeather.setChecked(weather);
    }

    private void initCount(boolean b) {
        if (b)
            count++;
    }

    private void countCheckedBox(boolean b) {
        if (b) {
            if (count < 22)
                count++;
        } else if (count > 0){
            count--;
        }
    }


    private void validateCheckBoxes() {

        Log.d(TAG, "validateCheckBoxes: COUNT = " + count);
        if (count <= 1) {
            chkArtDesign.setEnabled(!chkArtDesign.isChecked());
            chkBooks.setEnabled(!chkBooks.isChecked());
            chkBusiness.setEnabled(!chkBusiness.isChecked());
            chkCulture.setEnabled(!chkCulture.isChecked());
            chkEducation.setEnabled(!chkEducation.isChecked());
            chkEnvironment.setEnabled(!chkEnvironment.isChecked());
            chkFashion.setEnabled(!chkFashion.isChecked());
            chkFilm.setEnabled(!chkFilm.isChecked());
            chkFootball.setEnabled(!chkFootball.isChecked());
            chkLaw.setEnabled(!chkLaw.isChecked());
            chkLifestyle.setEnabled(!chkLifestyle.isChecked());
            chkMedia.setEnabled(!chkMedia.isChecked());
            chkMoney.setEnabled(!chkMoney.isChecked());
            chkMusic.setEnabled(!chkMusic.isChecked());
            chkPolitics.setEnabled(!chkPolitics.isChecked());
            chkScience.setEnabled(!chkScience.isChecked());
            chkSociety.setEnabled(!chkSociety.isChecked());
            chkSport.setEnabled(!chkSport.isChecked());
            chkTechnology.setEnabled(!chkTechnology.isChecked());
            chkTravel.setEnabled(!chkTravel.isChecked());
            chkTvRadio.setEnabled(!chkTvRadio.isChecked());
            chkWeather.setEnabled(!chkWeather.isChecked());
        } else {
            chkArtDesign.setEnabled(true);
            chkBooks.setEnabled(true);
            chkBusiness.setEnabled(true);
            chkCulture.setEnabled(true);
            chkEducation.setEnabled(true);
            chkEnvironment.setEnabled(true);
            chkFashion.setEnabled(true);
            chkFilm.setEnabled(true);
            chkFootball.setEnabled(true);
            chkLaw.setEnabled(true);
            chkLifestyle.setEnabled(true);
            chkMedia.setEnabled(true);
            chkMoney.setEnabled(true);
            chkMusic.setEnabled(true);
            chkPolitics.setEnabled(true);
            chkScience.setEnabled(true);
            chkSociety.setEnabled(true);
            chkSport.setEnabled(true);
            chkTechnology.setEnabled(true);
            chkTravel.setEnabled(true);
            chkTvRadio.setEnabled(true);
            chkWeather.setEnabled(true);

        }

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button reset = getActivity().findViewById(R.id.reset);

        final SharedPreferences.Editor editor = sharedpreferences.edit();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
                editor.putBoolean("au_enabled", true);
                editor.putBoolean("uk_enabled", true);
                editor.putBoolean("us_enabled", true);
                editor.putBoolean("international_enabled", true);

                editor.putBoolean("artdesign_enabled", true);
                editor.putBoolean("books_enabled", true);
                editor.putBoolean("business_enabled", true);
                editor.putBoolean("culture_enabled", true);
                editor.putBoolean("education_enabled", true);
                editor.putBoolean("environment_enabled", true);
                editor.putBoolean("fashion_enabled", true);
                editor.putBoolean("film_enabled", true);
                editor.putBoolean("football_enabled", true);
                editor.putBoolean("law_enabled", true);
                editor.putBoolean("lifestyle_enabled", true);
                editor.putBoolean("media_enabled", true);
                editor.putBoolean("money_enabled", true);
                editor.putBoolean("music_enabled", true);
                editor.putBoolean("politics_enabled", true);
                editor.putBoolean("science_enabled", true);
                editor.putBoolean("society_enabled", true);
                editor.putBoolean("sport_enabled", true);
                editor.putBoolean("technology_enabled", true);
                editor.putBoolean("travel_enabled", true);
                editor.putBoolean("tvradio_enabled", true);
                editor.putBoolean("weather_enabled", true);
                editor.apply();
                initCheckBoxes();

                au = sharedpreferences.getBoolean("au_enabled", true);
                uk = sharedpreferences.getBoolean("uk_enabled", true);
                us = sharedpreferences.getBoolean("us_enabled", true);
                international = sharedpreferences.getBoolean("international_enabled", true);

                if (au)
                    HeadlinesTab.chkAustralia.setChecked(true);
                if (uk)
                    HeadlinesTab.chkUk.setChecked(true);
                if (us)
                    HeadlinesTab.chkUs.setChecked(true);
                if (international)
                    HeadlinesTab.chkInternational.setChecked(true);
                }
        });
    }

}
