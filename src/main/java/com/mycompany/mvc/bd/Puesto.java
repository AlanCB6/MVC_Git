package com.mycompany.mvc.bd;

public class Puesto {
    private int idPuesto;
    private String nombre;
    private float salarioBase;

    public Puesto() {}

    public Puesto(int idPuesto, String nombre, float salarioBase) {
        this.idPuesto = idPuesto;
        this.nombre = nombre;
        this.salarioBase = salarioBase;
    }

    public int getIdPuesto() { return idPuesto; }
    public void setIdPuesto(int idPuesto) { this.idPuesto = idPuesto; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public float getSalarioBase() { return salarioBase; }
    public void setSalarioBase(float salarioBase) { this.salarioBase = salarioBase; }
}