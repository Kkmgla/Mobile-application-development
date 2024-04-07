package com.example.mobileappdevpractice7;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;


public class MainActivity extends AppCompatActivity {
    private int hour;
    private int minute;
    private TextView textView;
    private int year;
    private int month;
    private int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /********************************************************
        // Запуск сервиса для воспроизведения музыки
        Intent startIntent = new Intent(this, MyService.class);
        startService(startIntent);


        *******************************************************
         *

        // Создание строителя диалоговых окон
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

    // Установка заголовка и сообщения диалогового окна
        builder.setTitle("Подтверждение");
        builder.setMessage("Вы уверены, что хотите выполнить это действие?");
        builder.setIcon(android.R.drawable.ic_dialog_info); // Заменил на другую системную иконку

    // Установка кнопки "Да" и ее обработчика
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Обработка подтверждения
            }
        });

    // Установка кнопки "Отмена" и ее обработчика
        builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Обработка отмены действия
                dialog.dismiss();
            }
        });

    // Создание и отображение AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();


        *******************************************************


        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        textView.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, true);
        timePickerDialog.show();


         *******************************************************


        // Создание обработчика выбора даты
        DatePickerDialog.OnDateSetListener dateSetListener = new
                DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month,
                                          int dayOfMonth) {
                        // Обработка выбора даты
                    }
                };
    // Создание и отображение DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                MainActivity.this,
                dateSetListener,
                year, // текущий год
                month, // текущий месяц
                day); // текущий день
        datePickerDialog.show();


         *******************************************************


        // Создание диалога
        Dialog dialog = new Dialog(MainActivity.this);
        // Установка макета для диалогового окна
        dialog.setContentView(R.layout.custom_dialog);
        // Настройка элементов в макете
        TextView questionTextView = dialog.findViewById(R.id.dialog_question);
        questionTextView.setText("Вы уверены, что хотите выполнить это действие?");
        Button yesButton = dialog.findViewById(R.id.button_yes);
        Button noButton = dialog.findViewById(R.id.button_no);

        // Установка обработчиков событий для кнопок
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработка нажатия на кнопку "Да"
                dialog.dismiss(); // Закрыть диалог
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработка нажатия на кнопку "Нет"
                dialog.dismiss(); // Закрыть диалог
            }
        });

        // Отображение диалогового окна
        dialog.show();
         *******************************************************


        MyDialogFragment dialogFragment = new MyDialogFragment();
        dialogFragment.show(getSupportFragmentManager(),"Проверка");


         ******************************************************/


        Button alertButton = findViewById(R.id.buttonAlert);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        alertButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EditText nameText = findViewById(R.id.textName);
                String name = nameText.getText().toString();
                builder.setTitle("Подтверждение");
                builder.setMessage("Вы уверены, что хотите удалить " + name + " ?");
                builder.create().show();
            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Остановка сервиса и музыки при уничтожении активности
        Intent stopIntent = new Intent(this, MyService.class);
        stopService(stopIntent);
    }
}