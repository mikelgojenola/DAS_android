package com.example.miapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdaptadorListView extends BaseAdapter {
    private Context contexto;
    private LayoutInflater inflater;
    private String[] nombres;
    private String[] posiciones;
    private int[] imagenes;

    public AdaptadorListView(Context pcontext, String[] pnombres, int[] pimagenes, String[] pposiciones)
    {
        contexto = pcontext;
        nombres = pnombres;
        imagenes=pimagenes;
        posiciones=pposiciones;
        inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return nombres.length;
    }

    @Override
    public Object getItem(int i) {
        return nombres[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        view=inflater.inflate(R.layout.cardview,null);
        TextView nombre= (TextView) view.findViewById(R.id.textView_nombre);
        TextView posicion= (TextView) view.findViewById(R.id.textView_pos);
        ImageView img=(ImageView) view.findViewById(R.id.imageView_tienda);
        nombre.setText(nombres[i]);
        posicion.setText(posiciones[i]);
        img.setImageResource(imagenes[i]);
        return view;
    }
}
