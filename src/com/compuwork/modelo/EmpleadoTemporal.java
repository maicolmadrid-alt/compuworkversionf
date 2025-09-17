package com.compuwork.modelo;

public class EmpleadoTemporal extends Empleado {
    private int mesesContrato;

    public EmpleadoTemporal(int id, String nombre, int mesesContrato) {
        super(id, nombre);
        this.mesesContrato = mesesContrato;
    }

        @Override
        public String getDetalles() {
        return "temporal - ID: " + getId() + ", Nombre: " + getNombre() +
               ", departamento: " + getDepartamento() +
               ", contrato: " + mesesContrato + " meses";
    }
}
