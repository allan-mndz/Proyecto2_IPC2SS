package com.example.proyecto2ss.dtos;

public class ReporteIngresoDTO {
    private String concepto;
    private double total;

    public ReporteIngresoDTO() {
    }

    public ReporteIngresoDTO(String concepto, double total) {
        this.concepto = concepto;
        this.total = total;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
