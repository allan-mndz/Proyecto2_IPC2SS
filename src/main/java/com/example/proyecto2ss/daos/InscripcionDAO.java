package com.example.proyecto2ss.daos;

import com.example.proyecto2ss.dtos.InscripcionDTO;
import com.example.proyecto2ss.modelos.Inscripcion;
import com.example.proyecto2ss.util.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InscripcionDAO {

    public boolean crearInscripcion(Inscripcion inscripcion){
        String sql = "INSERT INTO Inscripcion(estudiante_id, grado_id, seccion_id, ciclo_escolar_id, fecha_inscripcion) VALUES (?,?,?,?,?)";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setInt(1, inscripcion.getEstudianteId());
            stmt.setInt(2, inscripcion.getGradoId());
            stmt.setInt(3, inscripcion.getSeccionId());
            stmt.setInt(4, inscripcion.getCicloEscolarId());
            stmt.setString(5, inscripcion.getFechaInscripcion());
            return stmt.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Inscripcion> obtenerTodos(){
        List<Inscripcion> lista = new ArrayList<>();
        String sql = "SELECT * FROM Inscripcion";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){

            while (rs.next()){
                Inscripcion inscripcion = new Inscripcion();

                inscripcion.setId(rs.getInt("id"));
                inscripcion.setEstudianteId(rs.getInt("estudiante_id"));
                inscripcion.setGradoId(rs.getInt("grado_id"));
                inscripcion.setSeccionId(rs.getInt("seccion_id"));
                inscripcion.setCicloEscolarId(rs.getInt("ciclo_escolar_id"));
                inscripcion.setFechaInscripcion(rs.getString("fecha_inscripcion"));
                lista.add(inscripcion);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }


    public List<InscripcionDTO> obtenerDetalles() {
        List<InscripcionDTO> lista = new ArrayList<>();

        String sql = "SELECT i.id, CONCAT(e.nombres, ' ', e.apellidos) AS estudiante, " +
                "g.nombre AS grado, s.nombre AS seccion, c.anio AS ciclo, i.fecha_inscripcion " +
                "FROM Inscripcion i " +
                "INNER JOIN Estudiante e ON i.estudiante_id = e.id " +
                "INNER JOIN Grado g ON i.grado_id = g.id " +
                "INNER JOIN Seccion s ON i.seccion_id = s.id " +
                "INNER JOIN Ciclo_escolar c ON i.ciclo_escolar_id = c.id";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                InscripcionDTO dto = new InscripcionDTO();
                dto.setId(rs.getInt("id"));
                dto.setEstudiante(rs.getString("estudiante"));
                dto.setGrado(rs.getString("grado"));
                dto.setSeccion(rs.getString("seccion"));
                dto.setCiclo(rs.getString("ciclo"));
                dto.setFechaInscripcion(rs.getString("fecha_inscripcion"));

                lista.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean actualizar(Inscripcion inscripcion){
        String sql = "UPDATE Inscripcion SET estudiante_id=?, grado_id=?, seccion_id=?, ciclo_escolar_id=?, fecha_inscripcion=? WHERE id=?";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setInt(1, inscripcion.getEstudianteId());
            stmt.setInt(2, inscripcion.getGradoId());
            stmt.setInt(3, inscripcion.getSeccionId());
            stmt.setInt(4, inscripcion.getCicloEscolarId());
            stmt.setString(5, inscripcion.getFechaInscripcion());
            stmt.setInt(6, inscripcion.getId());
            return stmt.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id){
        String sql = "DELETE FROM Inscripcion WHERE id=?";

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
