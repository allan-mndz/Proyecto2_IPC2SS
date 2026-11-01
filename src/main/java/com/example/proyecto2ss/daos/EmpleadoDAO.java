package com.example.proyecto2ss.daos;

import com.example.proyecto2ss.dtos.EmpleadoDTO;
import com.example.proyecto2ss.modelos.Empleado;
import com.example.proyecto2ss.util.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    public boolean crear(Empleado emp) {
        String sql = "INSERT INTO Empleado (usuario_id, nombres, apellidos, puesto, salario, fecha_contratacion, estado) VALUES (?, ?, ?, ?, ?, ?, 'Activo')";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, emp.getUsuarioId(), java.sql.Types.INTEGER);
            stmt.setString(2, emp.getNombres());
            stmt.setString(3, emp.getApellidos());
            stmt.setString(4, emp.getPuesto());
            stmt.setDouble(5, emp.getSalario());
            stmt.setString(6, emp.getFechaContratacion());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Empleado> obtenerTodos() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM Empleado";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Empleado emp = new Empleado();
                emp.setId(rs.getInt("id"));
                emp.setUsuarioId((Integer) rs.getObject("usuario_id"));
                emp.setNombres(rs.getString("nombres"));
                emp.setApellidos(rs.getString("apellidos"));
                emp.setPuesto(rs.getString("puesto"));
                emp.setSalario(rs.getDouble("salario"));
                emp.setFechaContratacion(rs.getString("fecha_contratacion"));
                emp.setEstado(rs.getString("estado"));
                lista.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<EmpleadoDTO> obtenerDetalles() {
        List<EmpleadoDTO> lista = new ArrayList<>();

        String sql = "SELECT e.id, e.nombres, e.apellidos, e.puesto, e.salario, e.fecha_contratacion, u.username AS nombre_usuario " +
                "FROM Empleado e " +
                "INNER JOIN Usuario u ON e.usuario_id = u.id";

        try (Connection connection = ConexionDB.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){

            while (rs.next()){
                EmpleadoDTO empleadoDTO = new EmpleadoDTO();

                empleadoDTO.setId(rs.getInt("id"));
                empleadoDTO.setNombres(rs.getString("nombres"));
                empleadoDTO.setApellidos(rs.getString("apellidos"));
                empleadoDTO.setPuesto(rs.getString("puesto"));
                empleadoDTO.setSalario(rs.getDouble("salario"));
                empleadoDTO.setFechaContratacion(rs.getString("fecha_contratacion"));
                empleadoDTO.setNombreUsuario(rs.getString("nombre_usuario"));
                lista.add(empleadoDTO);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public boolean actualizar(Empleado empleado) {
        String sql = "UPDATE Empleado SET nombres=?, apellidos=?, puesto=?, salario=?, estado=? WHERE id=?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, empleado.getNombres());
            stmt.setString(2, empleado.getApellidos());
            stmt.setString(3, empleado.getPuesto());
            stmt.setDouble(4, empleado.getSalario());
            stmt.setString(5, empleado.getEstado());
            stmt.setInt(6, empleado.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean desactivar(int id) {
        String sql = "UPDATE Empleado SET estado = 'Inactivo' WHERE id=?";
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