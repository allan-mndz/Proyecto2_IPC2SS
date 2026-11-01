package com.example.proyecto2ss.daos;

import com.example.proyecto2ss.modelos.Carrera;
import com.example.proyecto2ss.util.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarreraDAO {

    public boolean crear(Carrera carrera) {

        String sql = "INSERT INTO Carrera (nombre, activo) VALUES (?, 'Activo')";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, carrera.getNombre());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Carrera> obtenerTodas() {
        List<Carrera> lista = new ArrayList<>();
        String sql = "SELECT * FROM Carrera";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Carrera carrera = new Carrera();
                carrera.setId(rs.getInt("id"));
                carrera.setNombre(rs.getString("nombre"));
                carrera.setActivo(rs.getString("estado"));

                lista.add(carrera);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean actualizar(Carrera carrera) {
        String sql = "UPDATE Carrera SET nombre=?, activo=? WHERE id=?";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, carrera.getNombre());
            stmt.setString(2, carrera.getActivo());
            stmt.setInt(3, carrera.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean desactivar(int id) {
        String sql = "UPDATE Carrera SET activo='Inactivo' WHERE id=?";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}