package com.example.proyecto2ss.modelos;

public class Curriculo {
    private int id;
    private int gradoId;
    private int cursoId;
    private int cicloEscolarId;

    public Curriculo() {
    }

    public Curriculo(int id, int gradoId, int cursoId, int cicloEscolarId) {
        this.id = id;
        this.gradoId = gradoId;
        this.cursoId = cursoId;
        this.cicloEscolarId = cicloEscolarId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGradoId() {
        return gradoId;
    }

    public void setGradoId(int gradoId) {
        this.gradoId = gradoId;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public int getCicloEscolarId() {
        return cicloEscolarId;
    }

    public void setCicloEscolarId(int cicloEscolarId) {
        this.cicloEscolarId = cicloEscolarId;
    }
}
