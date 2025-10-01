package com.compuwork.vista;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class VentanaDepartamento extends JFrame {
    // Tabla para listar departamentos
    public JTable tablaDepartamentos;
    public DefaultTableModel modeloTabla;

    // Botones
    public JButton btnCrear;
    public JButton btnEliminar;
    public JButton btnVerEmpleados;
    public JButton btnActualizarLista;
    public JButton btnAsignar;

    // Combos ocultos .para seleccionar empleado y depto en controlador
    public JComboBox<String> comboEmpleados;
    public JComboBox<String> comboDepartamentos;

    public VentanaDepartamento() {
        setTitle("Gestión de Departamentos");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        
        //  la tabla 
modeloTabla = new DefaultTableModel(
    new Object[]{"ID", "Nombre", "Descripción", "Cantidad Empleados"}, 0
);
tablaDepartamentos = new JTable(modeloTabla);

        tablaDepartamentos = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaDepartamentos);

        //  Botones 
        btnCrear = new JButton("Crear");
        btnEliminar = new JButton("Eliminar");
        btnVerEmpleados = new JButton("Ver Empleados");
        btnActualizarLista = new JButton("Actualizar Lista");
        btnAsignar = new JButton("Asignar Empleado");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnCrear);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnVerEmpleados);
        panelBotones.add(btnActualizarLista);
        panelBotones.add(btnAsignar);

        // Combos. no se muestran en el frame
        comboEmpleados = new JComboBox<>();
        comboDepartamentos = new JComboBox<>();

        //  Layout
        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }
}
