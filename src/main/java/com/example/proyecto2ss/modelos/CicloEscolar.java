package com.example.proyecto2ss.modelos;

public class CicloEscolar {
    private int id;
    private int anio;
    private String activo;

    public CicloEscolar() {
    }

    public CicloEscolar(int id, int anio, String activo) {
        this.id = id;
        this.anio = anio;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }
}
