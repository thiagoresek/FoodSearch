package com.lifesum.thiago.foodsearch.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import com.lifesum.thiago.foodsearch.database.FoodSqliteHelper;

// Content provider to make access easier. Could be made public if we wanted other apps to have access
public class FoodInfoContentProvider extends ContentProvider {

    public static final String AUTHORITY = "com.lifesum.thiago.foodsearch.providers";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    
    private SQLiteDatabase mSqlDb = null;
    private FoodSqliteHelper mDb = null;
    
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return mSqlDb.delete(FoodSqliteHelper.TABLE_FOODS, selection, selectionArgs);
    }
    
    @Override
    public String getType(Uri uri) {
        return null;
    }
    
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowId = mSqlDb.insert(FoodSqliteHelper.TABLE_FOODS, null, values);
        Uri endInsUri = ContentUris.withAppendedId(CONTENT_URI, rowId);
        return endInsUri;
    }
    
    
    @Override
    public boolean onCreate() {
        mDb = new FoodSqliteHelper(getContext());
        mSqlDb = mDb.getWritableDatabase();

        if(mSqlDb == null) {
            return false;
        }
        return true;
    }
    
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
            String sortOrder) {
        Cursor cursor = null;
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(FoodSqliteHelper.TABLE_FOODS);
        cursor = builder.query(mSqlDb, projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }
    
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int rowId = mSqlDb.update(FoodSqliteHelper.TABLE_FOODS, values, selection, selectionArgs);
        return rowId;
    }
    
    
}
