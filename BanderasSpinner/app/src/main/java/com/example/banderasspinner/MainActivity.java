package com.example.banderasspinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView=findViewById(R.id.textView2);

        //Nos vamos al Xml que esta creado por defecto y añadimos boton spinner
        //Creamos variable
        Spinner spinner = findViewById(R.id.spinner);
        //Creamos arrayList y array para guardar las imagenes y los nombre
        ArrayList<String> nombrePais = new ArrayList<String>();
        nombrePais.add("Italia");
        nombrePais.add("Bélgica");

        //Añadir imagenes
        int[] imagenes = new int[]{R.drawable.flag_of_italy_svg, R.drawable.belgica};
        ClaseNueva1 clase = new ClaseNueva1(this, R.layout.fila,nombrePais,imagenes);
        spinner.setAdapter(clase);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String paisesSeleccionado = nombrePais.get(position);
                textView.setText("Has seleccionado " + paisesSeleccionado);

                //Toast.makeText(MainActivity.this, "Has seleccionado "
                // + paisesSeleccionado, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}