
# Equipo 18: AppKant Planes Turísticos 🧭

**AppKant** es una aplicación de escritorio desarrollada en **Java** con interfaz gráfica basada en `Swing`, diseñada para gestionar y vender **planes turísticos**. El sistema está dividido en dos módulos principales: **Administrador** y **Recepcionista**, cada uno con sus propias funciones y responsabilidades dentro de la aplicación.

🔗 **Video de demostración:**  
👉 [Ver en YouTube](https://youtu.be/TU_VIDEO)

---

![Pantalla principal](KANT2.png)

## 🧭 Estructura del Proyecto

El sistema se divide en dos capas funcionales:

### 👤 Módulo Recepcionista (`Comprau.java`)
- Visualiza y filtra los planes turísticos disponibles.
- Selecciona uno o varios planes para cotizar.
- Muestra el total acumulado en tiempo real.
- Visualiza la imagen correspondiente del plan seleccionado.
- Permite buscar por nombre o descripción mediante filtros dinámicos.

### 🔐 Módulo Administrador (`Recepcionista.java`)
- Gestiona el catálogo completo de planes turísticos.
- Agrega, edita o elimina planes desde la base de datos.
- Carga imágenes desde rutas internas o externas.
- Permite a los administradores monitorear qué planes están disponibles y sus detalles.

---

## 📁 Gestión de Imágenes

AppKant trabaja con **dos tipos de rutas de imagen**:

- **Imágenes internas:** Recursos incluidos dentro del proyecto, accesibles desde el classpath (`/imagenes/playa.png`, etc.).
- **Imágenes externas:** Archivos cargados desde el equipo local mediante rutas absolutas (por ejemplo: `C:\Usuarios\TuNombre\Imagenes\destino.jpg`).

```java
private ImageIcon cargarIconoImagen(String ruta) throws Exception {
    java.net.URL url = getClass().getResource("/" + ruta);
    if (url != null) return new ImageIcon(url);

    File f = new File(ruta);
    if (f.exists()) return new ImageIcon(f.getAbsolutePath());
    throw new FileNotFoundException("No se encontró la imagen: " + ruta);
}
```

---

## 💾 Base de Datos

AppKant utiliza **SQLite** como sistema de almacenamiento local.

### Tabla: `Planes`

| Campo         | Tipo     | Descripción                               |
|---------------|----------|-------------------------------------------|
| `planesID`    | TEXT     | ID único del plan                         |
| `nombre`      | TEXT     | Nombre del plan turístico                 |
| `descripcion` | TEXT     | Descripción detallada del plan            |
| `precio`      | DOUBLE   | Precio en formato decimal (ej. 1499.99)   |
| `imagen`      | TEXT     | Ruta absoluta o relativa de la imagen     |

---

## 🔍 Filtrado y Selección Dinámica

```java
String query = buscarPorDescripcion
    ? "SELECT * FROM Planes WHERE descripcion LIKE ?"
    : "SELECT * FROM Planes WHERE nombre LIKE ?";

stmt.setString(1, "%" + texto + "%");
ResultSet rs = stmt.executeQuery();
```

Los resultados se cargan dinámicamente en la tabla. Los checkboxes permiten seleccionar uno o varios planes al mismo tiempo.

---

## 🧮 Cálculo de Total Dinámico

```java
checkBox.addActionListener(e -> {
    if (checkBox.isSelected()) {
        if (!idsSeleccionados.contains(id)) {
            idsSeleccionados.add(id);
            preciosSeleccionados.put(id, precio);
        }
    } else {
        idsSeleccionados.remove(id);
        preciosSeleccionados.remove(id);
    }

    double total = preciosSeleccionados.values()
        .stream()
        .mapToDouble(Double::doubleValue)
        .sum();

    totalLabel.setText(String.format("$%.2f", total));
});
```

El total se actualiza automáticamente con cada selección o deselección.

---

## 🖼️ Visualización de Imágenes

```java
public void mostrarImagenEnPanel(String imagenURL, JPanel panelDestino) {
    SwingUtilities.invokeLater(() -> {
        panelDestino.removeAll();
        try {
            if (imagenURL == null || imagenURL.trim().isEmpty()) {
                throw new Exception("Ruta de imagen vacía");
            }

            ImageIcon icono = cargarIconoImagen(imagenURL);
            Image img = icono.getImage().getScaledInstance(
                panelDestino.getWidth(),
                panelDestino.getHeight(),
                Image.SCALE_SMOOTH
            );

            panelDestino.setLayout(new BorderLayout());
            panelDestino.add(new JLabel(new ImageIcon(img), JLabel.CENTER));
        } catch (Exception ex) {
            panelDestino.setLayout(new BorderLayout());
            panelDestino.add(new JLabel("Imagen no disponible", JLabel.CENTER));
        } finally {
            panelDestino.revalidate();
            panelDestino.repaint();
        }
    });
}
```

---

## 🔧 Métodos de Utilidad

```java
private JPanel wrap(Component comp, int ancho) {
    JPanel contenedor = new JPanel(new BorderLayout());
    contenedor.setPreferredSize(new Dimension(ancho, 30));
    contenedor.add(comp, BorderLayout.CENTER);
    return contenedor;
}
```

---

## 📌 Tecnologías Usadas

- Java 8 o superior
- Swing (`JFrame`, `JPanel`, `JButton`, `JLabel`, etc.)
- SQLite + JDBC
- IDE: NetBeans o IntelliJ IDEA
- Arquitectura simple por capas

---

## ▶️ Cómo Ejecutar AppKant

1. Clona este repositorio:

```bash
git clone https://github.com/usuario/AppKant.git
```

2. Abre el proyecto en NetBeans (u otro IDE).
3. Asegúrate de tener Java 8+ instalado.
4. Ajusta la ruta a la base de datos en la clase `Conexion.java` si es necesario.
5. Ejecuta `Recepcionista.java` o `Comprau.java` según el módulo que desees usar.

---

## 📷 Capturas del Sistema

### Vista del Recepcionista
![Tabla de planes turísticos](KANT1.png)

### Selección con Imagen y Total
![Vista dinámica de imagen](KANT2.png)

### Módulo Administrador
![Gestión del administrador](KANT3.png)

---

## 🚀 Funciones Pendientes / Ideas Futuras

- [ ] Generación de tickets en PDF
- [ ] Envío de confirmación por correo electrónico
- [ ] Gestión de usuarios y roles
- [ ] Estadísticas de ventas
- [ ] Interfaz más moderna y responsive

---

## 👨‍💻 Créditos

- **Equipo:** Equipo 18
- **Proyecto escolar:** AppKant — Venta de Planes Turísticos
- **Institución:** [Nombre de tu escuela]
- **Docente:** [Nombre del docente]

---

## 🎥 Video de Demostración

👉 [Ver en YouTube](https://youtu.be/TU_VIDEO)


