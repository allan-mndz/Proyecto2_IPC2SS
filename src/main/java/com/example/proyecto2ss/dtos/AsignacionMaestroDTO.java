package com.example.proyecto2ss.dtos;

public class AsignacionMaestroDTO {
    private int id;
    private String empleado;
    private String curso;
    private String seccion;
    private String cicloEscolar;

    public AsignacionMaestroDTO() {
    }

    public AsignacionMaestroDTO(int id, String empleado, String curso, String seccion, String cicloEscolar) {
        this.id = id;
        this.empleado = empleado;
        this.curso = curso;
        this.seccion = seccion;
        this.cicloEscolar = cicloEscolar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getCicloEscolar() {
        return cicloEscolar;
    }

    public void setCicloEscolar(String cicloEscolar) {
        this.cicloEscolar = cicloEscolar;
    }
}
