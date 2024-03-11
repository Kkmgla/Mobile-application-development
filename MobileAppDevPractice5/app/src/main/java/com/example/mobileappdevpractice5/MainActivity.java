package com.example.mobileappdevpractice5;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();
        TextView selection = findViewById(R.id.RadioButtonText);

        if(view.getId() == R.id.FirstOption){
            selection.setText("First selected");
        }
        else if(view.getId() == R.id.SecondOption){
            selection.setText("Second selected");
        }
        else if(view.getId() == R.id.ThirdOption){
            selection.setText("Third selected");
        }
    }
        /*
        SeekBar seekBar = findViewById(R.id.SeekBar);
        TextView textView = findViewById(R.id.SeekBarText);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(String.valueOf(progress));}
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        TextView textView = findViewById(R.id.TimePickerText);
        TimePicker timePicker = findViewById(R.id.TimePicker);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                textView.setText("Время: " + view.getHour() + ":" + view.getMinute());
            }
        });
        */



}
