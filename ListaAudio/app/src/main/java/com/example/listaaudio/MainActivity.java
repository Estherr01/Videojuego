package com.example.listaaudio;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner audioSpinner = findViewById(R.id.audioSpinner);

        // Obtén la lista de recursos de audio en la carpeta "raw"
        final String[] audioFiles = {
                "audio",  // Reemplaza con el nombre de tu primer archivo de audio (sin extensión)
                "audio2",  // Reemplaza con el nombre de tu segundo archivo de audio (sin extensión)
                // Agrega más archivos según sea necesario
        };


        // Crea un ArrayAdapter para el Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, audioFiles);
        audioSpinner.setAdapter(adapter);

        // Configura el listener para el Spinner
        audioSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Cuando se selecciona un elemento, reproduce el archivo de audio correspondiente
                playAudio(audioFiles[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // No se requiere acción cuando no se selecciona nada
            }
        });
    }

    private void playAudio(String fileName) {
        // Detén la reproducción si ya está en progreso
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }

        // Crea un nuevo MediaPlayer y reproduce el archivo de audio seleccionado
        int audioResourceId = getResources().getIdentifier(fileName, "raw", getPackageName());
        mediaPlayer = MediaPlayer.create(this, audioResourceId);
        mediaPlayer.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Detén la reproducción cuando la aplicación está en segundo plano
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
