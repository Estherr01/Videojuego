package com.example.reproduccionaudio;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button bPlay, bPause, bStop, bMin, bSumar;
    MediaPlayer mediaplayer;
    SeekBar seekBar;
    TextView tv;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bPlay = findViewById(R.id.bPlay);
        bPause = findViewById(R.id.bPause);
        bStop = findViewById(R.id.bStop);
        bMin = findViewById(R.id.bMin);
        bSumar = findViewById(R.id.bSumar);
        seekBar = findViewById(R.id.seekBar);
        tv = findViewById(R.id.tv);

        mediaplayer = MediaPlayer.create(this, R.raw.audio);

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

        bStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplayer.stop();
                try {
                    mediaplayer.prepare();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        bMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplayer.seekTo(60000);
            }
        });

        bSumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = mediaplayer.getCurrentPosition();
                mediaplayer.seekTo(pos + 60000);
            }
        });

        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mediaplayer != null) {
                    int mCurrentPosition = mediaplayer.getCurrentPosition() / 1000;
                    seekBar.setProgress(mCurrentPosition);
                }
                mHandler.postDelayed(this, 1000);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int minutosTranscurridos = progress / 60;
                int segundosTranscurridos = progress % 60;

                int minutosTotales = mediaplayer.getDuration() / 1000 / 60;
                int segundosTotales = mediaplayer.getDuration() / 1000 % 60;

                tv.setText(String.format("%02d:%02d/%02d:%02d", minutosTranscurridos, segundosTranscurridos, minutosTotales, segundosTotales));

                mediaplayer.seekTo(progress * 1000);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}