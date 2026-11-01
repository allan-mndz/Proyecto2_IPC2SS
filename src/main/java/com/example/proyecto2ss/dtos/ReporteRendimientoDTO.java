package com.example.proyecto2ss.dtos;

public class ReporteRendimientoDTO {
    private String curso;
    private double promedio;

    public ReporteRendimientoDTO() {}

    public ReporteRendimientoDTO(String curso, double promedio) {
        this.curso = curso;
        this.promedio = promedio;
    }

    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }

    public double getPromedio() { return promedio; }
    public void setPromedio(double promedio) { this.promedio = promedio; }
}
