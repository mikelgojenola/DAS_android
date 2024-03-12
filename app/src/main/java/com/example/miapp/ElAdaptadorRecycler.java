package com.example.miapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ElAdaptadorRecycler extends RecyclerView.Adapter<ElViewHolder> {
    private String[] losnombres;
    private String[] lasposiciones;

    private int[] lasimagenes;
    public ElAdaptadorRecycler (String[] nombres, String[] posiciones, int[] imagenes)
    {
        losnombres=nombres;
        lasposiciones=posiciones;
        lasimagenes=imagenes;
    }

    public ElViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View elLayoutDeCadaItem= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,null);
        ElViewHolder evh = new ElViewHolder(elLayoutDeCadaItem);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ElViewHolder holder, int position) {
        holder.getEltexto1().setText(losnombres[position]);
        holder.getEltexto2().setText(lasposiciones[position]);
        holder.laimagen.setImageResource(lasimagenes[position]);
    }
    @Override
    public int getItemCount() {
        return losnombres.length;
    }
}