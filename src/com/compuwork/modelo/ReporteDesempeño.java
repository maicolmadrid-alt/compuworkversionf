package com.compuwork.modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class ReporteDesempeño {
    private int idReporte;
    private Date fecha;
    private float productividad;
    private float puntualidad;
    private float objetivos;
    private float calificacionGlobal;

    private Empleado empleado;
    private Departamento departamento;

    //  Constructor para Empleado 
    public ReporteDesempeño(int idReporte, Empleado empleado, float productividad, float puntualidad, float objetivos) {
        this.idReporte = idReporte;
        this.empleado = empleado;
        this.productividad = productividad;
        this.puntualidad = puntualidad;
        this.objetivos = objetivos;
        this.fecha = new Date();
        this.calificacionGlobal = (productividad + puntualidad + objetivos) / 3f;
    }

    // Constructor para Departamento 
    public ReporteDesempeño(int idReporte, Departamento departamento, float productividad, float puntualidad, float objetivos) {
        this.idReporte = idReporte;
        this.departamento = departamento;
        this.productividad = productividad;
        this.puntualidad = puntualidad;
        this.objetivos = objetivos;
        this.fecha = new Date();
        this.calificacionGlobal = (productividad + puntualidad + objetivos) / 3f;
    }

    //  Exportar a archivo -opcional aun no defino cual usar 
    public File exportar() throws IOException {
        String fileName = "Reporte_" + idReporte + "_" + fecha.getTime() + ".txt";
        File file = new File(fileName);

        try (FileWriter writer = new FileWriter(file)) {
            writer.write("=== Reporte de Desempeño ===\n");
            writer.write("ID Reporte: " + idReporte + "\n");
            writer.write("Fecha: " + fecha + "\n");
            if (empleado != null) {
                writer.write("Empleado: " + empleado.getNombre() + "\n");
            }
            if (departamento != null) {
                writer.write("Departamento: " + departamento.getNombre() + "\n");
            }
            writer.write("Productividad: " + productividad + "\n");
            writer.write("Puntualidad: " + puntualidad + "\n");
            writer.write("Objetivos: " + objetivos + "\n");
            writer.write("Calificación Global: " + calificacionGlobal + " (" + getEvaluacion() + ")\n");
        }

        return file;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public float getCalificacion() {
        return calificacionGlobal;
    }

    public String getEvaluacion() {
        if (calificacionGlobal >= 4.5) return "Excelente";
        if (calificacionGlobal >= 3.5) return "Bueno";
        if (calificacionGlobal >= 2.5) return "Regular";
        return "Deficiente";
    }
    public float getProductividad() {
        return productividad;
}

    public float getPuntualidad() {
        return puntualidad;
}

    public float getObjetivos() {
        return objetivos;
    }

}
