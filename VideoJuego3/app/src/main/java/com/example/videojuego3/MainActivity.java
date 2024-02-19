package com.example.videojuego3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnimationDrawable animacion;

        ImageView imagen= findViewById(R.id.imageView);
        imagen.setImageResource(R.drawable.animacioneslista);
        animacion=(AnimationDrawable) imagen.getDrawable();
        animacion.start();

    }
}