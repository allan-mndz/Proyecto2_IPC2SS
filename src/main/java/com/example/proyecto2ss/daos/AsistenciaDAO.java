package com.example.proyecto2ss.daos;

import com.example.proyecto2ss.dtos.AsistenciaDTO;
import com.example.proyecto2ss.modelos.Asistencia;
import com.example.proyecto2ss.util.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AsistenciaDAO {

    public boolean crearAsistencia(Asistencia asistencia) {
        String sql = "INSERT INTO Asistencia(inscripcion_id, curso_id, fecha, presente) VALUES (?,?,?,?)";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, asistencia.getInscripcionId());
            stmt.setInt(2, asistencia.getCursoId());
            stmt.setString(3, asistencia.getFecha());
            stmt.setInt(4, asistencia.getPresente());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Asistencia> obtenerTodas() {
        List<Asistencia> lista = new ArrayList<>();
        String sql = "SELECT * FROM Asistencia";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Asistencia asistencia = new Asistencia();
                asistencia.setId(rs.getInt("id"));
                asistencia.setInscripcionId(rs.getInt("inscripcion_id"));
                asistencia.setCursoId(rs.getInt("curso_id"));
                asistencia.setFecha(rs.getString("fecha"));
                asistencia.setPresente(rs.getInt("presente"));
                lista.add(asistencia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<AsistenciaDTO> obtenerDetalles() {
        List<AsistenciaDTO> lista = new ArrayList<>();

        String sql = "SELECT a.id, CONCAT(e.nombres, ' ', e.apellidos) AS inscripcion, " +
                "c.nombre AS curso, a.fecha, a.presente " +
                "FROM Asistencia a " +
                "INNER JOIN Inscripcion i ON a.inscripcion_id = i.id " +
                "INNER JOIN Estudiante e ON i.estudiante_id = e.id " +
                "INNER JOIN Curso c ON a.curso_id = c.id";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                AsistenciaDTO dto = new AsistenciaDTO();
                dto.setId(rs.getInt("id"));
                dto.setInscripcion(rs.getString("inscripcion"));
                dto.setCurso(rs.getString("curso"));
                dto.setFecha(rs.getString("fecha"));
                dto.setPresente(rs.getInt("presente"));

                lista.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean actualizar(Asistencia asistencia) {
        String sql = "UPDATE Asistencia SET inscripcion_id=?, curso_id=?, fecha=?, presente=? WHERE id=?";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, asistencia.getInscripcionId());
            stmt.setInt(2, asistencia.getCursoId());
            stmt.setString(3, asistencia.getFecha());
            stmt.setInt(4, asistencia.getPresente());
            stmt.setInt(5, asistencia.getId());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM Asistencia WHERE id=?";

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