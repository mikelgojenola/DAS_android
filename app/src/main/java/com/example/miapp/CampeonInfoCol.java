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
        setContentView(R.layout.activity_campeon_info_col);

        Bundle extras = super.getIntent().getExtras();
        int image = extras.getInt("imagen");
        String n = extras.getString("nombre");
        String pos = extras.getString("posicion");
        String des = extras.getString("descripcion");

        ImageView iv = findViewById(R.id.ivFotoCol);
        iv.setImageResource(image);

        TextView tvNombre = findViewById(R.id.tvNombreCol);
        tvNombre.setText(n);

        TextView tvPos = findViewById(R.id.tvPosCol);
        tvPos.setText(pos);

        TextView tvDes = findViewById(R.id.tvDesCol);
        tvDes.setText(des);


        Button volver = findViewById(R.id.botonatrasCol);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (getApplicationContext(), ColeccionActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}