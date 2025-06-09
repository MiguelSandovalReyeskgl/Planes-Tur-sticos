AppKant: Tienda de Planes Turísticos
====================================

**AppKant** es una aplicación de escritorio desarrollada en **Java** con interfaz gráfica basada en `Swing`, diseñada para gestionar y vender planes turísticos. El sistema está dividido en dos módulos principales: **Administrador** y **Recepcionista**, cada uno con sus propias funciones y responsabilidades dentro de la aplicación.

🔗 **Video de demostración:**  
👉 [Ver en YouTube](https://youtu.be/Pp1ZxmI0CH8)

---

🧭 Estructura del Proyecto
--------------------------

El sistema se divide en dos capas funcionales:

### 👤 Módulo Recepcionista
- Visualiza y filtra los planes turísticos disponibles.
- Selecciona uno o varios planes para cotizar.
- Muestra los totales acumulados.
- Visualiza miniaturas y detalles de imágenes asociadas a los planes.

### 🔐 Módulo Administrador
- Gestiona el catálogo de planes turísticos.
- Agrega, edita o elimina planes.
- Carga imágenes a través de rutas locales absolutas o desde recursos del proyecto.
- Administra usuarios y accesos al sistema.

---

📁 Gestión de Imágenes
----------------------

AppKant trabaja con **dos tipos de rutas de imagen**:

- 📦 **Imágenes internas**: Las 100 imágenes por defecto se encuentran dentro del proyecto en:


Estas se acceden mediante rutas relativas y **funcionarán en cualquier PC**, incluso después de subir el proyecto a GitHub o empaquetarlo como `.jar`.

- 🖼️ **Imágenes externas**: Cualquier otra imagen agregada desde el equipo se accede mediante **ruta absoluta**  
(por ejemplo: `C:\Usuarios\Imágenes\playa.jpg`).  
Estas no se incluyen en el repositorio, pero pueden visualizarse correctamente desde el sistema local.

---

💾 Base de Datos
----------------

La aplicación utiliza **SQLite** como sistema de almacenamiento local.

📌 El archivo `.db` se incluye con el proyecto, pero es importante que actualices la ruta absoluta al archivo si descargas el proyecto en otra ubicación.

> 🛠️ **Importante**:  
> Asegúrate de modificar en el código la ruta del archivo `.db` si es diferente, usando una ruta válida de tu sistema local.

---

🔐 Seguridad (Recomendado)
--------------------------

Las contraseñas de los usuarios **deben guardarse usando hashing (ej. BCrypt)**.  
Esto impide que puedan verse incluso desde la base de datos, mejorando significativamente la seguridad.

Actualmente puedes integrarlo fácilmente si aún no lo has hecho.

---

📌 Tecnologías Usadas
---------------------

- Java 8 o superior
- Swing (`JFrame`, `JPanel`, `JLabel`, `JButton`, etc.)
- JDBC con SQLite
- Arquitectura simple por capas

---

▶️ Instrucciones para Ejecutar el Proyecto
------------------------------------------

1. Clona o descarga este repositorio.
2. Abre el proyecto en NetBeans (u otro IDE de Java).
3. Ajusta la ruta de conexión a la base de datos si es necesario.
4. Ejecuta la clase principal (`Main.java`, `Login.java` o similar).
5. Accede como **Administrador** o **Recepcionista** según corresponda.

---

📷 Capturas del Sistema
-----------------------



```markdown
![Pantalla principal](imagenes/menu_principal.png)
![Tabla de planes](imagenes/tabla_planes.png)
![Gestión de administrador](imagenes/admin_editar.png)

