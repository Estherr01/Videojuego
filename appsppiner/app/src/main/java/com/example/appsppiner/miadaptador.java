package com.example.appsppiner;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;


public class miadaptador extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<String>nombres;
    private int[] listaimagenes;

    public miadaptador(Context context, int layout, ArrayList<String> nombres, int[] listaimagenes) {
        this.context = context;
        this.layout = layout;
        this.nombres = nombres;
        this.listaimagenes = listaimagenes;
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

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
// Copiamos la vista
        View v = convertView;
//Inflamos la vista con nuestro propio layout
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        v = layoutInflater.inflate(R.layout.disenioo, null);
// Valor actual según la posición
        String currentName = nombres.get(position);
        int currentImagen = listaimagenes[position];
// Referenciamos el elemento a modificar y lo rellenamos
        TextView textView = (TextView) v.findViewById(R.id.textou);
        textView.setText(currentName);
        ImageView imagenview = (ImageView) v.findViewById(R.id.espania);
        imagenview.setImageResource(currentImagen);
//Devolvemos la vista inflada
        return v;
    }
}

