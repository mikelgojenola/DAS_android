package com.example.miapp;

public class Campeon {
    private String nombre;
    private String descripcion;
    private String posicion;
    private int precio;
    private int poder;
    private boolean comprado;
    private int img;

    public Campeon(String pNombre, String pDes, String pPos, int pPrecio, int pPoder, boolean pComprado, int pImg){
        nombre = pNombre;
        descripcion = pDes;
        posicion = pPos;
        precio = pPrecio;
        poder = pPoder;
        comprado = pComprado;
        img = pImg;
    }

    public String getNombre(){ return nombre; }
    public String getDescripcion(){ return descripcion; }
    public String getPosicion(){ return posicion; }
    public int getPrecio(){ return precio; }
    public int getPoder(){ return poder; }
    public boolean estaComprado(){ return comprado; }
    public int getImg(){ return img; }


}
