package com.example.proyecto2ss.modelos;

import java.time.LocalDateTime;

public class RecuperacionContrasena {
    private int id;
    private int usuarioId;
    private String token;
    private LocalDateTime fechaExpiracion;

    public RecuperacionContrasena() {
    }

    public RecuperacionContrasena(int id, int usuarioId, String token, LocalDateTime fechaExpiracion) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.token = token;
        this.fechaExpiracion = fechaExpiracion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDateTime fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }
}
