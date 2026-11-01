package com.example.proyecto2ss.dtos;

public class ReporteHistorialPagoDTO {
    private String fecha;
    private String concepto;
    private double monto;

    public ReporteHistorialPagoDTO() {}

    public ReporteHistorialPagoDTO(String fecha, String concepto, double monto) {
        this.fecha = fecha;
        this.concepto = concepto;
        this.monto = monto;
    }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getConcepto() { return concepto; }
    public void setConcepto(String concepto) { this.concepto = concepto; }

    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }
}
