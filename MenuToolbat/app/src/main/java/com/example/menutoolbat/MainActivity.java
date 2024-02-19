package com.example.menutoolbat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Infla el menú; esto añade elementos a la barra de herramientas si está presente.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Maneja los clics en los elementos del menú de la barra de herramientas.
        int id = item.getItemId();
        if (id == R.id.action_option1) {
            Toast.makeText(this, "Seleccionaste Opción 1", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_option2) {
            Toast.makeText(this, "Seleccionaste Opción 2", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_option3) {
            Toast.makeText(this, "Seleccionaste Opción 3", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}








