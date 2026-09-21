package com.mycompany.mvc.Dao;

import com.mycompany.mvc.bd.Conexion;
import com.mycompany.mvc.bd.Marca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarcaDao implements CrudDao<Marca> {

    @Override
    public boolean insertar(Marca objeto) {
        String sql = "INSERT INTO marcas (nombre) VALUES (?)";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, objeto.getNombre());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar marca: ");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizar(Marca objeto) {
        String sql = "UPDATE marcas SET nombre = ? WHERE id_marca = ?";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, objeto.getNombre());
            ps.setInt(2, objeto.getIdMarca());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar marca: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM marcas WHERE id_marca = ?";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar marca: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Marca buscarPorId(int id) {
        String sql = "SELECT id_marca, nombre FROM marcas WHERE id_marca = ?";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Marca(rs.getInt("id_marca"), rs.getString("nombre"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar marca: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Marca> listarTodos() {
        List<Marca> lista = new ArrayList<>();
        String sql = "SELECT id_marca, nombre FROM marcas";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Marca(rs.getInt("id_marca"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar marcas: " + e.getMessage());
        }
        return lista;
    }
}