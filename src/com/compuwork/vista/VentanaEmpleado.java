package com.compuwork.vista;

import javax.swing.table.DefaultTableModel;

import javax.swing.*;
import java.awt.*;

public class VentanaEmpleado extends JFrame {
   // Tabla para listar empleados
    public JTable tablaEmpleados;
    public DefaultTableModel modeloTabla;

    // Botones 
    public JButton btnAgregar;
    public JButton btnEditar;
    public JButton btnEliminar;
    public JButton btnActualizarLista;

    public VentanaEmpleado() {
        setTitle("Gestion de Empleados");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

  //tabla
modeloTabla = new DefaultTableModel(
    new Object[]{
        "ID", "Nombre", "Apellido", "Documento", "Cargo",
        "Salario", "Email", "Estado", "Departamento",
        "Tipo", "Extras"
    }, 0
);
tablaEmpleados = new JTable(modeloTabla);
JScrollPane scroll = new JScrollPane(tablaEmpleados);

    // Botones 
        btnAgregar = new JButton("Agregar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnActualizarLista = new JButton("Actualizar Lista");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnActualizarLista);

        // Layout 
        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }
}
