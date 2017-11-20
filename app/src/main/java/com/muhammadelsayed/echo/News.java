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

/**
 * Created by Muhammad Elsayed on 11/18/2017.
 */

public class News {
    private String mTitle;
    private String mDate;
    private String mUrl;
    private String mSectionName;
    private static final String NO_AUTHOR_PROVIDED = "null";
    private String mAuthor = NO_AUTHOR_PROVIDED;
    private String mThumbnail;

    public News(String title, String date, String url, String sectionName, String thumbnail, String author) {
        mTitle = title;
        mDate = date;
        mUrl = url;
        mSectionName = sectionName;
        mThumbnail = thumbnail;
        mAuthor = author;
    }

    public String getDate() {
        return mDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getSectionName() {
        return mSectionName;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public boolean hasAuthor() {
        return mAuthor != NO_AUTHOR_PROVIDED;
    }

    public String getThumbnail() {
        return mThumbnail;
    }


}
