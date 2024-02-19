package com.example.videojuego11;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;

public class Colision {
    public static boolean hayColision (Bitmap b1, int x1, int y1, Bitmap b2, int x2, int y2) {
        Rect rect1= new Rect(x1,y1,x1+b1.getWidth(),y1+b1.getHeight());
        Rect rect2=new Rect(x2,y2,x2+b2.getWidth(),y2+b2.getHeight());

        if (Rect.intersects(rect1,rect2)) {
            Rect rectColision=limitesColision(rect1,rect2);
            for (int i = rectColision.left; i < rectColision.right; i++) {
                for (int j = rectColision.top; j < rectColision.bottom; j++) {
                    int pixel1=b1.getPixel(i-x1,j-y1);
                    int pixel2=b2.getPixel(i-x2,j-y2);
                    if (tieneRelleno(pixel1) && tieneRelleno(pixel2)) {
                        return true;
                    }}}}
        return false;
    }
    private static Rect limitesColision (Rect r1, Rect r2) {
        int izquierda=(int) Math.max(r1.left,r2.left);
        int arriba=(int) Math.max(r1.top,r2.top);
        int derecha=(int) Math.min(r1.right,r2.right);
        int abajo=(int) Math.min(r1.bottom,r2.bottom);
        return new Rect(izquierda, arriba, derecha, abajo);}

    private static boolean tieneRelleno (int pixel) {
        return pixel!= Color.TRANSPARENT;
    }
}