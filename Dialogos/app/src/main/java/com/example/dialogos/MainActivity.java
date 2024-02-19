package com.example.dialogos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    AlertDialog.Builder builder;
    AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btnMostrarDialogo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogoPersonalizado().show(); // Mostrar el diálogo
            }
        });
    }

    public AlertDialog dialogoPersonalizado() {
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Título del Diálogo");
        builder.setMessage("Este es un mensaje de ejemplo.");
        builder.setCancelable(false);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Cierra el diálogo
            }
        });
        builder.setNeutralButton("hola", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Hola", Toast.LENGTH_SHORT).show();
            }
        });
        return builder.create();
    }
}







