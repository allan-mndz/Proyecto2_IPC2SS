package com.example.proyecto2ss.daos;

import com.example.proyecto2ss.dtos.*;
import com.example.proyecto2ss.util.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteDAO {

    public List<ReporteIngresoDTO> obtenerIngresos(String fechaInicio, String fechaFin) {
        List<ReporteIngresoDTO> lista = new ArrayList<>();
        String sql;

        // Miramos si angular nos envió las fechas o si vienen vacías
        boolean filtrarFechas = (fechaInicio != null && !fechaInicio.trim().isEmpty() &&
                fechaFin != null && !fechaFin.trim().isEmpty());

        if (filtrarFechas) {
            // SQL con filtro de fechas
            sql = "SELECT concepto, SUM(monto_exacto) AS total FROM Pago " +
                    "WHERE fecha_pago BETWEEN ? AND ? " +
                    "GROUP BY concepto";
        } else {
            // SQL general
            sql = "SELECT concepto, SUM(monto_exacto) AS total FROM Pago " +
                    "GROUP BY concepto";
        }

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Si hay fechas
            if (filtrarFechas) {
                stmt.setString(1, fechaInicio);
                stmt.setString(2, fechaFin);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ReporteIngresoDTO dto = new ReporteIngresoDTO();
                    dto.setConcepto(rs.getString("concepto"));
                    dto.setTotal(rs.getDouble("total")); // Atrapamos la suma
                    lista.add(dto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<ReporteMorosoDTO> obtenerMorosos(int gradoId, int seccionId) {
        List<ReporteMorosoDTO> lista = new ArrayList<>();

        // Hacemos JOIN desde Inscripcion para filtrar a los alumnos por grado y sección
        // LEFT JOIN con Pago porque un alumno moroso podría no tener ningun pago registrado aun
        String sql = "SELECT CONCAT(e.nombres, ' ', e.apellidos) AS estudiante, " +
                "(g.cuota_inscripcion + (g.cuota_mensual * 10)) AS total_esperado, " +
                "IFNULL(SUM(p.monto_exacto), 0) AS total_pagado " +
                "FROM Inscripcion i " +
                "INNER JOIN Estudiante e ON i.estudiante_id = e.id " +
                "INNER JOIN Grado g ON i.grado_id = g.id " +
                "LEFT JOIN Pago p ON p.estudiante_id = e.id " +
                "WHERE i.grado_id = ? AND i.seccion_id = ? " +
                "GROUP BY e.id, g.cuota_inscripcion, g.cuota_mensual";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, gradoId);
            stmt.setInt(2, seccionId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ReporteMorosoDTO dto = new ReporteMorosoDTO();
                    dto.setEstudiante(rs.getString("estudiante"));

                    double totalEsperado = rs.getDouble("total_esperado");
                    double totalPagado = rs.getDouble("total_pagado");
                    double deuda = totalEsperado - totalPagado;

                    // Logica para determinar el estado
                    if (deuda > 0) {
                        dto.setEstado("Moroso");
                        dto.setMontoAdeudado(deuda);
                    } else {
                        dto.setEstado("Solvente");
                        dto.setMontoAdeudado(0.00);
                    }

                    lista.add(dto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<ReporteRendimientoDTO> obtenerRendimiento(int gradoId, int seccionId) {
        List<ReporteRendimientoDTO> lista = new ArrayList<>();
        // AVG saca el promedio de las notas en ese curso
        String sql = "SELECT c.nombre AS curso, AVG(n.punteo_obtenido) AS promedio " +
                "FROM Nota n " +
                "INNER JOIN Curso c ON n.curso_id = c.id " +
                "INNER JOIN Inscripcion i ON n.inscripcion_id = i.id " +
                "WHERE i.grado_id = ? AND i.seccion_id = ? " +
                "GROUP BY c.id, c.nombre " +
                "ORDER BY promedio DESC"; // Ordena del mejor promedio al peor

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, gradoId);
            stmt.setInt(2, seccionId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ReporteRendimientoDTO dto = new ReporteRendimientoDTO();
                    dto.setCurso(rs.getString("curso"));
                    dto.setPromedio(rs.getDouble("promedio"));
                    lista.add(dto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<ReporteHistorialPagoDTO> obtenerHistorialPagos(int estudianteId, String fechaInicio, String fechaFin) {
        List<ReporteHistorialPagoDTO> lista = new ArrayList<>();
        String sql;

        boolean filtrarFechas = (fechaInicio != null && !fechaInicio.trim().isEmpty() &&
                fechaFin != null && !fechaFin.trim().isEmpty());

        if (filtrarFechas) {
            sql = "SELECT fecha_pago, concepto, monto_exacto FROM Pago " +
                    "WHERE estudiante_id = ? AND fecha_pago BETWEEN ? AND ? " +
                    "ORDER BY fecha_pago ASC";
        } else {
            sql = "SELECT fecha_pago, concepto, monto_exacto FROM Pago " +
                    "WHERE estudiante_id = ? " +
                    "ORDER BY fecha_pago ASC";
        }

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, estudianteId);
            if (filtrarFechas) {
                stmt.setString(2, fechaInicio);
                stmt.setString(3, fechaFin);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ReporteHistorialPagoDTO dto = new ReporteHistorialPagoDTO();
                    dto.setFecha(rs.getString("fecha_pago"));
                    dto.setConcepto(rs.getString("concepto"));
                    dto.setMonto(rs.getDouble("monto_exacto"));
                    lista.add(dto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<ReportePrestamoMoraDTO> obtenerPrestamosMora() {
        List<ReportePrestamoMoraDTO> lista = new ArrayList<>();

        // DATEDIFF calcula los días de retraso usando la fecha actual del servidor
        // Multiplicamos esos días por 5.00 para simular una multa de Q5 diarios
        String sql = "SELECT l.titulo AS libro, CONCAT(e.nombres, ' ', e.apellidos) AS estudiante, " +
                "DATEDIFF(CURDATE(), p.fecha_esperada_devolucion) AS dias_retraso, " +
                "(DATEDIFF(CURDATE(), p.fecha_esperada_devolucion) * 5.00) AS recargo " +
                "FROM Prestamo p " +
                "INNER JOIN Libro l ON p.libro_id = l.id " +
                "INNER JOIN Usuario u ON p.usuario_id = u.id " +
                "INNER JOIN Estudiante e ON e.usuario_id = u.id " +
                "WHERE p.fecha_devolucion_real IS NULL " +
                "AND p.fecha_esperada_devolucion < CURDATE() " +
                "ORDER BY dias_retraso DESC";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ReportePrestamoMoraDTO dto = new ReportePrestamoMoraDTO();
                dto.setLibro(rs.getString("libro"));
                dto.setEstudiante(rs.getString("estudiante"));
                dto.setDiasRetraso(rs.getInt("dias_retraso"));
                dto.setRecargoFinanciero(rs.getDouble("recargo"));
                lista.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}