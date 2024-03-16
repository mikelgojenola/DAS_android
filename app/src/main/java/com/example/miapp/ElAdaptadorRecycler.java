package com.example.miapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ElAdaptadorRecycler extends RecyclerView.Adapter<ElAdaptadorRecycler.ElViewHolder> {
    private ArrayList<String> losnombres;
    private ArrayList<String> lasdescripciones;
    private ArrayList<String> lasposiciones;

    private ArrayList<Integer> lasimagenes;
    private ArrayList<String> losprecios;
    private boolean[] seleccionados = {false, false, false, false, false};
    private Context contexto;
    private final interfazCV interfazCV;
    public ElAdaptadorRecycler (ArrayList<String> nombres, ArrayList<String> descripciones, ArrayList<String> posiciones, ArrayList<Integer> imagenes, ArrayList<String> precios, Context pcontext, interfazCV pinterfaz)
    {
        losnombres=nombres;
        lasdescripciones=descripciones;
        lasposiciones=posiciones;
        lasimagenes=imagenes;
        losprecios=precios;
        contexto=pcontext;
        interfazCV=pinterfaz;
    }

    @Override
    @NonNull
    public ElAdaptadorRecycler.ElViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View elLayoutDeCadaItem= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_tienda,null);
        ElViewHolder evh = new ElViewHolder(elLayoutDeCadaItem, contexto);
        evh.seleccion = seleccionados;
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ElViewHolder holder, int position) {
        holder.eltexto1.setText(losnombres.get(position));
        holder.eltexto2.setText(lasposiciones.get(position));
        holder.elprecio.setText(String.valueOf(losprecios.get(position)));
        holder.laimagen.setImageResource(lasimagenes.get(position));
    }

    @Override
    public int getItemCount() {
        return losnombres.size();
    }

    public class ElViewHolder extends RecyclerView.ViewHolder {
        public TextView eltexto1;
        public TextView eltexto2;
        public ImageView laimagen;
        public Button elprecio;
        public boolean[] seleccion;
        public ElViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            eltexto1 = itemView.findViewById(R.id.textView_nombre);
            eltexto2 = itemView.findViewById(R.id.textView_pos);
            laimagen = itemView.findViewById(R.id.imageView_tienda);
            elprecio = itemView.findViewById(R.id.botonPrecio);
            elprecio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(interfazCV != null) {
                        int posicion = getAdapterPosition();
                        interfazCV.pasarInfo(posicion);
                    }
                }
            });
        }
    }


}