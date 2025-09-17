package com.compuwork.modelo;

public class EmpleadoPermanente extends Empleado {
    private double salario;

    public EmpleadoPermanente(int id, String nombre, double salario) {
        super(id, nombre);
        this.salario = salario;
    }

    @Override
    public String getDetalles() {
        return "permanente - ID: " + getId() + " nombre: " + getNombre() +
               " departamento: " + getDepartamento() +
               " salario: " + salario;
    }
}