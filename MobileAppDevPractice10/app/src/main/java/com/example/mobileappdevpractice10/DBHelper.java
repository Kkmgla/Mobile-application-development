package com.example.mobileappdevpractice10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "mytable";
    public static final String COLUMN_ID = "_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_ID = "user_id";
    public static final String NUMBER = "number";
    public static final String HEIGHT = "height";
    public static final String WEIGHT = "weight";
    public static final int DB_VERSION = 1;
    public static final String TABLE_STRUCT = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY," + USER_NAME + " TEXT," +
            USER_ID + " TEXT," + NUMBER + " TEXT," + HEIGHT + " TEXT," + WEIGHT + " TEXT)";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DBHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_STRUCT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
    public void updateUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        db.delete(DBHelper.TABLE_NAME, "user_name=?", new String[]{user.getName()});

        contentValues.put(USER_NAME, user.getName());
        contentValues.put(USER_ID, user.getId());
        contentValues.put(NUMBER, user.getNumber());
        contentValues.put(HEIGHT, user.getHeight());
        contentValues.put(WEIGHT, user.getWeight());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }
    public List<User> getAllUsers(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<User> users = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()){
            do {
                User user = new User(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5));
                users.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return users;
    }
    public void deleteUser(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DBHelper.TABLE_NAME, "user_name=?", new String[]{name});
        db.close();
    }
    public User findUser(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                new String[]{COLUMN_ID, USER_NAME, USER_ID, NUMBER, HEIGHT, WEIGHT},
                USER_NAME + " = ?", new String[]{name}, null, null, null);
        if (cursor != null && cursor.moveToFirst()){
            User user = new User(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)
            );
            db.close();
            return user;
        }
        if (cursor != null)
            cursor.close();
        db.close();
        return null;
    }


}