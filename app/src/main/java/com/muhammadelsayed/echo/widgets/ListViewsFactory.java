package com.muhammadelsayed.echo.widgets;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.muhammadelsayed.echo.R;
import com.muhammadelsayed.echo.echo_utils.Constants;
import com.muhammadelsayed.echo.echo_utils.Utils;
import com.muhammadelsayed.echo.model.Article;
import com.squareup.picasso.Picasso;
import com.thefinestartist.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * RemoteViewsFactory
 * Serves the purpose of an adapter in the widget’s context.
 * An adapter is used to connect the collection items
 * (for example, ListView items or GridView items) with the data set.
 * <p>
 * This class is the Adapter of the ListView.
 */
public class ListViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private static final String TAG = ListViewsFactory.class.getSimpleName();
    static final String CLICK_ACTION = "com.muhammadelsayed.echo.Widgets.CLICK_ACTION";
    private Context context = null;
    private int appWidgetId;
    private List<Article> selectedSection = null;

    public ListViewsFactory() {
    }

    ListViewsFactory(Context context, Intent intent) {
        Log.wtf(TAG, "ListViewsFactory() has been instantiated");
        this.context = context;
        appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {
        Log.wtf(TAG, "onCreate() has been instantiated");
        Base.initialize(context);
        selectedSection = new ArrayList<>();
        Map<String, Object> options = new HashMap<>();
        String prefSection = HomeWidgetConfigureActivity.loadSectionPref(context, appWidgetId);
        Log.wtf(TAG, "prefSection Name -> " + prefSection);
        options.put("section", prefSection);
        options.put("order-by", "newest");
        options.put("show-tags", "contributor");
        options.put("show-fields", "thumbnail,showInRelatedContent,shortUrl");
        options.put("page", 1);
        options.put("page-size", 20);
        options.put("api-key", Constants.GUARDIAN_API_KEY);
        Utils.getNews(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                Log.wtf(TAG, "onSuccess:" + articles);
//                selectedSection = filterArticles(articles);
                selectedSection = articles;
                Log.wtf(TAG, "Data has been completely loaded from the internet");
                // Make sure we pass back the original appWidgetId
                // It is the responsibility of the configuration activity to update the app widget
                Intent resultValue = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE, null, context, WidgetProvider.class);
                resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, new int[]{appWidgetId});
                context.sendBroadcast(resultValue);
                Log.wtf(TAG, "Broadcast sent");
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, "onFailure(): " + t.getMessage());
            }
        });

//        selectedSection = SplashActivity.sections.get(sectionTitle);
//        Log.wtf(TAG, "Data Source -> " + selectedSection.toString());


        // We sleep for 3 seconds here to show how the empty view appears in the interim.
        // The empty view is set in the WidgetProvider and should be a sibling of the
        // collection view.
//        try {
//            Thread.sleep(3000);
//        } catch (final InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onDataSetChanged() {
        // This is triggered when you call AppWidgetManager notifyAppWidgetViewDataChanged
        // on the collection view corresponding to this factory. You can do heaving lifting in
        // here, synchronously. For example, if you need to process an image, fetch something
        // from the network, etc., it is ok to do it here, synchronously. The widget will remain
        // in its current state while work is being done here, so you don't need to worry about
        // locking up the widget.
    }

    @Override
    public void onDestroy() {
        // In onDestroy() you should tear down anything that was setup for your data source,
        // eg. cursors, connections, etc.
        selectedSection.clear();
    }

    @Override
    public int getCount() {
        Log.wtf(TAG, "getCount() has been instantiated");
        Log.wtf(TAG, "getCount() = " + selectedSection.size());
        return selectedSection.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        Log.wtf(TAG, "getViewAt() has been instantiated");

        // We construct a remote views item based on our widget item xml file, and set the
        // text based on the position.
        RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.widget_list_item);
        Article article = selectedSection.get(position);
        remoteView.setTextViewText(R.id.widget_title_text, article.getWebTitle());
        Bitmap b = null;
        try {
            b = Picasso.get().load(article.getFields().getThumbnail()).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        remoteView.setImageViewBitmap(R.id.widget_news_image, b);

        //  Next, we set a fill-intent which will be used to fill-in the pending intent template
        //  which is set on the collection view in WidgetProvider.
        final Bundle extras = new Bundle();
        extras.putInt(WidgetProvider.EXTRA_ITEM, position);
        extras.putString("article_url", article.getWebUrl());
        final Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);
        remoteView.setOnClickFillInIntent(R.id.list_item_container, fillInIntent);

        return remoteView;
    }

    @Override
    public RemoteViews getLoadingView() {
        // You can create a custom loading view (for instance when getViewAt() is slow.) If you
        // return null here, you will get the default loading view.
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}