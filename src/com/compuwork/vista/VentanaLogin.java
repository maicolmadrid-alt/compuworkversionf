package com.compuwork.vista;

import javax.swing.*;

public class VentanaLogin extends JFrame {
    public JTextField txtUsuario;
    public JPasswordField txtPassword;
    public JButton btnLogin;

    public VentanaLogin() {
        setTitle("Login de Usuario");
        setSize(300, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        txtUsuario = new JTextField();
        txtPassword = new JPasswordField();
        btnLogin = new JButton("Iniciar sesion");

        panel.add(new JLabel("Usuario:"));
        panel.add(txtUsuario);
        panel.add(new JLabel("Contraseña:"));
        panel.add(txtPassword);
        panel.add(btnLogin);

        add(panel);
    }
}
