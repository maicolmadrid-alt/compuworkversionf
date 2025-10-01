package com.compuwork.ui;

import com.compuwork.vista.VentanaPrincipal;
import com.compuwork.controlador.VentanaPrincipalController;
import com.compuwork.servicio.GestorEmpresa;

public class TestEmpleadoGUI {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            GestorEmpresa servicio = new GestorEmpresa();
            VentanaPrincipal vista = new VentanaPrincipal();
            new VentanaPrincipalController(vista, servicio);
            vista.setVisible(true);
        });
    }
}
