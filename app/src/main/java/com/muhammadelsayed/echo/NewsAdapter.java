package com.muhammadelsayed.echo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Muhammad Elsayed on 11/18/2017.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(@NonNull Context context, ArrayList<News> news) {
        super(context, 0, news);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View newsListView = convertView;

        if (newsListView == null) {
            newsListView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        News currentNews = getItem(position);

        TextView date = newsListView.findViewById(R.id.date_text_view);
        date.setText(currentNews.getDate());


        TextView title = newsListView.findViewById(R.id.title_text_view);
        title.setText(currentNews.getTitle());


        return newsListView;
    }
}
