package com.compuwork.modelo;

public abstract class Empleado {
    private int id;
    private String nombre;
    private String departamento;

    public Empleado(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = "Sin asignar";
    }

     public int getId() { return id; }
     public String getNombre() { return nombre; }
     public String getDepartamento() { return departamento; }

     public void setNombre(String nombre) { this.nombre = nombre; }
     public void setDepartamento(String departamento) { this.departamento = departamento; }

        public abstract String getDetalles();
}

