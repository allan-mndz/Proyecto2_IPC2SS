package com.example.proyecto2ss.modelos;

public class Pago {
    private int id;
    private int estudianteId;
    private int secretariaId;
    private String concepto;
    private double montoExacto;
    private String fechaPago;

    public Pago() {
    }

    public Pago(int id, int estudianteId, int secretariaId, String concepto, double montoExacto, String fechaPago) {
        this.id = id;
        this.estudianteId = estudianteId;
        this.secretariaId = secretariaId;
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

    public int getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(int estudianteId) {
        this.estudianteId = estudianteId;
    }

    public int getSecretariaId() {
        return secretariaId;
    }

    public void setSecretariaId(int secretariaId) {
        this.secretariaId = secretariaId;
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
