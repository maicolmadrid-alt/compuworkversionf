package com.compuwork.modelo;

public class ReporteDesempeño {
    private Empleado  empleado;
    private String evaluacion;
    private int calificacion; 

    public ReporteDesempeño(Empleado empleado, String evaluacion, int calificacion) {
        this.empleado = empleado;
        this.evaluacion = evaluacion;
        if ( calificacion < 1 || calificacion > 10) {
            throw new IllegalArgumentException("La calificacion debe estar entre 1 y 10");
        }
        this.calificacion = calificacion;
    }

    public Empleado getEmpleado(){return empleado; }
    public String getEvaluacion(){ return evaluacion; }
    public int getCalificacion(){return calificacion; }

    @Override
    public String toString() {
        return "Reporte de " + empleado.getNombre() +
               " - Evaluacion: " + evaluacion +
               " (Calificacion: " + calificacion + " ) ";
    }
}
