package com.example.miapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

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
        elprecio = itemView.findViewById(R.id.button_comprar);
        elprecio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                elprecio.setText("COMPRADO");

                String n = eltexto1.getText().toString(); // Conseguir el nombre del campeon comprado

                AdminDB adb = new AdminDB(context, 1);
                adb.comprarCampeon(n, context);
                adb.close();

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