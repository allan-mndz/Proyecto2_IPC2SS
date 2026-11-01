package com.example.proyecto2ss.daos;

import com.example.proyecto2ss.dtos.MultaDTO;
import com.example.proyecto2ss.modelos.Multa;
import com.example.proyecto2ss.util.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MultaDAO {

    public boolean crear(Multa multa) {
        String sql = "INSERT INTO Multa (prestamo_id, monto_calculado, pagado) VALUES (?, ?, 0)";
        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, multa.getPrestamoId());
            stmt.setDouble(2, multa.getMontoCalculado());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Multa> obtenerTodas() {
        List<Multa> lista = new ArrayList<>();
        String sql = "SELECT * FROM Multa";
        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Multa m = new Multa();
                m.setId(rs.getInt("id"));
                m.setPrestamoId(rs.getInt("prestamo_id"));
                m.setPagoId((Integer) rs.getObject("pago_id")); // Maneja el nulo correctamente
                m.setMontoCalculado(rs.getDouble("monto_calculado"));
                m.setPagado(rs.getInt("pagado"));
                lista.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<MultaDTO> obtenerDetalles() {
        List<MultaDTO> lista = new ArrayList<>();

        String sql = "SELECT m.id, " +
                "CONCAT(l.titulo, ' - Lector: ', u.username) AS prestamo, " +
                "pa.concepto AS pago, " +
                "m.monto_calculado, m.pagado " +
                "FROM Multa m " +
                "INNER JOIN Prestamo p ON m.prestamo_id = p.id " +
                "INNER JOIN Libro l ON p.libro_id = l.id " +
                "INNER JOIN Usuario u ON p.usuario_id = u.id " +
                "LEFT JOIN Pago pa ON m.pago_id = pa.id";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                MultaDTO dto = new MultaDTO();
                dto.setId(rs.getInt("id"));
                dto.setPrestamo(rs.getString("prestamo"));


                String conceptoPago = rs.getString("pago");
                if (conceptoPago == null) {
                    dto.setPago("Pendiente de pago");
                } else {
                    dto.setPago(conceptoPago);
                }

                dto.setMontoCalculado(rs.getDouble("monto_calculado"));
                dto.setPagado(rs.getInt("pagado"));

                lista.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean pagarMulta(Multa multa) {
        String sql = "UPDATE Multa SET pago_id=?, pagado=1 WHERE id=?";
        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setObject(1, multa.getPagoId(), java.sql.Types.INTEGER);
            stmt.setInt(2, multa.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}