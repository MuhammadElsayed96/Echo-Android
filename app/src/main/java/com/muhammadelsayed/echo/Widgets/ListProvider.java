package com.muhammadelsayed.echo.Widgets;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.muhammadelsayed.echo.Model.Article;
import com.muhammadelsayed.echo.R;

import java.util.ArrayList;
import java.util.List;

import static com.muhammadelsayed.echo.SplashActivity.sections;

/**
 * RemoteViewsFactory
 * Serves the purpose of an adapter in the widget’s context.
 * An adapter is used to connect the collection items
 * (for example, ListView items or GridView items) with the data set.
 * <p>
 * This class is the Adapter of the ListView.
 */
public class ListProvider implements RemoteViewsService.RemoteViewsFactory {
    private static final String TAG = ListProvider.class.getSimpleName();
    private Context context = null;
    private int appWidgetId;
    private CharSequence sectionTitle = null;
    private List<Article> selectedSection = new ArrayList<>();

    public ListProvider() {
    }

    public ListProvider(Context context, Intent intent) {
        Log.wtf(TAG, "ListProvider() has been instantiated");
        this.context = context;
        appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }


    @Override
    public void onCreate() {
        Log.wtf(TAG, "onCreate() has been instantiated");

        // In onCreate() you setup any connections / cursors to your data source. Heavy lifting,
        // for example downloading or creating content etc, should be deferred to onDataSetChanged()
        // or getViewAt(). Taking more than 20 seconds in this call will result in an ANR.

        sectionTitle = HomeWidgetConfigureActivity.loadSectionPref(context, appWidgetId);
        selectedSection = (List<Article>) sections.get(sectionTitle);


        // We sleep for 3 seconds here to show how the empty view appears in the interim.
        // The empty view is set in the HomeWidget and should be a sibling of the
        // collection view.
        try {
            Thread.sleep(3000);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
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
        return selectedSection.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        Log.wtf(TAG, "getViewAt() has been instantiated");
        // position will always range from 0 to getCount() - 1.

        // We construct a remote views item based on our widget item xml file, and set the
        // text based on the position.
        RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.widget_list_item);
        Article article = selectedSection.get(position);
        remoteView.setTextViewText(R.id.widget_title_text, article.getWebTitle());
        remoteView.setTextViewText(R.id.widget_author_name, article.getTags()[0].getWebTitle());
//        try {
//            Bitmap b = Picasso.get().load(article.getFields().getThumbnail()).get();
//            remoteView.setImageViewBitmap(R.id.widget_news_image, b);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        // Next, we set a fill-intent which will be used to fill-in the pending intent template
//        // which is set on the collection view in HomeWidget.
//        final Bundle extras = new Bundle();
//        extras.putInt(HomeWidget.EXTRA_ITEM, position);
//        final Intent fillInIntent = new Intent();
//        fillInIntent.putExtras(extras);
//        remoteView.setOnClickFillInIntent(R.id.widget_headline_view, fillInIntent);
//
//        // You can do heaving lifting in here, synchronously. For example, if you need to
//        // process an image, fetch something from the network, etc., it is ok to do it here,
//        // synchronously. A loading view will show up in lieu of the actual contents in the
//        // interim.
//        try {
//            L.d("Loading view " + position);
//            Thread.sleep(500);
//        } catch (final InterruptedException e) {
//            e.printStackTrace();
//        }

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
