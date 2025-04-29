package com.example;

import com.example.utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String url  = "jdbc:mysql://localhost:3306/sakila?useSSL=false&serverTimezone=UTC";
        String user = "root";

        try (
            Connection conn = DatabaseConnection.getInstance(url, user);
            Statement  stmt = conn.createStatement();
            ResultSet  rs   = stmt.executeQuery("SELECT actor_id, first_name, last_name FROM actor")
        ) {
            while (rs.next()) {
                Integer id     = rs.getInt("actor_id");
                String  nombre = rs.getString("first_name") + " " + rs.getString("last_name");
                System.out.println("Actor ID: " + id + ", Nombre: " + nombre);
            }
            System.out.println("Conexion exitosa");
        } catch (SQLException e) {
            System.out.println("Error Conexion");
            e.printStackTrace();
        }
    }
}
