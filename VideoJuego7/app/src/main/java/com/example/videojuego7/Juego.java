package com.example.videojuego7;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class Juego extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener{

    private SurfaceHolder holder;
    private BucleJuego bucle;
    int x, y, contadorFrames;
    boolean hacia_abajo = true;
    boolean hayToque;
    int touchX, touchY;

    private static final String TAG=Juego.class.getSimpleName();

    public Juego(Context context) {
        super(context);

        holder=getHolder();
        holder.addCallback(this);

        setOnTouchListener(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        getHolder().addCallback(this); //Para interceptar eventos de la surfaceview
        bucle = new BucleJuego(getHolder(),this);
        setFocusable(true); //Para poder capturar eventos
        bucle.start(); //Arrancamos el hilo
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        Log.d(TAG,"Juego destruido");
        boolean retry=true;
        while (retry) {
            bucle.fin();
            try {
                bucle.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            retry=false;
        }
    }
    // lo nuevo es esto
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getActionMasked()) {

            case MotionEvent.ACTION_DOWN:
                hayToque = true;
                Log.d("toque","down");

                Toast.makeText(this.getContext(), "pulsando", Toast.LENGTH_SHORT).show();
                break;

            case MotionEvent.ACTION_UP:
                hayToque = false;
                Log.d("toque","up");

                Toast.makeText(this.getContext(), "quitando el dedo", Toast.LENGTH_SHORT).show();
                break;
        }

        touchX = (int) event.getX();
        touchY = (int) event.getY();

        return true;
    }

    public void actualizar() {
        if (x > bucle.maxX)
            //si llega al final de la imagen vuelve??
            hacia_abajo = false;
        if (x == 0)
            hacia_abajo = true;
        if (hacia_abajo) {
            x++;
            y++;
        } else {
            x--;
            y--;
        }
        contadorFrames++;
    }

    public void renderizar(Canvas canvas) {


    }
}