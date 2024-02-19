package com.example.practicalista12_11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView= findViewById(R.id.lista);
        TextView textView= findViewById(R.id.textView);

        ArrayList<String>listaCompra= new ArrayList<>();
        listaCompra.add("pan");
        listaCompra.add("leche");
        listaCompra.add("huevos");
        listaCompra.add("chocolate");

        ArrayAdapter<String>adapter;
        adapter= new ArrayAdapter<>(this, R.layout.lista,listaCompra);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String elementoSeleccionado=(String) parent.getItemAtPosition(position);
                textView.setText("has seleccionado  "+ elementoSeleccionado);

            }
        });


    }
}