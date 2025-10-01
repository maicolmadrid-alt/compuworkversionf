package com.compuwork.servicio;

import com.compuwork.modelo.*;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class GestorEmpresaTest {

    private GestorEmpresa gestor;

    @Before
    public void setUp() {
        gestor = new GestorEmpresa();
    }

    //  EMPLEADOS 
@Test
public void testCrearEmpleadoPermanente() {
    Empleado emp = gestor.crearEmpleadoPermanente("Juan", 1000);
    assertNotNull(emp);
    assertTrue(emp instanceof EmpleadoPermanente);
    assertEquals("Juan", emp.getNombre());
    
    // CORECCION: usar compareTo para BigDecimal
    assertEquals(0, BigDecimal.valueOf(1000).compareTo(emp.getSalario()));
}


    @Test
    public void testCrearEmpleadoTemporal() {
        Empleado emp = gestor.crearEmpleadoTemporal("Ana", 6);
        assertNotNull(emp);
        assertTrue(emp instanceof EmpleadoTemporal);
        assertEquals("Ana", emp.getNombre());
        assertEquals(6, ((EmpleadoTemporal) emp).getMesesContrato());
    }

    @Test
    public void testEliminarEmpleado() {
        Empleado emp = gestor.crearEmpleadoPermanente("Luis", 1200);
        int id = emp.getId();
        gestor.eliminarEmpleado(id);
        assertNull(gestor.buscarEmpleadoPorId(id));
    }

    @Test
    public void testActualizarEmpleado() {
        Empleado emp = gestor.crearEmpleadoPermanente("María", 1500);
        int id = emp.getId();
        gestor.actualizarEmpleadoCompleto(id, "MariaActualizada", "Pérez", "12345",
                "Desarrolladora", BigDecimal.valueOf(2000), "maria@test.com", "Activo",
                "General", "BeneficiosX", "Indefinido", null, null);

        Empleado actualizado = gestor.buscarEmpleadoPorId(id);
        assertEquals("MariaActualizada", actualizado.getNombre());
        assertEquals("Pérez", actualizado.getApellido());
        assertEquals("Desarrolladora", actualizado.getCargo());
        assertEquals(BigDecimal.valueOf(2000), actualizado.getSalario());
        assertEquals("BeneficiosX", ((EmpleadoPermanente) actualizado).getBeneficios());
    }

    // DEPARTAMENTOS 

@Test
public void testCrearDepartamento() {
    gestor.crearDepartamento("IT", "Departamento de tecnología");
    assertEquals(2, gestor.listarDepartamentos().size()); // ya existe "General"
    assertTrue(gestor.listarDepartamentos().stream()
            .anyMatch(d -> d.getNombre().equals("IT")));
}

@Test
public void testEliminarDepartamento() {
    gestor.crearDepartamento("RRHH", "Recursos Humanos");
    gestor.eliminarDepartamento("RRHH");
    assertFalse(gestor.listarDepartamentos().stream()
            .anyMatch(d -> d.getNombre().equals("RRHH")));
}

@Test
public void testAsignarEmpleadoADepartamento() {
    Empleado emp = gestor.crearEmpleadoPermanente("Carlos", 1800);
    gestor.crearDepartamento("Finanzas", "Departamento de finanzas");

    boolean asignado = gestor.asignarEmpleadoADepartamento(emp.getId(), "Finanzas");
    assertTrue(asignado);
    assertEquals("Finanzas", emp.getDepartamento().getNombre());
}

    //  REPORTES 

    @Test
    public void testAgregarReporteEmpleado() {
        EmpleadoPermanente emp = (EmpleadoPermanente) gestor.crearEmpleadoPermanente("Pedro", 2000);

        // Constructor correcto: (int id, Empleado o Departamento, float, float, float)
        ReporteDesempeño reporte = new ReporteDesempeño(
                1,
                emp,
                10f,
                20f,
                30f
        );

        gestor.agregarReporte(reporte);
        assertTrue(gestor.listarReportes().contains(reporte));
    }

    @Test
    public void testAgregarReporteDepartamento() {
        Departamento dept = new Departamento(1, "Marketing", "Dept. Marketing");

        ReporteDesempeño reporte = new ReporteDesempeño(
                2,
                dept,
                5f,
                15f,
                25f
        );

        gestor.agregarReporteDepartamento(reporte);
        assertTrue(gestor.listarReportes().contains(reporte));
    }

    // REPORTE FINAL

@Test
public void testReporteFinal() {
    // Crear algunos empleados
    gestor.crearEmpleadoPermanente("Empleado1", 1000);
    gestor.crearEmpleadoTemporal("Empleado2", 6);

    // Crear departamento (ID ahora es automático)
    gestor.crearDepartamento("Ventas", "Dept. Ventas");

    // Asociar empleado a departamento
    Empleado emp = gestor.crearEmpleadoPermanente("Empleado3", 1500);
    gestor.asignarEmpleadoADepartamento(emp.getId(), "Ventas");

    // Agregar reportes
    ReporteDesempeño repEmp = new ReporteDesempeño(1, emp, 10f, 20f, 30f);
    gestor.agregarReporte(repEmp);

    Departamento dept = gestor.listarDepartamentos().stream()
            .filter(d -> d.getNombre().equals("Ventas"))
            .findFirst().orElse(null);
    assertNotNull(dept);

    ReporteDesempeño repDept = new ReporteDesempeño(2, dept, 5f, 10f, 15f);
    gestor.agregarReporteDepartamento(repDept);

    // Validaciones finales
    assertEquals(2, gestor.listarReportes().size());
    assertTrue(gestor.listarEmpleados().size() >= 3);
    assertTrue(gestor.listarDepartamentos().stream()
            .anyMatch(d -> d.getNombre().equals("Ventas")));
    }
}