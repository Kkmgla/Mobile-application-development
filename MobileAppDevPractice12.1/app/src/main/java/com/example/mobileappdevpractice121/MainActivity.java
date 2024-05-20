package com.example.mobileappdevpractice121;


import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvData;
    private static final String PROVIDER_URI = "content://com.example.mobileappdevpractice121.provider/data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAddData1 = findViewById(R.id.btnAddData1);
        Button btnAddData2 = findViewById(R.id.btnAddData2);
        Button btnShowData = findViewById(R.id.btnShowData);
        tvData = findViewById(R.id.tvData);

        btnAddData1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData("Data(1)");
            }
        });

        btnAddData2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData("Data(2)");
            }
        });

        btnShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData();
            }
        });
    }

    private void addData(String name) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        Uri uri = Uri.parse(PROVIDER_URI);
        Uri newUri = getContentResolver().insert(uri, values);
        if (newUri != null) {
            Log.i("MainActivity", "Data added: " + name);
        } else {
            Log.e("MainActivity", "Failed to add data: " + name);
        }
    }

    private void showData() {
        Uri uri = Uri.parse(PROVIDER_URI);
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        if (cursor != null) {
            StringBuilder data = new StringBuilder();
            if (cursor.moveToFirst()) {
                int nameIndex = cursor.getColumnIndex("name");
                if (nameIndex != -1) {
                    do {
                        String name = cursor.getString(nameIndex);
                        data.append("Name: ").append(name).append("\n");
                    } while (cursor.moveToNext());
                } else {
                    Log.e("MainActivity", "Column 'name' not found");
                }
            } else {
                Log.e("MainActivity", "No data returned by the provider");
            }
            tvData.setText(data.toString());
            cursor.close();
        } else {
            Log.e("MainActivity", "Cursor is null");
            tvData.setText("Failed to load data");
        }
    }
}