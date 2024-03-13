package com.example.miapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AdminDB extends miBD{

    public AdminDB(@Nullable Context context, int version) {
        super(context, version);
    }

    public void cargarDatos(Context context){
        SQLiteDatabase db = getWritableDatabase();
        try {
            if (context != null) {
                InputStream inputStream = context.getResources().openRawResource(R.raw.datos);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String linea;
                while ((linea = bufferedReader.readLine()) != null) {
                    db.execSQL(linea);
                }
                bufferedReader.close();
                inputStream.close();
            }
        } catch (Exception e){

        }
        db.close();
    }

    public ArrayList<Campeon> getCampeonesParaVender(){
        ArrayList<Campeon> listaC = new ArrayList<>();

        SQLiteDatabase bd = getReadableDatabase();

        Cursor cu = bd.rawQuery("SELECT Nombre,Posicion,Precio,Poder,Comprado FROM Campeones", null);

        int i = 0;
        while (cu.moveToNext()) {
            String nom = cu.getString(0);
            String pos = cu.getString(1);
            int precio = cu.getInt(2);
            int poder = cu.getInt(3);

            boolean comprado = false;
            int bit = cu.getInt(4);
            if(bit==1){ comprado = true; } // Si bit=0 significa que no está comprado, si bit=1 si está comprado

            listaC.add(new Campeon(nom, pos, precio, poder, comprado)); //Creamos el campeon con la info de la base de datos
            i++;
        }

        cu.close();
        bd.close();

        return listaC;
    }

    public void comprarCampeon(String n){
        SQLiteDatabase bd = getReadableDatabase();
        ContentValues modificacion = new ContentValues();
        modificacion.put("Comprado",1);
        String[] argumentos = new String[] {n};
        bd.update("Campeones", modificacion, "Nombre=?", argumentos);
        bd.close();
    }
}
