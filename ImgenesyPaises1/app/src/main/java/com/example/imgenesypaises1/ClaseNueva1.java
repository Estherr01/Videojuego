package com.example.imgenesypaises1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
public class ClaseNueva1 extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<String> nombres;
    private int[] imagenes;

    public ClaseNueva1(Context context, int layout, ArrayList<String> nombres, int[] imagenes) {
        this.context = context;
        this.layout = layout;
        this.nombres = nombres;
        this.imagenes = imagenes;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
// Copiamos la vista
        View view  = convertView;
//Inflamos la vista con nuestro propio layout
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        //fila seria el nombre del xml creado nuevo
        view = layoutInflater.inflate(R.layout.fila, null);
// Valor actual según la posición
        String currentName = nombres.get(position);
        int currentImagen = imagenes[position];
// Referenciamos el elemento a modificar y lo rellenamos
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(currentName);
        ImageView imagenview= (ImageView) view.findViewById(R.id.imageView);
        imagenview.setImageResource(currentImagen);
//Devolvemos la vista inflada
        return view;
    }

    @Override
    public int getCount() {
        return nombres.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
