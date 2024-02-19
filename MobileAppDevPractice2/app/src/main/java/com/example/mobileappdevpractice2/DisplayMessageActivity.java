package com.example.mobileappdevpractice2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
// Получить сообщение из Intent
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
// Создание объекта TextView
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);
// Делаем textView корневым элементов разметки activity
        setContentView(textView);
    }
}