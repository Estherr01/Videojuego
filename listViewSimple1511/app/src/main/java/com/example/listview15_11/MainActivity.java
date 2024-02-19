package com.example.listview15_11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// Obtener la referencia al ListView
        ListView listView = findViewById(R.id.lista);

        // Crear una lista de elementos para la lista
        ArrayList<String> itemList = new ArrayList<>();
        itemList.add("Elemento 1");
        itemList.add("Elemento 2");
        itemList.add("Elemento 3");
        itemList.add("Elemento 4");

        // Crear un ArrayAdapter para el ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                itemList
        );

        // Establecer el adaptador en el ListView
        listView.setAdapter(adapter);

        // Manejar la selección de elementos en la lista
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el elemento seleccionado
                String selectedItem = itemList.get(position);

                // Mostrar un mensaje con el elemento seleccionado
                Toast.makeText(MainActivity.this, "Seleccionaste: " + selectedItem, Toast.LENGTH_SHORT).show();
            }
        });
    }
}