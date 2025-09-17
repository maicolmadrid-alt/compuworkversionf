package com.compuwork.modelo;

import java.util.ArrayList;
import java.util.List;

    public class Departamento {
    private String nombre;
    private List<Empleado> empleados;

    public Departamento(String nombre) {
        this.nombre = nombre;
        this.empleados = new ArrayList<>();
    }

    public String getNombre() { return nombre; }
    public List<Empleado> getEmpleados() { return empleados; }

    public void agregarEmpleado(Empleado e) {
        if (empleados.contains(e)) {
            throw new IllegalArgumentException("El empleado ya esta en el departamento");
        }
        empleados.add(e);
        e.setDepartamento(nombre);
    }

    public void eliminarEmpleado(Empleado e) {
        if (!empleados.remove(e)) {
            throw new IllegalArgumentException("El empleado no pertenece a este departamento");
        }
        e.setDepartamento("sin asignar");
    }
}
