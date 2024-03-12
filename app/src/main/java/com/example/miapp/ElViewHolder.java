package com.example.miapp;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ElViewHolder extends RecyclerView.ViewHolder {
    public TextView eltexto1;
    public TextView eltexto2;

    public ImageView laimagen;
    public Button elprecio;

    public ElViewHolder(@NonNull View itemView) {
        super(itemView);
        eltexto1 = itemView.findViewById(R.id.textView_nombre);
        eltexto2 = itemView.findViewById(R.id.textView_pos);
        laimagen = itemView.findViewById(R.id.imageView_tienda);
        elprecio = itemView.findViewById(R.id.button_comprar);
    }

    public void setEltexto1(TextView pNombre){
        eltexto1 = pNombre;
    }

    public void setEltexto2(TextView pPos){
        eltexto1 = pPos;
    }

    public void setImagen(TextView pImg){
        eltexto1 = pImg;
    }

    public TextView getEltexto1(){
        return eltexto1;
    }

    public TextView getEltexto2(){
        return eltexto2;
    }

    public ImageView getImg(){
        return laimagen;
    }
    public Button getElprecio(){ return elprecio; }
}
