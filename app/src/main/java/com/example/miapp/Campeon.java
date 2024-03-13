package com.example.miapp;

public class Campeon {
    private String nombre;
    private String posicion;
    private int precio;
    private int poder;
    private boolean comprado;

    public Campeon(String pNombre, String pPos, int pPrecio, int pPoder, boolean pComprado){
        nombre = pNombre;
        posicion = pPos;
        precio = pPrecio;
        poder = pPoder;
        comprado = pComprado;
    }

    public String getNombre(){ return nombre; }
    public String getPosicion(){ return posicion; }
    public int getPrecio(){ return precio; }
    public int getPoder(){ return poder; }
    public boolean estaComprado(){ return comprado; }


}
