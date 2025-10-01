package com.compuwork.controlador;

import com.compuwork.servicio.GestorEmpresa;
import com.compuwork.vista.*;

public class VentanaPrincipalController {
    private final VentanaPrincipal vista;
    private final GestorEmpresa servicio;

    public VentanaPrincipalController(VentanaPrincipal vista, GestorEmpresa servicio) {
        this.vista = vista;
        this.servicio = servicio;

        inicializar();
    }

    private void inicializar() {
        vista.btnEmpleados.addActionListener(e -> {
            VentanaEmpleado ve = new VentanaEmpleado();
            new EmpleadoController(ve, servicio);
            ve.setVisible(true);
        });

        vista.btnDepartamentos.addActionListener(e -> {
            VentanaDepartamento vd = new VentanaDepartamento();
            new DepartamentoController(vd, servicio);
            vd.setVisible(true);
        });

        vista.btnReportes.addActionListener(e -> {
            VentanaReportes vr = new VentanaReportes();
            new ReporteController(vr, servicio);
            vr.setVisible(true);
        });

        vista.btnSalir.addActionListener(e -> System.exit(0));
    }
}
