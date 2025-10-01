package com.compuwork.servicio;

import com.compuwork.modelo.*;

import java.util.*;
import java.math.BigDecimal;

/**
 * Servicio central que implementa la logiica para gestionar empleados departamentos y reportes
 */
public class GestorEmpresa {

    private final Map<String, Usuario> usuarios = new HashMap<>();
    private final Map<Integer, Empleado> empleados = new LinkedHashMap<>();
    private final Map<String, Departamento> departamentos = new LinkedHashMap<>();
    private final List<ReporteDesempeño> reportes = new ArrayList<>();
    private int idCounter = 1;
    private int deptIdCounter = 1;
    private int reporteIdCounter = 1;

    public GestorEmpresa() {
        // inicializar con un departamento por defecto
        Departamento d = new Departamento(nextDeptId(), "General", "Departamento general");
        departamentos.put(d.getNombre(), d);

        Usuario admin = new Usuario("admin", "1234");
        usuarios.put(admin.getUsername(), admin);
    }

    private int nextId() { return idCounter++; }
    private int nextDeptId() { return deptIdCounter++; }
    private int nextReporteId() { return reporteIdCounter++; } //aun no remover

    //  USUARIOS 
    public Usuario autenticarUsuario(String username, String password) {
        Usuario u = usuarios.get(username);
        if (u != null && u.getPassword().equals(password)) {
            return u;
        }
        return null;
    }

    // EMPLEADOS 
    public Empleado crearEmpleadoPermanente(String nombre, double salario) {
        int id = nextId();
        EmpleadoPermanente emp = new EmpleadoPermanente(id, nombre, 0, salario);
        empleados.put(id, emp);
        return emp;
    }

    public Empleado crearEmpleadoTemporal(String nombre, int meses) {
        int id = nextId();
        EmpleadoTemporal emp = new EmpleadoTemporal(id, nombre, meses);
        empleados.put(id, emp);
        return emp;
    }

    public Collection<Empleado> listarEmpleados() {
        return Collections.unmodifiableCollection(empleados.values());
    }

    public Empleado buscarEmpleadoPorId(int id) {
        return empleados.get(id);
    }

    public void agregarEmpleado(Empleado empleado) {
        if (empleado == null) throw new IllegalArgumentException("Empleado nulo");
        if (empleado.getId() <= 0) return;
        empleados.put(empleado.getId(), empleado);
    }
    // GestorEmpresa
    public int generarIdEmpleado() {
     return nextId();
    }


    public void eliminarEmpleado(int id) {
        Empleado e = empleados.remove(id);
        if (e == null) return;

        // remover de departamento si estaba asignado
        for (Departamento d : departamentos.values()) {
            try {
                d.removerEmpleado(e);
            } catch (IllegalArgumentException ex) {
            }
        }
    }

    public void actualizarEmpleadoCompleto(int id, String nombre, String apellido,
                                           String documento, String cargo, BigDecimal salario,
                                           String email, String estado, String nombreDepartamento,
                                           String beneficios, String tipoContrato,
                                           Integer mesesContrato, String proveedor) {
        Empleado emp = empleados.get(id);
        if (emp == null) {
            throw new IllegalArgumentException("Empleado no encontrado con ID " + id);
        }

        // Propiedades comunes
        emp.setNombre(nombre);
        emp.setApellido(apellido);
        emp.setDocumento(documento);
        emp.setCargo(cargo);
        emp.setSalario(salario);
        emp.setEmail(email);
        emp.setEstado(estado);

        // asignación de departamento real
        Departamento dept = departamentos.get(nombreDepartamento);
        if (dept != null) {
            // si ya estaba en otro se quita
            if (emp.getDepartamento() != null) {
                emp.getDepartamento().removerEmpleado(emp);
            }
            emp.setDepartamento(dept);
            dept.agregarEmpleado(emp);
        } else {
            emp.setDepartamento(null); // no asignado
        }

        // Propiedades especificas
        if (emp instanceof EmpleadoPermanente) {
            EmpleadoPermanente p = (EmpleadoPermanente) emp;
            if (beneficios != null) p.setBeneficios(beneficios);
            if (tipoContrato != null) p.setTipoContrato(tipoContrato);
        } else if (emp instanceof EmpleadoTemporal) {
            EmpleadoTemporal t = (EmpleadoTemporal) emp;
            if (mesesContrato != null) t.setMesesContrato(mesesContrato);
            if (proveedor != null) t.setProveedor(proveedor);
        }
    }

    // DEPARTAMENTOS=e
    public Collection<Departamento> listarDepartamentos() {
        return Collections.unmodifiableCollection(departamentos.values());
    }

    public void crearDepartamento(String nombre, String descripcion) {
    if (nombre == null || nombre.trim().isEmpty())
        throw new IllegalArgumentException("Nombre inválido");
    if (departamentos.containsKey(nombre))
        throw new IllegalArgumentException("El departamento con ese nombre ya existe");

    int id = nextDeptId(); // ✅ asignación automática
    Departamento d = new Departamento(id, nombre, descripcion);
    departamentos.put(nombre, d);
}

    public void eliminarDepartamento(String nombre) {
        if (nombre == null) return;
        Departamento d = departamentos.remove(nombre);
        if (d != null) {
            // limpiar departamento de empleados
            for (Empleado e : d.listarEmpleados()) {
                e.setDepartamento(null);
            }
        }
    }

    public boolean asignarEmpleadoADepartamento(int idEmpleado, String nombreDepartamento) {
        Empleado e = empleados.get(idEmpleado);
        Departamento d = departamentos.get(nombreDepartamento);
        if (e == null || d == null) return false;

        // si ya estaba en otro toca removerlo
        if (e.getDepartamento() != null && !e.getDepartamento().equals(d)) {
            e.getDepartamento().removerEmpleado(e);
        }

        e.setDepartamento(d);
        d.agregarEmpleado(e);
        return true;
    }

    //  REPORTES (dobleImple)
    public List<ReporteDesempeño> listarReportes() {
        return Collections.unmodifiableList(reportes);
    }

    public void agregarReporte(ReporteDesempeño reporte) {
        if (reporte == null) throw new IllegalArgumentException("Reporte nulo");
        reportes.add(reporte);
    }

    public void agregarReporteDepartamento(ReporteDesempeño reporte) {
        if (reporte == null) throw new IllegalArgumentException("Reporte nulo");
        reportes.add(reporte);
    }
}
