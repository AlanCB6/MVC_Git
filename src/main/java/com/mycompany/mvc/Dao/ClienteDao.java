package com.mycompany.mvc.Dao;

import com.mycompany.mvc.bd.Cliente;
import com.mycompany.mvc.bd.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao implements CrudDao<Cliente>{

    @Override
    public boolean insertar(Cliente objeto) {
        String sql = "INSERT INTO CLIENTES (nit, nombre, apellidos, email) VALUES (?,?,?,?)";
        try(Connection conn = Conexion.obtenerConexion();
            PreparedStatement ps = conn.prepareStatement(sql);)
        {
            ps.setString(1,objeto.getNit());
            ps.setString(2,objeto.getNombre());
            ps.setString(3,objeto.getApellidos());
            ps.setString(4,objeto.getEmail());
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            System.err.println("Error al momento de insertar un cliente");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizar(Cliente objeto) {
        String sql = "UPDATE clientes SET nit = ?, nombre = ?, apellidos = ?, email = ? WHERE id_cliente = ?";
        try{Connection conn = Conexion.obtenerConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,objeto.getNit());
            ps.setString(2,objeto.getNombre());
            ps.setString(3,objeto.getApellidos());
            ps.setString(4,objeto.getEmail());
            ps.setInt(5,objeto.getIdCliente());
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            System.err.println("Error al momento de actualizar el cliente");
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM clientes WHERE id_cliente = ?";
        try(Connection conn = Conexion.obtenerConexion();
            PreparedStatement ps = conn.prepareStatement(sql);)
        {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            System.err.println("Error al momento de eliminar el cliente");
            return false;
        }
    }

    @Override
    public Cliente buscarPorId(int id) {
          String sql = "SELECT id_cliente, nit, nombre, apellidos, email FROM clientes WHERE id_cliente = ?";
    try{
        Connection conn = Conexion.obtenerConexion();
        PreparedStatement ps = conn.prepareStatement (sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return mapearCliente(rs);
        }
    }catch (SQLException e){
        System.err.println("Error al buscar al cliente");
    }
    return null;
    }

    @Override
    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList();
        String sql = "SELECT id_cliente, nit, nombre, apellidos, email FROM clientes ";
    try{
        Connection conn = Conexion.obtenerConexion();
        PreparedStatement ps = conn.prepareStatement (sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            clientes.add(mapearCliente(rs));
        }
    }catch (SQLException e){
        System.err.println("Error al buscar al cliente");
    }
    return clientes;
    }
    
    private Cliente mapearCliente(ResultSet rs) throws SQLException{
        return new Cliente(
        rs.getInt("id_cliente"),
        rs.getString("nit"),
        rs.getString("nombre"),
        rs.getString("apellidos"),
        rs.getString("email")
        );
    }
}