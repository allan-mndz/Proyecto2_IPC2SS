package com.example.proyecto2ss.dtos;

public class GradoDTO {
    private int id;
    private String nombre;
    private String nivel;
    private String carrera;
    private double cuotaInscripcion;
    private double cuotaMensual;

    public GradoDTO() {
    }

    public GradoDTO(int id, String nombre, String nivel, String carrera, double cuotaInscripcion, double cuotaMensual) {
        this.id = id;
        this.nombre = nombre;
        this.nivel = nivel;
        this.carrera = carrera;
        this.cuotaInscripcion = cuotaInscripcion;
        this.cuotaMensual = cuotaMensual;
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

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
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
