package com.muhammadelsayed.echo.Widgets;

import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViewsService;

public class WidgetService extends RemoteViewsService {
    private static final String TAG = WidgetService.class.getSimpleName();

    /**
     * So pretty simple just defining the Adapter of the listView
     * here Adapter is ListProvider
     */

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        Log.wtf(TAG, "RemoteViewsFactory() has been instantiated");

        return (new ListProvider(this.getApplicationContext(), intent));
    }
}
