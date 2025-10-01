package com.compuwork.vista;

import javax.swing.*;
import java.awt.*;

public class FormularioReporteDesempeño extends JDialog {
    private JTextField txtIdEmpleado;
    private JTextField txtProductividad;
    private JTextField txtPuntualidad;
    private JTextField txtObjetivos;

    private JButton btnGuardar;
    private JButton btnCancelar;

    public FormularioReporteDesempeño(JFrame parent) {
        super(parent, "Nuevo deporte de desempeño", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        // === Campos ===
        txtIdEmpleado = new JTextField(10);
        txtProductividad = new JTextField(5);
        txtPuntualidad = new JTextField(5);
        txtObjetivos = new JTextField(5);

        JPanel panelCampos = new JPanel(new GridLayout(4, 2, 10, 10));
        panelCampos.add(new JLabel("ID Empleado:"));
        panelCampos.add(txtIdEmpleado);
        panelCampos.add(new JLabel("Productividad (1-10):"));
        panelCampos.add(txtProductividad);
        panelCampos.add(new JLabel("Puntualidad (1-10):"));
        panelCampos.add(txtPuntualidad);
        panelCampos.add(new JLabel("Objetivos (1-10):"));
        panelCampos.add(txtObjetivos);

        // Botones 
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        //Layout principal 
        setLayout(new BorderLayout(10, 10));
        add(panelCampos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    public JTextField getTxtIdEmpleado() { return txtIdEmpleado; }
    public JTextField getTxtProductividad() { return txtProductividad; }
    public JTextField getTxtPuntualidad() { return txtPuntualidad; }
    public JTextField getTxtObjetivos() { return txtObjetivos; }

    public JButton getBtnGuardar() { return btnGuardar; }
    public JButton getBtnCancelar() { return btnCancelar; }
}
