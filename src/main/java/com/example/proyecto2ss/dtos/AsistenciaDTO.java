package com.example.proyecto2ss.dtos;

public class AsistenciaDTO {
    private int id;
    private String inscripcion;
    private String curso;
    private String fecha;
    private int presente;

    public AsistenciaDTO() {
    }

    public AsistenciaDTO(int id, String inscripcion, String curso, String fecha, int presente) {
        this.id = id;
        this.inscripcion = inscripcion;
        this.curso = curso;
        this.fecha = fecha;
        this.presente = presente;
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
