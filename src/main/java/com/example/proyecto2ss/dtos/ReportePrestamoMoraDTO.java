package com.example.proyecto2ss.dtos;

public class ReportePrestamoMoraDTO {
    private String libro;
    private String estudiante;
    private int diasRetraso;
    private double recargoFinanciero;

    public ReportePrestamoMoraDTO() {}

    public ReportePrestamoMoraDTO(String libro, String estudiante, int diasRetraso, double recargoFinanciero) {
        this.libro = libro;
        this.estudiante = estudiante;
        this.diasRetraso = diasRetraso;
        this.recargoFinanciero = recargoFinanciero;
    }

    public String getLibro() { return libro; }
    public void setLibro(String libro) { this.libro = libro; }

    public String getEstudiante() { return estudiante; }
    public void setEstudiante(String estudiante) { this.estudiante = estudiante; }

    public int getDiasRetraso() { return diasRetraso; }
    public void setDiasRetraso(int diasRetraso) { this.diasRetraso = diasRetraso; }

    public double getRecargoFinanciero() { return recargoFinanciero; }
    public void setRecargoFinanciero(double recargoFinanciero) { this.recargoFinanciero = recargoFinanciero; }
}
