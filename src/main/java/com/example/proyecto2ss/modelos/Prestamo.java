package com.example.proyecto2ss.modelos;

public class Prestamo {
    private int id;
    private int libroId;
    private int usuarioId;
    private String fechaPrestamo;
    private String fechaEsperadaDevolucion;
    private String fechaDevolucionReal;

    public Prestamo() {
    }

    public Prestamo(int id, int libroId, int usuarioId, String fechaPrestamo, String fechaEsperadaDevolucion, String fechaDevolucionReal) {
        this.id = id;
        this.libroId = libroId;
        this.usuarioId = usuarioId;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaEsperadaDevolucion = fechaEsperadaDevolucion;
        this.fechaDevolucionReal = fechaDevolucionReal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLibroId() {
        return libroId;
    }

    public void setLibroId(int libroId) {
        this.libroId = libroId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getFechaEsperadaDevolucion() {
        return fechaEsperadaDevolucion;
    }

    public void setFechaEsperadaDevolucion(String fechaEsperadaDevolucion) {
        this.fechaEsperadaDevolucion = fechaEsperadaDevolucion;
    }

    public String getFechaDevolucionReal() {
        return fechaDevolucionReal;
    }

    public void setFechaDevolucionReal(String fechaDevolucionReal) {
        this.fechaDevolucionReal = fechaDevolucionReal;
    }
}
