package com.mycompany.mvc.Dao;

import com.mycompany.mvc.bd.Conexion;
import com.mycompany.mvc.bd.Puesto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PuestoDao implements CrudDao<Puesto> {

    @Override
    public boolean insertar(Puesto objeto) {
        String sql = "INSERT INTO puestos (nombre, salario_base) VALUES (?, ?)";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, objeto.getNombre());
            ps.setFloat(2, objeto.getSalarioBase());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar puesto: ");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizar(Puesto objeto) {
        String sql = "UPDATE puestos SET nombre = ?, salario_base = ? WHERE id_puesto = ?";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, objeto.getNombre());
            ps.setFloat(2, objeto.getSalarioBase());
            ps.setInt(3, objeto.getIdPuesto());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar puesto: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM puestos WHERE id_puesto = ?";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar puesto: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Puesto buscarPorId(int id) {
        String sql = "SELECT id_puesto, nombre, salario_base FROM puestos WHERE id_puesto = ?";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Puesto(
                        rs.getInt("id_puesto"),
                        rs.getString("nombre"),
                        rs.getFloat("salario_base")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar puesto: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Puesto> listarTodos() {
        List<Puesto> lista = new ArrayList<>();
        String sql = "SELECT id_puesto, nombre, salario_base FROM puestos";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Puesto(
                    rs.getInt("id_puesto"),
                    rs.getString("nombre"),
                    rs.getFloat("salario_base")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar puestos: " + e.getMessage());
        }
        return lista;
    }
}