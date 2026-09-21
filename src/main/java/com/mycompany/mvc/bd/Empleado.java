package com.mycompany.mvc.bd;

import java.sql.Date;

public class Empleado extends Persona {
    private int idEmpleado;
    private int idPuesto;
    private Date fechaContratacion;

    public Empleado() {}

    public Empleado(int idEmpleado, int idPuesto, String nombre, String apellidos, Date fechaContratacion) {
        super(nombre, apellidos);
        this.idEmpleado = idEmpleado;
        this.idPuesto = idPuesto;
        this.fechaContratacion = fechaContratacion;
    }

    public int getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(int idEmpleado) { this.idEmpleado = idEmpleado; }

    public int getIdPuesto() { return idPuesto; }
    public void setIdPuesto(int idPuesto) { this.idPuesto = idPuesto; }

    public Date getFechaContratacion() { return fechaContratacion; }
    public void setFechaContratacion(Date fechaContratacion) { this.fechaContratacion = fechaContratacion; }

    @Override
    public String toString() {
        return "{" +
                "id= " + idEmpleado +
                ", nombre= '" + nombre + '\'' +
                ", apellidos= '" + apellidos + '\'' +
                ", idPuesto= " + idPuesto +
                ", fechaContratacion= " + fechaContratacion +
                '}';
    }
}