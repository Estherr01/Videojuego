package com.example.videojuego4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout layout1=findViewById(R.id.layout);
        Lienzo fondo= new Lienzo(this);
        layout1.addView(fondo);

    }
    class Lienzo extends View {
        public Lienzo(Context context){
            super(context);
        }

        protected void onDraw(Canvas canvas){
            canvas.drawRGB(255,255,255);
            int ancho= canvas.getWidth();
            Paint pincel= new Paint();
            pincel.setARGB(255,255,0,0);
            canvas.drawRect(10,10,ancho -10,40,pincel);

            pincel.setStyle(Paint.Style.STROKE);
            canvas.drawRect(10,60,ancho -10,90,pincel);

            pincel.setStrokeWidth(3);
            canvas.drawRect(10,110,ancho -10,140,pincel);


        }
    }
}