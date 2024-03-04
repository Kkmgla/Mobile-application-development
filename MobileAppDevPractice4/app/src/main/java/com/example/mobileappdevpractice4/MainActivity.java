package com.example.mobileappdevpractice4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout frameLayout = new FrameLayout(this);
        TextView textView1 = new TextView(this);
        TextView textView2 = new TextView(this);
        TextView textView3 = new TextView(this);


        FrameLayout.LayoutParams layoutParams = new
                FrameLayout.LayoutParams
                (FrameLayout.LayoutParams.WRAP_CONTENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.TOP;

        textView1.setText("First");
        textView1.setLayoutParams(layoutParams);
        textView1.setTextSize(20);
        textView1.setBackgroundColor(0xFFFF9800);

        textView2.setText("Second");
        textView2.setLayoutParams(layoutParams);
        textView2.setTextSize(100);
        textView2.setBackgroundColor(0xFFAAA1CC);


        textView3.setText("Third");
        textView3.setLayoutParams(layoutParams);
        textView3.setTextSize(20);
        textView3.setBackgroundColor(0xAA3FF1AA);


        frameLayout.addView(textView1);
        frameLayout.addView(textView2);
        frameLayout.addView(textView3);


        setContentView(frameLayout);
    }
}