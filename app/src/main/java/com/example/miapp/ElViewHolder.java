package com.example.miapp;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ElViewHolder extends RecyclerView.ViewHolder {
    private interfazCV interfazCV;
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
                //elprecio.setText("COMPRADO");
                if(interfazCV != null){
                    int posicion = getAdapterPosition();
                    interfazCV.pasarInfo(posicion);
                }
            }
        });
    }
}