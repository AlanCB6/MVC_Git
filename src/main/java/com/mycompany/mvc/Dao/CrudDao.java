package com.mycompany.mvc.Dao;
import java.util.List;

public interface CrudDao<T> {
    boolean insertar(T objeto);
    boolean actualizar(T objeto);
    boolean eliminar(int id);
    T buscarPorId(int id);
    List<T> listarTodos();   
}
