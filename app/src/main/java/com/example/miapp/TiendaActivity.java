package com.example.miapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class TiendaActivity extends AppCompatActivity implements interfazCV{
    ArrayList<Integer> personajes;
    ArrayList<String> nombres;
    ArrayList<String> descripciones;
    ArrayList<String> posiciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tienda);

        setSupportActionBar(findViewById(R.id.labarra_tienda));

        RecyclerView lalista= findViewById(R.id.elreciclerview);

        // Conseguir los campeones que no están en nuestra propiedad, comprobando el atributo "comprado" en la base de datos
        AdminDB adb = AdminDB.getMiADB(this,1);
        ArrayList<Campeon> campeones = adb.getCampeonesParaVender();

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


        LinearLayoutManager elLayoutLineal= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        lalista.setLayoutManager(elLayoutLineal);

        ElAdaptadorRecycler eladaptador = new ElAdaptadorRecycler(nombres, descripciones, posiciones, personajes, precios, this, this);
        lalista.setAdapter(eladaptador);

    }

    public void pasarInfo(int position){
        Intent i = new Intent(this, CampeonInfoTienda.class);
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
            finish();
        }
        else if(id == R.id.opcion_menu){
            Intent i = new Intent (this, MainActivity.class);
            /*i.putExtra("nombre1",valor);
            i.putExtra("nombre2", valor2);*/
            startActivity(i);
            finish();
        }
        else if(id == R.id.opcion_pers){
            finish();
        }
        else if(id == R.id.opcion_plantilla){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}