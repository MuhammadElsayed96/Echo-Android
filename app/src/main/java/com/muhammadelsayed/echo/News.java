package com.muhammadelsayed.echo;

/**
 * Created by Muhammad Elsayed on 11/18/2017.
 */

public class News {
    private String mTitle;
    private String mDate;
    private String mUrl;

    public News(String title, String date, String url){
        mTitle = title;
        mDate = date;
        mUrl = url;
    }

    public String getDate() {
        return mDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getmUrl() {
        return mUrl;
    }
}
