package com.example.videojuego11;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class Torero {
    int posX, posY;
    private Context mContexto;
    private Bitmap imagen;

    public Torero(Context c,int x, int y) {
        this.mContexto=c;
        this.posX=x;
        this.posY=y;
        cargar(R.drawable.toreropeque);}

    public void dibujar(Canvas c, Paint p) {
        Rect r1=new Rect(0,0,ancho(),alto());
        Rect r2=new Rect((int)posX,(int)posY,(int)posX+ancho(),(int)posY+alto());
        c.drawBitmap(imagen,r1,r2,p);}

    public Bitmap devolverBitmap () {
        return imagen;
    }

    public void cargar(int recurso) {imagen= BitmapFactory.decodeResource(mContexto.getResources(),recurso);}

    public int ancho() {
        return imagen.getWidth();
    }
    public int alto(){
        return imagen.getHeight();
    }
}
