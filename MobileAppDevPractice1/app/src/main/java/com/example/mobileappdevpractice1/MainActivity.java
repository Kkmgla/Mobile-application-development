package com.example.mobileappdevpractice1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity{

    public final static String EXTRA_MESSAGE = "com.example.mobileappdevpractice1.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*R в Android Studio представляет собой класс,
         который генерируется автоматически системой сборки приложения и содержит ссылки на ресурсы вашего проекта.*/
        setContentView(R.layout.activity_main);
    }
    /*super.onStart();: Этот вызов обращается к реализации метода onStart() в родительском классе (в данном случае, AppCompatActivity)*/
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("RRR","onStart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("RRR","onDestroy()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("RRR","onResume()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("RRR","onRestart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("RRR","onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("RRR","onStop()");
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

}