package com.example.miapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class TiendaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int[] personajes={R.drawable.nasus_0};
        String[] nombres={"Nasus"};
        String[] posiciones={"Top"};

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tienda);

        setSupportActionBar(findViewById(R.id.labarra_tienda));

        ListView simpsons= (ListView) findViewById(R.id.laLista);
        AdaptadorListView eladap= new AdaptadorListView(getApplicationContext(),nombres,personajes,posiciones);
        simpsons.setAdapter(eladap);
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