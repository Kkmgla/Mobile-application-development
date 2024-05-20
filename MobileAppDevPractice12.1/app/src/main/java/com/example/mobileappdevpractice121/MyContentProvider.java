package com.example.mobileappdevpractice121;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MyContentProvider extends ContentProvider {

    public static final String AUTHORITY = "com.example.mobileappdevpractice121.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/data");

    private static final int DATA = 1;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, "data", DATA);
    }

    private SQLiteDatabase database;

    @Override
    public boolean onCreate() {
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        database = dbHelper.getWritableDatabase();
        if (database != null) {
            addInitialData();
        }
        return database != null;
    }

    private void addInitialData() {
        ContentValues values = new ContentValues();
        values.put("name", "Data1");
        database.insert("data", null, values);

        values.put("name", "Data2");
        database.insert("data", null, values);

        values.put("name", "Data3");
        database.insert("data", null, values);

        Log.i("MyContentProvider", "Initial data added to the database.");
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (uriMatcher.match(uri) == DATA) {
            return database.query("data", projection, selection, selectionArgs, null, null, sortOrder);
        }
        throw new IllegalArgumentException("Unsupported URI: " + uri);
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case DATA:
                return "vnd.android.cursor.dir/vnd.com.example.data";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowID = database.insert("data", "", values);
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLException("Failed to add a record into " + uri);
    }
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return database.delete("data", selection, selectionArgs);
    }
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return database.update("data", values, selection, selectionArgs);
    }
    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "data.db";
        private static final int DATABASE_VERSION = 1;
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE data (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS data");
            onCreate(db);
        }
    }
}