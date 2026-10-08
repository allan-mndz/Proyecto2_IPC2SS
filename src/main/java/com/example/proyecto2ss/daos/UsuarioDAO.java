package com.example.proyecto2ss.daos;

import com.example.proyecto2ss.modelos.Usuario;
import com.example.proyecto2ss.util.ConexionDB;
import com.mysql.cj.xdevapi.PreparableStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public Usuario autenticar(String username, String contrasena){
        Usuario usuario = null;

        String sql = "SELECT * FROM Usuario WHERE username = ? AND contrasena = ?";

        try (Connection connection = ConexionDB.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setString(1, username);
            stmt.setString(2, contrasena);

            try (ResultSet rs = stmt.executeQuery()){

                if (rs.next()){
                    usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setUsername(rs.getString("username"));
                    usuario.setRol(rs.getString("rol"));
                    usuario.setContrasena(rs.getString("contrasena"));
                    usuario.setActivo(rs.getInt("activo"));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return usuario;
    }
}
