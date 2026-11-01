package com.example.proyecto2ss.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    public static Connection getConnection() throws SQLException{
        try {
            Context initContext = new InitialContext();

            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/MiPoolProyecto"); //buscamos el pool con el nombre que pusimos en .xml

            return ds.getConnection(); //conexion prestada al pool

        } catch (NamingException e) {
            throw new SQLException("Error al buscar el Pool de Conexiones", e);
        }
    }
}
