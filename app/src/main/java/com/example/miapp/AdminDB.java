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
    static AdminDB miadb;
    private AdminDB(@Nullable Context context, int version) {
        super(context, version);
    }
    public static AdminDB getMiADB(Context context, int version){
        if(miadb == null){
            miadb = new AdminDB(context,version);
        }
        return miadb;
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

        meterImagenes();
        db.close();
    }

    public ArrayList<Campeon> getCampeonesParaVender(){
        ArrayList<Campeon> listaC = new ArrayList<>();

        SQLiteDatabase bd = getReadableDatabase();

        Cursor cu = bd.rawQuery("SELECT DISTINCT Nombre,Posicion,Precio,Poder,Comprado,img FROM Campeones", null);

        while (cu.moveToNext()) {
            String nom = cu.getString(0);
            String pos = cu.getString(1);
            int precio = cu.getInt(2);
            int poder = cu.getInt(3);

            boolean comprado = false;
            int bit = cu.getInt(4);
            if(bit==1){ comprado = true; } // Si bit=0 significa que no está comprado, si bit=1 si está comprado

            int img = cu.getInt(5);

            listaC.add(new Campeon(nom, pos, precio, poder, comprado, img)); //Creamos el campeon con la info de la base de datos
        }

        cu.close();
        bd.close();

        return listaC;
    }

    public void comprarCampeon(String n, Context context){
        SQLiteDatabase bd = getWritableDatabase();
        String[] arg = {n};
        Cursor cu = bd.rawQuery("SELECT Comprado FROM Campeones WHERE Nombre=?", arg);
        cu.moveToNext();

        boolean comprado = false;
        int bit = cu.getInt(0);
        if(bit==1){ comprado = true; } // Si bit=0 significa que no está comprado, si bit=1 si está comprado
        cu.close();

        if(!comprado) {
            ContentValues modificacion = new ContentValues();
            modificacion.put("Comprado", 1);
            String[] argumentos = new String[]{n};
            bd.update("Campeones", modificacion, "Nombre=?", argumentos);
        }
        bd.close();
    }

    public boolean estaComprado(String n, Context context){
        boolean comprado = false;
        SQLiteDatabase bd = getWritableDatabase();
        String[] arg = {n};
        Cursor cu = bd.rawQuery("SELECT Comprado FROM Campeones WHERE Nombre=?", arg);
        cu.moveToNext();

        int bit = cu.getInt(0);
        if(bit==1){ comprado = true; } // Si bit=0 significa que no está comprado, si bit=1 si está comprado
        cu.close();

        if(!comprado) {
            ContentValues modificacion = new ContentValues();
            modificacion.put("Comprado", 1);
            String[] argumentos = new String[]{n};
            bd.update("Campeones", modificacion, "Nombre=?", argumentos);
        }
        bd.close();

        return comprado;
    }

    private void meterImagenes(){
        meterImagen(R.drawable.nasus, "Nasus");
        meterImagen(R.drawable.maokai, "Maokai");
        meterImagen(R.drawable.ahri, "Ahri");
        meterImagen(R.drawable.vayne, "Vayne");
        meterImagen(R.drawable.lulu, "Lulu");
    }

    private void meterImagen(Integer img, String nombre){
        SQLiteDatabase db = getWritableDatabase();

        Object[] arg = {img, nombre};
        db.execSQL("UPDATE Campeones SET img=? WHERE Nombre=?", arg);

        db.close();
    }

    public void resetear(){
        SQLiteDatabase bd = getWritableDatabase();
        bd.execSQL("DELETE FROM Campeones");
        bd.close();
    }
}
