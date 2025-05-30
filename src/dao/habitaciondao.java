/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import businessentity.habitacion;
import conexion.conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class habitaciondao implements ibasedao<habitacion> {

    @Override
    public void insertar(habitacion habitacion) {
        String sql = "INSERT INTO Habitacion (tipo, precio, disponible) VALUES (?, ?, ?)";
        try (Connection conn = conexion.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, habitacion.getTipo());
            stmt.setDouble(2, habitacion.getPrecio());
            stmt.setBoolean(3, habitacion.isDisponible());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public habitacion obtenerPorId(int id) {
        String sql = "SELECT * FROM Habitacion WHERE idHabitacion = ?";
        habitacion habitacion = null;
        try (Connection conn = conexion.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                habitacion = new habitacion();
                habitacion.setIdHabitacion(rs.getInt("idHabitacion"));
                habitacion.setTipo(rs.getString("tipo"));
                habitacion.setPrecio(rs.getDouble("precio"));
                habitacion.setDisponible(rs.getBoolean("disponible"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return habitacion;
    }

    @Override
    public List<habitacion> obtenerTodos() {
        String sql = "SELECT * FROM Habitacion";
        List<habitacion> habitaciones = new ArrayList<>();
        try (Connection conn = conexion.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                habitacion habitacion = new habitacion();
                habitacion.setIdHabitacion(rs.getInt("idHabitacion"));
                habitacion.setTipo(rs.getString("tipo"));
                habitacion.setPrecio(rs.getDouble("precio"));
                habitacion.setDisponible(rs.getBoolean("disponible"));
                habitaciones.add(habitacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return habitaciones;
    }

    @Override
    public void actualizar(habitacion habitacion) {
        String sql = "UPDATE Habitacion SET tipo = ?, precio = ?, disponible = ? WHERE idHabitacion = ?";
        try (Connection conn = conexion.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, habitacion.getTipo());
            stmt.setDouble(2, habitacion.getPrecio());
            stmt.setBoolean(3, habitacion.isDisponible());
            stmt.setInt(4, habitacion.getIdHabitacion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM Habitacion WHERE idHabitacion = ?";
        try (Connection conn = conexion.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
