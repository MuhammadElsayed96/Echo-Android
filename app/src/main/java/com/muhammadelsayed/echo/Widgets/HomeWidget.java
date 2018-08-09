package com.muhammadelsayed.echo.Widgets;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

import com.muhammadelsayed.echo.R;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link HomeWidgetConfigureActivity HomeWidgetConfigureActivity}
 * <p>
 * <p>
 * This method is called every 30 mins as specified on home_widget_info.xml
 * This method is also called on every phone reboot
 */
public class HomeWidget extends AppWidgetProvider {
    private static final String TAG = HomeWidget.class.getSimpleName();

    static RemoteViews updateAppWidget(Context context, int appWidgetId) {
        Log.wtf(TAG, "updateAppWidget() has been instantiated");

        CharSequence sectionTitle = HomeWidgetConfigureActivity.loadSectionPref(context, appWidgetId);
//        List<Article> selectedSection = (List<Article>) sections.get(sectionTitle);

        // Construct the RemoteViews object
        //which layout to show on widget
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.home_widget);
//        views.setTextViewText(R.id.widget_header_section_text, "Manual");

        //RemoteViews Service needed to provide adapter for ListView
        Intent svcIntent = new Intent(context, WidgetService.class);

        //passing app widget id to that RemoteViews Service
        svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        //setting a unique Uri to the intent
        //don't know its purpose to me right now
        svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
        //setting adapter to listview of the widget
        views.setRemoteAdapter(appWidgetId, R.id.widget_news_list, svcIntent);
        //setting an empty view in case of no data
        views.setEmptyView(R.id.widget_news_list, R.id.empty_view);
        return views;
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.wtf(TAG, "onUpdate() has been instantiated");

        // There may be multiple widgets active, so update all of them

        /*
         *  int[] appWidgetIds holds ids of multiple instance
         *  of your widget
         *  meaning you are placing more than one widgets on
         *  your homescreen
         * */
        int n = appWidgetIds.length;
        for (int appWidgetId : appWidgetIds) {
            RemoteViews remoteViews = updateAppWidget(context, appWidgetId);
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        Log.wtf(TAG, "onDeleted() has been instantiated");
        // When the user deletes the widget, delete the preference associated with it.
        for (int appWidgetId : appWidgetIds) {
            HomeWidgetConfigureActivity.deleteSectionPref(context, appWidgetId);
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
}

