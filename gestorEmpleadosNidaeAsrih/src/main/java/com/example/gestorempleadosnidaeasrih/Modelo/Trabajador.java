package com.example.gestorempleadosnidaeasrih.Modelo;

import java.util.Date;

public class Trabajador {

    private int ID;
    private String nombre;
    private String puesto;
    private int salario;
    private Date fecha;

    public Trabajador(int id, String nombre, String puesto, int salario, Date fecha) {
        this.ID = id;
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
        this.fecha = fecha;
    }

    public Trabajador(String nombre, String puesto, int salario, Date fecha) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
        this.fecha = fecha;
    }

    public int getID() {
        return ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
