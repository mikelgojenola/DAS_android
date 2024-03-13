package com.example.miapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ElAdaptadorRecycler extends RecyclerView.Adapter<ElViewHolder> {
    private ArrayList<String> losnombres;
    private ArrayList<String> lasposiciones;

    private ArrayList<Integer> lasimagenes;
    private ArrayList<String> losprecios;
    private boolean[] seleccionados = {false, false, false, false, false};
    private Context contexto;
    public ElAdaptadorRecycler (ArrayList<String> nombres, ArrayList<String> posiciones, ArrayList<Integer> imagenes, ArrayList<String> precios, Context pcontext)
    {
        losnombres=nombres;
        lasposiciones=posiciones;
        lasimagenes=imagenes;
        losprecios=precios;
        contexto=pcontext;
    }

    public ElViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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

    /*public void eliminarElemento(int posicion) {
        elementos.remove(posicion);
        notifyItemRemoved(posicion);
    }*/
}