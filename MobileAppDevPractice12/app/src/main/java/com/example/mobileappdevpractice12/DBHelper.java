package com.example.mobileappdevpractice12;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "crypto.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Создаем таблицу "crypto"
        final String SQL_CREATE_CRYPTO_TABLE = "CREATE TABLE " +
                "crypto" + " (" +
                "_id" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title" + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_CRYPTO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Метод вызывается, если изменяется версия базы данных
        db.execSQL("DROP TABLE IF EXISTS " + "crypto");
        onCreate(db);
    }
}