package com.muhammadelsayed.echo.Widgets;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
    public static final String TOAST_ACTION = "com.muhammadelsayed.echo.Widgets.TOAST_ACTION";
    public static final String EXTRA_ITEM = "com.muhammadelsayed.echo.Widgets.EXTRA_ITEM";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        Log.wtf(TAG, "updateAppWidget() has been instantiated");

//        CharSequence sectionTitle = HomeWidgetConfigureActivity.loadSectionPref(context, appWidgetId);
//        List<Article> selectedSection = (List<Article>) sections.get(sectionTitle);

        // Construct the RemoteViews object
        //which layout to show on widget
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.home_widget);
//        views.setTextViewText(R.id.widget_header_section_text, "Manual");
        setRemoteAdapter(context, views);

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


        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_news_list);
        // Tell the AppWidgetManager to perform an update on the current app widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private static void setRemoteAdapter(Context context, @NonNull final RemoteViews views) {
        Log.wtf(TAG, "setRemoteAdapter() has been instantiated");

        views.setRemoteAdapter(R.id.widget_news_list,
                new Intent(context, WidgetService.class));
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

    /**
     * However, if you have declared a configuration Activity,
     * this method is not called when the user adds the App Widget,
     * but is called for the subsequent updates.
     * It is the responsibility of the configuration Activity to perform
     * the first update when configuration is done.
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.wtf(TAG, "onUpdate() has been instantiated");

        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            // Instruct the widget manager to update the widget
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    /**
     * This is called when the widget is first placed and any time the widget is resized.
     * You can use this callback to show or hide content based on the widget's size ranges
     */
    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        updateAppWidget(context, appWidgetManager, appWidgetId);
    }
}

