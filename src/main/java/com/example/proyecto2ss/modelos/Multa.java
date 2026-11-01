package com.example.proyecto2ss.modelos;

public class Multa {
    private int id;
    private int prestamoId;
    private Integer pagoId;
    private double montoCalculado;
    private int pagado;

    public Multa() {
    }

    public Multa(int id, int prestamoId, Integer pagoId, double montoCalculado, int pagado) {
        this.id = id;
        this.prestamoId = prestamoId;
        this.pagoId = pagoId;
        this.montoCalculado = montoCalculado;
        this.pagado = pagado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrestamoId() {
        return prestamoId;
    }

    public void setPrestamoId(int prestamoId) {
        this.prestamoId = prestamoId;
    }

    public Integer getPagoId() {
        return pagoId;
    }

    public void setPagoId(Integer pagoId) {
        this.pagoId = pagoId;
    }

    public double getMontoCalculado() {
        return montoCalculado;
    }

    public void setMontoCalculado(double montoCalculado) {
        this.montoCalculado = montoCalculado;
    }

    public int getPagado() {
        return pagado;
    }

    public void setPagado(int pagado) {
        this.pagado = pagado;
    }
}
