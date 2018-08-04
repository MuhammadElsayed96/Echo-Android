package com.muhammadelsayed.echo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.muhammadelsayed.echo.model.Article;

import java.util.ArrayList;
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
        sqLiteDatabase.execSQL(SavedArticle.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.wtf(TAG, "onUpgrade() has been instantiated");

        sqLiteDatabase.execSQL(SavedArticle.DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    public long saveArticle (Article article) {
        Log.wtf(TAG, "saveArticle() has been instantiated");
        if (articleExists(article.getId()))
            return -1;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SavedArticle.COLUMN_ARTICLE_ID, article.getId());
        values.put(SavedArticle.COLUMN_TYPE, article.getType());
        values.put(SavedArticle.COLUMN_SECTION_ID, article.getSectionId());
        values.put(SavedArticle.COLUMN_SECTION_NAME, article.getSectionName());
        values.put(SavedArticle.COLUMN_DATE, article.getWebPublicationDate());
        values.put(SavedArticle.COLUMN_TITLE, article.getWebTitle());
        values.put(SavedArticle.COLUMN_WEB_URL, article.getWebUrl());
        values.put(SavedArticle.COLUMN_THUMBNAIL, article.getFields().getThumbnail());
        values.put(SavedArticle.COLUMN_AUTHOR, article.getTags()[0].getWebTitle());

        long id = db.insert(SavedArticle.TABLE_NAME, null, values);

        db.close();

        return id;
    }

    public boolean articleExists(String id) {
        Log.wtf(TAG, "articleExists() has been instantiated");
        String countQuery = "SELECT * FROM " + SavedArticle.TABLE_NAME + " WHERE " + SavedArticle.COLUMN_ARTICLE_ID + " == '" + id + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        if (count > 0)
            return true;
        return false;

    }

    public SavedArticle getArticle(String id) {
        Log.wtf(TAG, "getArticle() has been instantiated");
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(SavedArticle.TABLE_NAME,
                new String[] {SavedArticle.COLUMN_ID,
                        SavedArticle.COLUMN_ARTICLE_ID,
                        SavedArticle.COLUMN_TYPE,
                        SavedArticle.COLUMN_SECTION_ID,
                        SavedArticle.COLUMN_SECTION_NAME,
                        SavedArticle.COLUMN_DATE,
                        SavedArticle.COLUMN_TITLE,
                        SavedArticle.COLUMN_WEB_URL,
                        SavedArticle.COLUMN_THUMBNAIL,
                        SavedArticle.COLUMN_AUTHOR},
                SavedArticle.COLUMN_ARTICLE_ID + "=?",
                new String[] {String.valueOf(id)},
                null,
                null,
                null);

        if (cursor != null)
            cursor.moveToFirst();

        SavedArticle article = new SavedArticle(
                cursor.getString(cursor.getColumnIndex(SavedArticle.COLUMN_ARTICLE_ID)),
                cursor.getString(cursor.getColumnIndex(SavedArticle.COLUMN_TYPE)),
                cursor.getString(cursor.getColumnIndex(SavedArticle.COLUMN_SECTION_ID)),
                cursor.getString(cursor.getColumnIndex(SavedArticle.COLUMN_SECTION_NAME)),
                cursor.getString(cursor.getColumnIndex(SavedArticle.COLUMN_DATE)),
                cursor.getString(cursor.getColumnIndex(SavedArticle.COLUMN_TITLE)),
                cursor.getString(cursor.getColumnIndex(SavedArticle.COLUMN_WEB_URL)),
                cursor.getString(cursor.getColumnIndex(SavedArticle.COLUMN_THUMBNAIL)),
                cursor.getString(cursor.getColumnIndex(SavedArticle.COLUMN_AUTHOR))
                        );
        cursor.close();
        return article;
    }

    public List<SavedArticle> getAllArticles() {
        Log.wtf(TAG, "getAllArticles() has been instantiated");

        List<SavedArticle> articles = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + SavedArticle.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                SavedArticle article = new SavedArticle(
                        cursor.getString(cursor.getColumnIndex(SavedArticle.COLUMN_ARTICLE_ID)),
                        cursor.getString(cursor.getColumnIndex(SavedArticle.COLUMN_TYPE)),
                        cursor.getString(cursor.getColumnIndex(SavedArticle.COLUMN_SECTION_ID)),
                        cursor.getString(cursor.getColumnIndex(SavedArticle.COLUMN_SECTION_NAME)),
                        cursor.getString(cursor.getColumnIndex(SavedArticle.COLUMN_DATE)),
                        cursor.getString(cursor.getColumnIndex(SavedArticle.COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndex(SavedArticle.COLUMN_WEB_URL)),
                        cursor.getString(cursor.getColumnIndex(SavedArticle.COLUMN_THUMBNAIL)),
                        cursor.getString(cursor.getColumnIndex(SavedArticle.COLUMN_AUTHOR))
                );

                articles.add(article);

            } while (cursor.moveToNext());
        }

        db.close();

        return articles;
    }

    public int getSavedArticlesCount() {
        Log.wtf(TAG, "getSavedArticlesCount() has been instantiated");

        String countQuery = "SELECT * FROM " + SavedArticle.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        return count;
    }

    public void deleteSavedArticle(Article article) {
        Log.wtf(TAG, "deleteSavedArticle() has been instantiated");

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SavedArticle.TABLE_NAME,
                SavedArticle.COLUMN_ARTICLE_ID + " = ?",
                new String[] {String.valueOf(article.getId())});

        db.close();
    }


}
