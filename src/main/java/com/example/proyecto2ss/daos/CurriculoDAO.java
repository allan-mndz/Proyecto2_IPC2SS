package com.example.proyecto2ss.daos;

import com.example.proyecto2ss.dtos.CurriculoDTO;
import com.example.proyecto2ss.modelos.Curriculo;
import com.example.proyecto2ss.util.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurriculoDAO {

    public boolean crear(Curriculo curriculo){
        String sql = "INSERT INTO Curriculo (grado_id, curso_id, ciclo_escolar_id) VALUES (?,?,?)";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setInt(1, curriculo.getGradoId());
            stmt.setInt(2, curriculo.getCursoId());
            stmt.setInt(3, curriculo.getCicloEscolarId());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Curriculo> obtenerTodos(){
        List<Curriculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM Curriculo";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){

            while (rs.next()){
                Curriculo curriculo = new Curriculo();
                curriculo.setId(rs.getInt("id"));
                curriculo.setGradoId(rs.getInt("grado_id"));
                curriculo.setCursoId(rs.getInt("curso_id"));
                curriculo.setCicloEscolarId(rs.getInt("ciclo_escolar_id"));
                lista.add(curriculo);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public List<CurriculoDTO> obtenerDetalles() {
        List<CurriculoDTO> lista = new ArrayList<>();

        String sql = "SELECT cu.id, g.nombre AS grado, c.nombre AS curso, ci.anio AS cicloEscolar " +
                "FROM Curriculo cu " +
                "INNER JOIN Grado g ON cu.grado_id = g.id " +
                "INNER JOIN Curso c ON cu.curso_id = c.id " +
                "INNER JOIN Ciclo_escolar ci ON cu.ciclo_escolar_id = ci.id";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CurriculoDTO dto = new CurriculoDTO();
                dto.setId(rs.getInt("id"));
                dto.setGrado(rs.getString("grado"));
                dto.setCurso(rs.getString("curso"));
                dto.setCicloEscolar(rs.getString("cicloEscolar"));

                lista.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean actualizar(Curriculo curriculo) {
        String sql = "UPDATE Curriculo SET grado_id=?, curso_id=?, ciclo_escolar_id=? WHERE id=?";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, curriculo.getGradoId());
            stmt.setInt(2, curriculo.getCursoId());
            stmt.setInt(3, curriculo.getCicloEscolarId());
            stmt.setInt(4, curriculo.getId());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id){
        String sql = "DELETE FROM Curriculo WHERE id=?";

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
