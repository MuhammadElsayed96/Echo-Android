package com.muhammadelsayed.echo.SettingsFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.muhammadelsayed.echo.R;

public class DefaultEditionActivity extends AppCompatActivity {
    private static final String TAG = DefaultEditionActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_edition);
        Log.wtf(TAG, "onCreate() has been instantiated");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);


        RadioGroup defaultHeadline = findViewById(R.id.editions_radio_group);
        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.settings_preferences), Context.MODE_PRIVATE);
        RadioButton international = findViewById(R.id.radio_international);
        RadioButton us = findViewById(R.id.radio_us);
        RadioButton uk = findViewById(R.id.radio_uk);
        RadioButton australia = findViewById(R.id.radio_australia);

        boolean internationalHeadline = sharedpreferences.getBoolean("international_headline", true);
        boolean usHeadline = sharedpreferences.getBoolean("us_headline", false);
        boolean ukHeadline = sharedpreferences.getBoolean("uk_headline", false);
        boolean australiaHeadline = sharedpreferences.getBoolean("australia_headline", false);
        if (internationalHeadline)
            international.setChecked(true);
        else if (usHeadline)
            us.setChecked(true);
        else if (ukHeadline)
            uk.setChecked(true);
        else if (australiaHeadline)
            australia.setChecked(true);


        final SharedPreferences.Editor editor = sharedpreferences.edit();
        defaultHeadline.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radio_australia:
                        editor.putBoolean("australia_headline", true);
                        editor.putBoolean("uk_headline", false);
                        editor.putBoolean("us_headline", false);
                        editor.putBoolean("international_headline", false);
                        editor.apply();
                        break;
                    case R.id.radio_uk:
                        editor.putBoolean("australia_headline", false);
                        editor.putBoolean("uk_headline", true);
                        editor.putBoolean("us_headline", false);
                        editor.putBoolean("international_headline", false);
                        editor.apply();
                        break;
                    case R.id.radio_us:
                        editor.putBoolean("australia_headline", false);
                        editor.putBoolean("uk_headline", false);
                        editor.putBoolean("us_headline", true);
                        editor.putBoolean("international_headline", false);
                        editor.apply();
                        break;
                    case R.id.radio_international:
                        editor.putBoolean("australia_headline", false);
                        editor.putBoolean("uk_headline", false);
                        editor.putBoolean("us_headline", false);
                        editor.putBoolean("international_headline", true);
                        editor.apply();
                        break;
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.wtf(TAG, "onOptionsItemSelected() has been instantiated");
        int id = item.getItemId();
        if (id == android.R.id.home)
            NavUtils.navigateUpFromSameTask(this);
        return super.onOptionsItemSelected(item);
    }
}
