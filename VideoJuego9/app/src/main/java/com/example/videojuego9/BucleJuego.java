package com.example.videojuego9;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class BucleJuego extends Thread {

    // Constantes para el bucle del juego
    private final static int MAX_FPS=30; // Máximo de frames por segundo
    private final static int MAX_FRAMES_SALTADOS=5; // Máximo de frames a saltar si el juego está retrasado
    private final static int TIEMPO_FRAME=1000/MAX_FPS; // Tiempo en milisegundos de cada frame
    private Juego vg; // Instancia del juego
    public boolean juegoEnEjecucion=true; // Bandera para indicar si el juego está en ejecución
    private static final String TAG=Juego.class.getSimpleName(); // Etiqueta para mensajes de registro
    private SurfaceHolder sh; // Holder de la SurfaceView del juego
    int maxX, maxY; // Tamaño máximo de la pantalla

    // Constructor
    BucleJuego(SurfaceHolder sh, Juego vg) {
        this.vg=vg;
        this.sh=sh;

        // Obtiene el tamaño de la pantalla
        Canvas c=sh.lockCanvas();
        maxX=c.getWidth();
        maxY=c.getHeight();
        sh.unlockCanvasAndPost(c);
    }

    // Método principal del hilo
    @Override
    public void run() {
        Canvas canvas;
        long tiempoComienzo;
        long tiempoDiferencia;
        int tiempoDormir;
        int framesASaltar;

        tiempoDormir=0;

        // Bucle principal del juego
        while (juegoEnEjecucion) {
            canvas = null;
            try {
                canvas = this.sh.lockCanvas(); // Bloquea el canvas para dibujar
                synchronized (sh) {
                    tiempoComienzo = System.currentTimeMillis();
                    framesASaltar = 0;
                    vg.actualizar(); // Actualiza la lógica del juego
                    vg.renderizar(canvas); // Renderiza los gráficos del juego en el canvas
                    tiempoDiferencia = System.currentTimeMillis() - tiempoComienzo;
                    tiempoDormir = (int) (TIEMPO_FRAME - tiempoDiferencia);
                    if (tiempoDormir > 0) {
                        try {
                            Thread.sleep(tiempoDormir); // Duerme el hilo para mantener el framerate constante
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    while (tiempoDormir < 0 && framesASaltar < MAX_FRAMES_SALTADOS) {
                        vg.actualizar(); // Actualiza la lógica del juego sin renderizar
                        tiempoDormir += TIEMPO_FRAME;
                        framesASaltar++;
                    }
                }
            } finally {
                if (canvas!=null)
                    sh.unlockCanvasAndPost(canvas); // Desbloquea el canvas y muestra el dibujo
            }
            Log.d(TAG,"Nueva iteración"); // Registra en el registro que se ha completado una iteración del bucle
        }
    }

    // Método para detener el bucle del juego
    public void fin() {
        juegoEnEjecucion=false;
    }

}

