package com.example.proyecto2ss.dtos;

public class EmpleadoDTO {
    private int id;
    private String nombres;
    private String apellidos;
    private String puesto;
    private double salario;
    private String fechaContratacion;
    private String nombreUsuario;

    public EmpleadoDTO() {
    }

    public EmpleadoDTO(int id, String nombres, String apellidos, String puesto, double salario, String fechaContratacion, String nombreUsuario) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.puesto = puesto;
        this.salario = salario;
        this.fechaContratacion = fechaContratacion;
        this.nombreUsuario = nombreUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(String fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
