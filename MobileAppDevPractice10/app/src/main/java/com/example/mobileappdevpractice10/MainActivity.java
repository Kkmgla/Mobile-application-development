package com.example.mobileappdevpractice10;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /*
    private EditText edName,edID;
    private TextView tvInfo;
    private Button bGet;
    private Button bDelete;
    private Button bUpdate;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edName = findViewById(R.id.edName);
        edID = findViewById(R.id.edID);
        tvInfo= findViewById(R.id.tvInfo);
        bGet= findViewById(R.id.bGet);
        bDelete= findViewById(R.id.bDelete);
        bUpdate= findViewById(R.id.bUpdate);
        sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE);

    }

    public void onUpdateClicked(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("name", edName.getText().toString());
        editor.putString("id", edID.getText().toString());
        editor.apply();
    }

    public void onDeleteClicked(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        Toast.makeText(this, "Данные удалены", Toast.LENGTH_LONG).show();
    }

    public void onGetClicked(View view) {
        String info = sharedPreferences.getString("name", "default name") + " " +
                sharedPreferences.getString("id", "0000");
        tvInfo.setText(info);
    }
    */


    //SQL
    private EditText edName, edID, edNumber, edHeight, edWeight;
    private TextView tvInfo;
    private Button bGet, bDelete, bUpdate, bFind;
    private DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edName = findViewById(R.id.edName);
        edID = findViewById(R.id.edID);
        edNumber = findViewById(R.id.edNumber);
        edHeight = findViewById(R.id.edHeight);
        edWeight = findViewById(R.id.edWeigth);

        tvInfo = findViewById(R.id.tvInfo);
        bGet = findViewById(R.id.bGet);
        bDelete = findViewById(R.id.bDelete);
        bFind = findViewById(R.id.bFind);
        bUpdate = findViewById(R.id.bUpdate);

        dbHelper = new DBHelper(this);

        bUpdate.setOnClickListener(v -> {
            User user = new User(
                    edName.getText().toString(),
                    edID.getText().toString(),
                    edNumber.getText().toString(),
                    edHeight.getText().toString(),
                    edWeight.getText().toString()
            );
            System.out.println(user);
            dbHelper.updateUser(user);
            Toast.makeText(this, "User " +  user.getName() + " updated", Toast.LENGTH_LONG).show();
        });

        bGet.setOnClickListener(v -> {
            tvInfo.setText(dbHelper.getAllUsers().toString());
        });


        bDelete.setOnClickListener(v -> {
            String name = edName.getText().toString();
            dbHelper.deleteUser(name);
            Toast.makeText(this, "User " + name + " deleted", Toast.LENGTH_LONG).show();
        });

        bFind.setOnClickListener(v -> {
            String name = edName.getText().toString();
            User user = dbHelper.findUser(name);
            if (user != null) {
                edID.setText(user.getId());
                edNumber.setText(user.getNumber());
                edHeight.setText(user.getHeight());
                edWeight.setText(user.getWeight());
            } else {
                Toast.makeText(this, "User " + name + " ont found", Toast.LENGTH_LONG).show();
            }
        });
    }
}