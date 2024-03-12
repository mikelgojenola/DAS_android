package com.example.miapp;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ElAdaptadorRecycler extends RecyclerView.Adapter<ElViewHolder> {
    private String[] losnombres;
    private String[] lasposiciones;

    private int[] lasimagenes;
    private int[] losprecios;
    private boolean[] seleccionados = {false, false, false, false, false};
    public ElAdaptadorRecycler (String[] nombres, String[] posiciones, int[] imagenes, int[] precios)
    {
        losnombres=nombres;
        lasposiciones=posiciones;
        lasimagenes=imagenes;
        losprecios=precios;
    }

    public ElViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View elLayoutDeCadaItem= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_tienda,null);
        ElViewHolder evh = new ElViewHolder(elLayoutDeCadaItem);
        evh.seleccion = seleccionados;
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ElViewHolder holder, int position) {
        holder.eltexto1.setText(losnombres[position]);
        holder.eltexto2.setText(lasposiciones[position]);
        holder.elprecio.setText(String.valueOf(losprecios[position]));
        holder.laimagen.setImageResource(lasimagenes[position]);
    }
    @Override
    public int getItemCount() {
        return losnombres.length;
    }
}