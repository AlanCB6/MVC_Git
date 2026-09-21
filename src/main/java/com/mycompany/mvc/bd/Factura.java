package com.mycompany.mvc.bd;

import java.sql.Timestamp;

public class Factura {
    private int idFactura;
    private int idCliente;
    private int idEmpleado;
    private Timestamp fecha;
    private float total;

    public Factura() {}

    public Factura(int idFactura, int idCliente, int idEmpleado, Timestamp fecha, float total) {
        this.idFactura = idFactura;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.fecha = fecha;
        this.total = total;
    }

    public int getIdFactura() { return idFactura; }
    public void setIdFactura(int idFactura) { this.idFactura = idFactura; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public int getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(int idEmpleado) { this.idEmpleado = idEmpleado; }

    public Timestamp getFecha() { return fecha; }
    public void setFecha(Timestamp fecha) { this.fecha = fecha; }

    public float getTotal() { return total; }
    public void setTotal(float total) { this.total = total; }
}