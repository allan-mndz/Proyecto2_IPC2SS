package com.example.proyecto2ss.daos;

import com.example.proyecto2ss.modelos.Usuario;
import com.example.proyecto2ss.util.ConexionDB;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public Usuario obtenerPorUsername(String username) {
        String sql = "SELECT id, username, rol, contrasena, activo FROM Usuario WHERE username = ? AND activo = 1";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setUsername(rs.getString("username"));
                    usuario.setRol(rs.getString("rol"));
                    usuario.setContrasena(rs.getString("contrasena"));
                    usuario.setActivo(rs.getInt("activo"));
                    return usuario;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean crear(Usuario usuario) {
        String sql = "INSERT INTO Usuario (username, rol, contrasena, activo) VALUES (?, ?, ?, 1)"; // activo 1 por defecto

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getRol());

            // Encriptamos la contraseña antes de guardarla
            String contraEncriptada = BCrypt.hashpw(usuario.getContrasena(), BCrypt.gensalt());
            stmt.setString(3, contraEncriptada);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Usuario> obtenerTodos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT id, username, rol, activo FROM Usuario"; // No seleccionamos la contraseña por seguridad

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setUsername(rs.getString("username"));
                usuario.setRol(rs.getString("rol"));
                usuario.setActivo(rs.getInt("activo"));
                lista.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean actualizar(Usuario usuario) {
        String sql = "UPDATE Usuario SET username=?, rol=? WHERE id=?";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getRol());
            stmt.setInt(3, usuario.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean desactivar(int id) {
        String sql = "UPDATE Usuario SET activo=0 WHERE id=?";

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