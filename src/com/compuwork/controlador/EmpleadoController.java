package com.compuwork.controlador;

import com.compuwork.servicio.GestorEmpresa;
import com.compuwork.modelo.Empleado;
import com.compuwork.modelo.EmpleadoPermanente;
import com.compuwork.modelo.EmpleadoTemporal;
import com.compuwork.vista.VentanaEmpleado;
import java.math.BigDecimal;

import javax.swing.*;

public class EmpleadoController {
    private final VentanaEmpleado vista;
    private final GestorEmpresa servicio;

    public EmpleadoController(VentanaEmpleado vista, GestorEmpresa servicio) {
        this.vista = vista;
        this.servicio = servicio;

        inicializar();
        actualizarTabla();
    }

    private void inicializar() {
        vista.btnAgregar.addActionListener(e -> agregarEmpleado());
        vista.btnEditar.addActionListener(e -> editarEmpleado());
        vista.btnEliminar.addActionListener(e -> eliminarEmpleado());
        vista.btnActualizarLista.addActionListener(e -> actualizarTabla());
    }

    private void actualizarTabla() {
        vista.modeloTabla.setRowCount(0); // limpiar tabla

        for (Empleado emp : servicio.listarEmpleados()) {
            String tipo = (emp instanceof EmpleadoPermanente) ? "Permanente" : "Temporal";
            String extras = "";

            if (emp instanceof EmpleadoPermanente) {
                EmpleadoPermanente p = (EmpleadoPermanente) emp;
                extras = "beneficios: " + p.getBeneficios() + ", contrato: " + p.getTipoContrato();
            } else if (emp instanceof EmpleadoTemporal) {
                EmpleadoTemporal t = (EmpleadoTemporal) emp;
                extras = "meses: " + t.getMesesContrato() + ", proveedor: " + t.getProveedor();
            }

            vista.modeloTabla.addRow(new Object[]{
                emp.getId(),
                emp.getNombre(),
                emp.getApellido(),
                emp.getDocumento(),
                emp.getCargo(),
                emp.getSalario(),
                emp.getEmail(),
                emp.getEstado(),
                emp.getDepartamento(),
                tipo,
                extras
            });
        }
    }

