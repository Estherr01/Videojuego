package com.example.reproaudio2;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button bPlay, bPause, bStop;
    MediaPlayer mediaPlayer;
    SeekBar seekBar;
    TextView tv, text;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bPlay = findViewById(R.id.play);
        bPause = findViewById(R.id.pause);
        bStop = findViewById(R.id.btStop);
        seekBar = findViewById(R.id.seekBar);
        tv = findViewById(R.id.durationTextView);
        text = findViewById(R.id.text);

        mediaPlayer = MediaPlayer.create(this, R.raw.audio);

        bPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                updateSeekBar();
            }
        });

        bPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });

        bStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.seekTo(0);
                mediaPlayer.pause();
                seekBar.setProgress(0);
                updateDurationTextView(0);
            }
        });

        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
                    seekBar.setProgress(mCurrentPosition);
                    updateDurationTextView(mCurrentPosition);
                }
                mHandler.postDelayed(this, 1000);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateDurationTextView(progress);
                mediaPlayer.seekTo(progress * 1000);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void updateSeekBar() {
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
                    seekBar.setProgress(mCurrentPosition);
                    updateDurationTextView(mCurrentPosition);
                    mHandler.postDelayed(this, 1000);
                }
            }
        });
    }

    private void updateDurationTextView(int currentPosition) {
        int minutesCurrent = currentPosition / 60;
        int secondsCurrent = currentPosition % 60;
        int totalDuration = mediaPlayer.getDuration() / 1000;
        int minutesTotal = totalDuration / 60;
        int secondsTotal = totalDuration % 60;

        String durationString = String.format("%02d:%02d/%02d:%02d", minutesCurrent, secondsCurrent, minutesTotal, secondsTotal);
        tv.setText(durationString);
    }
}
