package com.example.proyecto2ss.dtos;

public class InscripcionDTO {
    private int id;
    private String estudiante;
    private String grado;
    private String seccion;
    private String ciclo;
    private String fechaInscripcion;

    public InscripcionDTO() {
    }

    public InscripcionDTO(int id, String estudiante, String grado, String seccion, String ciclo, String fechaInscripcion) {
        this.id = id;
        this.estudiante = estudiante;
        this.grado = grado;
        this.seccion = seccion;
        this.ciclo = ciclo;
        this.fechaInscripcion = fechaInscripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(String fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }
}
