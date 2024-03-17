package com.example.miapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private Handler handler;
    private Runnable runnable;
    private boolean bloqueado = false;
    private long tiempoParaEjecucion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar barra = findViewById(R.id.labarra);
        setSupportActionBar(barra);

        ImageView imagen = findViewById(R.id.imageView2);
        imagen.setImageResource(R.drawable.imgportada);

        Button pagina = findViewById(R.id.boton_pagina);
        pagina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNotification();
            }
        });

        Bundle extras=getIntent().getExtras();
        if (extras!=null){
            int id=extras.getInt("id");
            NotificationManager elManager = (NotificationManager)
                    getSystemService(Context.NOTIFICATION_SERVICE);
            elManager.cancel(id);
        }

        Button actividad = findViewById(R.id.boton_actividad);
        actividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bloqueado){
                    Toast.makeText(MainActivity.this, "Todavía está en proceso", Toast.LENGTH_SHORT).show();
                }
                else {
                    bloqueado = true;
                    // Inicia la ejecución del Runnable
                    tiempoParaEjecucion = System.currentTimeMillis() + 12000; // Ejecutar después de 12 segundos (12000 milisegundos)
                    handler.postDelayed(runnable, 1000); // Empezar a enseñar el tiempo restante cada segundo
                }
            }
        });

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if(bloqueado) {
                    long currentTime = System.currentTimeMillis();
                    long timeRemaining = tiempoParaEjecucion - currentTime;

                    // Actualizar el TextView con el tiempo restante, y comprobar si ya ha pasado el tiempo
                    actualizarTimer(timeRemaining, actividad);

                    handler.postDelayed(runnable, 1000); // Empezar a enseñar el tiempo restante cada segundo
                }
            }
        };

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
            Intent i = new Intent (this, ColeccionActivity.class);
            startActivity(i);
        }
        else if(id == R.id.opcion_tienda){
            Intent i = new Intent (this, TiendaActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    private void actualizarTimer(long timeRemaining, Button b) {
        if (timeRemaining > 0) {
            long segundos = timeRemaining / 1000;
            long minutos = segundos / 60;

            segundos = segundos % 60;
            minutos = minutos % 60;

            String tiempoRestante = String.format("%02d:%02d", minutos, segundos);
            b.setText(tiempoRestante);
        } else {
            // Acción a realizar cuando pase el tiempo que se especifica
            Toast.makeText(MainActivity.this, "¡Has conseguido 50 monedas!", Toast.LENGTH_SHORT).show();
            AdminDB adb = AdminDB.getMiADB(getApplicationContext(), 1);
            adb.sumar50();

            b.setText("Conseguir monedas");
            bloqueado = false;
        }
    }

    private void createNotification(){
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)!= PackageManager.PERMISSION_GRANTED) {
            //PEDIR EL PERMISO
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 11);
        }

        NotificationManager elManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder elBuilder = new NotificationCompat.Builder(this, "IdCanal");


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel elCanal = new NotificationChannel("IdCanal", "NombreCanal", NotificationManager.IMPORTANCE_DEFAULT);
            //Configuración del canal
            elCanal.setDescription("Descripción del canal");
            elCanal.enableLights(true);
            elCanal.setLightColor(Color.RED);
            elCanal.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            elCanal.enableVibration(true);

            elManager.createNotificationChannel(elCanal);
        }

        Intent volver = new Intent (this, MainActivity.class);
        volver.putExtra("id",1);
        PendingIntent intentVolver = PendingIntent.getActivity(this, 0, volver,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        Intent salir = new Intent(Intent.ACTION_VIEW, Uri.parse("https://universe.leagueoflegends.com/es_AR/champions/"));
        PendingIntent intentSalir = PendingIntent.getActivity(this, 0, salir,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        elBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ahri))
                .setSmallIcon(android.R.drawable.stat_sys_warning)
                .setContentTitle("Salir de la aplicación")
                .setContentText("Esta acción te sacará de la aplicación")
                .setSubText("Información extra")
                .setVibrate(new long[]{0, 1000, 500, 1000})
                .setAutoCancel(true)
                .addAction(android.R.drawable.sym_action_call,"Volver",intentVolver)
                .addAction(android.R.drawable.sym_action_call,"Continuar",intentSalir);

        elManager.notify(1, elBuilder.build());
    }

}