package com.example.proyecto2ss.dtos;

public class ReporteMorosoDTO {
    private String estudiante;
    private String estado;
    private double montoAdeudado;

    public ReporteMorosoDTO() {
    }

    public ReporteMorosoDTO(String estudiante, String estado, double montoAdeudado) {
        this.estudiante = estudiante;
        this.estado = estado;
        this.montoAdeudado = montoAdeudado;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getMontoAdeudado() {
        return montoAdeudado;
    }

    public void setMontoAdeudado(double montoAdeudado) {
        this.montoAdeudado = montoAdeudado;
    }
}
