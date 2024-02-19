package com.example.practicalistas1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.lista1);
        ListView listView1 = findViewById(R.id.lista2);
        listView1.setVisibility(View.INVISIBLE);

        //Creamos y llenamos ArrayList
        ArrayList<String> contenidoLista1 = new ArrayList<>();
        contenidoLista1.add("Casa");
        contenidoLista1.add("Coche");
        contenidoLista1.add("Vitroceramica");

        //Creamos la segunda lista
        ArrayList<String> contenidoLista2 = new ArrayList<>();
        contenidoLista2.add("Chocolate");
        contenidoLista2.add("Cafe");
        contenidoLista2.add("Leche");

        //Creamos el adaptador
        ArrayAdapter<String> adaptador;
        adaptador = new ArrayAdapter<>(this, R.layout.filas, contenidoLista1);

        //Asociamos la lista (listView) con el adaptador
        listView.setAdapter(adaptador);


        ArrayAdapter<String> adaptador1;
        adaptador1 = new ArrayAdapter<>(this, R.layout.filas, contenidoLista2);
        listView1.setAdapter(adaptador1);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("mensajes", contenidoLista1.get(position));

                if (contenidoLista1.get(position) == contenidoLista1.get(2)) {
                    listView1.setVisibility(View.VISIBLE);
                } else {
                    listView1.setVisibility(View.INVISIBLE);
                }
            }
        });

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("mensajes",contenidoLista2.get(position));
            }
        });

    }
}