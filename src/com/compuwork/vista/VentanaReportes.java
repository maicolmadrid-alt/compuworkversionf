package com.compuwork.vista;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class VentanaReportes extends JFrame {
    public JTable tablaReportes;
    public DefaultTableModel modeloTabla;

    public JButton btnCrear;
    public JButton btnActualizarLista;
    public JButton btnExportar; // 
    public JButton btnCrearDepto; 

   public VentanaReportes() {
    setTitle("Gestion de Reportes");
    setSize(600, 350);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

 //  Tabla 
    modeloTabla = new DefaultTableModel(
        new Object[]{"Empleado/Departamento", "Evaluacion", "Calificacion"}, 0
    );
    tablaReportes = new JTable(modeloTabla);
    JScrollPane scroll = new JScrollPane(tablaReportes);

   //  Botones 
    btnCrear = new JButton("Crear Reporte Empleado");
    btnCrearDepto = new JButton("Crear Reporte Departamento");
    btnActualizarLista = new JButton("Actualizar Lista");
    btnExportar = new JButton("Exportar");

    JPanel panelBotones = new JPanel();
    panelBotones.add(btnCrear);
    panelBotones.add(btnCrearDepto);      
    panelBotones.add(btnActualizarLista);
    panelBotones.add(btnExportar);

  // Layout 
    setLayout(new BorderLayout());
    add(scroll, BorderLayout.CENTER);
    add(panelBotones, BorderLayout.SOUTH);
}

}
