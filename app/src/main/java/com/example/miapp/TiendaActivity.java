package com.example.miapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.database.Cursor;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class TiendaActivity extends AppCompatActivity implements DialogoTienda.ListenerdelDialogo{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tienda);

        setSupportActionBar(findViewById(R.id.labarra_tienda));

        RecyclerView lalista= findViewById(R.id.elreciclerview);

        // Conseguir los campeones que no están en nuestra propiedad, comprobando el atributo "comprado" en la base de datos
        AdminDB adb = new AdminDB(this, 1);
        ArrayList<Campeon> campeones = adb.getCampeonesParaVender();

        Integer[] listaP = {R.drawable.nasus, R.drawable.maokai, R.drawable.ahri, R.drawable.vayne, R.drawable.lulu};

        ArrayList<Integer> personajes= new ArrayList<Integer>(Arrays.asList(listaP));
        ArrayList<String> nombres= new ArrayList<String>();
        ArrayList<String> posiciones= new ArrayList<String>();
        ArrayList<String> precios= new ArrayList<String>();


        for (int i = 0; i<campeones.size(); i++){
            nombres.add(campeones.get(i).getNombre());
            posiciones.add(campeones.get(i).getPosicion());
            if(!campeones.get(i).estaComprado()) {
                precios.add(String.valueOf(campeones.get(i).getPrecio()));
            }
            else{
                precios.add("COMPRADO");
            }
        }


        LinearLayoutManager elLayoutLineal= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        lalista.setLayoutManager(elLayoutLineal);

        ElAdaptadorRecycler eladaptador = new ElAdaptadorRecycler(nombres, posiciones, personajes, precios, this);
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
            Intent i = new Intent (this, MainActivity.class);
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

    @Override
    public void alpulsarSI() {

    }
    @Override
    public void alpulsarNO() {

    }

    public void dialogoComprar(){
        DialogFragment dialogoalerta= new DialogoTienda();
        dialogoalerta.show(getSupportFragmentManager(), "dialogComprar");
    }
}