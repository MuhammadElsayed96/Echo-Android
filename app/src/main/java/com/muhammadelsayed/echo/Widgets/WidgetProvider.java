package com.muhammadelsayed.echo.Widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import com.muhammadelsayed.echo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link HomeWidgetConfigureActivity HomeWidgetConfigureActivity}
 * <p>
 * <p>
 * This method is called every 30 mins as specified on widget_info * This method is also called on every phone reboot
 */
public class WidgetProvider extends AppWidgetProvider {
    public static final String TOAST_ACTION = "com.muhammadelsayed.echo.Widgets.TOAST_ACTION";
    public static final String EXTRA_ITEM = "com.muhammadelsayed.echo.Widgets.EXTRA_ITEM";
    private static final String TAG = WidgetProvider.class.getSimpleName();
    private List<String> sections = null;
    private List<String> section = null;
    private Map<String, String> sectionMap = null;
    private static int firstTime = 1;

    /**
     * However, if you have declared a configuration Activity,
     * this method is not called when the user adds the App Widget,
     * but is called for the subsequent updates.
     * It is the responsibility of the configuration Activity to perform
     * the first update when configuration is done.
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.wtf(TAG, "onUpdate() has been instantiated");

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int appWidgetId : appWidgetIds) {

            //RemoteViews Service needed to provide adapter for ListView
            Intent svcIntent = new Intent(context, WidgetService.class);

            //passing app widget id to that RemoteViews Service
            svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);

            // When intents are compared, the extras are ignored, so we need to embed the extras
            // into the data so that the extras will not be ignored.
            svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
            // Construct the RemoteViews object
            //which layout to show on widget
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);


            String sectionTitle = HomeWidgetConfigureActivity.loadSectionPref(context, appWidgetId);
            Log.wtf(TAG, "Section Name -> " + sectionTitle);
            if (!sectionTitle.equals("EXAMPLE")) {
                int title1 = sections.indexOf(sectionTitle);
                Log.wtf(TAG, "title1 -> " + title1);
                String title = section.get(title1);
                Log.wtf(TAG, "title -> " + title);
                views.setTextViewText(R.id.widget_header_section_text, title);
            }
            //setting adapter to listView of the widget
            views.setRemoteAdapter(R.id.widget_news_list, svcIntent);

            // The empty view is displayed when the collection has no items. It should be a sibling
            // of the collection view.
            views.setEmptyView(R.id.widget_news_list, R.id.empty_view);

            // Here we setup the a pending intent template. Individuals items of a collection
            // cannot setup their own pending intents, instead, the collection as a whole can
            // setup a pending intent template, and the individual items can set a fillInIntent
            // to create unique before on an item to item basis.

            Intent toastIntent = new Intent(context, WidgetProvider.class);
            toastIntent.setAction(WidgetProvider.TOAST_ACTION);
            toastIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
            PendingIntent toastPendingIntent = PendingIntent.getBroadcast(context, 0, toastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setPendingIntentTemplate(R.id.widget_news_list, toastPendingIntent);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_news_list);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        Log.wtf(TAG, "onDeleted() has been instantiated");
        // When the user deletes the widget, delete the preference associated with it.
        AppWidgetManager mgr = AppWidgetManager.getInstance(context);
        for (int appWidgetId : appWidgetIds) {
            HomeWidgetConfigureActivity.deleteSectionPref(context, appWidgetId);
            mgr.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_news_list);
        }
    }

    @Override
    public void onEnabled(Context context) {
        Log.wtf(TAG, "onEnabled() has been instantiated");
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        Log.wtf(TAG, "onDisabled() has been instantiated");
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.wtf(TAG, "onReceive() has been instantiated");
        sectionMap = new HashMap<>();
        sections = new ArrayList<>();
        section = new ArrayList<>();
        loadSections();

        AppWidgetManager mgr = AppWidgetManager.getInstance(context);

//        final String PREFS_NAME = "com.muhammadelsayed.echo.Widgets.WidgetProvider";
//        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
//        boolean clicked = prefs.getBoolean("click", false);
//        Log.wtf(TAG, "clicked -> " + clicked);
//        if (clicked) {
//            SharedPreferences.Editor pref = context.getSharedPreferences(PREFS_NAME, 0).edit();
//            pref.putBoolean("click", false).apply();
//            Log.wtf(TAG, "clicked -> " + prefs.getBoolean("click", false));
//            String articleUrl = intent.getStringExtra("article_url");
//            Intent intent1 = new Intent(context, MainActivity.class);
//            intent1.putExtra("article_url", articleUrl);
//            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            Log.wtf(TAG, "article_url = " + articleUrl);
//            context.startActivity(intent1);
//
//        }
        super.onReceive(context, intent);
    }

    @Override
    public void onAppWidgetOptionsChanged(final Context context, final AppWidgetManager appWidgetManager, final int appWidgetId, final Bundle newOptions) {
        Log.wtf(TAG, "onAppWidgetOptionsChanged() has been instantiated");
        onUpdate(context, appWidgetManager, new int[]{appWidgetId});
    }

    private void loadSections() {
        Log.wtf(TAG, "loadSections() has been instantiated");

        sections.add(0, "australia-news");
        sections.add(1, "uk-news");
        sections.add(1, "us-news");
        sections.add(3, "news");
        sections.add(4, "artanddesign");
        sections.add(5, "books");
        sections.add(6, "business");
        sections.add(7, "culture");
        sections.add(8, "education");
        sections.add(9, "environment");
        sections.add(10, "fashion");
        sections.add(11, "film");
        sections.add(12, "football");
        sections.add(13, "law");
        sections.add(14, "lifestyle");
        sections.add(15, "media");
        sections.add(16, "money");
        sections.add(17, "music");
        sections.add(18, "politics");
        sections.add(19, "science");
        sections.add(20, "society");
        sections.add(21, "sport");
        sections.add(22, "technology");
        sections.add(23, "travel");
        sections.add(24, "tv-and-radio");
        sections.add(25, "weather");


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


        section.add(0, "Australia Headlines");
        section.add(1, "UK Headlines");
        section.add(1, "US Headlines");
        section.add(3, "International Headlines");
        section.add(4, "Art and Design");
        section.add(5, "Books");
        section.add(6, "Business");
        section.add(7, "Culture");
        section.add(8, "Education");
        section.add(9, "Environment");
        section.add(10, "Fashion");
        section.add(11, "Film");
        section.add(12, "Football");
        section.add(13, "Law");
        section.add(14, "Lifestyle");
        section.add(15, "Media");
        section.add(16, "Money");
        section.add(17, "Music");
        section.add(18, "Politics");
        section.add(19, "Science");
        section.add(20, "Society");
        section.add(21, "Sport");
        section.add(22, "Technology");
        section.add(23, "Travel");
        section.add(24, "Tv and Radio");
        section.add(25, "Weather");
    }
}
