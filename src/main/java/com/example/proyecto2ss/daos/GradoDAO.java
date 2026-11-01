package com.example.proyecto2ss.daos;

import com.example.proyecto2ss.dtos.GradoDTO;
import com.example.proyecto2ss.modelos.Grado;
import com.example.proyecto2ss.util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradoDAO {

    public boolean crear (Grado grado){
        String sql = "INSERT INTO Grado(nombre, nivel, carrera_id, cuota_inscripcion, cuota_mensual) VALUES (?,?,?,?,?)";
        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt =  connection.prepareStatement(sql)){

            stmt.setString(1, grado.getNombre());
            stmt.setString(2, grado.getNivel());
            stmt.setObject(3, grado.getCarreraId(), Types.INTEGER);
            stmt.setDouble(4, grado.getCuotaInscripcion());
            stmt.setDouble(5, grado.getCuotaMensual());
            return stmt.executeUpdate() > 0;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    public List<Grado> obtenerTodos(){
        List<Grado> lista = new ArrayList<>();

        String sql = "SELECT * FROM Grado";

        try (Connection connection = ConexionDB.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){

           while (rs.next()){
               Grado grado = new Grado();

               grado.setId(rs.getInt("id"));
               grado.setNombre(rs.getString("nombre"));
               grado.setNivel(rs.getString("nivel"));
               grado.setCarreraId((Integer) rs.getObject("carrera_id"));
               grado.setCuotaInscripcion(rs.getDouble("cuota_inscripcion"));
               grado.setCuotaMensual(rs.getDouble("cuota_mensual"));

               lista.add(grado);
           }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public List<GradoDTO> obtenerDetalles(){
        List<GradoDTO> lista = new ArrayList<>();

        String sql = "SELECT g.id, g.nombre, g.nivel, c.nombre AS carrera, g.cuota_inscripcion, g.cuota_mensual " +
                "FROM Grado g " +
                "LEFT JOIN Carrera c ON g.carrera_id = c.id";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){

            while (rs.next()){
                GradoDTO gradoDTO = new GradoDTO();

                gradoDTO.setId(rs.getInt("id"));
                gradoDTO.setNombre(rs.getString("nombre"));
                gradoDTO.setNivel(rs.getString("nivel"));
                String carrera = rs.getString("carrera");
                if (carrera == null) {
                    gradoDTO.setCarrera("Sin carrera");
                } else {
                    gradoDTO.setCarrera(carrera);
                }

                gradoDTO.setCuotaInscripcion(rs.getDouble("cuota_inscripcion"));
                gradoDTO.setCuotaMensual(rs.getDouble("cuota_mensual"));

                lista.add(gradoDTO);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public boolean actualizar(Grado grado){
        String sql = "UPDATE Grado SET nombre=?, nivel=?, carrera_id=?, cuota_inscripcion=?, cuota_mensual=? WHERE id=?";
        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt =  connection.prepareStatement(sql)){

            stmt.setString(1, grado.getNombre());
            stmt.setString(2, grado.getNivel());
            stmt.setObject(3, grado.getCarreraId(), Types.INTEGER);
            stmt.setDouble(4, grado.getCuotaInscripcion());
            stmt.setDouble(5, grado.getCuotaMensual());
            stmt.setInt(6, grado.getId());
            return stmt.executeUpdate() > 0;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar (int id){
        String sql = "DELETE FROM Grado WHERE id=?";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt =  connection.prepareStatement(sql)){

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
