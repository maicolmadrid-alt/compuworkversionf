package com.compuwork.vista;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    public JButton btnEmpleados;
    public JButton btnDepartamentos;
    public JButton btnReportes;
    public JButton btnSalir;

    public VentanaPrincipal() {
        setTitle("Sistema Compuwork");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        btnEmpleados = new JButton("Gestion de Empleados");
        btnDepartamentos = new JButton("Gestion de Departamentos");
        btnReportes = new JButton("Gestion de Reportes");
        btnSalir = new JButton("Salir");

         JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(btnEmpleados);
        panel.add(btnDepartamentos);
        panel.add(btnReportes);
        panel.add(btnSalir);

        add(panel);
    }
}
