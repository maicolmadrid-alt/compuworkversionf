# 🚀 CompuWork — Sistema de Gestión de Empleados

> **Notas del autor:**
> La primera versión del proyecto fue desarrollada en consola.
> En esta versión final se implementó una **arquitectura MVC** completa con **interfaz gráfica, controladores, servicio centralizado y generación de reportes exportables**.


---

## 📌 Nota sobre el repositorio

Este proyecto fue publicado en un **nuevo repositorio** con el fin de diferenciar de manera clara la **versión inicial** y la **versión final actualizada** del sistema.

La decisión de crear un nuevo repositorio se tomó por dos motivos principales:

1. **Evitar conflictos en la evaluación:**  
   Al momento de actualizar el proyecto, la versión anterior aún no había sido calificada. Subir los cambios directamente podía generar confusión entre la entrega ya presentada y la versión final en desarrollo.

2. **Claridad en la evolución del proyecto:**  
   Mantener ambos repositorios permite distinguir con facilidad el avance del sistema, mostrando de forma transparente la transición entre la primera entrega y la versión final.

📌 Nota adicional:  
Soy consciente del funcionamiento de los repositorios y el control de versiones. Sin embargo, en este caso opté por separar los repositorios para aportar **mayor claridad y simplicidad en el proceso de evaluación académica**.


---

## 📌 1. Clase base `Empleado`
**Objetivo:** representar de manera genérica a cualquier empleado de la empresa.  

**Atributos definidos:**
- `id` (único por empleado)  
- `nombre`, `apellido`  
- `correo`  
- `departamento` (objeto `Departamento`)  

**Métodos implementados:**
- Getters y setters para manipular los atributos.  
- Método abstracto `getDetalles()` → obliga a las subclases a personalizar la información.  

🔹 **Decisión de diseño:** clase abstracta para aplicar **herencia** y **polimorfismo**.  

---

## 📌 2. Subclases de Empleado
### `EmpleadoPermanente`
- **Atributo adicional:** `salario`.  
- Implementación de `getDetalles()` mostrando salario y demás datos.  

### `EmpleadoTemporal`
- **Atributo adicional:** `mesesContrato`.  
- Implementación de `getDetalles()` mostrando duración del contrato.  

✅ **Justificación:** ambos comparten la lógica base de `Empleado`, pero ofrecen detalles personalizados.  

---

## 📌 3. Clase `Departamento`
**Objetivo:** agrupar empleados bajo un mismo nombre de departamento.  

**Atributos:**
- `nombre`  
- `empleados` (lista de empleados asignados)  

**Métodos:**
- `agregarEmpleado(Empleado e)` → asigna y actualiza el departamento.  
- `eliminarEmpleado(Empleado e)` → retira y lo marca como `"Sin asignar"`.  
- `getEmpleados()` → devuelve la lista.  

🔹 **Diseño:** mantiene la consistencia entre empleados y departamentos.  

---

## 📌 4. Servicio central `GestorEmpresa`
**Rol:** núcleo de la lógica de negocio.  

**Responsabilidades:**
- CRUD de empleados y departamentos.  
- Asignar empleados a departamentos.  
- Generar reportes.  
- Validaciones de integridad.  

**Estructura interna:**
- `Map<Integer, Empleado>` → empleados.  
- `Map<String, Departamento>` → departamentos.  
- Contador incremental → IDs automáticos.  

⚠️ **Excepciones controladas:**  
- IDs inexistentes.  
- Departamentos no encontrados.  
- Reportes inválidos.  

---

## 📌 5. Clase `ReporteDesempeño`
**Función:** centralizar la lógica de generación de reportes.  

- Reportes de empleados individuales.  
- Resumen por departamento.  
- Exportación en **CSV** para análisis externo.  

---

## 📌 6. Vistas gráficas (`vista`)
**Objetivo:** interfaz amigable con el usuario.  

**Componentes principales:**
- `LoginForm` → acceso al sistema.  
- `EmpleadoForm` → gestión de empleados.  
- `DepartamentoForm` → gestión de departamentos.  
- `ReporteForm` → generación y exportación de reportes.  

🖥️ **Diseño:** realizado con **Java Swing**, incluye validaciones de campos y manejo de errores.  

---

## 📌 7. Controladores (`controlador`)
**Rol:** gestionar la interacción entre usuario y lógica de negocio.  

**Controladores implementados:**
- `LoginController`  
- `EmpleadoController`  
- `DepartamentoController`  
- `ReporteController`  

**Flujo de trabajo:**  
1. Usuario interactúa con la vista.  
2. El controlador valida la acción.  
3. Se invoca a `GestorEmpresa`.  
4. El modelo se actualiza.  
5. La vista refleja los cambios.  

---

## 📌 8. Pruebas y validaciones
✔️ Creación, edición y eliminación de empleados/departamentos.  
✔️ Prueba de login con credenciales válidas e inválidas.  
✔️ Asignación correcta de empleados a departamentos.  
✔️ Exportación de reportes CSV con datos consistentes.  

---

## 📌 9. Principios aplicados
- **POO:** encapsulación, herencia, polimorfismo.  
- **Patrón de arquitectura:** MVC.  
- **Mantenibilidad:** separación en paquetes (`modelo`, `vista`, `controlador`, `servicio`).  
- **Escalabilidad:** preparado para nuevos tipos de empleados y métricas.  
- **Reutilización:** clases con responsabilidades claras y extensibles.  

---

## ✅ Resultado final
El sistema evolucionó de una **aplicación de consola básica** a un **sistema profesional con interfaz gráfica, controladores, validaciones, servicio centralizado y generación de reportes exportables**.  
