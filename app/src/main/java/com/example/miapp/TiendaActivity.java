package com.example.miapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class TiendaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tienda);

        setSupportActionBar(findViewById(R.id.labarra_tienda));

        RecyclerView lalista= findViewById(R.id.elreciclerview);

        int[] personajes= {R.drawable.nasus_0, R.drawable.maokai, R.drawable.ahri, R.drawable.vayne, R.drawable.lulu};
        String[] nombres={"Nasus", "Maokai", "Ahri", "Vayne", "Lulu"};
        String[] posiciones={"Top", "Jungla", "Mid", "Adc", "Support"};
        int[] precios={50,50,50,50,50};

        LinearLayoutManager elLayoutLineal= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        lalista.setLayoutManager(elLayoutLineal);

        ElAdaptadorRecycler eladaptador = new ElAdaptadorRecycler(nombres, posiciones, personajes, precios);
        lalista.setAdapter(eladaptador);
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
        else if(id == R.id.opcion_menu){

        }
        else if(id == R.id.opcion_pers){

        }
        else if(id == R.id.opcion_plantilla){

        }
        return super.onOptionsItemSelected(item);
    }
}