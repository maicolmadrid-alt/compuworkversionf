package com.compuwork.servicio;

import com.compuwork.modelo.*;

import java.util.*;

public class GestorEmpresa {
    private Map<Integer, Empleado> empleados = new HashMap<>();
    private Map<String, Departamento> departamentos = new HashMap<>();
    private List<ReporteDesempeño> reportes = new ArrayList<>();
    private int idCounter = 1;

    
    //  para crear empleados
    public Empleado crearEmpleadoPermanente(String nombre, double salario) {
        Empleado emp = new EmpleadoPermanente(idCounter++, nombre, salario);
        empleados.put(emp.getId(), emp);
        return emp;
    }

    public Empleado crearEmpleadoTemporal(String nombre, int mesesContrato) {
        Empleado emp = new EmpleadoTemporal(idCounter++, nombre, mesesContrato);
        empleados.put(emp.getId(), emp);
        return emp;
    }

    // CRUD 
    public boolean actualizarEmpleado(int id, String nuevoNombre) {
        Empleado e = empleados.get(id);
        if (e != null) {
            e.setNombre(nuevoNombre);
            return true;
        }
        return false;
    }

    public boolean eliminarEmpleado(int id) {
        return empleados.remove(id) != null;
    }

    public Collection<Empleado> listarEmpleados() {
        return empleados.values();
    }

    // CRUD 
    public Departamento crearDepartamento(String nombre) {
        Departamento d = new Departamento(nombre);
        departamentos.put(nombre, d);
        return d;
    }

    
    public boolean eliminarDepartamento(String nombre) {
        return departamentos.remove(nombre) != null;
    }

    public Collection<Departamento> listarDepartamentos() {
        return departamentos.values();
    }

 
    public boolean asignarEmpleadoADepartamento(int idEmpleado, String nombreDepartamento) {
        Empleado e = empleados.get(idEmpleado);
        Departamento d = departamentos.get(nombreDepartamento);
        if (e != null && d != null) {
            try {
                d.agregarEmpleado(e);
                return true;
            } catch (IllegalArgumentException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        return false;
    }

    
    
    
    public String generarReporteEmpleado(int id) {
        Empleado e = empleados.get(id);
        return (e != null) ? e.getDetalles() : "Empleado no encontrado.";
    }

    public String generarReporteDepartamento(String nombreDepto) {
        Departamento d = departamentos.get(nombreDepto);
        if (d == null) return "departamento no encontrado.";
        StringBuilder sb = new StringBuilder("Reporte de " + nombreDepto + ":\n");
        for (Empleado e : d.getEmpleados()) {
            sb.append(" - ").append(e.getDetalles()).append("\n");
        }
        return sb.toString();
    }

    public ReporteDesempeño generarReporteDesempeño(int id, String evaluacion, int calificacion) {
        Empleado e = empleados.get(id);
        if (e == null) throw new IllegalArgumentException("Empleado no encontrado");
        ReporteDesempeño rep = new ReporteDesempeño(e, evaluacion, calificacion);
        reportes.add(rep);
        return rep;
    }

    public List<ReporteDesempeño> listarReportes() {
        return reportes;
    }
}
