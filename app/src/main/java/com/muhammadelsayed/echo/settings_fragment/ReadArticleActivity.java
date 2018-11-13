package com.muhammadelsayed.echo.settings_fragment;

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

public class ReadArticleActivity extends AppCompatActivity {

    private static final String TAG = ReadArticleActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_article);
        Log.wtf(TAG, "onCreate() has been instantiated");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);


        RadioGroup readArticleIn = findViewById(R.id.articles_radio_group);
        RadioButton appBrowser = findViewById(R.id.radio_app_browser);
        RadioButton systemBrowser = findViewById(R.id.radio_system_browser);

        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.settings_preferences), Context.MODE_PRIVATE);
        boolean app = sharedpreferences.getBoolean("in_app_browser", true);
        if (app)
            appBrowser.setChecked(true);
        else
            systemBrowser.setChecked(true);

        final SharedPreferences.Editor editor = sharedpreferences.edit();
        readArticleIn.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radio_app_browser:
                        editor.putBoolean("in_app_browser", true).apply();
                        break;
                    case R.id.radio_system_browser:
                        editor.putBoolean("in_app_browser", false).apply();
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
