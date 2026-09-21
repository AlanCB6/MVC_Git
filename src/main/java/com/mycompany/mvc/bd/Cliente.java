package com.mycompany.mvc.bd;

public class Cliente extends Persona {
    private int idCliente;
    private String nit;
    private String email;

    public Cliente() {}

    public Cliente(int idCliente, String nit, String nombre, String apellidos, String email) {
        super(nombre, apellidos);
        this.idCliente = idCliente;
        this.nit = nit;
        this.email = email;
    }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getNit() { return nit; }
    public void setNit(String nit) { this.nit = nit; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}