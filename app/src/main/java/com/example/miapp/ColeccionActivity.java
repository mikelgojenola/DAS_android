 package com.example.miapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

 public class ColeccionActivity extends AppCompatActivity implements interfazCV{
     ArrayList<Integer> personajes;
     ArrayList<String> nombres;
     ArrayList<String> descripciones;
     ArrayList<String> posiciones;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coleccion);

        setSupportActionBar(findViewById(R.id.labarra_col));

        RecyclerView lalista= findViewById(R.id.elreciclerviewCol);

        AdminDB adb = AdminDB.getMiADB(this,1);
        ArrayList<Campeon> campeones = adb.getCampeonesColeccion();

        //Integer[] listaP = {R.drawable.nasus, R.drawable.maokai, R.drawable.ahri, R.drawable.vayne, R.drawable.lulu};

        personajes= new ArrayList<Integer>();
        descripciones= new ArrayList<String>();
        nombres= new ArrayList<String>();
        posiciones= new ArrayList<String>();
        ArrayList<String> precios= new ArrayList<String>();


        for (int i = 0; i<campeones.size(); i++){
            nombres.add(campeones.get(i).getNombre());
            descripciones.add(campeones.get(i).getDescripcion());
            posiciones.add(campeones.get(i).getPosicion());
            personajes.add(campeones.get(i).getImg());
            if(!campeones.get(i).estaComprado()) {
                precios.add(String.valueOf(campeones.get(i).getPrecio()));
            }
            else{
                precios.add("COMPRADO");
            }
        }


        GridLayoutManager elLayoutRejillaIgual= new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        lalista.setLayoutManager(elLayoutRejillaIgual);

        ElAdaptadorRecyclerCol eladaptador = new ElAdaptadorRecyclerCol(nombres, personajes,this);
        lalista.setAdapter(eladaptador);
    }

     public void pasarInfo(int position){
         Intent i = new Intent(this, CampeonInfoCol.class);
         i.putExtra("imagen", personajes.get(position));
         i.putExtra("nombre", nombres.get(position));
         i.putExtra("posicion", posiciones.get(position));
         i.putExtra("descripcion", descripciones.get(position));
         startActivity(i);
         finish();
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
             Intent i = new Intent (this, MainActivity.class);
            /*i.putExtra("nombre1",valor);
            i.putExtra("nombre2", valor2);*/
             startActivity(i);
             finish();
         }
         else if(id == R.id.opcion_tienda){
             Intent i = new Intent (this, TiendaActivity.class);
             startActivity(i);
             finish();
         }
         return super.onOptionsItemSelected(item);
     }
}