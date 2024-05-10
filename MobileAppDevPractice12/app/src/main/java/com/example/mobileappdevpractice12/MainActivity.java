package com.example.mobileappdevpractice12;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final Uri CONTENT_URI = Uri.parse("content://com.example.mobileappdevpractice12.provider/crypto");


    private static final String[] CRYPTO = {
                "Monero", "Zcash", "Dash",
                "Verge", "Ton"
        };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendDataButton = findViewById(R.id.send_data_button);
        sendDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToContentProvider();
            }
        });

        Button convertToJsonButton = findViewById(R.id.convert_to_json_button);
        convertToJsonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertToJsonAndSaveToFile();
            }
        });

        // Найти кнопку для перехода на вторую активность
        Button goToSecondActivityButton = findViewById(R.id.go_to_second_activity_button);
        // Установить слушатель кликов для кнопки
        goToSecondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Создать намерение для перехода на вторую активность
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                // Запустить вторую активность
                startActivity(intent);
            }
        });
    }

    private void sendDataToContentProvider() {
        // Заполняем таблицу криптой
        fillCryptoTable();

        // Создание объекта ContentValues для вставки данных
        ContentValues values = new ContentValues();
        values.put("title", "Привет, другое приложение!");

        // Вставка данных через Content Resolver
        try {
            Uri insertedUri = getContentResolver().insert(CONTENT_URI, values);
            if (insertedUri != null) {
                Toast.makeText(MainActivity.this, "Данные отправлены через провайдер контента", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Ошибка при отправке данных", Toast.LENGTH_SHORT).show();
            }
        } catch (SecurityException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Ошибка безопасности при отправке данных", Toast.LENGTH_SHORT).show();
        }
    }

    private void convertToJsonAndSaveToFile() {
        // Создание объекта JSON
        JSONObject jsonObject = new JSONObject();

        // Получение данных из таблицы базы данных
        Cursor cursor = getContentResolver().query(CONTENT_URI, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            try {
                int idColumnIndex = cursor.getColumnIndex("_id");
                int titleColumnIndex = cursor.getColumnIndex("title");
                if (idColumnIndex == -1 || titleColumnIndex == -1) {
                    Toast.makeText(MainActivity.this, "Ошибка при получении данных", Toast.LENGTH_SHORT).show();
                    return;
                }

                do {
                    int id = cursor.getInt(idColumnIndex);
                    String title = cursor.getString(titleColumnIndex);
                    jsonObject.put(String.valueOf(id), title);
                } while (cursor.moveToNext());

                // Преобразование JSONObject в строку JSON
                String jsonString = jsonObject.toString();

                // Сохранение JSON в файл в папке "Документы" на внешнем хранилище
                File documentsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
                if (documentsDir != null) {
                    File file = new File(documentsDir, "data.json");
                    try (FileWriter fileWriter = new FileWriter(file)) {
                        fileWriter.write(jsonString);
                        Toast.makeText(MainActivity.this, "Данные успешно преобразованы и сохранены в файл", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Ошибка при сохранении данных в файл", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Ошибка при сохранении данных в файл", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Ошибка при создании JSON", Toast.LENGTH_SHORT).show();
            } finally {
                cursor.close();
            }
        } else {
            Toast.makeText(MainActivity.this, "Ошибка при получении данных", Toast.LENGTH_SHORT).show();
        }
    }

    private void fillCryptoTable() {
        // Вставляем названия фильмов в таблицу
        for (int i = 0; i < CRYPTO.length; i++) {
            ContentValues values = new ContentValues();
            values.put("title", CRYPTO[i]);

            // Вставка данных через Content Resolver
            try {
                Uri insertedUri = getContentResolver().insert(CONTENT_URI, values);
                if (insertedUri == null) {
                    Log.e("FillCryptoTable", "Ошибка при вставке крипты \"" + CRYPTO[i] + "\" в таблицу");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("FillCryptoTable", "Ошибка при вставке крипты \"" + CRYPTO[i] + "\" в таблицу: " + e.getMessage());
            }
        }
    }
}