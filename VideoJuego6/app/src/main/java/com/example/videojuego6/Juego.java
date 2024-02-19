package com.example.videojuego6;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;


public class Juego extends SurfaceView implements SurfaceHolder.Callback {
    // Declaración de variables y objetos miembros de la clase
    private SurfaceHolder surfaceHolder;
    private BucleJuego bucleJuego;
    int x, y, contadorFrames;
    boolean hacia_abajo = true;
    private static final String TAG = Juego.class.getSimpleName();

    // Constructor de la clase Juego
    public Juego(Context context) {
        super(context);
        // Obtención del SurfaceHolder y registro de esta clase como callback
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
    }

    // Método llamado cuando se crea la superficie
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        // Creación de un nuevo BucleJuego y configuración del hilo de juego
        bucleJuego = new BucleJuego(getHolder(), this);
        setFocusable(true);
        bucleJuego.start();
    }

    // Método llamado cuando cambia el tamaño de la superficie
    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        // Puedes agregar lógica específica si es necesario
    }

    // Método llamado cuando se destruye la superficie
    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        // Detención segura del hilo de juego cuando se destruye la superficie
        Log.i(TAG, "Juego destruido");
        boolean retry = true;
        while (retry) {
            bucleJuego.fin();
            try {
                bucleJuego.join();
                retry = false;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Método para actualizar la lógica del juego (posición, estado, etc.)
    public void actualizar() {
        // Lógica para actualizar la posición de la imagen y otros elementos del juego
        if (x > bucleJuego.maxX)
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

    // Método para renderizar los gráficos en la superficie
    public void renderizar(Canvas canvas) {
        if (canvas != null) {
            // Configuración de pinturas y otros elementos gráficos

            // Dibujar fondo de color magenta
            canvas.drawColor(Color.MAGENTA);

            // Dibujar un bitmap (imagen de perro) en una posición específica
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.perro);
            canvas.drawBitmap(bmp, 0 + x, 0 + y, null);

            // Dibujar un rectángulo azul
            Paint mypaint = new Paint();
            mypaint.setStyle(Paint.Style.STROKE);
            mypaint.setStrokeWidth(10);
            mypaint.setColor(Color.BLUE);
            canvas.drawRect(0 + x, 0 + y, 300, 300, mypaint);

            // Dibujar un óvalo negro
            mypaint.setColor(Color.BLACK);
            RectF rectF = new RectF(0 + x, 0 + y, 200, 120);
            canvas.drawOval(rectF, mypaint);

            // Dibujar un arco negro en el óvalo
            mypaint.setColor(Color.BLACK);
            canvas.drawArc(rectF, 90, 45, true, mypaint);

            // Dibujar texto con información sobre los frames ejecutados
            mypaint.setStyle(Paint.Style.FILL);
            mypaint.setTextSize(40);
            canvas.drawText("Frames ejecutados: " + contadorFrames, 0, 0 + y, mypaint);
        }
    }
}

