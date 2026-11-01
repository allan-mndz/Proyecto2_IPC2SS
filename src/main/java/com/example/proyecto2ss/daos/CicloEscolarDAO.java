package com.example.proyecto2ss.daos;

import com.example.proyecto2ss.modelos.CicloEscolar;
import com.example.proyecto2ss.util.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CicloEscolarDAO {

    public boolean crear(CicloEscolar ciclo) {
        // Asumimos que al crearlo entra como 'Inactivo' hasta que el admin decida activarlo
        String sql = "INSERT INTO Ciclo_escolar (anio, activo) VALUES (?, 'Inactivo')";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, ciclo.getAnio());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<CicloEscolar> obtenerTodos() {
        List<CicloEscolar> lista = new ArrayList<>();
        String sql = "SELECT * FROM Ciclo_escolar";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CicloEscolar ciclo = new CicloEscolar();
                ciclo.setId(rs.getInt("id"));
                ciclo.setAnio(rs.getInt("anio"));
                ciclo.setActivo(rs.getString("estado"));

                lista.add(ciclo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean actualizar(CicloEscolar ciclo) {
        String sql = "UPDATE Ciclo_escolar SET anio=?, activo=? WHERE id=?";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, ciclo.getAnio());
            stmt.setString(2, ciclo.getActivo());
            stmt.setInt(3, ciclo.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM Ciclo_escolar WHERE id=?";

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