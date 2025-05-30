/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;

public interface ibasedao<T> {
    void insertar(T t);
    T obtenerPorId(int id);
    List<T> obtenerTodos();
    void actualizar(T t);
    void eliminar(int id);
}
