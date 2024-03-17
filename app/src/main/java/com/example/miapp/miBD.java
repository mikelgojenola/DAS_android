package com.example.miapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class miBD extends SQLiteOpenHelper {
    private static final String nombredb = "campeon.db";
    private Context contexto;
    public miBD(@Nullable Context context, int version) {
        super(context,nombredb, null, version);
        this.contexto = context;
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // crear la tabla de los campeones para guardar su info, y la del usuario para guardar el dinero
        sqLiteDatabase.execSQL("CREATE TABLE Campeones ('id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'Nombre' VARCHAR(255) NOT NULL UNIQUE, 'Descripcion' TEXT, 'Posicion' VARCHAR(255), 'Precio' INTEGER, 'Poder' INTEGER, 'Comprado' BOOLEAN, 'img' INTEGER)");
        sqLiteDatabase.execSQL("CREATE TABLE Usuario ('id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'Dinero' INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // método para resetear la base de datos y que vuelva a pasar por el onCreate (ejecutar en el constructor de miBD)
    public void resetearBaseDeDatos() {
        // Eliminar la base de datos actual
        contexto.deleteDatabase(getDatabaseName());
        close();
    }
}