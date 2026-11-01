package com.example.proyecto2ss.daos;

import com.example.proyecto2ss.dtos.AsignacionMaestroDTO;
import com.example.proyecto2ss.modelos.AsignacionMaestro;
import com.example.proyecto2ss.util.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AsignacionMaestroDAO {

    public boolean crearAsignacionMaestro(AsignacionMaestro asignacionMaestro){
        String sql = "INSERT INTO Asignacion_maestro(empleado_id, curso_id, seccion_id, ciclo_escolar_id) VALUES (?,?,?,?)";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setInt(1, asignacionMaestro.getEmpleadoId());
            stmt.setInt(2, asignacionMaestro.getCursoId());
            stmt.setInt(3, asignacionMaestro.getSeccionId());
            stmt.setInt(4, asignacionMaestro.getCicloEscolarId());
            return stmt.executeUpdate() > 0;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public List<AsignacionMaestro> listarTodos(){
        List<AsignacionMaestro> lista = new ArrayList<>();

        String sql = "SELECT * FROM Asignacion_maestro";
        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){

            while (rs.next()){
                AsignacionMaestro asignacionMaestro = new AsignacionMaestro();

                asignacionMaestro.setId(rs.getInt("id"));
                asignacionMaestro.setEmpleadoId(rs.getInt("empleado_id"));
                asignacionMaestro.setCursoId(rs.getInt("curso_id"));
                asignacionMaestro.setSeccionId(rs.getInt("seccion_id"));
                asignacionMaestro.setCicloEscolarId(rs.getInt("ciclo_escolar_id"));

                lista.add(asignacionMaestro);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;

    }

    public List<AsignacionMaestroDTO> obtenerDetalles() {
        List<AsignacionMaestroDTO> lista = new ArrayList<>();

        String sql = "SELECT am.id, CONCAT(e.nombres, ' ', e.apellidos) AS empleado, " +
                "c.nombre AS curso, s.nombre AS seccion, ci.anio AS cicloEscolar " +
                "FROM Asignacion_maestro am " +
                "INNER JOIN Empleado e ON am.empleado_id = e.id " +
                "INNER JOIN Curso c ON am.curso_id = c.id " +
                "INNER JOIN Seccion s ON am.seccion_id = s.id " +
                "INNER JOIN Ciclo_escolar ci ON am.ciclo_escolar_id = ci.id";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                AsignacionMaestroDTO dto = new AsignacionMaestroDTO();
                dto.setId(rs.getInt("id"));
                dto.setEmpleado(rs.getString("empleado"));
                dto.setCurso(rs.getString("curso"));
                dto.setSeccion(rs.getString("seccion"));
                dto.setCicloEscolar(rs.getString("cicloEscolar"));

                lista.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean actualizarAsignacionMaestro(AsignacionMaestro asignacionMaestro){
        String sql = "UPDATE Asignacion_maestro SET empleado_id=?, curso_id=?, seccion_id=?, ciclo_escolar_id=? WHERE id=?";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setInt(1, asignacionMaestro.getEmpleadoId());
            stmt.setInt(2, asignacionMaestro.getCursoId());
            stmt.setInt(3, asignacionMaestro.getSeccionId());
            stmt.setInt(4, asignacionMaestro.getCicloEscolarId());
            stmt.setInt(5, asignacionMaestro.getId());
            return stmt.executeUpdate() > 0;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarAsignacionMaestro(int id){
        String sql = "DELETE FROM Asignacion_maestro WHERE id=?";

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
