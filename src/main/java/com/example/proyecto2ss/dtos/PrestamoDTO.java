package com.example.proyecto2ss.dtos;

public class PrestamoDTO {
    private int id;
    private String libro;
    private String usuario;
    private String fechaPrestamo;
    private String fechaEsperadaDevolucion;
    private String fechaDevolucionReal;

    public PrestamoDTO() {
    }

    public PrestamoDTO(int id, String libro, String usuario, String fechaPrestamo, String fechaEsperadaDevolucion, String fechaDevolucionReal) {
        this.id = id;
        this.libro = libro;
        this.usuario = usuario;
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

    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
