package com.compuwork.controlador;

import com.compuwork.modelo.AsignacionEmpleado;
import com.compuwork.servicio.GestorEmpresa;
import com.compuwork.modelo.Departamento;
import com.compuwork.modelo.Empleado;
import com.compuwork.vista.VentanaDepartamento;

import javax.swing.*;
import java.util.Collection;

public class DepartamentoController {
    private final VentanaDepartamento vista;
    private final GestorEmpresa servicio;

    public DepartamentoController(VentanaDepartamento vista, GestorEmpresa servicio) {
        this.vista = vista;
        this.servicio = servicio;

        inicializar();
        actualizarTabla();
    }

    private void inicializar() {
        vista.btnCrear.addActionListener(e -> crearDepartamento());
        vista.btnEliminar.addActionListener(e -> eliminarDepartamento());
        vista.btnVerEmpleados.addActionListener(e -> verEmpleadosDepartamento());
        vista.btnActualizarLista.addActionListener(e -> actualizarTabla());
        vista.btnAsignar.addActionListener(e -> asignarEmpleadoADepartamento());
    }

    private void actualizarTabla() {
    vista.modeloTabla.setRowCount(0); // limpiar
    Collection<Departamento> departamentos = servicio.listarDepartamentos();
    for (Departamento d : departamentos) {
        vista.modeloTabla.addRow(new Object[]{
            d.getIdDepartamento(),
            d.getNombre(),
            d.getDescripcion(),
            d.getEmpleados().size()
        });
    }
}


  private void crearDepartamento() {
    try {
        String nombre = JOptionPane.showInputDialog(vista, "Nombre del departamento:");
        if (nombre == null) return;
        nombre = nombre.trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "El nombre no puede estar vacío.");
            return;
        }

        String descripcion = JOptionPane.showInputDialog(vista, "Descripción del departamento:");
        if (descripcion == null) descripcion = "";

        //el id lo debe asignar el servicio automaticamente
        servicio.crearDepartamento(nombre, descripcion);

        actualizarTabla();
        JOptionPane.showMessageDialog(vista, "Departamento creado con éxito.");

    } catch (IllegalArgumentException ex) {
        JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage());
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(vista, "Hubo un error: " + ex.getMessage());
    }
}



   private void eliminarDepartamento() {
    int fila = vista.tablaDepartamentos.getSelectedRow();
    if (fila == -1) {
        JOptionPane.showMessageDialog(vista, "Seleccione un departamento de la tabla");
        return;
    }

    // Cambiar columna 0 (ID) por columna 1 
    String nombre = String.valueOf(vista.modeloTabla.getValueAt(fila, 1));

    int confirm = JOptionPane.showConfirmDialog(
        vista,
        "¿Eliminar el departamento " + nombre + "??",
        "Confirmar eliminacion",
        JOptionPane.YES_NO_OPTION
    );
    if (confirm == JOptionPane.YES_OPTION) {
        servicio.eliminarDepartamento(nombre);
        actualizarTabla();
    }
}


   private void verEmpleadosDepartamento() {
    int fila = vista.tablaDepartamentos.getSelectedRow();
    if (fila == -1) {
        JOptionPane.showMessageDialog(vista, "Seleccione un departamento de la tabla");
        return;
    }

    String nombre = String.valueOf(vista.modeloTabla.getValueAt(fila, 1));

    Departamento d = servicio.listarDepartamentos().stream()
            .filter(dep -> dep.getNombre().equals(nombre))
            .findFirst()
            .orElse(null);

    if (d != null) {
        StringBuilder sb = new StringBuilder("Empleados en " + nombre + ":\n");
        for (AsignacionEmpleado a : d.listarAsignaciones()) {
            sb.append(" - ")
              .append(a.getEmpleado().getDetalles())
              .append(" | Fecha: ").append(a.getFechaAsignacion())
              .append(" | Estado: ").append(a.getEstado())
              .append("\n");
        }
        JOptionPane.showMessageDialog(vista, sb.toString());
    }
}



   private void asignarEmpleadoADepartamento() {
    try {
        if (servicio.listarEmpleados().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "No hay empleados registrados.");
            return;
        }
        if (servicio.listarDepartamentos().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "No hay departamentos registrados.");
            return;
        }

        // Rellenar combo de empleados (id - nombre)
        vista.comboEmpleados.removeAllItems();
        for (Empleado e : servicio.listarEmpleados()) {
            vista.comboEmpleados.addItem(e.getId() + " - " + e.getNombre());
        }

        // Rellenar combo de departamentos (solo nombre porque gestorempresa usa nombre)
        vista.comboDepartamentos.removeAllItems();
        for (Departamento d : servicio.listarDepartamentos()) {
            vista.comboDepartamentos.addItem(d.getNombre());
        }

        // Mostrar selector de empleado
        int optEmp = JOptionPane.showConfirmDialog(
            vista, vista.comboEmpleados, "Seleccione empleado", JOptionPane.OK_CANCEL_OPTION
        );
        if (optEmp != JOptionPane.OK_OPTION) return;

        // Mostrar selector de departamento
        int optDep = JOptionPane.showConfirmDialog(
            vista, vista.comboDepartamentos, "Seleccione departamento", JOptionPane.OK_CANCEL_OPTION
        );
        if (optDep != JOptionPane.OK_OPTION) return;

        String empSel = (String) vista.comboEmpleados.getSelectedItem();
        String depSel = (String) vista.comboDepartamentos.getSelectedItem();

        if (empSel == null || depSel == null) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar empleado y departamento.");
            return;
        }

        // extraer id del empleado (formato: 123 - Nombre)
        int idEmpleado = Integer.parseInt(empSel.split(" - ")[0].trim());

        // Llamada al servicio: PASAR EL NOMBRE del departamento
        boolean asignado = servicio.asignarEmpleadoADepartamento(idEmpleado, depSel);

        if (asignado) {
            JOptionPane.showMessageDialog(vista, "Empleado asignado correctamente.");
            actualizarTabla();
        } else {
            JOptionPane.showMessageDialog(vista, "No se pudo asignar el empleado. Verifique ID y departamento.");
        }

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(vista, "ID de empleado inválido.");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage());
    }
}

}

