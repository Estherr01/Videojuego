package com.example.buscaminasentrega;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class PlayActivity extends AppCompatActivity {
    static boolean flagsMode = false;
    int numColumns;
    int numRows;
    int sizeText;
    int numBombs;
    CheckBox flags;
    Panel panel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Toolbar  toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        SharedPreferences preferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        numBombs = preferences.getInt("bombs",10);
        String dificultad = preferences.getString("difficult","normal");

        switch (dificultad){
            case "easy":
                numColumns = 5;
                numRows = 5;
                sizeText = 80;
                break;
            case "normal":
                numColumns = 8;
                numRows = 8;
                sizeText = 50;
                break;
            case "difficult":
                numColumns = 12;
                numRows = 12;
                sizeText = 40;
                break;
        }

        RelativeLayout layout = findViewById(R.id.layout);
        panel = new Panel(this, numColumns, numRows, sizeText, numBombs);
        layout.addView(panel);

        flags = findViewById(R.id.flags);

        flags.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                flagsMode = true;
                Toast toast = Toast.makeText(getApplicationContext(), "Flags mode", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM, 0, 0);
                toast.show();
            }
            else flagsMode = false;
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_restart){
            flagsMode = false;
            finish();
            Intent intent = new Intent(this, PlayActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.nav_solve){
            panel.setGameState(1);
        } else if (item.getItemId() == R.id.nav_exit){
            flagsMode = false;
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}