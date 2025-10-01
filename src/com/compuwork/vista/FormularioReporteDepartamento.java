package com.compuwork.vista;

import javax.swing.*;
import java.awt.*;

public class FormularioReporteDepartamento extends JDialog {
    private JTextField txtNombreDepto;
    private JTextField txtProductividad;
    private JTextField txtPuntualidad;
    private JTextField txtObjetivos;

    private JButton btnGuardar;
    private JButton btnCancelar;

    public FormularioReporteDepartamento(JFrame parent) {
        super(parent, "Nuevo Reporte de Departamento", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        txtNombreDepto = new JTextField(15);
        txtProductividad = new JTextField(5);
        txtPuntualidad = new JTextField(5);
        txtObjetivos = new JTextField(5);

        JPanel panelCampos = new JPanel(new GridLayout(4, 2, 10, 10));
        panelCampos.add(new JLabel("Departamento:"));
        panelCampos.add(txtNombreDepto);
        panelCampos.add(new JLabel("Productividad (1-10):"));
        panelCampos.add(txtProductividad);
        panelCampos.add(new JLabel("Puntualidad (1-10):"));
        panelCampos.add(txtPuntualidad);
        panelCampos.add(new JLabel("Objetivos (1-10):"));
        panelCampos.add(txtObjetivos);

        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        setLayout(new BorderLayout(10, 10));
        add(panelCampos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    public JTextField getTxtNombreDepto() { return txtNombreDepto; }
    public JTextField getTxtProductividad() { return txtProductividad; }
    public JTextField getTxtPuntualidad() { return txtPuntualidad; }
    public JTextField getTxtObjetivos() { return txtObjetivos; }
    public JButton getBtnGuardar() { return btnGuardar; }
    public JButton getBtnCancelar() { return btnCancelar; }
}
