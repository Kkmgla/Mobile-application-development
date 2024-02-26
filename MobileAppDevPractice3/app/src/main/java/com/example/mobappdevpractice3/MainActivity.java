package com.example.mobappdevpractice3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.TypedValue;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_layout);

        ConstraintLayout constraintLayout = new ConstraintLayout(this);
        TextView textView = new TextView(this);
        textView.setText("New text in Main Activity");
        textView.setBackgroundColor(0xe0e0e0e0);
        textView.setTextSize(30);
        //размер и расположение
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(35, 35, 35, 35);
        textView.setPadding(50, 50, 50, 50);

        //Выравниваем по левому краю
        layoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        //Выравниваем по праому краю
        layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        //применяем эти параметры
        textView.setLayoutParams(layoutParams);
        constraintLayout.addView(textView);

        setContentView(constraintLayout);

        //setContentView(R.layout.new_layout);

    }
}