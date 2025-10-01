package com.compuwork.modelo;

/**
 * Empleado temporal.
 */
public class EmpleadoTemporal extends Empleado {
    private int mesesContrato;
    private String proveedor;

    public EmpleadoTemporal(int id, String nombre, int mesesContrato) {
        super(id, nombre);
        this.mesesContrato = mesesContrato;
        this.proveedor = "";
    }

    public int getMesesContrato() { return mesesContrato; }
    public void setMesesContrato(int mesesContrato) { this.mesesContrato = mesesContrato; }

    public String getProveedor() { return proveedor; }
    public void setProveedor(String proveedor) { this.proveedor = proveedor; }

    @Override
    public String getDetalles() {
        return "Temporal - ID: " + getId() + " Nombre: " + getNombre() +
               " Departamento: " + getDepartamento() +
               " Contrato: " + mesesContrato + " meses";
    }
}
