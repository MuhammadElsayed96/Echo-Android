package com.muhammadelsayed.echo.Widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.muhammadelsayed.echo.R;

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
        // There may be multiple widgets active, so update all of them

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

//    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
//        Log.wtf(TAG, "updateAppWidget() has been instantiated");
//
//        /*
//         *  CharSequence sectionTitle = HomeWidgetConfigureActivity.loadSectionPref(context, appWidgetId);
//         *  Log.wtf(TAG, "sectionTitle -> " + sectionTitle.toString());
//         *  List<Article> selectedSection = (List<Article>) sections.get(sectionTitle);
//         **/
//
////        //RemoteViews Service needed to provide adapter for ListView
////        Intent svcIntent = new Intent(context, WidgetService.class);
////
////        //passing app widget id to that RemoteViews Service
////        svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
////
////        // When intents are compared, the extras are ignored, so we need to embed the extras
////        // into the data so that the extras will not be ignored.
////        svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
////
////        // Construct the RemoteViews object
////        //which layout to show on widget
////        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
////        //setting adapter to listView of the widget
////        views.setRemoteAdapter(appWidgetId, R.id.widget_news_list, svcIntent);
////
////        // The empty view is displayed when the collection has no items. It should be a sibling
////        // of the collection view.
////        views.setEmptyView(R.id.widget_news_list, R.id.empty_view);
////
////        // Here we setup the a pending intent template. Individuals items of a collection
////        // cannot setup their own pending intents, instead, the collection as a whole can
////        // setup a pending intent template, and the individual items can set a fillInIntent
////        // to create unique before on an item to item basis.
////        Intent toastIntent = new Intent(context, WidgetProvider.class);
////        toastIntent.setAction(WidgetProvider.TOAST_ACTION);
////        toastIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
////        svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
////        PendingIntent toastPendingIntent = PendingIntent.getBroadcast(context, 0, toastIntent,
////                PendingIntent.FLAG_UPDATE_CURRENT);
////        views.setPendingIntentTemplate(R.id.widget_news_list, toastPendingIntent);
////
//////        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_news_list);
//
//        // Construct the RemoteViews object
//        //which layout to show on widget
//        RemoteViews rv = buildRemoteViews(context, appWidgetId);
////        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_news_list);
//        // Tell the AppWidgetManager to perform an update on the current app widget
//        appWidgetManager.updateAppWidget(appWidgetId, rv);
//    }

//    private static void setRemoteAdapter(Context context, @NonNull final RemoteViews views) {
//        Log.wtf(TAG, "setRemoteAdapter() has been instantiated");
//
//        views.setRemoteAdapter(R.id.widget_news_list,
//                new Intent(context, WidgetService.class));
//    }

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

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.wtf(TAG, "onReceive() has been instantiated");
        AppWidgetManager mgr = AppWidgetManager.getInstance(context);
        if (intent.getAction().equals(TOAST_ACTION)) {
            int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
            int viewIndex = intent.getIntExtra(EXTRA_ITEM, 0);
            Toast.makeText(context, "Touched view " + viewIndex, Toast.LENGTH_SHORT).show();
        }
        super.onReceive(context, intent);
    }
}
