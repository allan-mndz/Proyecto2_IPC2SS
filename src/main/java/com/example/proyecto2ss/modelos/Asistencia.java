package com.example.proyecto2ss.modelos;

public class Asistencia {
    private int id;
    private int inscripcionId;
    private int cursoId;
    private String fecha;
    private int presente;

    public Asistencia() {
    }

    public Asistencia(int id, int inscripcionId, int cursoId, String fecha, int presente) {
        this.id = id;
        this.inscripcionId = inscripcionId;
        this.cursoId = cursoId;
        this.fecha = fecha;
        this.presente = presente;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getPresente() {
        return presente;
    }

    public void setPresente(int presente) {
        this.presente = presente;
    }
}
