package com.example.practicacambiarimagen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    boolean fotoActual = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView image = findViewById(R.id.imageView);
        Button button = findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fotoActual) {
                    image.setImageResource(R.drawable.imagen2);
                } else {
                    image.setImageResource(R.drawable.svg_logo_svg);
                }
                fotoActual = !fotoActual;
                // invierte el valor de la variable booleana fotoActual.
                // En otras palabras, si fotoActuales true,
                // la línea la cambiará a false,
                // y si fotoActuales false, la cambiará a true.
            }
        });
    }
}