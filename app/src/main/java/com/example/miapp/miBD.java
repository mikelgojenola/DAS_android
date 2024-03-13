package com.example.miapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class miBD extends SQLiteOpenHelper {
    public miBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Campeones ('id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'Nombre' VARCHAR(255), 'Posicion' VARCHAR(255), 'Precio' INTEGER, 'Poder' INTEGER, 'Comprado' BOOLEAN)");
        inicializar();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private void inicializar(){
        SQLiteDatabase bd = getWritableDatabase();
        bd.execSQL("INSERT INTO Campeones (Nombre, Posicion, Precio, Poder, Comprado) VALUES ('Nasus', 'Top', 50, 10, 0)");
        bd.execSQL("INSERT INTO Campeones (Nombre, Posicion, Precio, Poder, Comprado) VALUES ('Maokai', 'Jungla', 50, 10, 0)");
        bd.execSQL("INSERT INTO Campeones (Nombre, Posicion, Precio, Poder, Comprado) VALUES ('Ahri', 'Mid', 50, 10, 0)");
        bd.execSQL("INSERT INTO Campeones (Nombre, Posicion, Precio, Poder, Comprado) VALUES ('Vayne', 'Adc', 50, 10, 0)");
        bd.execSQL("INSERT INTO Campeones (Nombre, Posicion, Precio, Poder, Comprado) VALUES ('Lulu', 'Support', 50, 10, 0)");
        bd.close();
    }

    public static void insertarCampeon(Campeon c){

    }
}