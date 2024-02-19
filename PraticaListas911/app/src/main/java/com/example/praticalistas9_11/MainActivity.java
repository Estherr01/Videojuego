package com.example.praticalistas9_11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

        ListView listView = findViewById(R.id.lista);
        ListView listView1 = findViewById(R.id.lista2);

        ArrayList<String> contenidoLista = new ArrayList<>();
        contenidoLista.add("Nevera");
        contenidoLista.add("Tostadora");
        contenidoLista.add("Horno");
        contenidoLista.add("Sartén");

        //Creamos la otra lista
        ArrayList<String> contLista2 = new ArrayList<>();
        contLista2.add("Leche");
        contLista2.add("Harina");
        contLista2.add("Huevos");
        contLista2.add("Chocolate");


        //Creamos el adaptador para la primera lista
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(this, R.layout.nuevo, contenidoLista);
        listView.setAdapter(adapter);

        //Creamos segundo adapter
        ArrayAdapter<String>adapter1;
        adapter1 = new ArrayAdapter<>(this, R.layout.nuevo, contLista2);
        listView1.setAdapter(adapter1);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (contenidoLista.get(position)==contenidoLista.get(1)){
                    listView1.setVisibility(View.VISIBLE);
                }else{
                    listView1.setVisibility(View.INVISIBLE);
                }
            }
        });



    }
}