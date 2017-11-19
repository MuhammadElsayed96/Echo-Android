/*
 *  Copyright [2017] [Muhammad Elsayed]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.muhammadelsayed.echo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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

        String[] dateArray = handleDate(currentNews.getDate());

        TextView dateView = newsListView.findViewById(R.id.date_text_view);
        String date = formateDate(dateArray[0]);
        dateView.setText(date);

        TextView timeView = newsListView.findViewById(R.id.time_text_view);
        String time = formatTime(dateArray[1]);
        timeView.setText(time);

        TextView sectionView = newsListView.findViewById(R.id.section_name_text_view);
        sectionView.setText(currentNews.getmSectionName());

        TextView titleView = newsListView.findViewById(R.id.title_text_view);
        titleView.setText(currentNews.getTitle());


        return newsListView;
    }

    private String[] handleDate(String date) {
        // Date format: 2017-11-19T20:00:56Z
        String uDate = date.substring(0, 10);
        String uTime = date.substring(11, 16);

        String[] formattedDate = {uDate, uTime};
        return formattedDate;
    }

    private String formateDate(String date) {
        DateFormat originalFormat = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
        DateFormat targetFormat = new SimpleDateFormat("LLL dd, yyyy");
        Date uDate = null;
        try {
            uDate = originalFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = targetFormat.format(uDate);
        return formattedDate;
    }

    private String formatTime(String time) {
        DateFormat originalFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        DateFormat targetFormat = new SimpleDateFormat("h:mm a");
        Date uTime = null;
        try {
            uTime = originalFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedTime = targetFormat.format(uTime);
        return formattedTime;
    }
}
