package com.example.audiostreaming;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button bPlay, bPause;
    MediaPlayer mediaplayer;
    TextView tv;

    private Handler mHandler = new Handler();
    private int seg = 0;
    private int min = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bPlay = findViewById(R.id.bPlay);
        bPause = findViewById(R.id.bPause);
        tv = findViewById(R.id.tv);

        mediaplayer = new MediaPlayer();
        String url = "http://n0e.radiojar.com/5705c71sn2zuv?rj-ttl=5&rj-tok=AAABjD7Q5nsAC-ZGF_Eqs4QLbA";

        try {
            mediaplayer.setDataSource(url);
            mediaplayer.prepare();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),
                    "Error al conectar con la radio", Toast.LENGTH_LONG).show();
        }

        mediaplayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // Listo para reproducir
            }
        });

        bPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplayer.start();
            }
        });

        bPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplayer.pause();
            }
        });


        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateUI();
                mHandler.postDelayed(this, 1000);
            }
        }, 1000);
    }

    private void updateUI() {
        if (mediaplayer != null && mediaplayer.isPlaying()) {
            if (seg >= 60) {
                min++;
                seg = 0;
            }
            tv.setText("Llevas reproduciendo " + min + ":" + seg);
            seg = seg + 1;
        }
    }
}

