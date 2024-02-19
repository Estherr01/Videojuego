package com.example.videojuego11;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;


public class Personaje {
    int posX, posY;
    private Context mContexto;
    private Bitmap imagen;

    public Personaje(Context c,int x, int y) {
        this.mContexto=c;
        this.posX=x;
        this.posY=y;
        cargar(R.drawable.bull2);
    }

    public void actualizarX(boolean derecha){
        if (derecha) {
            posX=posX+10;
        } else {
            posX=posX-10;
        }
    }

    public void actualizarY(boolean derecha){
        if (derecha) {
            posY=posY+10;
        } else {
            posY=posY-10;
        }
    }

    public void dibujar(Canvas c, Paint p) {
        Rect r1=new Rect(0,0,ancho(),alto());
        Rect r2=new Rect((int)posX,(int)posY,(int)posX+ancho(),(int)posY+alto());
        c.drawBitmap(imagen,r1,r2,p);
    }

    public Bitmap devolverBitmap () {
        return imagen;
    }

    public void cargar(int recurso) {
        imagen= BitmapFactory.decodeResource(mContexto.getResources(),recurso);
    }

    public int ancho() {
        return imagen.getWidth();
    }
    public int alto(){
        return imagen.getHeight();
    }
}
