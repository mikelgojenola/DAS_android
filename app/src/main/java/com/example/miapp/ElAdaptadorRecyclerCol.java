package com.example.miapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class ElAdaptadorRecyclerCol extends RecyclerView.Adapter<ElAdaptadorRecyclerCol.ElViewHolderCol> {
    private ArrayList<String> losnombres;
    private ArrayList<Integer> lasimagenes;
    private static boolean[] seleccionados;
    private final interfazCV interfazCV;
    public ElAdaptadorRecyclerCol (ArrayList<String> nombres, ArrayList<Integer> imagenes, interfazCV pInterfaz)
    {
        losnombres=nombres;
        lasimagenes=imagenes;
        seleccionados=new boolean[nombres.size()];
        interfazCV = pInterfaz;
    }

    public ElViewHolderCol onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View elLayoutDeCadaItem= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_col,null);
        ElViewHolderCol evh = new ElViewHolderCol(elLayoutDeCadaItem);
        evh.seleccion = seleccionados;
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ElViewHolderCol holder, int position) {
        holder.elnombre.setText(losnombres.get(position));
        holder.laimagen.setImageResource(lasimagenes.get(position));
    }
    @Override
    public int getItemCount() {
        return losnombres.size();
    }

    public class ElViewHolderCol extends RecyclerView.ViewHolder {
        public TextView elnombre;
        public ImageView laimagen;
        public boolean[] seleccion;
        public ElViewHolderCol(@NonNull View itemView) {
            super(itemView);
            elnombre = itemView.findViewById(R.id.textView_nombre_col);
            laimagen = itemView.findViewById(R.id.imageView_col);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int posicion = getAdapterPosition();
                    interfazCV.pasarInfo(posicion);
                    /*if (seleccion[getAdapterPosition()]==true){
                        seleccion[getAdapterPosition()]=false;
                        laimagen.setColorFilter(null);
                    }
                    else{
                        seleccion[getAdapterPosition()]=true;
                        laimagen.setColorFilter(Color.BLACK);
                    }*/
                }
            });
        }
    }
}
