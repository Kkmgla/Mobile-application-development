package com.example.mobileappdevpractice122;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnLoadData = findViewById(R.id.btnLoadData);
        tvData = findViewById(R.id.tvData);
        btnLoadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readDataFromProvider();
            }
        });
    }
    private void readDataFromProvider() {
        Uri uri = Uri.parse("content://com.example.mobileappdevpractice121.provider/data");
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        if (cursor != null) {
            String[] columnNames = cursor.getColumnNames();
            for (String columnName : columnNames) {
                Log.d("DataConsumerApp", "Column: " + columnName);
            }
            StringBuilder data = new StringBuilder();
            if (cursor.moveToFirst()) {
                int nameIndex = cursor.getColumnIndex("name");
                if (nameIndex != -1) {
                    do {
                        String name = cursor.getString(nameIndex);
                        data.append("Name: ").append(name).append("\n");
                    } while (cursor.moveToNext());
                } else {
                    Log.e("DataConsumerApp", "Column 'name' not found");
                }
            }
            tvData.setText(data.toString());
            cursor.close();
        } else {
            Log.e("DataConsumerApp", "Cursor is null");
            tvData.setText("Failed to load data");
        }
    }
}