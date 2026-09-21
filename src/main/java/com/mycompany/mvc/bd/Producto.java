package com.mycompany.mvc.bd;

public class Producto {
    private int idProducto;
    private int idMarca;
    private String nombre;
    private float precio;
    private int stock;

    public Producto() {}

    public Producto(int idProducto, int idMarca, String nombre, float precio, int stock) {
        this.idProducto = idProducto;
        this.idMarca = idMarca;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public int getIdMarca() { return idMarca; }
    public void setIdMarca(int idMarca) { this.idMarca = idMarca; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public float getPrecio() { return precio; }
    public void setPrecio(float precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}