/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import businessentity.reserva;
import conexion.conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class reservadao implements ibasedao<reserva> {

    @Override
    public void insertar(reserva reserva) {
        String sql = "INSERT INTO Reserva (idCliente, idHabitacion, fechaEntrada, fechaSalida) VALUES (?, ?, ?, ?)";
        try (Connection conn = conexion.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reserva.getIdCliente());
            stmt.setInt(2, reserva.getIdHabitacion());
            stmt.setString(3, reserva.getFechaEntrada());
            stmt.setString(4, reserva.getFechaSalida());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public reserva obtenerPorId(int id) {
        String sql = "SELECT * FROM Reserva WHERE idReserva = ?";
        reserva reserva = null;
        try (Connection conn = conexion.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                reserva = new reserva();
                reserva.setIdReserva(rs.getInt("idReserva"));
                reserva.setIdCliente(rs.getInt("idCliente"));
                reserva.setIdHabitacion(rs.getInt("idHabitacion"));
                reserva.setFechaEntrada(rs.getString("fechaEntrada"));
                reserva.setFechaSalida(rs.getString("fechaSalida"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reserva;
    }

    @Override
    public List<reserva> obtenerTodos() {
        String sql = "SELECT * FROM Reserva";
        List<reserva> reservas = new ArrayList<>();
        try (Connection conn = conexion.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                reserva reserva = new reserva();
                reserva.setIdReserva(rs.getInt("idReserva"));
                reserva.setIdCliente(rs.getInt("idCliente"));
                reserva.setIdHabitacion(rs.getInt("idHabitacion"));
                reserva.setFechaEntrada(rs.getString("fechaEntrada"));
                reserva.setFechaSalida(rs.getString("fechaSalida"));
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservas;
    }

    @Override
    public void actualizar(reserva reserva) {
        String sql = "UPDATE Reserva SET idCliente = ?, idHabitacion = ?, fechaEntrada = ?, fechaSalida = ? WHERE idReserva = ?";
        try (Connection conn = conexion.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reserva.getIdCliente());
            stmt.setInt(2, reserva.getIdHabitacion());
            stmt.setString(3, reserva.getFechaEntrada());
            stmt.setString(4, reserva.getFechaSalida());
            stmt.setInt(5, reserva.getIdReserva());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM Reserva WHERE idReserva = ?";
        try (Connection conn = conexion.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
