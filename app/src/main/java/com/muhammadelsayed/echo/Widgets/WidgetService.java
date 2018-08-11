package com.muhammadelsayed.echo.Widgets;

import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViewsService;

public class WidgetService extends RemoteViewsService {
    private static final String TAG = WidgetService.class.getSimpleName();

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        Log.wtf(TAG, "RemoteViewsFactory() has been instantiated");
        return new ListViewsFactory(this.getApplicationContext(), intent);
    }
}


