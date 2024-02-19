package com.example.videojuego8;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;

public class Juego extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {

    private SurfaceHolder surfaceHolder;
    private BucleJuego bucleJuego;
    private static final String TAG=Juego.class.getSimpleName();
    int tocarX, tocarY;
    boolean hayToque;

    public Juego(Context context) {
        super(context);

        surfaceHolder=getHolder();
        surfaceHolder.addCallback(this);
        hayToque=false;
        setOnTouchListener(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {

        getHolder().addCallback(this);
        bucleJuego= new BucleJuego(getHolder(),this);
        setFocusable(true);
        bucleJuego.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

        boolean retry=true;
        while(retry){
            bucleJuego.fin();
            try{
                bucleJuego.join();
            }catch(InterruptedException e){
                throw new RuntimeException(e);
            }
            retry= false;
        }
    }

    public void actualizar(){

    }

    public void renderizar (Canvas canvas){
        canvas.drawColor(Color.GREEN);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int index = event.getActionIndex();
        switch(event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                tocarX=(int) event.getX(index);
                tocarY=(int) event.getY(index);
                hayToque=true;
                

            case  MotionEvent.ACTION_UP:
                hayToque=false;
                break;
        }
        tocarX=(int) event.getX();
        tocarY=(int) event.getY();
        return false;
    }
}

