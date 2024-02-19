package com.example.videojuego5;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class BucleJuego extends Thread{
    private final static int MAX_FPS=30;
    private final static int MAX_FRAMES_SALTADOS=5;
    private final static int TIEMPO_FRAME=1000/MAX_FPS;
    private Juego vg;
    public boolean juegoEnEjecucion= true;
    private static final String TAG= Juego.class.getSimpleName();
    private SurfaceHolder surfaceHolder;
    BucleJuego(SurfaceHolder surfaceHolder, Juego vg){
        this.vg=vg;
        this.surfaceHolder=surfaceHolder;

    }
    public void run(){
        Canvas canvas;
        long tiempoComienzo;
        long tiempoDiferencia;
        int tiempoDormir=0;
        int frameASaltar;

        while(juegoEnEjecucion){

            canvas=null;
            try{
                canvas=this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    tiempoComienzo=System.currentTimeMillis();
                    frameASaltar=0;
                    vg.actualizar();
                    vg.renderizar(canvas);
                    tiempoDiferencia=System.currentTimeMillis()-tiempoComienzo;
                    tiempoDormir=(int)(TIEMPO_FRAME - tiempoDiferencia);
                    if(tiempoDormir>0){
                        try{
                            Thread.sleep(tiempoDormir);
                        }catch (InterruptedException e){
                            throw new RuntimeException(e);

                        }
                    }while(tiempoDormir<0&& frameASaltar<MAX_FRAMES_SALTADOS){
                        vg.actualizar();
                        tiempoDormir+= TIEMPO_FRAME;
                        frameASaltar++;

                    }

                }
            }finally {
                if (canvas!=null){
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }


    public void fin() {
        juegoEnEjecucion=false;
    }
}
