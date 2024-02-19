package com.example.listavideo2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner= findViewById(R.id.spinner);
        TextView textView= findViewById(R.id.textView);
        String[] audioOptions = {"Audio 1", "Audio 2"};

        // Crear un ArrayAdapter utilizando las opciones definidas en el código
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, audioOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Detener la reproducción si ya se está reproduciendo
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }

                // Obtener el recurso de audio seleccionado
                int audioResource = (position == 0) ? R.raw.Audio1 : R.raw.Izal;

                // Crear un nuevo MediaPlayer y reproducir el audio seleccionado
                mediaPlayer = MediaPlayer.create(MainActivity.this, audioResource);
                mediaPlayer.start();
            }



            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // No se necesita implementación para este ejemplo
            }
        });
    }

    @Override
    protected void onDestroy() {
        // Liberar recursos del MediaPlayer al destruir la actividad
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        super.onDestroy();
    }
}