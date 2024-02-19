package com.example.buscaminasentrega;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button playButton = findViewById(R.id.play);
        Button configButton = findViewById(R.id.config);
        Button exitButton = findViewById(R.id.exit);

        playButton.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), PlayActivity.class);
            startActivity(intent);
        });

        configButton.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ConfigActivity.class);
            startActivity(intent);
        });

        exitButton.setOnClickListener(v -> {

            AlertDialog.Builder alert =  new AlertDialog.Builder(this);

            alert.setTitle(getString(R.string.title_dialog ));

            alert.setMessage(getString(R.string.message));

            alert.setPositiveButton("Si", (dialog, which) -> {

                //antes de salir se reinicia la configuración
                SharedPreferences preferences = getSharedPreferences("config", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit(); //editor de preferencias
                editor.putString("difficult","normal");
                editor.putInt("bombs",10);
                editor.commit();
                finish();
            });

            alert.setNeutralButton("Cancelar", null);
            alert.setIcon(R.drawable.exit);

            alert.show();
        });
    }
}