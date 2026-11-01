package com.example.proyecto2ss.dtos;

public class NotaDTO {
    private int id;
    private String inscripcion;
    private String curso;
    private String actividad;
    private double ponderacion;
    private double punteoObtenido;

    public NotaDTO() {
    }

    public NotaDTO(int id, String inscripcion, String curso, String actividad, double ponderacion, double punteoObtenido) {
        this.id = id;
        this.inscripcion = inscripcion;
        this.curso = curso;
        this.actividad = actividad;
        this.ponderacion = ponderacion;
        this.punteoObtenido = punteoObtenido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(String inscripcion) {
        this.inscripcion = inscripcion;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public double getPonderacion() {
        return ponderacion;
    }

    public void setPonderacion(double ponderacion) {
        this.ponderacion = ponderacion;
    }

    public double getPunteoObtenido() {
        return punteoObtenido;
    }

    public void setPunteoObtenido(double punteoObtenido) {
        this.punteoObtenido = punteoObtenido;
    }
}
