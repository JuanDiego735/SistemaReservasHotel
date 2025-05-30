/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import businessentity.factura;
import conexion.conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class facturadao implements ibasedao<factura> {

    @Override
    public void insertar(factura factura) {
        String sql = "INSERT INTO Factura (idReserva, montoTotal, fechaEmision) VALUES (?, ?, ?)";
        try (Connection conn = conexion.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, factura.getIdReserva());
            stmt.setDouble(2, factura.getMontoTotal());
            stmt.setString(3, factura.getFechaEmision());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public factura obtenerPorId(int id) {
        String sql = "SELECT * FROM Factura WHERE idFactura = ?";
        factura factura = null;
        try (Connection conn = conexion.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                factura = new factura();
                factura.setIdFactura(rs.getInt("idFactura"));
                factura.setIdReserva(rs.getInt("idReserva"));
                factura.setMontoTotal(rs.getDouble("montoTotal"));
                factura.setFechaEmision(rs.getString("fechaEmision"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return factura;
    }

    @Override
    public List<factura> obtenerTodos() {
        String sql = "SELECT * FROM Factura";
        List<factura> facturas = new ArrayList<>();
        try (Connection conn = conexion.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                factura factura = new factura();
                factura.setIdFactura(rs.getInt("idFactura"));
                factura.setIdReserva(rs.getInt("idReserva"));
                factura.setMontoTotal(rs.getDouble("montoTotal"));
                factura.setFechaEmision(rs.getString("fechaEmision"));
                facturas.add(factura);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facturas;
    }

    @Override
    public void actualizar(factura factura) {
        String sql = "UPDATE Factura SET idReserva = ?, montoTotal = ?, fechaEmision = ? WHERE idFactura = ?";
        try (Connection conn = conexion.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, factura.getIdReserva());
            stmt.setDouble(2, factura.getMontoTotal());
            stmt.setString(3, factura.getFechaEmision());
            stmt.setInt(4, factura.getIdFactura());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM Factura WHERE idFactura = ?";
        try (Connection conn = conexion.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