    private void agregarEmpleado() {
        String[] opciones = {"Permanente", "Temporal"};
        int tipo = JOptionPane.showOptionDialog(
            vista,
            "seleccione tipo de empleado",
            "Nuevo Empleado",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );

        if (tipo == JOptionPane.CLOSED_OPTION) return; // Si cierra el diálogo

        String nombre = JOptionPane.showInputDialog(vista, "Nombre:");
        if (nombre == null || nombre.isBlank()) {
            JOptionPane.showMessageDialog(vista, "El nombre no puede estar vacio.");
            return;
        }

        String apellido = JOptionPane.showInputDialog(vista, "Apellido:");
        String documento = JOptionPane.showInputDialog(vista, "Documento:");
        String cargo = JOptionPane.showInputDialog(vista, "Cargo:");
        String salarioStr = JOptionPane.showInputDialog(vista, "Salario:");
        String email = JOptionPane.showInputDialog(vista, "Gmail:");
        double salario = 0;
        try {
            salario = Double.parseDouble(salarioStr);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Debe ingresar un salario valido.");
            return;
        }

        try {
            // Generar ID único desde GestorEmpresa
            int id = servicio.generarIdEmpleado();

            if (tipo == 0) { // Permanente
                String beneficios = JOptionPane.showInputDialog(vista, "Beneficios:");
                String tipoContrato = JOptionPane.showInputDialog(vista, "Tipo de contrato:");

                EmpleadoPermanente emp = new EmpleadoPermanente(
                    id,
                    nombre,
                    beneficios,
                    tipoContrato,
                    BigDecimal.valueOf(salario)
                );
                emp.setApellido(apellido);
                emp.setDocumento(documento);
                emp.setCargo(cargo);
                emp.setEmail(email);

                servicio.agregarEmpleado(emp);

            } else if (tipo == 1) { // Temporal
                String mesesStr = JOptionPane.showInputDialog(vista, "Meses del contrato:");
                String proveedor = JOptionPane.showInputDialog(vista, "Proveedor:");
                int meses = 0;
                try {
                    meses = Integer.parseInt(mesesStr);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(vista, "Meses de contrato invalidos.");
                    return;
                }

                EmpleadoTemporal emp = new EmpleadoTemporal(
                    id,
                    nombre,
                    meses
                );
                emp.setApellido(apellido);
                emp.setDocumento(documento);
                emp.setCargo(cargo);
                emp.setSalario(BigDecimal.valueOf(salario));
                emp.setEmail(email);
                emp.setProveedor(proveedor);

                servicio.agregarEmpleado(emp);
            }

            actualizarTabla();

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage());
        }
    }

    private void editarEmpleado() {
        int fila = vista.tablaEmpleados.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un empleado de la tabla");
            return;
        }

        int id = (int) vista.modeloTabla.getValueAt(fila, 0);
        Empleado emp = servicio.buscarEmpleadoPorId(id);
        if (emp == null) {
            JOptionPane.showMessageDialog(vista, " El Empleado no Se encontro");
            return;
        }

        String nombre = JOptionPane.showInputDialog(vista, "Nombre:", emp.getNombre());
        String apellido = JOptionPane.showInputDialog(vista, "Apellido:", emp.getApellido());
        String documento = JOptionPane.showInputDialog(vista, "Documento:", emp.getDocumento());
        String cargo = JOptionPane.showInputDialog(vista, "Cargo:", emp.getCargo());
        String salarioStr = JOptionPane.showInputDialog(vista, "Salario:", emp.getSalario().toString());
        String email = JOptionPane.showInputDialog(vista, "Email:", emp.getEmail());
        String estado = JOptionPane.showInputDialog(vista, "Estado (Activo/Inactivo):", emp.getEstado());
        String departamento = JOptionPane.showInputDialog(vista, "Departamento:", emp.getDepartamento());

        BigDecimal salario;
        try {
            salario = new BigDecimal(salarioStr);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Salario invalido.");
            return;
        }

        String beneficios = null, tipoContrato = null, proveedor = null;
        Integer mesesContrato = null;

        if (emp instanceof EmpleadoPermanente) {
            EmpleadoPermanente p = (EmpleadoPermanente) emp;
            beneficios =JOptionPane.showInputDialog(vista, "Beneficios:", p.getBeneficios());
            tipoContrato = JOptionPane.showInputDialog(vista, "Tipo de contrato:", p.getTipoContrato());
        } else if (emp instanceof EmpleadoTemporal) {
            EmpleadoTemporal t = (EmpleadoTemporal) emp;
            String mesesStr = JOptionPane.showInputDialog(vista, "Meses de contrato:", t.getMesesContrato());
            try {
                mesesContrato = Integer.parseInt(mesesStr);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(vista, "Meses invalidos.");
                return;
            }
            proveedor = JOptionPane.showInputDialog(vista, "Proveedor:", t.getProveedor());
        }

        servicio.actualizarEmpleadoCompleto(id, nombre, apellido, documento, cargo, salario,
                email, estado, departamento, beneficios, tipoContrato, mesesContrato, proveedor);

        actualizarTabla();
    }

    private void eliminarEmpleado() {
        int fila = vista.tablaEmpleados.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un empleado de la tabla");
            return;
        }

        int id = (int) vista.modeloTabla.getValueAt(fila, 0);
        int confirm = JOptionPane.showConfirmDialog(
            vista,
            "¿Eliminar empleado con ID " + id + "?",
            "Confirmar eliminacion",
            JOptionPane.YES_NO_OPTION
        );
        if (confirm == JOptionPane.YES_OPTION) {
            servicio.eliminarEmpleado(id);
            actualizarTabla();
        }
    }
}
