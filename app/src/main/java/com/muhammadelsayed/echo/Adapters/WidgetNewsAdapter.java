package com.muhammadelsayed.echo.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.muhammadelsayed.echo.model.Article;

import java.util.List;

public class WidgetNewsAdapter extends ArrayAdapter<Article> {
    public WidgetNewsAdapter(@NonNull Context context, int resource, List<Article> articles) {
        super(context, resource, articles);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
