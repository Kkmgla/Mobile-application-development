package com.example.mobileappdevpractice8;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView imageView = new ImageView(MainActivity.this);

        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(com.example.mobileappdevpractice8.MyWorker.class).build();
        WorkManager.getInstance(this).enqueue(workRequest);

        WorkManager.getInstance(this).
                getWorkInfoByIdLiveData(workRequest.getId()).observe(
                        this, new Observer<WorkInfo>(){
                            @Override
                            public void onChanged(WorkInfo workInfo) {

                                String strurl = workInfo.getOutputData().getString("key1");
                                Picasso.get()
                                        .load(strurl)
                                        .into(imageView);

                            }
                        }
                );



        setContentView(imageView);

    }
}