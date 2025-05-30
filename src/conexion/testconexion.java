/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import java.sql.Connection;

public class testconexion {
    public static void main(String[] args) {
        Connection conn = conexion.obtenerConexion();
        if (conn != null) {
            System.out.println("¡Conexion exitosa!");
        } else {
            System.out.println("No se pudo conectar.");
        }
    }
}

