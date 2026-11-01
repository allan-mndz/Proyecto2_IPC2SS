package com.example.proyecto2ss.daos;

import com.example.proyecto2ss.dtos.PrestamoDTO;
import com.example.proyecto2ss.modelos.Prestamo;
import com.example.proyecto2ss.util.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {

    public boolean crear(Prestamo prestamo) {
        String sql = "INSERT INTO Prestamo (libro_id, usuario_id, fecha_prestamo, fecha_esperada_devolucion) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, prestamo.getLibroId());
            stmt.setInt(2, prestamo.getUsuarioId());
            stmt.setString(3, prestamo.getFechaPrestamo());
            stmt.setString(4, prestamo.getFechaEsperadaDevolucion());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Prestamo> obtenerTodos() {
        List<Prestamo> lista = new ArrayList<>();
        String sql = "SELECT * FROM Prestamo";
        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Prestamo p = new Prestamo();
                p.setId(rs.getInt("id"));
                p.setLibroId(rs.getInt("libro_id"));
                p.setUsuarioId(rs.getInt("usuario_id"));
                p.setFechaPrestamo(rs.getString("fecha_prestamo"));
                p.setFechaEsperadaDevolucion(rs.getString("fecha_esperada_devolucion"));
                p.setFechaDevolucionReal(rs.getString("fecha_devolucion_real"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<PrestamoDTO> obtenerDetalles() {
        List<PrestamoDTO> lista = new ArrayList<>();
        String sql = "SELECT p.id, l.titulo AS libro, u.username AS usuario, " +
                "p.fecha_prestamo AS fechaPrestamo, p.fecha_esperada_devolucion AS fechaEsperadaDevolucion, " +
                "p.fecha_devolucion_real AS fechaDevolucionReal " +
                "FROM Prestamo p " +
                "INNER JOIN Libro l ON p.libro_id = l.id " +
                "INNER JOIN Usuario u ON p.usuario_id = u.id";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                PrestamoDTO dto = new PrestamoDTO();
                dto.setId(rs.getInt("id"));
                dto.setLibro(rs.getString("libro"));
                dto.setUsuario(rs.getString("usuario"));
                dto.setFechaPrestamo(rs.getString("fechaPrestamo"));
                dto.setFechaEsperadaDevolucion(rs.getString("fechaEsperadaDevolucion"));

                // Si el libro no ha sido devuelto, la fecha real es nula
                String fechaReal = rs.getString("fechaDevolucionReal");
                if (fechaReal == null) {
                    dto.setFechaDevolucionReal("Pendiente");
                } else {
                    dto.setFechaDevolucionReal(fechaReal);
                }

                lista.add(dto);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public boolean actualizar(Prestamo prestamo) {
        String sql = "UPDATE Prestamo SET fecha_devolucion_real=? WHERE id=?";
        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, prestamo.getFechaDevolucionReal());
            stmt.setInt(2, prestamo.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}