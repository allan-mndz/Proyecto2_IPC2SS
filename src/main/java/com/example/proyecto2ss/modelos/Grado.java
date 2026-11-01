package com.example.proyecto2ss.modelos;

public class Grado {
    private int id;
    private String nombre;
    private String nivel;
    private double cuotaInscripcion;
    private double cuotaMensual;
    private Integer carreraId;

    public Grado() {
    }

    public Grado(int id, String nombre, String nivel, double cuotaInscripcion, double cuotaMensual, Integer carreraId) {
        this.id = id;
        this.nombre = nombre;
        this.nivel = nivel;
        this.cuotaInscripcion = cuotaInscripcion;
        this.cuotaMensual = cuotaMensual;
        this.carreraId = carreraId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Integer getCarreraId() {
        return carreraId;
    }

    public void setCarreraId(Integer carreraId) {
        this.carreraId = carreraId;
    }

    public double getCuotaInscripcion() {
        return cuotaInscripcion;
    }

    public void setCuotaInscripcion(double cuotaInscripcion) {
        this.cuotaInscripcion = cuotaInscripcion;
    }

    public double getCuotaMensual() {
        return cuotaMensual;
    }

    public void setCuotaMensual(double cuotaMensual) {
        this.cuotaMensual = cuotaMensual;
    }
}
