package com.example.videojuego9;

//import static androidx.transition.GhostViewHolder.getHolder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
public class Juego extends SurfaceView implements SurfaceHolder.Callback{
    private SurfaceHolder holder;
    private BucleJuego bucle;
    private static final String TAG=Juego.class.getSimpleName();

    // Constantes y variables para el fondo del juego
    private static final int MAX_IMAGENES_FONDO=2;
    Bitmap imagenes[]=new Bitmap[MAX_IMAGENES_FONDO];
    int recursos_imagenes[]={R.drawable.reyesmedianos,R.drawable.reyesmedianos2};
    int yImgActual, yImgSiguiente;
    int img_actual=0, img_siguiente=1;

    // Constructor
    public Juego(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
        yImgActual=0;
        yImgSiguiente=-MainActivity.altoPantalla; // Inicializa la posición de las imágenes de fondo
    }

    // Método para cuando la superficie es creada
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        cargaBackground(); // Carga las imágenes de fondo
        getHolder().addCallback(this); // Para interceptar eventos de la SurfaceView
        bucle=new BucleJuego(getHolder(),this); // Inicia el bucle de juego
        setFocusable(true); // Para poder capturar eventos
        bucle.start(); // Arranca el hilo del juego
    }

    // Método para cuando la superficie cambia
    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        // No se utiliza en este ejemplo
    }

    // Método para cuando la superficie es destruida
    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        Log.d(TAG,"Juego destruido");
        boolean retry=true;
        while (retry) {
            bucle.fin(); // Detiene el bucle de juego
            try {
                bucle.join(); // Espera a que termine el hilo del bucle de juego
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            retry=false;
        }
    }

    // Método para actualizar la lógica del juego
    public void actualizar() {
        actualizarFondo(); // Actualiza la posición del fondo
    }

    // Método para renderizar los gráficos del juego
    public void renderizar(Canvas canvas) {
        // Dibuja las imágenes de fondo en el canvas
        canvas.drawBitmap(imagenes[img_actual],0,yImgActual,null);
        canvas.drawBitmap(imagenes[img_siguiente],0,yImgSiguiente,null);
    }

    // Método para cargar las imágenes de fondo
    public void cargaBackground(){
        for (int i = 0; i < MAX_IMAGENES_FONDO; i++) {
            Bitmap fondo= BitmapFactory.decodeResource(getResources(),recursos_imagenes[i]);
            // Escala la imagen para que se ajuste al tamaño de la pantalla
            if (imagenes[i]==null) {
                imagenes[i] = fondo.createScaledBitmap(fondo, MainActivity.anchoPantalla, MainActivity.altoPantalla, true);
            }
            fondo.recycle(); // Libera la memoria de la imagen original
        }
    }

    // Método para actualizar la posición del fondo
    public void actualizarFondo(){
        yImgActual++; // Incrementa la posición de la imagen actual
        yImgSiguiente++; // Incrementa la posición de la próxima imagen
        // Si la imagen actual sale de la pantalla, se mueve a la siguiente imagen
        if(yImgActual>MainActivity.altoPantalla) {
            if(img_actual==MAX_IMAGENES_FONDO-1)
                img_actual=0;
            else
                img_actual++;

            // Si la próxima imagen sale de la pantalla, se vuelve a la primera imagen
            if (img_siguiente==MAX_IMAGENES_FONDO-1)
                img_siguiente=0;
            else
                img_siguiente++;
            yImgActual=0; // Reinicia la posición de la imagen actual
            yImgSiguiente=-MainActivity.altoPantalla; // Reinicia la posición de la próxima imagen
        }
    }
}
