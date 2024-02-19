package com.example.videojuego5;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class Juego extends SurfaceView implements SurfaceHolder.Callback {
    
    private SurfaceHolder surfaceHolder;
    private BucleJuego bucleJuego;
    private static final String TAG=Juego.class.getSimpleName();
    
    public Juego(Context context){
        super(context);
        surfaceHolder=getHolder();
        surfaceHolder.addCallback(this);
        
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

        Log.i(TAG,"Juego destruido");
        boolean retry=true;
        while(retry){
            bucleJuego.fin();
            try{
               bucleJuego.join();
               retry=false;
            } catch (InterruptedException e ){
                throw  new RuntimeException(e);
            }
        }
    }
    public void actualizar(){

    }

    public void renderizar (Canvas canvas){

    }
}
