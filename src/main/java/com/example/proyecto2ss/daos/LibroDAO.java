package com.example.proyecto2ss.daos;

import com.example.proyecto2ss.modelos.Libro;
import com.example.proyecto2ss.util.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {

    public boolean crearLibro(Libro libro){
        String sql = "INSERT INTO Libro (titulo, autor, editorial, cantidad_disponible) VALUES (?,?,?,?)";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setString(3, libro.getEditorial());
            stmt.setInt(4, libro.getCantidadDisponible());
            return stmt.executeUpdate() > 0;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    public List<Libro> obtenerTodos(){
        List<Libro> lista = new ArrayList<>();
        String sql = "SELECT * FROM Libro";

        try (Connection connection = ConexionDB.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){

            while (rs.next()){
                Libro libro = new Libro();

                libro.setId(rs.getInt("id"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setEditorial(rs.getString("editorial"));
                libro.setCantidadDisponible(rs.getInt("cantidad_disponible"));
                lista.add(libro);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public boolean actualizarLibro(Libro libro){
        String sql = "UPDATE Libro SET titulo=?, autor=?, editorial=?, cantidad_disponible=? WHERE id=?";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setString(3, libro.getEditorial());
            stmt.setInt(4, libro.getCantidadDisponible());
            stmt.setInt(5, libro.getId());
            return stmt.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarLibro(int id){
        String sql = "DELETE FROM Libro WHERE id=?";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }


}
