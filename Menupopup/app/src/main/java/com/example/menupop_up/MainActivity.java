package com.example.menupop_up;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMenu = findViewById(R.id.btnMenu);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Creamos la instancia del menu
                PopupMenu popup = new PopupMenu(MainActivity.this, btnMenu);

                // Inflamos (Dibujamos) el xml que creamos
                popup.getMenuInflater().inflate(R.menu.menu, popup.getMenu());

                // Ponemos el escuchador del popup
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(MainActivity.this, "Seleccionaste: "+item.getTitle(), Toast.LENGTH_LONG).show();
                        return true;
                    }
                });

                // Muestra el popup
                popup.show();

            }
        });
    }
}