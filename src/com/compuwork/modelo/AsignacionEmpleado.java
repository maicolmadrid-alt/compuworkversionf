package com.compuwork.modelo;

import java.util.Date;

public class AsignacionEmpleado {
    private final Empleado empleado;
    private final Date fechaAsignacion;
    private EstadoAsignacion estado;

    public enum EstadoAsignacion {
        ACTIVO, BAJA
    }

    public AsignacionEmpleado(Empleado empleado) {
        this.empleado = empleado;
        this.fechaAsignacion = new Date();
        this.estado = EstadoAsignacion.ACTIVO;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public EstadoAsignacion getEstado() {
        return estado;
    }

    public void darDeBaja() {
        this.estado = EstadoAsignacion.BAJA;
    }

    @Override
    public String toString() {
        return empleado.getNombre() + " (" + estado + ") - Asignado el " + fechaAsignacion;
    }
}
