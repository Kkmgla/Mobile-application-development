package com.example.mobileappdevpractice12;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button readDataButton = findViewById(R.id.read_data_button);
        readDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readDataFromContentProvider();
            }
        });

        Button readJsonButton = findViewById(R.id.read_json_button);
        readJsonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readJsonDataAndDisplay();
            }
        });
    }

    private void readDataFromContentProvider() {
        // URI для доступа к данным провайдера контента
        Uri contentUri = Uri.parse("content://com.example.mobileappdevpractice12.provider/crypto");

        // Получение ContentResolver
        ContentResolver resolver = getContentResolver();

        // Выполнение запроса к провайдеру контента
        Cursor cursor = resolver.query(contentUri, new String[]{"_id", "title"}, null, null, "title ASC");

        // Проверка наличия данных в курсоре
        if (cursor != null) {
            try {
                // Получение индексов столбцов
                int idColumn = cursor.getColumnIndex("_id");
                int titleColumn = cursor.getColumnIndex("title");

                // Получение TextView для вывода данных
                TextView dataTextView = findViewById(R.id.data_textview);

                // Очистка TextView перед выводом новых данных
                dataTextView.setText("");

                // Перебор данных в курсоре
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(idColumn);
                    String title = cursor.getString(titleColumn);

                    // Формирование строки с данными
                    String data = "ID: " + id + "\nTitle: " + title + "\n\n";

                    // Вывод данных в TextView
                    dataTextView.append(data);
                }
            } finally {
                // Важно закрыть курсор после использования
                cursor.close();
            }
        } else {
            // В случае, если курсор равен null, выводим сообщение об ошибке
            Toast.makeText(this, "Ошибка при чтении данных из провайдера контента", Toast.LENGTH_SHORT).show();
        }
    }

    private void readJsonDataAndDisplay() {
        // Чтение данных из файла JSON
        File documentsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        if (documentsDir != null) {
            File file = new File(documentsDir, "data.json");
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                String jsonString = stringBuilder.toString();

                // Преобразование строки JSON в объект JSONObject
                JSONObject jsonObject = new JSONObject(jsonString);

                // Получение итератора по именам ключей
                Iterator<String> keys = jsonObject.keys();

                // Получение TextView для вывода данных
                TextView dataTextView = findViewById(R.id.data_textview);

                // Очистка TextView перед выводом новых данных
                dataTextView.setText("");

                // Обход итератора и получение данных из объекта JSON
                while (keys.hasNext()) {
                    String key = keys.next();
                    String title = jsonObject.getString(key);
                    // Преобразование заголовка перед выводом
                    String formattedTitle = "Title: " + title.toUpperCase() + "\n";

                    // Формирование строки с данными
                    String data = "Key: " + key + "\n" + formattedTitle + "\n";

                    // Вывод данных в TextView
                    dataTextView.append(data);
                }

                Toast.makeText(this, "Данные из файла JSON успешно обработаны", Toast.LENGTH_SHORT).show();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                Toast.makeText(this, "Ошибка при чтении данных из файла JSON", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Ошибка при чтении данных из файла JSON", Toast.LENGTH_SHORT).show();
        }
    }
}