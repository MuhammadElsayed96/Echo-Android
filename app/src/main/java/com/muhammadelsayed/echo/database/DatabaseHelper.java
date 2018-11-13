package com.muhammadelsayed.echo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.muhammadelsayed.echo.database.Model.ArticleModel;
import com.muhammadelsayed.echo.model.Article;
import com.muhammadelsayed.echo.model.Fields;
import com.muhammadelsayed.echo.model.Tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "news_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.wtf(TAG, "onCreate() has been instantiated");
        sqLiteDatabase.execSQL(ArticleModel.CREATE_SAVED_TABLE);
        sqLiteDatabase.execSQL(ArticleModel.CREATE_HISTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.wtf(TAG, "onUpgrade() has been instantiated");

        sqLiteDatabase.execSQL(ArticleModel.DROP_SAVED_TABLE);
        sqLiteDatabase.execSQL(ArticleModel.DROP_HISTORY_TABLE);
        onCreate(sqLiteDatabase);
    }

    public long saveArticle(Article article) {
        Log.wtf(TAG, "saveArticle() has been instantiated");

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ArticleModel.COLUMN_ARTICLE_ID, article.getId());
        values.put(ArticleModel.COLUMN_TYPE, article.getType());
        values.put(ArticleModel.COLUMN_SECTION_ID, article.getSectionId());
        values.put(ArticleModel.COLUMN_SECTION_NAME, article.getSectionName());
        values.put(ArticleModel.COLUMN_DATE, article.getWebPublicationDate());
        values.put(ArticleModel.COLUMN_TITLE, article.getWebTitle());
        values.put(ArticleModel.COLUMN_WEB_URL, article.getWebUrl());
        values.put(ArticleModel.COLUMN_THUMBNAIL, article.getFields().getThumbnail());
        if (article.getTags().length <= 0) {
            Tag tag = new Tag();
            tag.setWebTitle("Anonymous");
            article.setTags(new Tag[]{tag});
        }
        values.put(ArticleModel.COLUMN_AUTHOR, article.getTags()[0].getWebTitle());

        long id = db.insert(ArticleModel.SAVED_TABLE_NAME, null, values);

        db.close();

        return id;
    }

    public long addArticleToHistory(Article article) {
        Log.wtf(TAG, "saveArticle() has been instantiated");
        if (isArticleAlreadyHistory(article.getId()))
            return -1;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ArticleModel.COLUMN_ARTICLE_ID, article.getId());
        values.put(ArticleModel.COLUMN_TYPE, article.getType());
        values.put(ArticleModel.COLUMN_SECTION_ID, article.getSectionId());
        values.put(ArticleModel.COLUMN_SECTION_NAME, article.getSectionName());
        values.put(ArticleModel.COLUMN_DATE, article.getWebPublicationDate());
        values.put(ArticleModel.COLUMN_TITLE, article.getWebTitle());
        values.put(ArticleModel.COLUMN_WEB_URL, article.getWebUrl());
        values.put(ArticleModel.COLUMN_THUMBNAIL, article.getFields().getThumbnail());
        if (article.getTags().length <= 0) {
            Tag tag = new Tag();
            tag.setWebTitle("Anonymous");
            article.setTags(new Tag[]{tag});
        }
        values.put(ArticleModel.COLUMN_AUTHOR, article.getTags()[0].getWebTitle());

        long id = db.insert(ArticleModel.HISTORY_TABLE_NAME, null, values);

        db.close();

        return id;
    }

    public boolean isArticleAlreadySaved(String id) {
        Log.wtf(TAG, "articleExists() has been instantiated");
        String countQuery = "SELECT * FROM " + ArticleModel.SAVED_TABLE_NAME + " WHERE " + ArticleModel.COLUMN_ARTICLE_ID + " == '" + id + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        return count > 0;
    }

    private boolean isArticleAlreadyHistory(String id) {
        Log.wtf(TAG, "articleExists() has been instantiated");
        String countQuery = "SELECT * FROM " + ArticleModel.HISTORY_TABLE_NAME + " WHERE " + ArticleModel.COLUMN_ARTICLE_ID + " == '" + id + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        return count > 0;
    }

    public ArticleModel getSavedArticle(String id) {
        Log.wtf(TAG, "getArticle() has been instantiated");
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(ArticleModel.SAVED_TABLE_NAME,
                new String[]{ArticleModel.COLUMN_ID,
                        ArticleModel.COLUMN_ARTICLE_ID,
                        ArticleModel.COLUMN_TYPE,
                        ArticleModel.COLUMN_SECTION_ID,
                        ArticleModel.COLUMN_SECTION_NAME,
                        ArticleModel.COLUMN_DATE,
                        ArticleModel.COLUMN_TITLE,
                        ArticleModel.COLUMN_WEB_URL,
                        ArticleModel.COLUMN_THUMBNAIL,
                        ArticleModel.COLUMN_AUTHOR},
                ArticleModel.COLUMN_ARTICLE_ID + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);

        if (cursor != null)
            cursor.moveToFirst();

        ArticleModel article = new ArticleModel(
                cursor.getString(cursor.getColumnIndex(ArticleModel.COLUMN_ARTICLE_ID)),
                cursor.getString(cursor.getColumnIndex(ArticleModel.COLUMN_TYPE)),
                cursor.getString(cursor.getColumnIndex(ArticleModel.COLUMN_SECTION_ID)),
                cursor.getString(cursor.getColumnIndex(ArticleModel.COLUMN_SECTION_NAME)),
                cursor.getString(cursor.getColumnIndex(ArticleModel.COLUMN_DATE)),
                cursor.getString(cursor.getColumnIndex(ArticleModel.COLUMN_TITLE)),
                cursor.getString(cursor.getColumnIndex(ArticleModel.COLUMN_WEB_URL)),
                cursor.getString(cursor.getColumnIndex(ArticleModel.COLUMN_THUMBNAIL)),
                cursor.getString(cursor.getColumnIndex(ArticleModel.COLUMN_AUTHOR))
        );
        cursor.close();
        return article;
    }

    public List<Article> getAllSavedArticles() {
        Log.wtf(TAG, "getAllSavedArticles() has been instantiated");

        List<Article> articles = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + ArticleModel.SAVED_TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                Article article = new Article();
                article.setId(cursor.getString(cursor.getColumnIndex(ArticleModel.COLUMN_ARTICLE_ID)));
                article.setType(cursor.getString(cursor.getColumnIndex(ArticleModel.COLUMN_TYPE)));
                article.setSectionId(cursor.getString(cursor.getColumnIndex(ArticleModel.COLUMN_SECTION_ID)));
                article.setSectionName(cursor.getString(cursor.getColumnIndex(ArticleModel.COLUMN_SECTION_NAME)));
                article.setWebPublicationDate(cursor.getString(cursor.getColumnIndex(ArticleModel.COLUMN_DATE)));
                article.setWebTitle(cursor.getString(cursor.getColumnIndex(ArticleModel.COLUMN_TITLE)));
                article.setWebUrl(cursor.getString(cursor.getColumnIndex(ArticleModel.COLUMN_WEB_URL)));

                Fields field = new Fields();
                field.setThumbnail(cursor.getString(cursor.getColumnIndex(ArticleModel.COLUMN_THUMBNAIL)));
                article.setFields(field);

                Tag tag = new Tag();
                tag.setWebTitle(cursor.getString(cursor.getColumnIndex(ArticleModel.COLUMN_AUTHOR)));
                article.setTags(new Tag[]{tag});

                articles.add(article);

            } while (cursor.moveToNext());
        }

        db.close();

        Collections.reverse(articles);

        return articles;
    }

    public List<Article> getAllHistoryArticles() {
        Log.wtf(TAG, "getAllSavedArticles() has been instantiated");

        List<Article> articles = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + ArticleModel.HISTORY_TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                Article article = new Article();
                article.setId(cursor.getString(cursor.getColumnIndex(ArticleModel.COLUMN_ARTICLE_ID)));
                article.setType(cursor.getString(cursor.getColumnIndex(ArticleModel.COLUMN_TYPE)));
                article.setSectionId(cursor.getString(cursor.getColumnIndex(ArticleModel.COLUMN_SECTION_ID)));
                article.setSectionName(cursor.getString(cursor.getColumnIndex(ArticleModel.COLUMN_SECTION_NAME)));
                article.setWebPublicationDate(cursor.getString(cursor.getColumnIndex(ArticleModel.COLUMN_DATE)));
                article.setWebTitle(cursor.getString(cursor.getColumnIndex(ArticleModel.COLUMN_TITLE)));
                article.setWebUrl(cursor.getString(cursor.getColumnIndex(ArticleModel.COLUMN_WEB_URL)));

                Fields field = new Fields();
                field.setThumbnail(cursor.getString(cursor.getColumnIndex(ArticleModel.COLUMN_THUMBNAIL)));
                article.setFields(field);

                Tag tag = new Tag();
                tag.setWebTitle(cursor.getString(cursor.getColumnIndex(ArticleModel.COLUMN_AUTHOR)));
                article.setTags(new Tag[]{tag});

                articles.add(article);

            } while (cursor.moveToNext());
        }

        db.close();

        Collections.reverse(articles);

        return articles;
    }

    public int getSavedArticlesCount() {
        Log.wtf(TAG, "getSavedArticlesCount() has been instantiated");

        String countQuery = "SELECT * FROM " + ArticleModel.SAVED_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        return count;
    }

    public int getHistoryArticlesCount() {
        Log.wtf(TAG, "getSavedArticlesCount() has been instantiated");

        String countQuery = "SELECT * FROM " + ArticleModel.HISTORY_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        return count;
    }

    public void deleteSavedArticle(Article article) {
        Log.wtf(TAG, "deleteSavedArticle() has been instantiated");

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ArticleModel.SAVED_TABLE_NAME,
                ArticleModel.COLUMN_ARTICLE_ID + " = ?",
                new String[]{String.valueOf(article.getId())});

        db.close();
    }


    public void deleteHistoryArticle(Article article) {
        Log.wtf(TAG, "deleteSavedArticle() has been instantiated");

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ArticleModel.HISTORY_TABLE_NAME,
                ArticleModel.COLUMN_ARTICLE_ID + " = ?",
                new String[]{String.valueOf(article.getId())});

        db.close();
    }


}
