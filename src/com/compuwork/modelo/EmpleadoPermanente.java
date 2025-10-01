package com.compuwork.modelo;

import java.math.BigDecimal;

/**
 * Empleado de tipo permanente.
 */
public class EmpleadoPermanente extends Empleado {
    private String beneficios;
    private String tipoContrato;

    public EmpleadoPermanente(int id, String nombre, int edad, double salarioDouble) {
        super(id, nombre);
        this.setSalario(BigDecimal.valueOf(salarioDouble));
        this.beneficios = "";
        this.tipoContrato = "Indefinido";
    }

    public EmpleadoPermanente(int id, String nombre, String beneficios, String tipoContrato, BigDecimal salario) {
        super(id, nombre);
        this.beneficios = beneficios != null ? beneficios : "";
        this.tipoContrato = tipoContrato != null ? tipoContrato : "Indefinido";
        this.setSalario(salario);
    }

    public String getBeneficios() { return beneficios; }
    public void setBeneficios(String beneficios) { this.beneficios = beneficios; }

    public String getTipoContrato() { return tipoContrato; }
    public void setTipoContrato(String tipoContrato) { this.tipoContrato = tipoContrato; }

    @Override
    public String getDetalles() {
        return "Permanente - ID: " + getId() + " Nombre: " + getNombre() +
               " Departamento: " + getDepartamento() +
               " Salario: " + getSalario();
    }
}
