package com.compuwork.ui;

import com.compuwork.servicio.GestorEmpresa;
import com.compuwork.modelo.Empleado;
import com.compuwork.modelo.ReporteDesempeño;

import java.util.Scanner;

public class AppMenu {
    
    public static void main(String[] args) {
        GestorEmpresa gestor = new GestorEmpresa();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n Sistema Compuwordk ");
            System.out.println("1.Crear empleado permanente");
            System.out.println("2.crear empleado temporal");
            System.out.println("3.Actualizar empleado ");
            System.out.println("4. eliminar empleado" );
            System.out.println("5. Listar empleados");
            System.out.println("6. Crear departamento ");
            System.out.println("7. Eliminar departamento");
            System.out.println("8. Asignar empleado  a departamento ");
            System.out.println("9.Generar reporte empleado");
            System.out.println(" 10. Generar reporte departamento");
            System.out.println("11. Generar reporte de desempeño" );
            System.out.println("12. Listar reportes de desempeño ");
            System.out.println(" 0. salir" );
            System.out.print("Seleccione una opcion: ");

            
            try {
                int opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.print(" Nombre: ");
                        String n1 = sc.nextLine( );
                        System.out.print("salario: ");
                        double sal = Double.parseDouble(sc.nextLine());
                        Empleado ep = gestor.crearEmpleadoPermanente(n1, sal );
                        System.out.println("Creado: " + ep.getDetalles( ));
                        break;
                    case 2:
                        System.out.print("Nombre: ");
                        String n2 = sc.nextLine();
                        System.out.print("meses de contrato: ");
                        int meses = Integer.parseInt(sc.nextLine() );
                        Empleado et =  gestor.crearEmpleadoTemporal(n2, meses);
                        System.out.println("Creado: " +  et.getDetalles());
                        break;
                    case 3:
                        System.out.print("id empleado: ");
                        int idUpd =  Integer.parseInt(sc.nextLine());
                        System.out.print("Nuevo nombre: ");
                        String newName = sc.nextLine();
                        if (gestor.actualizarEmpleado (idUpd, newName))
                            System.out.println("Empleado actualizado.");
                        else
                            System.out.println("Empleado no encontrado.");
                        break;
                    case 4:
                        System.out.print("ID empleado: ");
                        int idDel = Integer.parseInt(sc.nextLine());
                        if (gestor.eliminarEmpleado(idDel))
                            System.out.println("Empleado eliminado.");
                        else
                            System.out.println("Empleado no encontrado.");
                        break;
                    case 5:
                        System.out.println("-Lista de empleados-");
                        for (Empleado e : gestor.listarEmpleados()) {
                            System.out.println(e.getDetalles());
                        }
                        break;
                    case 6:
                        System.out.print("Nombre del departamento: ");
                        String dep = sc.nextLine();
                        gestor.crearDepartamento(dep);
                        System.out.println("Departamento creado.");
                        break;
                    case 7:
                        System.out.print("Nombre del departamento: ");
                        String depDel = sc.nextLine();
                        if (gestor.eliminarDepartamento(depDel))
                            System.out.println("Departamento eliminado.");
                        else
                            System.out.println("No existe ese departamento.");
                        break;
                        
                    case 8:
                        System.out.print("ID empleado: ");
                        int idEmp = Integer.parseInt(sc.nextLine());
                        System.out.print("departamento: ");
                        String depName = sc.nextLine();
                        if (gestor.asignarEmpleadoADepartamento(idEmp, depName))
                            System.out.println("Empleado asignado.");
                        else
                            System.out.println("Error Verifique ID y Departamento.");
                        break;
                    case 9:
                        System.out.print("ID empleado: ");
                        int idRep = Integer.parseInt(sc.nextLine());
                        System.out.println(gestor.generarReporteEmpleado(idRep));
                        break;
                    case 10:
                        System.out.print("Nombre departamento: ");
                        String depRep = sc.nextLine();
                        System.out.println(gestor.generarReporteDepartamento(depRep));
                        break;
                    case 11:
                        System.out.print("ID empleado: ");
                        int idEval = Integer.parseInt(sc.nextLine());
                        System.out.print("evaluación: ");
                        String eval = sc.nextLine();
                        System.out.print("calificacion (1-10): ");
                        int cal = Integer.parseInt(sc.nextLine());
                        try {
                            ReporteDesempeño rep = gestor.generarReporteDesempeño(idEval, eval, cal);
                            System.out.println("generado: " + rep);
                        } catch (IllegalArgumentException ex) {
                            System.out.println("error: " + ex.getMessage());
                        }
                        
                        break;
                    case 12:
                        System.out.println("=== Reportes de desempeño ===");
                        for (ReporteDesempeño r : gestor.listarReportes()) {
                            System.out.println(r);
                        }
                        
                        break;
                    case 0:
                        System.out.println("saliendo");
                        sc.close();
                        return;
                    default:
                        System.out.println("Opcion no vaslida.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Entrada invalida debe ser un numero.");
            }
        }
    }
}
