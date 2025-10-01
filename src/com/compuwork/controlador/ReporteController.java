package com.compuwork.controlador;

import com.compuwork.modelo.Departamento;
import com.compuwork.servicio.GestorEmpresa;
import com.compuwork.modelo.ReporteDesempeño;
import com.compuwork.modelo.Empleado;
import com.compuwork.vista.FormularioReporteDepartamento;
import com.compuwork.vista.FormularioReporteDesempeño;
import com.compuwork.vista.VentanaReportes;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import java.util.List;

public class ReporteController {
    private final VentanaReportes vista;
    private final GestorEmpresa servicio;

    public ReporteController(VentanaReportes vista, GestorEmpresa servicio) {
        this.vista = vista;
        this.servicio = servicio;

        inicializar();
        actualizarTabla();
        

    }

    private void inicializar() {
        vista.btnCrear.addActionListener(e -> crearReporte());
        vista.btnActualizarLista.addActionListener(e -> actualizarTabla());
        vista.btnExportar.addActionListener(e -> exportarReportes());
        vista.btnCrearDepto.addActionListener(e -> crearReporteDepartamento());


    }
//Sin uso de momento, lo tengo que integrar a futuro
  private void exportarReportes() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("guardar reportes como un csv");
    int userSelection = fileChooser.showSaveDialog(vista);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
        java.io.File fileToSave = fileChooser.getSelectedFile();
        try (java.io.PrintWriter pw = new java.io.PrintWriter(fileToSave)) {
            // Encabezados
            pw.println("Empleado/Evaluado,Evaluacion,Calificacion");

            // Datos desde la tabla
            for (int i = 0; i < vista.modeloTabla.getRowCount(); i++) {
                pw.println(
                        vista.modeloTabla.getValueAt(i, 0) + "," +
                        vista.modeloTabla.getValueAt(i, 1) + "," +
                        vista.modeloTabla.getValueAt(i, 2)
                );
            }

            JOptionPane.showMessageDialog(vista, "Reportes exportados con exito.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al exportar: " + ex.getMessage());
        }
    }
}

    private void actualizarTabla() {
    vista.modeloTabla.setRowCount(0); // limpiar
    List<ReporteDesempeño> reportes = servicio.listarReportes();
    for (ReporteDesempeño r : reportes) {
        String evaluado;
        if (r.getEmpleado() != null) {
            evaluado = "Empleado: " + r.getEmpleado().getNombre();
        } else if (r.getDepartamento() != null) {
            evaluado = "DeptoE: " + r.getDepartamento().getNombre();
        } else {
            evaluado = "N/A";
        }

        vista.modeloTabla.addRow(new Object[]{
            evaluado,
            r.getEvaluacion(),
            r.getCalificacion()
        });
    }
}


   private void crearReporte() {
    if (servicio.listarEmpleados().isEmpty()) {
        JOptionPane.showMessageDialog(vista, "No hay empleados registrados .");
        return;
    }

    FormularioReporteDesempeño form = new FormularioReporteDesempeño(vista);
    
    form.getBtnGuardar().addActionListener(e -> {
        try {
            int idEmpleado = Integer.parseInt(form.getTxtIdEmpleado().getText().trim());
            float productividad = Float.parseFloat(form.getTxtProductividad().getText().trim());
            float puntualidad = Float.parseFloat(form.getTxtPuntualidad().getText().trim());
            float objetivos = Float.parseFloat(form.getTxtObjetivos().getText().trim());

            // Validaciones basicas
            if (productividad < 1 || productividad > 10 ||
                puntualidad < 1 || puntualidad > 10 ||
                objetivos < 1 || objetivos > 10) {
                JOptionPane.showMessageDialog(form, "Todos los valores deben estar entre 1 y 10.");
                return;
            }

            // Crear el reporte usando la clase modelo
            int idReporte = servicio.listarReportes().size() + 1;
            Empleado emp = servicio.buscarEmpleadoPorId(idEmpleado);

            if (emp == null) {
                JOptionPane.showMessageDialog(form, "Empleado no encontrado.");
                return;
            }

            ReporteDesempeño reporte = new ReporteDesempeño(
                idReporte, emp, productividad, puntualidad, objetivos
            );

         
            servicio.agregarReporte(reporte);

            actualizarTabla();
            JOptionPane.showMessageDialog(vista, "Reporte generado con exito.");
            form.dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(form, "Debe ingresar valores numuricos validos.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(form, "Error: " + ex.getMessage());
        }
    });

    form.getBtnCancelar().addActionListener(e -> form.dispose());

    form.setVisible(true);
}

   private void crearReporteDepartamento() {
    if (servicio.listarDepartamentos().isEmpty()) {
        JOptionPane.showMessageDialog(vista, "No hay departamentos registrados.");
        return;
    }

    FormularioReporteDepartamento form = new FormularioReporteDepartamento(vista);

    form.getBtnGuardar().addActionListener(e -> {
        try {
            String nombreDepto = form.getTxtNombreDepto().getText().trim();
            float productividad = Float.parseFloat(form.getTxtProductividad().getText().trim());
            float puntualidad = Float.parseFloat(form.getTxtPuntualidad().getText().trim());
            float objetivos = Float.parseFloat(form.getTxtObjetivos().getText().trim());

            if (productividad < 1 || productividad > 10 ||
                puntualidad < 1 || puntualidad > 10 ||
                objetivos < 1 || objetivos > 10) {
                JOptionPane.showMessageDialog(form, "Valores entre 1 y 10.");
                return;
            }

            Departamento depto = servicio.listarDepartamentos()
                    .stream()
                    .filter(d -> d.getNombre().equalsIgnoreCase(nombreDepto))
                    .findFirst()
                    .orElse(null);

            if (depto == null) {
                JOptionPane.showMessageDialog(form, "Departamento no encontrado.");
                return;
            }

            int idReporte = servicio.listarReportes().size() + 1;
            ReporteDesempeño reporte = new ReporteDesempeño(idReporte, depto, productividad, puntualidad, objetivos);

            servicio.agregarReporte(reporte);
            actualizarTabla();
            JOptionPane.showMessageDialog(vista, "Reporte de departamento creado con exito.");
            form.dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(form, "Debe ingresar numeros vaslidos.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(form, "Error: " + ex.getMessage());
        }
    });

    form.getBtnCancelar().addActionListener(e -> form.dispose());
    form.setVisible(true);
}

}
