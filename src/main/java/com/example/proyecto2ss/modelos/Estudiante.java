package com.example.proyecto2ss.modelos;

public class Estudiante {
    private int id;
    private int usuarioId;
    private String nombres;
    private String apellidos;
    private String informacionMedica;
    private String nombreEncargado;
    private String telefonoEncargado;
    private String estado;

    public Estudiante() {
    }

    public Estudiante(int id, int usuarioId, String nombres, String apellidos, String informacionMedica, String nombreEncargado, String telefonoEncargado, String estado) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.informacionMedica = informacionMedica;
        this.nombreEncargado = nombreEncargado;
        this.telefonoEncargado = telefonoEncargado;
        this.estado = estado;
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

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getInformacionMedica() {
        return informacionMedica;
    }

    public void setInformacionMedica(String informacionMedica) {
        this.informacionMedica = informacionMedica;
    }

    public String getNombreEncargado() {
        return nombreEncargado;
    }

    public void setNombreEncargado(String nombreEncargado) {
        this.nombreEncargado = nombreEncargado;
    }

    public String getTelefonoEncargado() {
        return telefonoEncargado;
    }

    public void setTelefonoEncargado(String telefonoEncargado) {
        this.telefonoEncargado = telefonoEncargado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
