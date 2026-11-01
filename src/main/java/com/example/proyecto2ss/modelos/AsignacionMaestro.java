package com.example.proyecto2ss.modelos;

public class AsignacionMaestro {
    private int id;
    private int empleadoId;
    private int cursoId;
    private int seccionId;
    private int cicloEscolarId;

    public AsignacionMaestro() {
    }

    public AsignacionMaestro(int id, int empleadoId, int cursoId, int seccionId, int cicloEscolarId) {
        this.id = id;
        this.empleadoId = empleadoId;
        this.cursoId = cursoId;
        this.seccionId = seccionId;
        this.cicloEscolarId = cicloEscolarId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public int getSeccionId() {
        return seccionId;
    }

    public void setSeccionId(int seccionId) {
        this.seccionId = seccionId;
    }

    public int getCicloEscolarId() {
        return cicloEscolarId;
    }

    public void setCicloEscolarId(int cicloEscolarId) {
        this.cicloEscolarId = cicloEscolarId;
    }
}
