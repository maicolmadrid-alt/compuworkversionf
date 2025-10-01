package com.compuwork.modelo;

import java.util.ArrayList;
import java.util.List;

public class Departamento {

    private int idDepartamento;
    private String nombre;
    private String descripcion;
    private final List<AsignacionEmpleado> asignaciones;

    public Departamento(int idDepartamento, String nombre, String descripcion) {
        this.idDepartamento = idDepartamento;
        this.nombre = nombre;
        this.descripcion = descripcion != null ? descripcion : " ";
        this.asignaciones = new ArrayList<>();
    }

    public int getIdDepartamento() { return idDepartamento; }
    public void setIdDepartamento(int id) { this.idDepartamento = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    // === Métodos de negocio ===
    public void agregarEmpleado(Empleado e) {
        if (e == null) return;

        boolean yaAsignado = asignaciones.stream()
                .anyMatch(a -> a.getEmpleado().equals(e) && a.getEstado().equals("Activo"));

        if (yaAsignado) {
            throw new IllegalArgumentException("El empleado ya esta activo en este departamento");
        }

        AsignacionEmpleado asignacion = new AsignacionEmpleado(e);
        asignaciones.add(asignacion);

        // ahora se asigna el objeto departamento
        e.setDepartamento(this);
    }

    public void removerEmpleado(Empleado e) {
    for (AsignacionEmpleado a : asignaciones) {
        if (a.getEmpleado().equals(e) && a.getEstado() == AsignacionEmpleado.EstadoAsignacion.ACTIVO) {
            a.darDeBaja();

            // desvincular del departamento
            e.setDepartamento(null);
            return;
        }
    }
    throw new IllegalArgumentException("El empleado no pertenece a este departamento o ya está dado de baja");
}


    public List<AsignacionEmpleado> listarAsignaciones() {
        return new ArrayList<>(asignaciones);
    }

    public List<Empleado> getEmpleados() {
        List<Empleado> lista = new ArrayList<>();
        for (AsignacionEmpleado a : asignaciones) {
            if (a.getEstado() == AsignacionEmpleado.EstadoAsignacion.ACTIVO) {
                lista.add(a.getEmpleado());
            }

        }
        return lista;
    }

    public List<Empleado> listarEmpleados() {
        return getEmpleados(); // unificar la lógica
    }

    @Override
    public String toString() {
        return idDepartamento + " - " + nombre;
    }
}
