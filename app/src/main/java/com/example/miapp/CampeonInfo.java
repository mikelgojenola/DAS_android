package com.example.miapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CampeonInfo extends AppCompatActivity implements DialogoTienda.ListenerdelDialogo {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campeon_info);

        Bundle extras = super.getIntent().getExtras();
        int image = extras.getInt("imagen");
        String n = extras.getString("nombre");
        String pos = extras.getString("posicion");

        ImageView iv = findViewById(R.id.imageView);
        iv.setImageResource(image);

        TextView tvNombre = findViewById(R.id.tvNombreComprar);
        tvNombre.setText(n);

        TextView tvPos = findViewById(R.id.tvPosicionComprar);
        tvPos.setText(pos);

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
    }

    @Override
    public void alpulsarSI() {

    }
    @Override
    public void alpulsarNO() {

    }
}