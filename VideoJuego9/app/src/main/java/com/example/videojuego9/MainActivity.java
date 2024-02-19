package com.example.videojuego9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.view.WindowMetrics;

public class MainActivity extends AppCompatActivity {

    public static int anchoPantalla, altoPantalla; // Variables estáticas para almacenar el ancho y alto de la pantalla

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Establece el diseño de la actividad desde activity_main.xml

        calculartamanopantalla(); // Llama al método para calcular el tamaño de la pantalla
        setContentView(new Juego(this)); // Establece la vista del juego en pantalla completa
    }

    // Método para calcular el tamaño de la pantalla
    private void calculartamanopantalla() {
        // Obtiene una instancia de WindowManager para manejar las ventanas del sistema
        WindowManager windowManager = (WindowManager) this.getSystemService(this.WINDOW_SERVICE);
        // Obtiene las métricas de la ventana actual, que incluyen el tamaño de la pantalla
        WindowMetrics currentWindowMetrics = windowManager.getCurrentWindowMetrics();
        // Obtiene la altura de la pantalla y la almacena en la variable altoPantalla
        altoPantalla = currentWindowMetrics.getBounds().height();
        // Obtiene el ancho de la pantalla y lo almacena en la variable anchoPantalla
        anchoPantalla = currentWindowMetrics.getBounds().width();
        // Registra en el registro (Log) la altura de la pantalla
        Log.d("medidas", "alto " + altoPantalla);
        // Registra en el registro (Log) el ancho de la pantalla
        Log.d("medidas", "ancho " + anchoPantalla);
    }
}