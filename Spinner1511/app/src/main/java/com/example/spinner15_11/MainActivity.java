package com.example.spinner15_11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    private TextView TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        TextView = findViewById(R.id.selected_item_textview);


        ArrayList<String>lista= new ArrayList<>();
        lista.add("Item 1");
        lista.add("Item 2");
        lista.add("Item 3");
        // Crear un ArrayAdapter usando el array de recursos y un diseño simple
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,lista);


        // Especificar el diseño del dropdown
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Aplicar el adaptador al Spinner
        spinner.setAdapter(adapter);

        // Manejar la selección del Spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Mostrar el elemento seleccionado en el TextView
                String selectedItem = parentView.getItemAtPosition(position).toString();
                TextView.setText("Selected Item: " + selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // No hacer nada aquí si no hay nada seleccionado
            }
        });
    }
}