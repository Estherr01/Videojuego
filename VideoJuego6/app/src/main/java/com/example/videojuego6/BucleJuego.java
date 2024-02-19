package com.example.videojuego6;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class BucleJuego extends Thread {
    // Definición de constantes para el control del bucle del juego
    private final static int MAX_FPS = 30; // Tasa máxima de frames por segundo
    private final static int MAX_FRAMES_SALTADOS = 5; // Número máximo de frames que se pueden saltar
    private final static int TIEMPO_FRAME = 1000 / MAX_FPS; // Duración de cada frame en milisegundos

    // Variables para el control del canvas y el juego
    private Juego vg; // Referencia al juego principal
    private SurfaceHolder surfaceHolder; // Manejador de la superficie del canvas

    // Dimensiones máximas del canvas
    public int maxX, maxY;

    // Indicador de ejecución del juego
    public boolean juegoEnEjecucion = true;

    // Etiqueta para identificar la clase en los registros
    private static final String TAG = Juego.class.getSimpleName();

    /**
     * Constructor de la clase BucleJuego.
     * Inicializa el objeto con la referencia al juego y el manejador del canvas.
     * Calcula las dimensiones máximas del canvas.
     */
    BucleJuego(SurfaceHolder surfaceHolder, Juego vg) {
        this.vg = vg;
        this.surfaceHolder = surfaceHolder;

        // Obtener las dimensiones máximas del canvas
        Canvas canvas = surfaceHolder.lockCanvas();
        maxX = canvas.getWidth();
        maxY = canvas.getHeight();
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    /**
     * Método principal del hilo que controla el bucle del juego.
     * Se encarga de actualizar y renderizar el juego a una tasa de FPS controlada.
     * También gestiona la lógica para evitar que se salten demasiados frames.
     */
    public void run() {
        Canvas canvas;
        long tiempoComienzo;
        long tiempoDiferencia;
        int tiempoDormir = 0;
        int frameASaltar;

        while (juegoEnEjecucion) {
            canvas = null;
            try {
                // Intenta obtener el canvas para dibujar
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    tiempoComienzo = System.currentTimeMillis();
                    frameASaltar = 0;

                    // Actualiza el estado del juego
                    vg.actualizar();

                    // Renderiza el juego en el canvas
                    vg.renderizar(canvas);

                    // Calcula el tiempo transcurrido en el frame
                    tiempoDiferencia = System.currentTimeMillis() - tiempoComienzo;

                    // Calcula el tiempo de espera antes del siguiente frame
                    tiempoDormir = (int) (TIEMPO_FRAME - tiempoDiferencia);

                    // Espera si es necesario para mantener la tasa de FPS
                    if (tiempoDormir > 0) {
                        try {
                            Thread.sleep(tiempoDormir);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    // Salta frames si se están renderizando demasiado lentamente
                    while (tiempoDormir < 0 && frameASaltar < MAX_FRAMES_SALTADOS) {
                        vg.actualizar();
                        tiempoDormir += TIEMPO_FRAME;
                        frameASaltar++;
                    }
                }
            } finally {
                // Libera el canvas una vez que se ha terminado de renderizar
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    /**
     * Método para detener la ejecución del juego.
     * Cambia el estado del juego a "juegoEnEjecucion = false".
     */
    public void fin() {
        juegoEnEjecucion = false;
    }
}