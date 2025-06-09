# 🏝️ AppKant: Tienda de Planes Turísticos

**AppKant** es una aplicación de escritorio desarrollada en Java con interfaz gráfica basada en `Swing`, diseñada para gestionar y vender planes turísticos. El sistema está dividido en dos módulos principales: **Administrador** y **Recepcionista**, cada uno con sus propias funciones y responsabilidades dentro de la aplicación.

🔗 **Video de demostración:**  
👉 [Ver en YouTube](https://youtu.be/Pp1ZxmI0CH8)

---

## 🧭 Estructura del proyecto

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

## 📁 Gestión de imágenes

AppKant trabaja con **dos tipos de rutas de imagen**:

- 📦 **Imágenes internas**: Las 100 imágenes por defecto se encuentran dentro del proyecto, en la ruta:  
Estas se acceden mediante rutas relativas y **funcionarán en cualquier PC**, incluso después de subir el proyecto a GitHub o empaquetarlo en `.jar`.

- 🖼️ **Imágenes externas**: Cualquier otra imagen agregada desde el equipo se accede mediante **ruta absoluta** (por ejemplo `C:\Usuarios\Imágenes\playa.jpg`). Estas imágenes no se comparten en GitHub ni se empaquetan automáticamente, pero pueden visualizarse correctamente desde cualquier ubicación local.

---

## 💾 Base de datos

La aplicación utiliza **SQLite** como sistema de almacenamiento local. El archivo `.db` se incluye con el proyecto, pero es importante que actualices la ruta en el código si lo descargas en una ubicación diferente.

> 🛠️ **Importante**: Una vez descargado el proyecto, debes ajustar la ruta absoluta de la base de datos en el código fuente. Asegúrate de que el archivo `.db` esté ubicado correctamente o cambia la cadena de conexión como corresponda.

---

## 🔐 Seguridad (Recomendado)

Las contraseñas de los usuarios deberían guardarse usando **hashing con BCrypt**, lo que evita que se vean incluso en la base de datos. (Esta funcionalidad puede implementarse fácilmente si aún no está integrada).

---

## 📌 Tecnologías usadas

- Java 8+
- Swing (`JFrame`, `JPanel`, `JLabel`, etc.)
- JDBC + SQLite
- MVC (modelo simple de capas)

---

## ▶️ Instrucciones para ejecutar el proyecto

1. Clona o descarga este repositorio.
2. Abre el proyecto en NetBeans o tu IDE de preferencia.
3. Ajusta la ruta de conexión a la base de datos si es necesario.
4. Ejecuta el proyecto desde la clase principal (`Main.java`, `Login.java`, etc.).
5. Accede como administrador o recepcionista.

---

## 📷 Capturas del sistema

> *(Aquí puedes insertar imágenes de la interfaz si gustas)*

- 🖼️ Tabla de planes turísticos
- 📋 Panel de selección
- 🔧 Formulario de edición de planes
- 🔒 Pantalla de login

---

## 📽️ Video de demostración

👉 [Ver en YouTube](https://youtu.be/Pp1ZxmI0CH8)

---

## 👥 Colaboradores

Desarrollado por:  
**[Sandoval Reyes Miguel]**  
GitHub: [TuGitHub](https://github.com/TuUsuario)

---

## ✅ Licencia

Este proyecto se distribuye bajo la licencia MIT
