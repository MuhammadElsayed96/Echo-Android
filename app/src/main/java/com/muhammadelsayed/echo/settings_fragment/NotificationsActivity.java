package com.muhammadelsayed.echo.settings_fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.MenuItem;
import android.widget.CompoundButton;

import com.muhammadelsayed.echo.R;

import static com.muhammadelsayed.echo.activities.SplashActivity.alarmManager;
import static com.muhammadelsayed.echo.activities.SplashActivity.pendingIntent;

public class NotificationsActivity extends AppCompatActivity {

    private static final String TAG = "NotificationsActivity";

    private SharedPreferences sharedpreferences;
    private SwitchCompat notificationSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        sharedpreferences = getSharedPreferences(getString(R.string.settings_preferences), Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedpreferences.edit();

        notificationSwitch = findViewById(R.id.notification_switch);
        notificationSwitch.setChecked(sharedpreferences.getBoolean("notification_enabled", true));
        notificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!b) {
                    alarmManager.cancel(pendingIntent);
                    editor.putBoolean("notification_enabled", false);
                    editor.apply();
                } else {
                    editor.putBoolean("notification_enabled", true);
                    editor.apply();
                }
            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home)
            NavUtils.navigateUpFromSameTask(this);
        return super.onOptionsItemSelected(item);
    }

}
