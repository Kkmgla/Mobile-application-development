package com.example.mobileappdevpractice9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {


    EditText filename;
    EditText textinput;
    TextView filetext;

    Button save, read, append, delete;
    String TAG = "FFF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filename = findViewById(R.id.editFilename);
        textinput = findViewById(R.id.editTextInput);
        filetext = findViewById(R.id.fileText);

        save = findViewById(R.id.savebutton);
        read = findViewById(R.id.readbutton);
        append = findViewById(R.id.addbutton);
        delete = findViewById(R.id.deletebutton);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "save");
                String name = filename.getText().toString();
                String text = textinput.getText().toString();
                //создание файла
                File storageDir =
                        Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_DOCUMENTS);
                if (!storageDir.exists()) {
                    storageDir.mkdirs();
                }
                File file = new File(storageDir, name);

                try {
                    if (!file.exists()) {
                        boolean created = file.createNewFile();
                        if (created) {
                            // Записываем данные в файл
                            FileWriter writer = new FileWriter(file);
                            writer.append(text);
                            writer.flush();
                            writer.close();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "read");
                String name = filename.getText().toString();
                File storageDir =
                        Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_DOCUMENTS);
                File file = new File(storageDir, name);
                if (file.exists()) {
                    StringBuilder text = new StringBuilder();
                    try {
                        BufferedReader br = new BufferedReader(new
                                FileReader(file));
                        String line;
                        while ((line = br.readLine()) != null) {
                            text.append(line);
                            text.append('\n');
                        }
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    filetext.setText(text.toString());
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = filename.getText().toString();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Вы уверены?");
                builder.setMessage("Вы хотите удалить файл " + name +'?');
                builder.setPositiveButton("Удалить файл", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        File storageDir =
                                Environment.getExternalStoragePublicDirectory(
                                        Environment.DIRECTORY_DOCUMENTS);
                        File file = new File(storageDir, name);
                        if (file.exists()) {
                            boolean deleted = file.delete();
                            if (deleted) {
                                // Файл успешно удален
                                Toast.makeText(MainActivity.this,"Файл удален",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                // Не удалось удалить файл
                                Toast.makeText(MainActivity.this,"Ошибка при удалении",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });



        /*

        //Для внутреннего хранилища

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "save");
                //создание файла
                String name = filename.getText().toString();
                String text = textinput.getText().toString();
                try {
                    FileOutputStream fos = openFileOutput(name, Context.MODE_PRIVATE);
                    fos.write(text.getBytes(StandardCharsets.UTF_8));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "read");
                //чтение
                try {
                    FileInputStream fis = openFileInput(filename.getText().toString());
                    InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
                    StringBuilder stringBuilder = new StringBuilder();
                    try {
                        BufferedReader reader = new BufferedReader(inputStreamReader);
                        String str = reader.readLine();
                        while (str != null){
                            stringBuilder.append(str).append('\n');
                            str = reader.readLine();
                        }
                        filetext.setText(stringBuilder);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        append.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "append");
                String name = filename.getText().toString();
                String text = textinput.getText().toString();
                try {
                    FileOutputStream fos = openFileOutput(name, Context.MODE_APPEND);
                    fos.write(text.getBytes(StandardCharsets.UTF_8));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "delete");
                String name = filename.getText().toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Вы уверены?");
                builder.setMessage("Вы хотите удалить файл " + name +'?');
                builder.setPositiveButton("Удалить файл", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        File dir = getFilesDir();
                        File file = new File(dir, name);
                        file.delete();
                    }
                });
                builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });

         */

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("filename", filename.getText().toString());
        outState.putString("textinput", textinput.getText().toString());
        outState.putString("filetext", filetext.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        filename.setText(savedInstanceState.getString("filename"));
        textinput.setText(savedInstanceState.getString("textinput"));
        filetext.setText(savedInstanceState.getString("filetext"));


    }


}