package com.example.proyecto2ss.modelos;

public class Nota {
    private int id;
    private int inscripcionId;
    private int cursoId;
    private String actividad;
    private double ponderacion;
    private double punteoObtenido;

    public Nota() {
    }

    public Nota(int id, int inscripcionId, int cursoId, String actividad, double ponderacion, double punteoObtenido) {
        this.id = id;
        this.inscripcionId = inscripcionId;
        this.cursoId = cursoId;
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

    public int getInscripcionId() {
        return inscripcionId;
    }

    public void setInscripcionId(int inscripcionId) {
        this.inscripcionId = inscripcionId;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
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
