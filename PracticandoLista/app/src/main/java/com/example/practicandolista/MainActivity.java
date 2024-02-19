package com.example.practicandolista;

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

        //Creamos variable de tipo ListView
        ListView listView = findViewById(R.id.listView1);
        ListView listView1 = findViewById(R.id.listView2);

        listView1.setVisibility(View.INVISIBLE);

        //Creamos y rellenamos el ArrayList
        ArrayList<String> contenidoLista = new ArrayList<>();
        contenidoLista.add("Casa");
        contenidoLista.add("Coche");
        contenidoLista.add("Nevera");

        //Creamos otra lista ya que tenemos 2
        ArrayList<String> contenido2 = new ArrayList<>();
        contenido2.add("Café");
        contenido2.add("Chocolate");
        contenido2.add("Leche");

        //Creamos el adaptador
        ArrayAdapter<String> adapter;
        //Se inicializa
        //filas hace referencia al XML creamo y contenidoLista al arrayList
        adapter = new ArrayAdapter<String>(this, R.layout.filas, contenidoLista);

        //Asociamos la lista(nombre asociado a ListView)
        listView.setAdapter(adapter);

        ArrayAdapter<String> adapter1;
        adapter1 = new ArrayAdapter<String>(this, R.layout.filas2, contenido2);
        listView1.setAdapter(adapter1);

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
                Log.i("mensajes", contenido2.get(position));

            }
        });
    }
}
