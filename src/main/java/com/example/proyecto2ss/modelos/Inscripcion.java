package com.example.proyecto2ss.modelos;

public class Inscripcion {
    private int id;
    private int estudianteId;
    private int gradoId;
    private int seccionId;
    private int cicloEscolarId;
    private String fechaInscripcion;

    public Inscripcion() {
    }

    public Inscripcion(int id, int estudianteId, int gradoId, int seccionId, int cicloEscolarId, String fechaInscripcion) {
        this.id = id;
        this.estudianteId = estudianteId;
        this.gradoId = gradoId;
        this.seccionId = seccionId;
        this.cicloEscolarId = cicloEscolarId;
        this.fechaInscripcion = fechaInscripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(int estudianteId) {
        this.estudianteId = estudianteId;
    }

    public int getGradoId() {
        return gradoId;
    }

    public void setGradoId(int gradoId) {
        this.gradoId = gradoId;
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

    public String getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(String fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }
}
