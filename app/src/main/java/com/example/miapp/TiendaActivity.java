package com.example.miapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.database.Cursor;
import android.widget.Toast;

public class TiendaActivity extends AppCompatActivity implements DialogoTienda.ListenerdelDialogo{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tienda);

        setSupportActionBar(findViewById(R.id.labarra_tienda));

        RecyclerView lalista= findViewById(R.id.elreciclerview);

        // Conseguir los campeones que no están en nuestra propiedad, comprobando el atributo "comprado" en la base de datos
        Campeon[] campeones = getCampeonesParaVender();

        int[] personajes= {R.drawable.nasus, R.drawable.maokai, R.drawable.ahri, R.drawable.vayne, R.drawable.lulu};
        String[] nombres={};
        String[] posiciones={};
        int[] precios={};

        for (int i = 0; i<campeones.length; i++){
            nombres[i] = campeones[i].getNombre();
            posiciones[i] = campeones[i].getPosicion();
            precios[i] = campeones[i].getPrecio();
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

    public Campeon[] getCampeonesParaVender(){
        Campeon[] listaC = {};

        miBD GestorDB = new miBD (this, "NombreBD", null, 1);
        SQLiteDatabase bd = GestorDB.getReadableDatabase();

        Cursor cu = bd.rawQuery("SELECT Nombre,Posicion,Precio,Poder,Comprado FROM Campeones", null);

        int i = 0;
        while (cu.moveToNext()) {
            String nom = cu.getString(0);
            String pos = cu.getString(1);
            int precio = cu.getInt(2);
            int poder = cu.getInt(3);

            boolean comprado = false;
            int bit = cu.getInt(4);
            if(bit==1){ comprado = true; } // Si bit=0 significa que no está comprado, si bit=1 si está comprado

            listaC[i] = new Campeon(nom, pos, precio, poder, comprado); //Creamos el campeon con la info de la base de datos
            i++;
        }

        Toast.makeText(getApplicationContext(),"Mensaje: " + i,Toast.LENGTH_LONG).show();

        cu.close();
        bd.close();

        return listaC;
    }
}