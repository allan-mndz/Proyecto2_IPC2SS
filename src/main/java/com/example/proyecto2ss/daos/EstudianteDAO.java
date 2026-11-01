package com.example.proyecto2ss.daos;

import com.example.proyecto2ss.dtos.EstudianteDTO;
import com.example.proyecto2ss.modelos.Estudiante;
import com.example.proyecto2ss.util.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {

    // Create
    public boolean crear(Estudiante estudiante){
        String sql = "INSERT INTO Estudiante (usuario_id, nombres, apellidos, informacion_medica, nombre_encargado, telefono_encargado, estado) VALUES (?,?,?,?,?,?,?)";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setInt(1, estudiante.getUsuarioId());
            stmt.setString(2, estudiante.getNombres());
            stmt.setString(3, estudiante.getApellidos());
            stmt.setString(4, estudiante.getInformacionMedica());
            stmt.setString(5, estudiante.getNombreEncargado());
            stmt.setString(6, estudiante.getTelefonoEncargado());
            stmt.setString(7, estudiante.getEstado());

            return stmt.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    // Read
    public List<Estudiante> obtenerTodos() {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM Estudiante";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){

            while (rs.next()){
                Estudiante estudiante = new Estudiante();

                estudiante.setId(rs.getInt("id"));
                estudiante.setUsuarioId(rs.getInt("usuario_id"));
                estudiante.setNombres(rs.getString("nombres"));
                estudiante.setApellidos(rs.getString("apellidos"));
                estudiante.setInformacionMedica(rs.getString("informacion_medica"));
                estudiante.setNombreEncargado(rs.getString("nombre_encargado"));
                estudiante.setTelefonoEncargado(rs.getString("telefono_encargado"));
                estudiante.setEstado(rs.getString("estado"));
                lista.add(estudiante); // Agregamos cada estudiante a la lista
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public List<EstudianteDTO> obtenerDetales(){
        List<EstudianteDTO> lista = new ArrayList<>();

        String sql = "SELECT e.id, e.nombres, e.apellidos, e.informacion_medica, e.nombre_encargado, e.telefono_encargado, e.estado, u.username AS nombre_usuario " +
                "FROM Estudiante e " +
                "INNER JOIN Usuario u ON e.usuario_id = u.id";

        try (Connection connection = ConexionDB.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){

            while (rs.next()){
                EstudianteDTO estudianteDTO = new EstudianteDTO();

                estudianteDTO.setId(rs.getInt("id"));
                estudianteDTO.setNombres(rs.getString("nombres"));
                estudianteDTO.setApellidos(rs.getString("apellidos"));
                estudianteDTO.setInformacionMedica("informacion_medica");
                estudianteDTO.setTelefonoEncargado("telefono_encargado");
                estudianteDTO.setNombreEncargado("telefono_encargado");
                estudianteDTO.setEstado(rs.getString("estado"));
                estudianteDTO.setNombreUsuario(rs.getString("nombre_usuario"));

                lista.add(estudianteDTO);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    // Update
    public boolean actualizar (Estudiante estudiante) {
        String sql = "UPDATE Estudiante SET nombres=?, apellidos=?, informacion_medica=?, nombre_encargado=?, telefono_encargado=?, estado=? WHERE id=?";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, estudiante.getNombres());
            stmt.setString(2, estudiante.getApellidos());
            stmt.setString(3, estudiante.getInformacionMedica());
            stmt.setString(4, estudiante.getNombreEncargado());
            stmt.setString(5, estudiante.getTelefonoEncargado());
            stmt.setString(6, estudiante.getEstado());
            stmt.setInt(7, estudiante.getId());

            return stmt.executeUpdate() > 0;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    // "Delete" solo para cambiar el estado

    public boolean cambiarEstado(int id){

        String sql = "UPDATE Estudiante SET estado = 'Inactivo' WHERE id=?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}