package com.example.proyecto2ss.daos;

import com.example.proyecto2ss.modelos.Curso;
import com.example.proyecto2ss.util.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    public boolean crear (Curso curso){
        String sql = "INSERT INTO Curso (nombre) VALUES (?)";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setString(1, curso.getNombre());
            return stmt.executeUpdate() > 0;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Curso> obtenerTodos(){
        List<Curso> lista = new ArrayList<>();
        String sql = "SELECT * FROM Curso";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){

            while (rs.next()){
                Curso curso = new Curso();

                curso.setId(rs.getInt("id"));
                curso.setNombre(rs.getString("nombre"));
                lista.add(curso);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;

    }

    public boolean actualizar(Curso curso){
        String sql = "UPDATE Curso SET nombre=? WHERE id = ?";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, curso.getNombre());
            stmt.setInt(2, curso.getId());
            return stmt.executeUpdate() > 0;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    public boolean eliminar (int id){
        String sql = "DELETE FROM Curso WHERE id = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
