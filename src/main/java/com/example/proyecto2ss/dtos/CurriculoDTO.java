package com.example.proyecto2ss.dtos;

public class CurriculoDTO {
    private int id;
    private String grado;
    private String curso;
    private String cicloEscolar;

    public CurriculoDTO() {
    }

    public CurriculoDTO(int id, String grado, String curso, String cicloEscolar) {
        this.id = id;
        this.grado = grado;
        this.curso = curso;
        this.cicloEscolar = cicloEscolar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCicloEscolar() {
        return cicloEscolar;
    }

    public void setCicloEscolar(String cicloEscolar) {
        this.cicloEscolar = cicloEscolar;
    }
}
