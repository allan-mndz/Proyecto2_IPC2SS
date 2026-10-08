package com.example.proyecto2ss.modelos;

public class Usuario {
    private int id;
    private String username;
    private String rol;
    private String contrasena;
    private int activo;

    public Usuario() {
    }

    public Usuario(int id, String username, String rol, String contrasena, int activo) {
        this.id = id;
        this.username = username;
        this.rol = rol;
        this.contrasena = contrasena;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
}
