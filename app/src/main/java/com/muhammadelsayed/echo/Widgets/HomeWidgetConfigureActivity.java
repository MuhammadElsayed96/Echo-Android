package com.muhammadelsayed.echo.Widgets;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private List<String> section = null;
    private List<String> sections = null;
    private Map<String, String> sectionMap = null;

    ListView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Log.wtf(TAG, "itemClickListener, Item has been clicked!");

            final Context context = HomeWidgetConfigureActivity.this;

            // When an item is clicked, store it's name locally
            String sectionName = sections.get(i);
            Log.wtf(TAG, "Section Name -> " + sectionName);

            String sectionPref = sectionMap.get(sectionName);
            saveSectionPref(context, mAppWidgetId, sectionPref);

            // Make sure we pass back the original appWidgetId
            // It is the responsibility of the configuration activity to update the app widget
            Intent resultValue = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE, null, HomeWidgetConfigureActivity.this, WidgetProvider.class);
//            int[] ids = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(), WidgetProvider.class));
            resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, new int[]{mAppWidgetId});
            sendBroadcast(resultValue);

            int id = getIntent().getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, 0);
            final Intent result = new Intent().putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, id);
            setResult(RESULT_OK, result);

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
        section = new ArrayList<>();
        sectionMap = new HashMap<>();
        sections = new ArrayList<>();
        loadSections();
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
        sectionsAdapter = new WidgetNewsAdapter(HomeWidgetConfigureActivity.this, sections);
        mSectionsList.setAdapter(sectionsAdapter);
    }


    private void loadSections() {
        Log.wtf(TAG, "loadSections() has been instantiated");
        section.add(0, "australia-news");
        section.add(1, "uk-news");
        section.add(2, "us-news");
        section.add(3, "news");
        section.add(4, "artanddesign");
        section.add(5, "books");
        section.add(6, "business");
        section.add(7, "culture");
        section.add(8, "education");
        section.add(9, "environment");
        section.add(10, "fashion");
        section.add(11, "film");
        section.add(12, "football");
        section.add(13, "law");
        section.add(14, "lifeandstyle");
        section.add(15, "media");
        section.add(16, "money");
        section.add(17, "music");
        section.add(18, "politics");
        section.add(19, "science");
        section.add(20, "society");
        section.add(21, "sport");
        section.add(22, "technology");
        section.add(23, "travel");
        section.add(24, "tv-and-radio");
        section.add(25, "weather");

        sections.add(0, "Australia Headlines");
        sections.add(1, "UK Headlines");
        sections.add(1, "US Headlines");
        sections.add(3, "International Headlines");
        sections.add(4, "Art and Design");
        sections.add(5, "Books");
        sections.add(6, "Business");
        sections.add(7, "Culture");
        sections.add(8, "Education");
        sections.add(9, "Environment");
        sections.add(10, "Fashion");
        sections.add(11, "Film");
        sections.add(12, "Football");
        sections.add(13, "Law");
        sections.add(14, "Lifestyle");
        sections.add(15, "Media");
        sections.add(16, "Money");
        sections.add(17, "Music");
        sections.add(18, "Politics");
        sections.add(19, "Science");
        sections.add(20, "Society");
        sections.add(21, "Sport");
        sections.add(22, "Technology");
        sections.add(23, "Travel");
        sections.add(24, "Tv and Radio");
        sections.add(25, "Weather");

        sectionMap.put("Australia Headlines", "australia-news");
        sectionMap.put("UK Headlines", "uk-news");
        sectionMap.put("US Headlines", "us-news");
        sectionMap.put("International Headlines", "news");
        sectionMap.put("Art and Design", "artanddesign");
        sectionMap.put("Books", "books");
        sectionMap.put("Business", "business");
        sectionMap.put("Culture", "culture");
        sectionMap.put("Education", "education");
        sectionMap.put("Environment", "environment");
        sectionMap.put("Fashion", "fashion");
        sectionMap.put("Film", "film");
        sectionMap.put("Football", "football");
        sectionMap.put("Law", "law");
        sectionMap.put("Lifestyle", "lifeandstyle");
        sectionMap.put("Media", "media");
        sectionMap.put("Money", "money");
        sectionMap.put("Music", "music");
        sectionMap.put("Politics", "politics");
        sectionMap.put("Science", "science");
        sectionMap.put("Society", "society");
        sectionMap.put("Sport", "sport");
        sectionMap.put("Technology", "technology");
        sectionMap.put("Travel", "travel");
        sectionMap.put("Tv and Radio", "tv-and-radio");
        sectionMap.put("Weather", "weather");
    }
}



