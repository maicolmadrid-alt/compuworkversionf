package com.compuwork.ui;

import com.formdev.flatlaf.FlatLightLaf;
import com.compuwork.servicio.GestorEmpresa;
import com.compuwork.vista.VentanaLogin;
import com.compuwork.controlador.LoginController;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainSwing {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            // Configurar FlatLaf
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }

            // Inicializar servicio
            GestorEmpresa servicio = new GestorEmpresa();

            // Abrir login primero
            VentanaLogin login = new VentanaLogin();
            new LoginController(login, servicio);
            login.setVisible(true);
        });
    }
}
