package com.example.mobileappdevpractice6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mobileappdevpractice6.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConstraintLayout constraintLayout = new ConstraintLayout(this);

        TextView textView1 = new TextView(this);
        textView1.setText("Стринг");
        textView1.setTextColor(getResources().getColor(R.color.teal_700));
        textView1.setId(View.generateViewId()); // Генерируем уникальный идентификатор для первого TextView
        textView1.setTextSize(getResources().getDimension(R.dimen.text_size));

        int horizontalMargin = (int) getResources().getDimension(R.dimen.horizontal_margin);
        int verticalMargin = (int) getResources().getDimension(R.dimen.vertical_margin);
        textView1.setPadding(horizontalMargin, verticalMargin, horizontalMargin, verticalMargin);

        // Добавляем TextView в ConstraintLayout
        constraintLayout.addView(textView1);

        setContentView(constraintLayout);
    }
}
