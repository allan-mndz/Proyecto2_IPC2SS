package com.example.proyecto2ss.dtos;

public class MultaDTO {
    private int id;
    private String prestamo;
    private String pago;
    private double montoCalculado;
    private int pagado;

    public MultaDTO() {
    }

    public MultaDTO(int id, String prestamo, String pago, double montoCalculado, int pagado) {
        this.id = id;
        this.prestamo = prestamo;
        this.pago = pago;
        this.montoCalculado = montoCalculado;
        this.pagado = pagado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(String prestamo) {
        this.prestamo = prestamo;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
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
