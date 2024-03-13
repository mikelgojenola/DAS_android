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
        sqLiteDatabase.execSQL("CREATE TABLE Campeones ('id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'Nombre' VARCHAR(255), 'Posicion' VARCHAR(255), 'Precio' INTEGER, 'Poder' INTEGER, 'Comprado' BOOLEAN)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public static void insertarCampeon(Campeon c){

    }
}