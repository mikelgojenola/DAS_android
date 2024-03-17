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
    private static Context contexto;
    private AdminDB(@Nullable Context context, int version) {
        super(context, version);
    }
    public static AdminDB getMiADB(Context context, int version){
        contexto = context;
        if(miadb == null){
            miadb = new AdminDB(context,version);
        }
        return miadb;
    }

    // ejecuta todas las sentencias sql del archivo de texto "datos.txt", las cuales meten la información de todos
    // los campeones en la base de datos, e inicializa el dinero del usuario a 250
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

    // recoge todos los campeones de la base de datos para mostrarlos en la actividad de la tienda
    public ArrayList<Campeon> getCampeonesParaVender(){
        ArrayList<Campeon> listaC = new ArrayList<>();

        SQLiteDatabase bd = getReadableDatabase();

        Cursor cu = bd.rawQuery("SELECT DISTINCT Nombre,Descripcion,Posicion,Precio,Poder,Comprado,img FROM Campeones", null);

        while (cu.moveToNext()) {
            String nom = cu.getString(0);
            String des = cu.getString(1);
            String pos = cu.getString(2);
            int precio = cu.getInt(3);
            int poder = cu.getInt(4);

            boolean comprado = false;
            int bit = cu.getInt(5);
            if(bit==1){ comprado = true; } // Si bit=0 significa que no está comprado, si bit=1 si está comprado

            int img = cu.getInt(6);

            listaC.add(new Campeon(nom, des, pos, precio, poder, comprado, img)); //Creamos el campeon con la info de la base de datos
        }

        cu.close();
        bd.close();

        return listaC;
    }

    // Recoge los campeones desbloqueados para mostrarlos en la actividad de la colección
    public ArrayList<Campeon> getCampeonesColeccion(){
        ArrayList<Campeon> listaC = new ArrayList<>();

        SQLiteDatabase bd = getReadableDatabase();

        Cursor cu = bd.rawQuery("SELECT DISTINCT Nombre,Descripcion,Posicion,Precio,Poder,Comprado,img FROM Campeones WHERE Comprado=1", null);

        while (cu.moveToNext()) {
            String nom = cu.getString(0);
            String des = cu.getString(1);
            String pos = cu.getString(2);
            int precio = cu.getInt(3);
            int poder = cu.getInt(4);

            boolean comprado = false;
            int bit = cu.getInt(5);
            if(bit==1){ comprado = true; } // Si bit=0 significa que no está comprado, si bit=1 si está comprado

            int img = cu.getInt(6);

            listaC.add(new Campeon(nom, des, pos, precio, poder, comprado, img)); //Creamos el campeon con la info de la base de datos
        }

        cu.close();
        bd.close();

        return listaC;
    }

    //Primero comprueba si el campeón que se quiere comprar está desbloqueado, y si no lo está, lo desbloquea y actualiza la base de datos
    public void comprarCampeon(String n){
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

        int dinero = getDinero() - 50;
        String[] arg2 = {String.valueOf(dinero)};
        bd = getWritableDatabase();
        bd.execSQL("UPDATE Usuario SET Dinero=?", arg2);

        bd.close();
    }

    // método para sumar 50 monedas cuando se completan los 10 segundos después de pulsar el botón de la actividad principal
    public void sumar50(){
        int dinero = getDinero() + 50;
        Object[] arg2 = {dinero};
        SQLiteDatabase bd = getWritableDatabase();
        bd.execSQL("UPDATE Usuario SET Dinero=?", arg2);

        bd.close();
    }

    //comprueba si el personaje está desbloqueado o no
    public boolean estaComprado(String n, Context context){
        boolean comprado = false;
        SQLiteDatabase bd = getWritableDatabase();
        String[] arg = {n};
        Cursor cu = bd.rawQuery("SELECT Comprado FROM Campeones WHERE Nombre=?", arg);
        cu.moveToNext();

        int bit = cu.getInt(0);
        if(bit==1){ comprado = true; } // Si bit=0 significa que no está comprado, si bit=1 si está comprado
        cu.close();

        bd.close();

        return comprado;
    }

    //devuelve true si puede comprar un campeon y false si no puede (todos los campeones cuestan 50 monedas)
    public boolean puedeComprar(){
        int dinero = getDinero();
        if(dinero>=50){
            return true;
        }
        else return false;
    }

    //devuelve el dinero actual del usuario
    public int getDinero(){
        int dinero = 0;

        SQLiteDatabase bd = getWritableDatabase();
        Cursor cu = bd.rawQuery("SELECT Dinero FROM Usuario", null);
        cu.moveToNext();

        dinero = cu.getInt(0);
        cu.close();

        bd.close();

        return dinero;
    }

    //mete cada imagen al campeón correspondiente
    private void meterImagenes(){
        meterImagen(R.drawable.nasus, "Nasus");
        meterImagen(R.drawable.maokai, "Maokai");
        meterImagen(R.drawable.ahri, "Ahri");
        meterImagen(R.drawable.vayne, "Vayne");
        meterImagen(R.drawable.lulu, "Lulu");
        meterImagen(R.drawable.kayle, "Kayle");
        meterImagen(R.drawable.shaco, "Shaco");
        meterImagen(R.drawable.azir, "Azir");
        meterImagen(R.drawable.zeri, "Zeri");
        meterImagen(R.drawable.janna, "Janna");
        meterImagen(R.drawable.teemo, "Teemo");
        meterImagen(R.drawable.viego, "Viego");
        meterImagen(R.drawable.jinx, "Jinx");
        meterImagen(R.drawable.yone, "Yone");
        meterImagen(R.drawable.leona, "Leona");
        meterImagen(R.drawable.renekton, "Renekton");
        meterImagen(R.drawable.kindred, "Kindred");
        meterImagen(R.drawable.cassiopeia, "Cassiopeia");
        meterImagen(R.drawable.caitlyn, "Caitlyn");
        meterImagen(R.drawable.blitzcrank, "Blitzcrank");
        meterImagen(R.drawable.aatrox, "Aatrox");
        meterImagen(R.drawable.leesin, "Lee Sin");
        meterImagen(R.drawable.neeko, "Neeko");
        meterImagen(R.drawable.varus, "Varus");
        meterImagen(R.drawable.rell, "Rell");
    }

    private void meterImagen(Integer img, String nombre){
        SQLiteDatabase db = getWritableDatabase();

        Object[] arg = {img, nombre};
        db.execSQL("UPDATE Campeones SET img=? WHERE Nombre=?", arg);

        db.close();
    }
}
