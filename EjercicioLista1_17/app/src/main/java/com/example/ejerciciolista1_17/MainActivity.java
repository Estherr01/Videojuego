package com.example.ejerciciolista1_17;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView=findViewById(R.id.listView1);
        ListView listView1= findViewById(R.id.listView2);
        //hacer invisible la lista 2
        listView1.setVisibility(View.INVISIBLE);

        ArrayList<String>contenidoLista= new ArrayList<>();
        contenidoLista.add("Base");
        contenidoLista.add("Escolta");
        contenidoLista.add("Alero");
        contenidoLista.add("Ala-pivot");
        contenidoLista.add("pivot");

        ArrayList<String>contenidoLista2;
        contenidoLista2= new ArrayList<>();
        contenidoLista2.add("Hola");
        contenidoLista2.add("Escolta");
        contenidoLista2.add("Soy");
        contenidoLista2.add("Esther");
        contenidoLista2.add("pivot");

        ArrayAdapter<String>adaptador;
        adaptador= new ArrayAdapter<>(this,android.R.layout.simple_list_item_multiple_choice,contenidoLista);
        listView.setAdapter(adaptador);

        ArrayAdapter<String>adaptador2;
        adaptador2= new ArrayAdapter<>(this,R.layout.filas2,contenidoLista2);
        listView1.setAdapter(adaptador2);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("mensajes", contenidoLista.get(position));

                //Si la posicion que pulso es igual a la que deseo ques e vuelva visible
                if (contenidoLista.get(position)==contenidoLista.get(0)){
                    listView1.setVisibility(View.VISIBLE);
                    //y si no se vuelve invisible
                }else{
                    listView1.setVisibility(View.INVISIBLE);
                }
            }
        });

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("mensajes", contenidoLista2.get(position));

            }
        });
    }
}