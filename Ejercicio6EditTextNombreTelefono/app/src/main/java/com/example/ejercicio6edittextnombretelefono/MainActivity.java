package com.example.ejercicio6edittextnombretelefono;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.editText1);
        EditText editText1 = findViewById(R.id.editText2);
        EditText editText2 = findViewById(R.id.editTextPhone);
        EditText editText3=findViewById(R.id.editText3);
        Button button = findViewById(R.id.button);
        TextView textView=findViewById(R.id.textView6);

       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String nombre = editText.getText().toString();
               String apellidos = editText1.getText().toString();
               String phone = editText2.getText().toString();
               String dni = editText3.getText().toString();

                //Declaramos la variable mensaje donde haremos referencia en el Toast para mostrar el mensaje
               String mensaje = nombre + " " + apellidos +

                       " ,número de teléfono " + phone + "y  DNI " + dni +
                       ", ha sido inscrito.";

               textView.setText(mensaje);

               //Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_SHORT).show();
           }
       });

    }
}