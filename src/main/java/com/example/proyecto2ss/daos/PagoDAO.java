package com.example.proyecto2ss.daos;

import com.example.proyecto2ss.dtos.PagoDTO;
import com.example.proyecto2ss.modelos.Pago;
import com.example.proyecto2ss.util.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PagoDAO {

    public boolean crear(Pago pago) {
        String sql = "INSERT INTO Pago (estudiante_id, secretaria_id, concepto, monto_exacto, fecha_pago) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, pago.getEstudianteId());
            stmt.setInt(2, pago.getSecretariaId());
            stmt.setString(3, pago.getConcepto());
            stmt.setDouble(4, pago.getMontoExacto());
            stmt.setString(5, pago.getFechaPago());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Pago> obtenerTodos() {
        List<Pago> lista = new ArrayList<>();
        String sql = "SELECT * FROM Pago";
        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pago pago = new Pago();
                pago.setId(rs.getInt("id"));
                pago.setEstudianteId(rs.getInt("estudiante_id"));
                pago.setSecretariaId(rs.getInt("secretaria_id"));
                pago.setConcepto(rs.getString("concepto"));
                pago.setMontoExacto(rs.getDouble("monto_exacto"));
                pago.setFechaPago(rs.getString("fecha_pago"));
                lista.add(pago);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<PagoDTO> obtenerDetalles() {
        List<PagoDTO> lista = new ArrayList<>();
        String sql = "SELECT p.id, CONCAT(e.nombres, ' ', e.apellidos) AS estudiante, " +
                "u.username AS secretaria, p.concepto, p.monto_exacto AS montoExacto, p.fecha_pago AS fechaPago " +
                "FROM Pago p " +
                "INNER JOIN Estudiante e ON p.estudiante_id = e.id " +
                "INNER JOIN Usuario u ON p.secretaria_id = u.id";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                PagoDTO dto = new PagoDTO();
                dto.setId(rs.getInt("id"));
                dto.setEstudiante(rs.getString("estudiante"));
                dto.setSecretaria(rs.getString("secretaria"));
                dto.setConcepto(rs.getString("concepto"));
                dto.setMontoExacto(rs.getDouble("montoExacto"));
                dto.setFechaPago(rs.getString("fechaPago"));
                lista.add(dto);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
}