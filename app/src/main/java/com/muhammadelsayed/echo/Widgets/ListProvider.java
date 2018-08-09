package com.muhammadelsayed.echo.Widgets;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.muhammadelsayed.echo.Model.Article;
import com.muhammadelsayed.echo.R;

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
    private List<Article> selectedSection = null;

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
        sectionTitle = HomeWidgetConfigureActivity.loadSectionPref(context, appWidgetId);
        selectedSection = (List<Article>) sections.get(sectionTitle);
    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        Log.wtf(TAG, "getCount() has been instantiated");
        return selectedSection.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        Log.wtf(TAG, "getViewAt() has been instantiated");

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
        return remoteView;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
