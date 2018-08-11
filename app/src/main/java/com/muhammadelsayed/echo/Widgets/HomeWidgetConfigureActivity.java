package com.muhammadelsayed.echo.Widgets;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.muhammadelsayed.echo.R;
import com.muhammadelsayed.echo.Widgets.Adapter.WidgetNewsAdapter;

import static com.muhammadelsayed.echo.Fragments.App.HomeFragment.sortedSections;

/**
 * The configuration screen for the {@link WidgetProvider WidgetProvider} AppWidget.
 */
public class HomeWidgetConfigureActivity extends Activity {
    private static final String TAG = HomeWidgetConfigureActivity.class.getSimpleName();
    private static final String PREFS_NAME = "com.muhammadelsayed.echo.Widgets.WidgetProvider";
    private static final String PREF_PREFIX_KEY = "appwidget_";
    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    ListView mSectionsList;
    WidgetNewsAdapter sectionsAdapter;

    ListView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Log.wtf(TAG, "itemClickListener, Item has been clicked!");

            final Context context = HomeWidgetConfigureActivity.this;

            // When an item is clicked, store it's name locally
            String sectionName = sortedSections.get(i).getTitle();
            Log.wtf(TAG, "Section Name -> " + sectionName);
            saveSectionPref(context, mAppWidgetId, sectionName);


            // Make sure we pass back the original appWidgetId
            // It is the responsibility of the configuration activity to update the app widget
            Intent resultValue = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE, null, HomeWidgetConfigureActivity.this, WidgetProvider.class);
            int[] ids = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(), WidgetProvider.class));
            resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
            sendBroadcast(resultValue);


            finish();
        }
    };

    public HomeWidgetConfigureActivity() {
        super();
    }

    // Write the prefix to the SharedPreferences object for this widget
    static void saveSectionPref(Context context, int appWidgetId, String text) {
        Log.wtf(TAG, "saveSectionPref() has been instantiated");

        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.putString(PREF_PREFIX_KEY + appWidgetId, text);
        prefs.apply();
    }

    // Read the prefix from the SharedPreferences object for this widget.
    // If there is no preference saved, get the default from a resource
    static String loadSectionPref(Context context, int appWidgetId) {
        Log.wtf(TAG, "loadSectionPref() has been instantiated");

        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        String titleValue = prefs.getString(PREF_PREFIX_KEY + appWidgetId, null);
        if (titleValue != null) {
            return titleValue;
        } else {
            return context.getString(R.string.appwidget_text);
        }
    }

    static void deleteSectionPref(Context context, int appWidgetId) {
        Log.wtf(TAG, "deleteSectionPref() has been instantiated");

        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.remove(PREF_PREFIX_KEY + appWidgetId);
        prefs.apply();
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Log.wtf(TAG, "onCreate() has been instantiated");

        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.
        setResult(RESULT_CANCELED);
        setContentView(R.layout.home_widget_configure);

        // Find the widget id from the intent.
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }

        mSectionsList = findViewById(R.id.sections_list_view);
        mSectionsList.setOnItemClickListener(itemClickListener);
        // After getting the data, set the adapter to the listView.
        sectionsAdapter = new WidgetNewsAdapter(HomeWidgetConfigureActivity.this, sortedSections);
        mSectionsList.setAdapter(sectionsAdapter);
    }
}



