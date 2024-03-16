package com.example.miapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CampeonInfoCol extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campeon_info);

        Bundle extras = super.getIntent().getExtras();
        int image = extras.getInt("imagen");
        String n = extras.getString("nombre");
        String des = extras.getString("descripcion");

        ImageView iv = findViewById(R.id.ivFotoTienda);
        iv.setImageResource(image);

        TextView tvNombre = findViewById(R.id.tvNombreTienda);
        tvNombre.setText(n);

        TextView tvDes = findViewById(R.id.tvPosTienda);
        tvDes.setText(des);

        Button bcomprar = findViewById(R.id.botonComprar);
        bcomprar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AdminDB adb = AdminDB.getMiADB(getApplicationContext(), 1);
                if(adb.estaComprado(n, getApplicationContext())){
                    Toast.makeText(getApplicationContext(), "Ya tienes este campeon en tu propiedad", Toast.LENGTH_SHORT).show();
                }
                else {
                    DialogoTienda dialogoalerta = new DialogoTienda();
                    dialogoalerta.show(getSupportFragmentManager(), "dialogComprar");
                }
            }
        });

        Button volver = findViewById(R.id.botonatrasTienda);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (getApplicationContext(), TiendaActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}