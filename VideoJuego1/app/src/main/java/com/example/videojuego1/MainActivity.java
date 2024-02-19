package com.example.videojuego1;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button boton = findViewById(R.id.button);
        ImageView imagen = findViewById(R.id.imageView);
        imagen.setImageResource(R.drawable.avion);

        // Crear una instancia de AnimatorSet,
        // que se utiliza para coordinar un conjunto de animaciones.
        AnimatorSet animadorBoton = new AnimatorSet();

        // Crear un ObjectAnimator para animar la propiedad "translationY" del botón.
        ObjectAnimator trasladar = ObjectAnimator.ofFloat(boton, "translationY", -200, 20);

        // Establecer la duración de la animación en milisegundos.
        trasladar.setDuration(5000);// Duración de la animación: 5000 milisegundos (5 segundos).

        // Crear un ObjectAnimator para animar la propiedad "alpha" (transparencia) del botón.
        ObjectAnimator fade = ObjectAnimator.ofFloat(boton, "alpha", 0f, 1f);

        // Establecer la duración de la animación en milisegundos.
        fade.setDuration(8000); // Duración de la animación: 8000 milisegundos (8 segundos).

        // Crear un ObjectAnimator para animar la propiedad "scaleX" (escala en el eje X) del botón.
        ObjectAnimator escala = ObjectAnimator.ofFloat(boton, "scaleX", 2f);

        // Establecer la duración de la animación en milisegundos.
        escala.setDuration(13000); // Duración de la animación: 13000 milisegundos (13 segundos).

        // Crear un ObjectAnimator para animar la propiedad "rotationY" (rotación en el eje Y) de la imagen.
        ObjectAnimator rotacion = ObjectAnimator.ofFloat(imagen, "rotationY", 180);

        // Establecer la duración de la animación en milisegundos.
        rotacion.setDuration(15000); // Duración de la animación: 15000 milisegundos (15 segundos).

        // Agregar animaciones al AnimatorSet llamado "animadorBoton".
        animadorBoton.play(trasladar).with(fade).with(escala).with(rotacion);

        // Iniciar el conjunto de animaciones.
        animadorBoton.start();

    }
}