package com.example.proyecto2ss.daos;

import com.example.proyecto2ss.modelos.Seccion;
import com.example.proyecto2ss.util.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeccionDAO {

    public boolean crear (Seccion seccion){
        String sql = "INSERT INTO Seccion (nombre) VALUES (?)";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setString(1, seccion.getNombre());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public List<Seccion> obtenerTodos(){
        List<Seccion> lista = new ArrayList<>();
        String sql = "SELECT * FROM Seccion";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()){
                Seccion seccion = new Seccion();

                seccion.setId(rs.getInt("id"));
                seccion.setNombre(rs.getString("nombre"));
                lista.add(seccion);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public boolean actualizar(Seccion seccion){
        String sql = "UPDATE Seccion SET nombre=? WHERE id=?";

        try (Connection connection = ConexionDB.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setString(1, seccion.getNombre());
            stmt.setInt(2, seccion.getId());
            return stmt.executeUpdate() > 0;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    public boolean eliminar (int id){
        String sql = "DELETE FROM Seccion WHERE id=?";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0 ;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
