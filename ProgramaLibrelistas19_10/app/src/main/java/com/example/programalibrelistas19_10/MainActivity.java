package com.example.programalibrelistas19_10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.lista);
        ArrayList<String> contenidoLista = new ArrayList<>();
        contenidoLista.add("Pan");
        contenidoLista.add("Leche");
        contenidoLista.add("Chocolate");
        contenidoLista.add("Huevos");
        contenidoLista.add("Cereales");

        ArrayAdapter<String> adaptador;
        adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, contenidoLista);
        listView.setAdapter(adaptador);

        EditText editText = findViewById(R.id.nombre);

        TextView textView = findViewById(R.id.resultado);

        Button button = findViewById(R.id.boton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //opciones seleccionadas
                SparseBooleanArray opcionesSelecionadas = listView.getCheckedItemPositions();
                Log.d("aaa", opcionesSelecionadas.toString());
                String seleccion = "";
                for (int i = 0; i < opcionesSelecionadas.size(); i++) {
                    if(opcionesSelecionadas.get(i)) {
                        int posicionSeleccionada = opcionesSelecionadas.keyAt(i);
                        Log.d("aaa", posicionSeleccionada + "");
                        String producto = contenidoLista.get(posicionSeleccionada);
                        Log.d("aaa", producto);
                        seleccion += producto + ", ";
                    }
                }


                String nombre = editText.getText().toString();
                //empty es vacio
                if (nombre.trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Debes introducir tu nombre", Toast.LENGTH_LONG).show();
                } else {
                    String mensaje = nombre + ", has elegido: " + seleccion;
                    textView.setText(mensaje);
                }
            }
        });


    }
}