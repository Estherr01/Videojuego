package com.example.praclistaselecmultiple9_11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        Button button = findViewById(R.id.button);
        TextView textView = findViewById(R.id.textView);

        ArrayList<String> lista = new ArrayList<>();
        lista.add("Huevos");
        lista.add("Leche");
        lista.add("Pan");

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, lista);
        listView.setAdapter(adapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //opciones seleccionadas
                SparseBooleanArray opcionesSelecionadas = listView.getCheckedItemPositions();
                Log.d("aaa", opcionesSelecionadas.toString());
                String seleccion = "";
                for (int i = 0; i < opcionesSelecionadas.size(); i++) {
                    if (opcionesSelecionadas.get(i)) {
                        int posicionSeleccionada = opcionesSelecionadas.keyAt(i);
                        Log.d("aaa", posicionSeleccionada + "");
                        String producto = lista.get(posicionSeleccionada);
                        Log.d("aaa", producto);
                        seleccion += producto + ", ";
                    }
                }
                String mensaje = " has elegido: " + seleccion;
                textView.setText(mensaje);
            }
        });


    }
}

