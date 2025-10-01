package com.compuwork.modelo;

import java.math.BigDecimal;

/**
 * Clase base para empleados.
 */
public abstract class Empleado {
    private int id;
    private String nombre;
    private String apellido;
    private String documento;
    private String cargo;
    private BigDecimal salario;
    private String email;
    private String estado;

 
    private Departamento departamento;

    public Empleado(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = "";
        this.documento = "";
        this.cargo = "";
        this.salario = BigDecimal.ZERO;
        this.email = "";
        this.estado = "Activo";
        this.departamento = null; 
    }

    public Empleado(int id, String nombre, String apellido, String documento,
                    String cargo, BigDecimal salario, String email, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.cargo = cargo;
        this.salario = salario != null ? salario : BigDecimal.ZERO;
        this.email = email;
        this.estado = estado != null ? estado : "Activo";
        this.departamento = null; // no asignado
    }

    //  Getters 
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDocumento() { return documento; }
    public String getCargo() { return cargo; }
    public BigDecimal getSalario() { return salario; }
    public String getEmail() { return email; }
    public String getEstado() { return estado; }

    
    public Departamento getDepartamento() { return departamento; }

    //  Setters 
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setDocumento(String documento) { this.documento = documento; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public void setSalario(BigDecimal salario) { this.salario = salario != null ? salario : BigDecimal.ZERO; }
    public void setEmail(String email) { this.email = email; }
    public void setEstado(String estado) { this.estado = estado; }

    
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    // Metodos del UML 
    public void registrar() {
        this.estado = "Activo";
    }

    public void actualizar() {
        this.estado = this.estado == null ? "Activo" : this.estado;
    }

    public void darDeBaja() {
        this.estado = "Inactivo";
    }

    //  Metodos obsoletos o de compatibilidad no los debo eliminar aun.
    public Object getEdad() { return 0; }
    public Object getSalarioRaw() { return salario; }

    // Metodo abstracto
    public abstract String getDetalles();

    @Override
    public String toString() {
        return getId() + " - " + (getNombre() != null ? getNombre() : "");
    }
}
