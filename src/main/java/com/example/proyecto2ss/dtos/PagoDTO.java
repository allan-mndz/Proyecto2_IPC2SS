package com.example.proyecto2ss.dtos;

public class PagoDTO {
    private int id;
    private String estudiante;
    private String secretaria;
    private String concepto;
    private double montoExacto;
    private String fechaPago;

    public PagoDTO() {
    }

    public PagoDTO(int id, String estudiante, String secretaria, String concepto, double montoExacto, String fechaPago) {
        this.id = id;
        this.estudiante = estudiante;
        this.secretaria = secretaria;
        this.concepto = concepto;
        this.montoExacto = montoExacto;
        this.fechaPago = fechaPago;
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

    public String getSecretaria() {
        return secretaria;
    }

    public void setSecretaria(String secretaria) {
        this.secretaria = secretaria;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public double getMontoExacto() {
        return montoExacto;
    }

    public void setMontoExacto(double montoExacto) {
        this.montoExacto = montoExacto;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }
}
