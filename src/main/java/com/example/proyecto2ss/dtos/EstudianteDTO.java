package com.example.proyecto2ss.dtos;

public class EstudianteDTO {
    private int id;
    private String nombres;
    private String apellidos;
    private String informacionMedica;
    private String nombreEncargado;
    private String telefonoEncargado;
    private String estado;
    private String nombreUsuario;

    public EstudianteDTO() {
    }

    public EstudianteDTO(int id, String nombres, String apellidos, String informacionMedica, String nombreEncargado, String telefonoEncargado, String estado, String nombreUsuario) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.informacionMedica = informacionMedica;
        this.nombreEncargado = nombreEncargado;
        this.telefonoEncargado = telefonoEncargado;
        this.estado = estado;
        this.nombreUsuario = nombreUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
