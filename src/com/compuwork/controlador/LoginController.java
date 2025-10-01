package com.compuwork.controlador;

import com.compuwork.modelo.Usuario;
import com.compuwork.servicio.GestorEmpresa;
import com.compuwork.ui.MainSwing;
import com.compuwork.vista.VentanaLogin;
import com.compuwork.vista.VentanaPrincipal;

import javax.swing.*;

public class LoginController {
    private VentanaLogin vista;
    private GestorEmpresa servicio;

    public LoginController(VentanaLogin vista, GestorEmpresa servicio) {
        this.vista = vista;
        this.servicio = servicio;

        this.vista.btnLogin.addActionListener(e -> login());
    }

   private void login() {
    String user = vista.txtUsuario.getText();
    String pass = new String(vista.txtPassword.getPassword());

    Usuario u = servicio.autenticarUsuario(user, pass);
    if (u != null) {
        JOptionPane.showMessageDialog(vista, "Bienvenido " + u.getUsername());
        vista.dispose(); // cerrar login

        // Abrir ventana principal con su controlador
        VentanaPrincipal principal = new VentanaPrincipal();
        new VentanaPrincipalController(principal, servicio);        // inicializa sus botones
        principal.setVisible(true);
    } else {
        JOptionPane.showMessageDialog(vista, "Credenciales invalidas");
    }
}



}
