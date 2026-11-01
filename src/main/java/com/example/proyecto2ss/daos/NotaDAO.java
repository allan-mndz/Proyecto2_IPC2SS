package com.example.proyecto2ss.daos;

import com.example.proyecto2ss.dtos.NotaDTO;
import com.example.proyecto2ss.modelos.Nota;
import com.example.proyecto2ss.util.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotaDAO {

    public boolean crearNota(Nota nota) {
        String sql = "INSERT INTO Nota(inscripcion_id, curso_id, actividad, ponderacion, punteo_obtenido) VALUES (?,?,?,?,?)";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, nota.getInscripcionId());
            stmt.setInt(2, nota.getCursoId());
            stmt.setString(3, nota.getActividad());
            stmt.setDouble(4, nota.getPonderacion());
            stmt.setDouble(5, nota.getPunteoObtenido());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Nota> obtenerTodas() {
        List<Nota> lista = new ArrayList<>();
        String sql = "SELECT * FROM Nota";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Nota nota = new Nota();
                nota.setId(rs.getInt("id"));
                nota.setInscripcionId(rs.getInt("inscripcion_id"));
                nota.setCursoId(rs.getInt("curso_id"));
                nota.setActividad(rs.getString("actividad"));
                nota.setPonderacion(rs.getDouble("ponderacion"));
                nota.setPunteoObtenido(rs.getDouble("punteo_obtenido"));
                lista.add(nota);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<NotaDTO> obtenerDetalles() {
        List<NotaDTO> lista = new ArrayList<>();
        String sql = "SELECT n.id, CONCAT(e.nombres, ' ', e.apellidos) AS inscripcion, " +
                "c.nombre AS curso, n.actividad, n.ponderacion, n.punteo_obtenido AS punteoObtenido " +
                "FROM Nota n " +
                "INNER JOIN Inscripcion i ON n.inscripcion_id = i.id " +
                "INNER JOIN Estudiante e ON i.estudiante_id = e.id " +
                "INNER JOIN Curso c ON n.curso_id = c.id";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                NotaDTO dto = new NotaDTO();
                dto.setId(rs.getInt("id"));
                dto.setInscripcion(rs.getString("inscripcion"));
                dto.setCurso(rs.getString("curso"));
                dto.setActividad(rs.getString("actividad"));
                dto.setPonderacion(rs.getDouble("ponderacion"));
                dto.setPunteoObtenido(rs.getDouble("punteoObtenido"));
                lista.add(dto);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public boolean actualizar(Nota nota) {
        String sql = "UPDATE Nota SET inscripcion_id=?, curso_id=?, actividad=?, ponderacion=?, punteo_obtenido=? WHERE id=?";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, nota.getInscripcionId());
            stmt.setInt(2, nota.getCursoId());
            stmt.setString(3, nota.getActividad());
            stmt.setDouble(4, nota.getPonderacion());
            stmt.setDouble(5, nota.getPunteoObtenido());
            stmt.setInt(6, nota.getId());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM Nota WHERE id=?";

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