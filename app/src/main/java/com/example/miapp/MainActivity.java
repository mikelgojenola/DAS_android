package com.example.miapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.labarra));

        AdminDB adb = AdminDB.getMiADB(this, 1);

        adb.cargarDatos(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.def_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id == R.id.opcion_col){

        }
        else if(id == R.id.opcion_tienda){
            Intent i = new Intent (this, TiendaActivity.class);
            /*i.putExtra("nombre1",valor);
            i.putExtra("nombre2", valor2);*/
            startActivity(i);
        }
        else if(id == R.id.opcion_pers){

        }
        else if(id == R.id.opcion_plantilla){

        }
        return super.onOptionsItemSelected(item);
    }

}