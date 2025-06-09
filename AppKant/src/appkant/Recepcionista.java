/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package appkant;
import validador.Validador;
import com.github.lgooddatepicker.components.DatePicker;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;

import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.security.SecureRandom;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.sql.*; 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;

import java.util.Properties;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JCheckBox;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * 
 */
public class Recepcionista extends javax.swing.JFrame {
    
    private Conexion conexionBD;
    private Connection conn;
    /**
     * Creates new form Recepcionista
     */
    public Recepcionista() {
             this.setUndecorated(true);
        initComponents();
            jTabbedPane1.setSelectedComponent(panelInicial);
        conexionBD = new Conexion();
        try {
            conexionBD.conectar();
            conn = conexionBD.obtenerConexion(); // O puedes exponer la conexión desde tu clase
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        cargarPlanesEnComboBox();
        logOutBtn.setOpaque(false);
        logOutBtn.setBorder(new RoundedBorder(20)); 
        panelAddUsers5.setOpaque(false);
        panelAddUsers5.setBorder(new RoundedBorder(20)); 
        panelAddUsers5.setOpaque(false);
        panelAddUsers5.setBorder(new RoundedBorder(20)); 
        panelAddUsers5.setOpaque(false);
        panelAddUsers5.setBorder(new RoundedBorder(20)); 
        planesBtn5.setOpaque(false);
        planesBtn5.setBorder(new RoundedBorder(20)); 
        panelAddUsers6.setOpaque(false);
        panelAddUsers6.setBorder(new RoundedBorder(20)); 
        panelAddUsers6.setOpaque(false);
        panelAddUsers6.setBorder(new RoundedBorder(20)); 
        panelAddUsers6.setOpaque(false);
        panelAddUsers6.setBorder(new RoundedBorder(20)); 
        planesBtn6.setOpaque(false);
        planesBtn6.setBorder(new RoundedBorder(20)); 
      
        agregarListeners();
         construirTablaUsuarios(panelTabla1, buscador1, jRadioButton4, jRadioButton3, jLabel45, jLabel46);
         construirTablaPlanes(panelTabla); 
             buscador.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
    public void insertUpdate(javax.swing.event.DocumentEvent e) { actualizarFiltroPlanes(); }
    public void removeUpdate(javax.swing.event.DocumentEvent e) { actualizarFiltroPlanes(); }
    public void changedUpdate(javax.swing.event.DocumentEvent e) { actualizarFiltroPlanes(); }
});
         
         
        DatePicker datePicker = new DatePicker();
        datePicker.setBounds(50, 220,270,40);// x, y, ancho, alto
        panelAsignarPlanTuristico.setLayout(null);
      //  panelAsignarPlanTuristico.add(datePicker);
        datePicker.setBackground(Color.white);
        datePicker.setBorder(null);
         jComboBox1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        actualizarPrecioDesdeCombo();
        }
        });
     
         
        construirTablaPlanes2(panelTabla2); 

              buscador2.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
    public void insertUpdate(javax.swing.event.DocumentEvent e) { actualizarFiltroPlanes2(); }
    public void removeUpdate(javax.swing.event.DocumentEvent e) { actualizarFiltroPlanes2(); }
    public void changedUpdate(javax.swing.event.DocumentEvent e) { actualizarFiltroPlanes2(); }
});

   
txtImagen.setEditable(false); 

    }  
    private File imagenSeleccionada = null;

private void actualizarFiltroPlanes2() {
    String texto = buscador2.getText().trim(); 
    boolean buscarPorDescripcion = jRadioButton6.isSelected(); 
    filtrarPlanes2(panelTabla2, texto, buscarPorDescripcion);
}







private void actualizarFiltroPlanes() {
    String texto = buscador.getText().trim(); 
    boolean buscarPorDescripcion = jRadioButton1.isSelected(); 
    filtrarPlanes(panelTabla, texto, buscarPorDescripcion);
}

private void filtrarPlanes(JPanel panelPlanes, String texto, boolean buscarPorDescripcion) {
    panelPlanes.setOpaque(true);
    panelPlanes.removeAll();
    panelPlanes.setLayout(new BoxLayout(panelPlanes, BoxLayout.Y_AXIS));

    String[] columnas = {"", "Nombre", "Descripción", "Precio"};
    int[] anchos = {50, 150, 250, 100};

    JPanel header = new JPanel(new GridLayout(1, columnas.length));
    for (int i = 0; i < columnas.length; i++) {
        JLabel lbl = new JLabel(columnas[i], JLabel.CENTER);
        lbl.setFont(new Font("Roboto Medium", Font.BOLD, 14));
        lbl.setPreferredSize(new Dimension(anchos[i], 30));
        header.add(lbl);
    }
    panelPlanes.add(header);

    ButtonGroup grupo = new ButtonGroup();

    Conexion conexion = new Conexion();
    Connection conn = null;

    try {
        conexion.conectar();
        conn = conexion.getConexion();

        String query = "SELECT planesID, nombre, descripcion, precio, imagen FROM Planes ";
        if (buscarPorDescripcion) {
            query += "WHERE descripcion LIKE ?";
        } else {
            query += "WHERE nombre LIKE ?";
        }

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, "%" + texto + "%");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String id = rs.getString("planesID");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            double precio = rs.getDouble("precio");
            String imagenURL = rs.getString("imagen");

            JPanel filaPanel = new JPanel(new GridLayout(1, columnas.length));
            filaPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)));

            JRadioButton radio = new JRadioButton();
            radio.setHorizontalAlignment(SwingConstants.CENTER);
            grupo.add(radio);
            filaPanel.add(wrap(radio, anchos[0]));

            filaPanel.add(wrap(new JLabel(nombre, JLabel.CENTER), anchos[1]));
            filaPanel.add(wrap(new JLabel(descripcion, JLabel.CENTER), anchos[2]));
            filaPanel.add(wrap(new JLabel(String.format("$%.2f", precio), JLabel.CENTER), anchos[3]));

            radio.addActionListener(e -> {
                for (Component comp : panelPlanes.getComponents()) {
                    if (comp instanceof JPanel && comp != header) {
                        comp.setBackground(null);
                    }
                }

                filaPanel.setBackground(new Color(230, 255, 240));
                jLabel32.setText(id);
                jLabel17.setText(nombre);
                total.setText("$" + precio + " MXN");

                mostrarImagenEnPanel(imagenURL, jPanel5);
            });

            panelPlanes.add(filaPanel);
        }

        rs.close();
        stmt.close();

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null,
            "Error al cargar planes:\n" + e.getMessage(),
            "Error de base de datos",
            JOptionPane.ERROR_MESSAGE);
    } finally {
        conexion.cerrar();
    }

    panelPlanes.revalidate();
    panelPlanes.repaint();
}


private java.util.List<String> idsSeleccionados = new ArrayList<>();




private void construirTablaPlanes2(JPanel panelPlanes) {
    panelPlanes.setOpaque(true);
    panelPlanes.removeAll();
    panelPlanes.setLayout(new BoxLayout(panelPlanes, BoxLayout.Y_AXIS));

    String[] columnas = {"", "Nombre", "Descripción", "Precio"};
    int[] anchos = {50, 180, 280, 100};

    JPanel header = new JPanel(new GridLayout(1, columnas.length));
    for (int i = 0; i < columnas.length; i++) {
        JLabel lbl = new JLabel(columnas[i], JLabel.CENTER);
        lbl.setFont(new Font("Roboto Medium", Font.BOLD, 14));
        lbl.setPreferredSize(new Dimension(anchos[i], 30));
        header.add(lbl);
    }
    panelPlanes.add(header);

    idsSeleccionados.clear();
    java.util.Map<String, Double> preciosSeleccionados = new java.util.HashMap<>();

    Conexion conexion = new Conexion();
    Connection conn = null;

    try {
        conexion.conectar();
        conn = conexion.getConexion();

        String query = "SELECT planesID, nombre, descripcion, precio, imagen FROM Planes";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String id = rs.getString("planesID");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            double precio = rs.getDouble("precio");
            String imagenURL = rs.getString("imagen");

            JPanel filaPanel = new JPanel(new GridLayout(1, columnas.length));
            filaPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)));

            JCheckBox checkBox = new JCheckBox();
            checkBox.setName(id);
            checkBox.setHorizontalAlignment(SwingConstants.CENTER);
            filaPanel.add(wrap2(checkBox, anchos[0]));

            filaPanel.add(wrap2(new JLabel(nombre, JLabel.CENTER), anchos[1]));
            filaPanel.add(wrap2(new JLabel(descripcion, JLabel.CENTER), anchos[2]));
            filaPanel.add(wrap2(new JLabel(String.format("$%.2f", precio), JLabel.CENTER), anchos[3]));

            // Acción del checkbox
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

                jLabel33.setText(id); // opcional

                mostrarImagenEnPanel2(imagenURL, imaPanel);

                double total = preciosSeleccionados.values().stream().mapToDouble(Double::doubleValue).sum();
                total1.setText(String.format("$%.2f", total));

                for (Component comp : panelPlanes.getComponents()) {
                    if (comp instanceof JPanel && comp != header) {
                        comp.setBackground(null);
                    }
                }
                filaPanel.setBackground(new Color(230, 255, 240));
            });

            panelPlanes.add(filaPanel);
        }

        rs.close();
        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null,
            "Error al cargar planes:\n" + e.getMessage(),
            "Error de base de datos",
            JOptionPane.ERROR_MESSAGE);
    } finally {
        conexion.cerrar();
    }

    panelPlanes.revalidate();
    panelPlanes.repaint();
}




public void mostrarImagenEnPanel2(String imagenURL, JPanel panelDestino) {
    SwingUtilities.invokeLater(() -> {
        panelDestino.removeAll();
        try {
            if (imagenURL == null || imagenURL.trim().isEmpty()) {
                throw new Exception("Ruta de imagen vacía");
            }

            ImageIcon icono = cargarIconoImagen(imagenURL);     // 👈🏼 helper
            Image img = icono.getImage().getScaledInstance(
                    panelDestino.getWidth()  > 0 ? panelDestino.getWidth()  : 200,
                    panelDestino.getHeight() > 0 ? panelDestino.getHeight() : 150,
                    Image.SCALE_SMOOTH);

            panelDestino.setLayout(new BorderLayout());
            panelDestino.add(new JLabel(new ImageIcon(img), JLabel.CENTER));
        } catch (Exception ex) {
            panelDestino.setLayout(new BorderLayout());
            panelDestino.add(new JLabel("Imagen no disponible", JLabel.CENTER));
            System.out.println("Error al cargar imagen: " + ex.getMessage());
        } finally {
            panelDestino.revalidate();
            panelDestino.repaint();
        }
    });
}





private JPanel wrap2(Component comp, int ancho) {
    JPanel contenedor = new JPanel(new BorderLayout());
    contenedor.setPreferredSize(new Dimension(ancho, 30));
    contenedor.add(comp, BorderLayout.CENTER);
    return contenedor;
}

private void filtrarPlanes2(JPanel panelPlanes, String texto, boolean buscarPorDescripcion) {
    panelPlanes.setOpaque(true);
    panelPlanes.removeAll();
    panelPlanes.setLayout(new BoxLayout(panelPlanes, BoxLayout.Y_AXIS));
    panelPlanes.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

    String[] columnas = {"", "Nombre", "Descripción", "Precio"};
    int[] anchos = {50, 180, 280, 100};

    JPanel header = new JPanel(new GridLayout(1, columnas.length, 5, 0));
    header.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
    header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

    for (int i = 0; i < columnas.length; i++) {
        JLabel lbl = new JLabel(columnas[i], JLabel.CENTER);
        lbl.setFont(new Font("Roboto Medium", Font.BOLD, 14));
        lbl.setPreferredSize(new Dimension(anchos[i], 30));
        header.add(lbl);
    }
    panelPlanes.add(header);

    idsSeleccionados.clear();
    java.util.Map<String, Double> preciosSeleccionados = new java.util.HashMap<>();

    Conexion conexion = new Conexion();
    Connection conn = null;

    try {
        conexion.conectar();
        conn = conexion.getConexion();

        String query = "SELECT planesID, nombre, descripcion, precio, imagen FROM Planes ";
        query += buscarPorDescripcion ? "WHERE descripcion LIKE ?" : "WHERE nombre LIKE ?";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, "%" + texto + "%");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String id = rs.getString("planesID");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            double precio = rs.getDouble("precio");
            String imagenURL = rs.getString("imagen");

            JPanel filaPanel = new JPanel(new GridLayout(1, columnas.length, 5, 0));
            filaPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
            filaPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

            JCheckBox checkBox = new JCheckBox();
            checkBox.setName(id);
            checkBox.setHorizontalAlignment(SwingConstants.CENTER);
            filaPanel.add(wrap(checkBox, anchos[0]));

            filaPanel.add(wrap(new JLabel(nombre, JLabel.CENTER), anchos[1]));
            filaPanel.add(wrap(new JLabel(descripcion, JLabel.CENTER), anchos[2]));
            filaPanel.add(wrap(new JLabel(String.format("$%.2f", precio), JLabel.CENTER), anchos[3]));

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

                mostrarImagenEnPanel2(imagenURL, imaPanel);

                double total = preciosSeleccionados.values().stream().mapToDouble(Double::doubleValue).sum();
                total1.setText(String.format("$%.2f", total));

                for (Component comp : panelPlanes.getComponents()) {
                    if (comp instanceof JPanel && comp != header) {
                        comp.setBackground(null);
                    }
                }
                filaPanel.setBackground(new Color(230, 255, 240));
            });

            panelPlanes.add(filaPanel);
        }

        rs.close();
        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null,
            "Error al cargar planes:\n" + e.getMessage(),
            "Error de base de datos",
            JOptionPane.ERROR_MESSAGE);
    } finally {
        conexion.cerrar();
    }

    panelPlanes.revalidate();
    panelPlanes.repaint();
}







private void construirTablaPlanes(JPanel panelPlanes) {
    panelPlanes.setOpaque(true);
    panelPlanes.removeAll();
    panelPlanes.setLayout(new BoxLayout(panelPlanes, BoxLayout.Y_AXIS));

    String[] columnas = {"", "Nombre", "Descripción", "Precio"}; 
    int[] anchos = {50, 150, 250, 100};

    JPanel header = new JPanel(new GridLayout(1, columnas.length));
    for (int i = 0; i < columnas.length; i++) {
        JLabel lbl = new JLabel(columnas[i], JLabel.CENTER);
        lbl.setFont(new Font("Roboto Medium", Font.BOLD, 14));
        lbl.setPreferredSize(new Dimension(anchos[i], 30));
        header.add(lbl);
    }
    panelPlanes.add(header);

    ButtonGroup grupo = new ButtonGroup();

    Conexion conexion = new Conexion();
    Connection conn = null;

    try {
        conexion.conectar();
        conn = conexion.getConexion();

        String query = "SELECT planesID, nombre, descripcion, precio, imagen FROM Planes";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String id = rs.getString("planesID");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            double precio = rs.getDouble("precio");
            String imagenURL = rs.getString("imagen");

            JPanel filaPanel = new JPanel(new GridLayout(1, columnas.length));
            filaPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)));

            JRadioButton radio = new JRadioButton();
            radio.setHorizontalAlignment(SwingConstants.CENTER);
            grupo.add(radio);
            filaPanel.add(wrap(radio, anchos[0]));

            filaPanel.add(wrap(new JLabel(nombre, JLabel.CENTER), anchos[1]));
            filaPanel.add(wrap(new JLabel(descripcion, JLabel.CENTER), anchos[2]));
            filaPanel.add(wrap(new JLabel(String.format("$%.2f", precio), JLabel.CENTER), anchos[3]));

            radio.addActionListener(e -> {
                for (Component comp : panelPlanes.getComponents()) {
                    if (comp instanceof JPanel && comp != header) {
                        comp.setBackground(null);
                    }
                }

                filaPanel.setBackground(new Color(230, 255, 240));
                jLabel32.setText(id);
                jLabel17.setText(nombre);
                total.setText("$" + precio + " MXN");
                mostrarImagenEnPanel(imagenURL, jPanel5);
            });

            panelPlanes.add(filaPanel);
        }

        rs.close();
        stmt.close();

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null,
            "Error al cargar planes:\n" + e.getMessage(),
            "Error de base de datos",
            JOptionPane.ERROR_MESSAGE);
    } finally {
        conexion.cerrar();
    }

    panelPlanes.revalidate();
    panelPlanes.repaint();
}


public void mostrarImagenEnPanel(String imagenURL, JPanel panelDestino) {
    SwingUtilities.invokeLater(() -> {
        panelDestino.removeAll();                // limpiamos de antemano
        try {
            if (imagenURL == null || imagenURL.trim().isEmpty()) {
                throw new Exception("Ruta de imagen vacía");
            }

            ImageIcon icon = cargarIconoImagen(imagenURL);   // 👈🏼 NUEVO helper

            // Redimensionamos respetando proporción
            Image img = icon.getImage().getScaledInstance(
                    panelDestino.getWidth()  > 0 ? panelDestino.getWidth()  : 200,
                    panelDestino.getHeight() > 0 ? panelDestino.getHeight() : 150,
                    Image.SCALE_SMOOTH);

            panelDestino.setLayout(new BorderLayout());
            panelDestino.add(new JLabel(new ImageIcon(img), JLabel.CENTER));
        } catch (Exception ex) {
            // Si hubo error mostramos texto de “no disponible”
            panelDestino.setLayout(new BorderLayout());
            panelDestino.add(new JLabel("Imagen no disponible", JLabel.CENTER));
            System.out.println("Error al cargar imagen: " + ex.getMessage());
        } finally {
            panelDestino.revalidate();
            panelDestino.repaint();
        }
    });
}

/**
 * Devuelve un ImageIcon ya cargado desde:
 * 1) Recurso dentro del jar (imagenesturismo/…)
 * 2) Archivo absoluto en disco
 * 3) Archivo relativo al directorio de trabajo
 */


/**
 * Devuelve un ImageIcon ya cargado desde:
 * 1) Recurso dentro del jar (imagenesturismo/…)
 * 2) Archivo absoluto en disco
 * 3) Archivo relativo al directorio de trabajo
 */
private ImageIcon cargarIconoImagen(String ruta) throws Exception {
    // 1) ¿Está en el classpath dentro de /imagenesturismo ?
    java.net.URL url = getClass().getResource("/" + ruta);
    if (url != null) {
        return new ImageIcon(url);
    }

    java.io.File f = new java.io.File(ruta);

    // 2) Si la ruta es absoluta y el archivo existe → lo cargamos
    if (f.isAbsolute() && f.exists()) {
        return new ImageIcon(f.getAbsolutePath());
    }

    // 3) Último intento: ruta relativa al directorio de ejecución
    if (!f.isAbsolute()) {
        f = new java.io.File(System.getProperty("user.dir"), ruta);
        if (f.exists()) {
            return new ImageIcon(f.getAbsolutePath());
        }
    }

    // Si llegamos aquí, no se encontró la imagen
    throw new java.io.FileNotFoundException("No se encontró la imagen: " + ruta);
}


private JPanel wrap(Component comp, int ancho) {
    JPanel contenedor = new JPanel(new BorderLayout());
    contenedor.setPreferredSize(new Dimension(ancho, 30));
    contenedor.add(comp, BorderLayout.CENTER);
    return contenedor;
}

 /*
  private JPanel wrap(Component comp, int ancho) {
    JPanel contenedor = new JPanel(new BorderLayout());
    contenedor.setPreferredSize(new Dimension(ancho, 30));
    contenedor.add(comp, BorderLayout.CENTER);
    return contenedor;
}
 */
 /*
 private void construirTablaPlanes(JPanel panelPlanes) {
    panelPlanes.setOpaque(true);
    panelPlanes.removeAll();
    panelPlanes.setLayout(new BoxLayout(panelPlanes, BoxLayout.Y_AXIS));

    String[] columnas = {"", "ID", "Nombre", "Descripción", "Precio", "Imagen"};
    int[] anchos = {50, 50, 150, 250, 100, 110}; // Aumentamos ancho para la imagen

    JPanel header = new JPanel(new GridLayout(1, columnas.length));
    for (int i = 0; i < columnas.length; i++) {
        JLabel lbl = new JLabel(columnas[i], JLabel.CENTER);
        lbl.setFont(new Font("Roboto Medium", Font.BOLD, 14));
        lbl.setPreferredSize(new Dimension(anchos[i], 30));
        header.add(lbl);
    }
    panelPlanes.add(header);

    ButtonGroup grupo = new ButtonGroup();

    Conexion conexion = new Conexion();
    Connection conn = null;

    try {
        conexion.conectar();
        conn = conexion.getConexion();

        String query = "SELECT planesID, nombre, descripcion, precio, imagen FROM Planes";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String id = rs.getString("planesID");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            double precio = rs.getDouble("precio");
            String imagenURL = rs.getString("imagen"); // Ruta de la imagen

            JPanel filaPanel = new JPanel(new GridLayout(1, columnas.length));
            filaPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)));

            JRadioButton radio = new JRadioButton();
            radio.setHorizontalAlignment(SwingConstants.CENTER);
            grupo.add(radio);
            filaPanel.add(wrap(radio, anchos[0]));

            filaPanel.add(wrap(new JLabel(id, JLabel.CENTER), anchos[1]));
            filaPanel.add(wrap(new JLabel(nombre, JLabel.CENTER), anchos[2]));
            filaPanel.add(wrap(new JLabel(descripcion, JLabel.CENTER), anchos[3]));
            filaPanel.add(wrap(new JLabel(String.format("$%.2f", precio), JLabel.CENTER), anchos[4]));

            
            JLabel lblImagen = new JLabel("Cargando...", JLabel.CENTER);

           
            new SwingWorker<ImageIcon, Void>() {
                @Override
                protected ImageIcon doInBackground() throws Exception {
                    ImageIcon icon = new ImageIcon(imagenURL);
                    Image img = icon.getImage().getScaledInstance(70, 50, Image.SCALE_SMOOTH); // Tamaño ajustado
                    return new ImageIcon(img);
                }

                @Override
                protected void done() {
                    try {
                        lblImagen.setIcon(get());
                        lblImagen.setText(""); 
                    } catch (Exception e) {
                        lblImagen.setText("Sin imagen");
                    }
                }
            }.execute();

            filaPanel.add(wrap(lblImagen, anchos[5]));

            radio.addActionListener(e -> {
                for (Component comp : panelPlanes.getComponents()) {
                    if (comp instanceof JPanel && comp != header) {
                        comp.setBackground(null);                        
                    }
                    
                }
                
                filaPanel.setBackground(new Color(230, 255, 240));
                 jLabel32.setText(id);
                 jLabel17.setText(nombre);
                 total.setText("$"+String.valueOf(precio)+" MXN");
            });

            panelPlanes.add(filaPanel);
        }

        rs.close();
        stmt.close();

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null,
            "Error al cargar planes:\n" + e.getMessage(),
            "Error de base de datos",
            JOptionPane.ERROR_MESSAGE);
    } finally {
        conexion.cerrar();
    }

    panelPlanes.revalidate();
    panelPlanes.repaint();
}
 private JPanel wrap(Component comp, int ancho) {
    JPanel contenedor = new JPanel(new BorderLayout());
    contenedor.setPreferredSize(new Dimension(ancho, 30));
    contenedor.add(comp, BorderLayout.CENTER);
    return contenedor;
}
 */
 

    private void agregarListeners() {
    buscador.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
        public void insertUpdate(javax.swing.event.DocumentEvent e) { actualizar(); }
        public void removeUpdate(javax.swing.event.DocumentEvent e) { actualizar(); }
        public void changedUpdate(javax.swing.event.DocumentEvent e) { actualizar(); }
    });
}
    private void actualizar() {
    if (!jRadioButton2.isSelected() && !jRadioButton1.isSelected()) {
        jRadioButton2.setSelected(true); 
    }

    construirTablaUsuarios(panelTabla1, buscador1, jRadioButton4, jRadioButton3, jLabel45, jLabel46);
}
private void actualizarPrecioDesdeCombo() {
     
String nombrePlan = (String) jComboBox1.getSelectedItem();

if (nombrePlan == null || conn == null) return;
try {
       conexionBD = new Conexion();
conexionBD.conectar();
conn = conexionBD.getConexion();
    String sql = "SELECT precio FROM Planes WHERE nombre = ?";
    PreparedStatement stmt = conn.prepareStatement(sql);
    stmt.setString(1, nombrePlan);
    ResultSet rs = stmt.executeQuery();

    if (rs.next()) {
        double precioMXN = rs.getDouble("precio");
        total.setText("$" + String.format("%.2f", precioMXN) + " MXN");
     
    }

    rs.close();
    stmt.close();
} catch (SQLException e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Error al obtener el precio del plan.", "Error", JOptionPane.ERROR_MESSAGE);
}
}

    public void cargarPlanesEnComboBox() {
        
        try {
             String url="C:\\Users\\migue\\Music\\KantDBNew.db";
        Connection con = DriverManager.getConnection("jdbc:sqlite:"+url);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT nombre FROM Planes"); // ajusta el nombre de la tabla y columna

        jComboBox1.removeAllItems(); // limpia el combo

        while (rs.next()) {
            jComboBox1.addItem(rs.getString("nombre"));
        }

        rs.close();
        st.close();
        con.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al cargar planes turísticos: " + e.getMessage());
    }
        actualizarPrecioDesdeCombo();
        }
    

    
    
    
    
    
    
    
   
    
    
/*
private void construirTablaUsuarios(JPanel panelUsuarios, JTextField buscador, JRadioButton rbNombre, JRadioButton rbId, JLabel jLabel17, JLabel jLabel18) {
    panelUsuarios.removeAll();
    panelUsuarios.setLayout(new BoxLayout(panelUsuarios, BoxLayout.Y_AXIS));

    String[] columnas = {"", "ID", "Nombre", "Usuario", "Correo", "Dirección", "Rol"};
    JPanel header = new JPanel(new GridLayout(1, columnas.length));
    for (String col : columnas) {
        JLabel lbl = new JLabel(col, JLabel.CENTER);
        lbl.setFont(new Font("Roboto Medium", Font.BOLD, 14));
        header.add(lbl);
    }
    panelUsuarios.add(header);

    ButtonGroup grupo = new ButtonGroup();

    String filtro = buscador.getText().trim().toLowerCase();
    boolean porNombre = rbNombre.isSelected();
    boolean porId = rbId.isSelected();

    Conexion conexion = new Conexion();
    Connection conn = null;

    try {
        conexion.conectar();
        conn = conexion.getConexion();

        StringBuilder sql = new StringBuilder("SELECT u.UsuarioID, u.Nombre, u.ClaveUsuario, c.Correo, c.Direccion, r.descripcion " +
                                              "FROM Usuarios u " +
                                              "LEFT JOIN Contactos c ON u.UsuarioID = c.UsuarioID " +
                                              "LEFT JOIN Roles r ON u.rolID = r.rolID " );

        if (!filtro.isEmpty()) {
            if (porNombre) {
                sql.append(" AND LOWER(u.Nombre) LIKE ?");
            } else if (porId) {
                sql.append(" AND CAST(u.UsuarioID AS TEXT) LIKE ?");
            }
        }

        PreparedStatement stmt = conn.prepareStatement(sql.toString());

        if (!filtro.isEmpty()) {
            stmt.setString(1, "%" + filtro + "%");
        }

        ResultSet rs = stmt.executeQuery();
        boolean hayResultados = false;

        while (rs.next()) {
            String id = rs.getString("UsuarioID");
            String nombre = rs.getString("Nombre");
            String claveUsuario = rs.getString("ClaveUsuario");
            String correo = rs.getString("Correo");
            String direccion = rs.getString("Direccion");
            String rol = rs.getString("descripcion");

            JPanel filaPanel = new JPanel(new GridLayout(1, columnas.length,0,0));
            filaPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(1, 174, 110)));

            JRadioButton radio = new JRadioButton();
            radio.setHorizontalAlignment(SwingConstants.CENTER);
            grupo.add(radio);
            filaPanel.add(radio);

            filaPanel.add(new JLabel(id != null ? id : "", JLabel.CENTER)); // ID
            filaPanel.add(new JLabel(nombre != null ? nombre : "", JLabel.CENTER));
            filaPanel.add(new JLabel(claveUsuario != null ? claveUsuario : "", JLabel.CENTER));
            filaPanel.add(new JLabel(correo != null ? correo : "", JLabel.CENTER));
            filaPanel.add(new JLabel(direccion != null ? direccion : "", JLabel.CENTER));
            filaPanel.add(new JLabel(rol != null ? rol : "", JLabel.CENTER));

            radio.addActionListener(e -> {
                for (Component comp : panelUsuarios.getComponents()) {
                    if (comp instanceof JPanel && comp != header) comp.setBackground(null);
                }
                filaPanel.setBackground(new Color(230, 255, 240));
                jLabel17.setText(nombre);
                jLabel18.setText(claveUsuario);
            });

            panelUsuarios.add(filaPanel);
            hayResultados = true;
        }

        if (!hayResultados) {
            JPanel empty = new JPanel();
            empty.add(new JLabel("No se encontraron usuarios."));
            panelUsuarios.add(empty);
        }

        rs.close();
        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al cargar usuarios:\n" + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
    } finally {
        conexion.cerrar();
    }

    panelUsuarios.revalidate();
    panelUsuarios.repaint();
}
*/
private void construirTablaUsuarios(JPanel panelUsuarios, JTextField buscador, JRadioButton rbNombre, JRadioButton rbId, JLabel jLabel17, JLabel jLabel18) {
    
    panelUsuarios.removeAll();
    panelUsuarios.setLayout(new BoxLayout(panelUsuarios, BoxLayout.Y_AXIS));

    String[] columnas = {"", "ID", "Nombre", "Telefono", "Correo", "Dirección", "Rol"};
    int[] anchos = {200, 200, 200, 200, 200, 200, 200};

    JPanel header = new JPanel();
    header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
    for (int i = 0; i < columnas.length; i++) {
        JLabel lbl = new JLabel(columnas[i], JLabel.CENTER);
        lbl.setFont(new Font("Roboto Medium", Font.BOLD, 14));
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setPreferredSize(new Dimension(anchos[i], 25));
        wrapper.setMaximumSize(new Dimension(anchos[i], 25));
        wrapper.add(lbl, BorderLayout.CENTER);
        header.add(wrapper);
    }
    panelUsuarios.add(header);

    ButtonGroup grupo = new ButtonGroup();
    String filtro = buscador.getText().trim().toLowerCase();
    boolean porNombre = rbNombre.isSelected();
    boolean porId = rbId.isSelected();

    Conexion conexion = new Conexion();
    Connection conn = null;

    try {
        conexion.conectar();
        conn = conexion.getConexion();

        StringBuilder sql = new StringBuilder(
            "SELECT u.UsuarioID, u.Nombre, u.Telefono, u.Correo, u.Direccion, r.nombre_rol " +
            "FROM Usuarios u " +
            "INNER JOIN Roles r ON u.rol = r.rolID"
        );

        if (!filtro.isEmpty()) {
            sql.append(" WHERE ");
            if (porNombre) {
                sql.append("LOWER(u.Nombre) LIKE ?");
            } else if (porId) {
                sql.append("CAST(u.UsuarioID AS TEXT) LIKE ?");
            }
        }

        PreparedStatement stmt = conn.prepareStatement(sql.toString());
        if (!filtro.isEmpty()) {
            stmt.setString(1, "%" + filtro + "%");
        }

        ResultSet rs = stmt.executeQuery();
        boolean hayResultados = false;

        while (rs.next()) {
            String id = rs.getString("UsuarioID");
            String nombre = rs.getString("Nombre");
            String telefono = rs.getString("Telefono");
            String correo = rs.getString("Correo");
            String direccion = rs.getString("Direccion");
            String rolNombre = rs.getString("nombre_rol");

            JPanel filaPanel = new JPanel();
            filaPanel.setLayout(new BoxLayout(filaPanel, BoxLayout.X_AXIS));
            filaPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(1, 174, 110)));

            JRadioButton radio = new JRadioButton();
            radio.setHorizontalAlignment(SwingConstants.CENTER);
            grupo.add(radio);
            filaPanel.add(crearCelda(radio, anchos[0]));

            filaPanel.add(crearCelda(new JLabel(id != null ? id : "", JLabel.CENTER), anchos[1]));
            filaPanel.add(crearCelda(new JLabel(nombre != null ? nombre : "", JLabel.CENTER), anchos[2]));
            filaPanel.add(crearCelda(new JLabel(telefono != null ? telefono : "", JLabel.CENTER), anchos[3]));
            filaPanel.add(crearCelda(new JLabel(correo != null ? correo : "", JLabel.CENTER), anchos[4]));
            filaPanel.add(crearCelda(new JLabel(direccion != null ? direccion : "", JLabel.CENTER), anchos[5]));
            filaPanel.add(crearCelda(new JLabel(rolNombre != null ? rolNombre : "", JLabel.CENTER), anchos[6]));

            radio.addActionListener(e -> {
                for (Component comp : panelUsuarios.getComponents()) {
                    if (comp instanceof JPanel && comp != header) comp.setBackground(null);
                }
                filaPanel.setBackground(new Color(230, 255, 240));
                jLabel17.setText(nombre);
                jLabel18.setText(telefono);
                jLabel56.setText(id); // asegúrate de tener este label definido
            });

            panelUsuarios.add(filaPanel);
            hayResultados = true;
        }

        if (!hayResultados) {
            JPanel empty = new JPanel();
            empty.add(new JLabel("No se encontraron usuarios."));
            panelUsuarios.add(empty);
        }

        rs.close();
        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al cargar usuarios:\n" + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
    } finally {
        conexion.cerrar();
    }

    panelUsuarios.revalidate();
    panelUsuarios.repaint();
}


private JPanel crearCelda(JComponent componente, int ancho) {
    JPanel celda = new JPanel(new BorderLayout());
    celda.setPreferredSize(new Dimension(ancho, 25));
    celda.setMaximumSize(new Dimension(ancho, 25));
    celda.add(componente, BorderLayout.CENTER);
    return celda;
}











/*
    String[] columnas = {"", "ID", "Nombre", "Telefono", "Correo", "Dirección", "Rol"};*/
private void construirTablaUsuarios1(JPanel panelUsuarios) {
    panelUsuarios.setOpaque(true);
    panelUsuarios.removeAll();
    panelUsuarios.setLayout(new BoxLayout(panelUsuarios, BoxLayout.Y_AXIS));

    String[] columnas = {"", "ID", "Nombre", "Telefono", "Correo", "Dirección", "Rol"};
    JPanel header = new JPanel(new GridLayout(1, columnas.length));
    for (String titulo : columnas) {
        JLabel lbl = new JLabel(titulo, JLabel.CENTER);
        lbl.setFont(new Font("Roboto Medium", Font.BOLD, 14));
        header.add(lbl);
    }
    panelUsuarios.add(header);

    ButtonGroup grupo = new ButtonGroup();

    Conexion conexion = new Conexion();
    Connection conn = null;

    try {
        conexion.conectar();
        conn = conexion.getConexion();

        String query = "SELECT u.UsuarioID, u.Nombre, u.ClaveUsuario, c.Correo, c.Direccion, r.descripcion " +
                       "FROM Usuarios u " +
                       "LEFT JOIN Contactos c ON u.UsuarioID = c.UsuarioID " +
                       "LEFT JOIN Roles r ON u.rolID = r.rolID " +
                       "WHERE u.rolID != 1 AND u.rolID != 5";

        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String id = rs.getString("UsuarioID");
            String nombre = rs.getString("Nombre");
            String usuario = rs.getString("ClaveUsuario");
            String correo = rs.getString("Correo");
            String direccion = rs.getString("Direccion");
            String rol = rs.getString("descripcion");

            JPanel filaPanel = new JPanel(new GridLayout(1, columnas.length));
            filaPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

            JRadioButton radio = new JRadioButton();
            radio.setHorizontalAlignment(SwingConstants.CENTER);
            grupo.add(radio);
            filaPanel.add(radio);

            filaPanel.add(new JLabel(id != null ? id : "", JLabel.CENTER));
            filaPanel.add(new JLabel(nombre != null ? nombre : "", JLabel.CENTER));
            filaPanel.add(new JLabel(usuario != null ? usuario : "", JLabel.CENTER));
            filaPanel.add(new JLabel(correo != null ? correo : "", JLabel.CENTER));
            filaPanel.add(new JLabel(direccion != null ? direccion : "", JLabel.CENTER));
            filaPanel.add(new JLabel(rol != null ? rol : "", JLabel.CENTER));

            radio.addActionListener(e -> {
                for (Component comp : panelUsuarios.getComponents()) {
                    if (comp instanceof JPanel && comp != header) {
                        comp.setBackground(null);
                    }
                }
                filaPanel.setBackground(new Color(230, 255, 240));
            });

            panelUsuarios.add(filaPanel);
        }

        rs.close();
        stmt.close();

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null,
            "Error al cargar usuarios:\n" + e.getMessage(),
            "Error de base de datos",
            JOptionPane.ERROR_MESSAGE);
    } finally {
        conexion.cerrar();
    }

    panelUsuarios.revalidate();
    panelUsuarios.repaint();
}
private void construirTablaUsuarios0(JPanel panelUsuarios) {
    panelUsuarios.setOpaque(true);
    String[][] datos = {
        {"U001", "Ana García", "AB123456", "España", "Recorrido Histórico"},
        {"U002", "John Smith", "CD789012", "Estados Unidos", "Aventura en la Montaña"},
        {"U003", "Marie Dubois", "EF345678", "Francia", "Experiencia Cultural"},
        {"U004", "Hans Müller", "GH901234", "Alemania", "Ruta Gastronómica"}
    };

    String[] columnas = {"", "ID", "Nombre", "Pasaporte", "Nacionalidad", "Plan Actual"};

    panelUsuarios.removeAll(); // Limpia lo anterior si hay algo
    panelUsuarios.setLayout(new BoxLayout(panelUsuarios, BoxLayout.Y_AXIS));

    JPanel header = new JPanel(new GridLayout(1, columnas.length));
    for (String titulo : columnas) {
        JLabel lbl = new JLabel(titulo, JLabel.CENTER);
        lbl.setFont(new Font("Roboto Medium", Font.BOLD, 14));
        header.add(lbl);
    }
    panelUsuarios.add(header);

    ButtonGroup grupo = new ButtonGroup();

    for (String[] fila : datos) {
        JPanel filaPanel = new JPanel(new GridLayout(1, columnas.length));
        filaPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        JRadioButton radio = new JRadioButton();
        radio.setHorizontalAlignment(SwingConstants.CENTER);
        grupo.add(radio);

        filaPanel.add(radio);

        for (String celda : fila) {
            JLabel lbl = new JLabel(celda, JLabel.CENTER);
            filaPanel.add(lbl);
        }

        radio.addActionListener(e -> {
            for (Component comp : panelUsuarios.getComponents()) {
                if (comp instanceof JPanel && comp != header) {
                    comp.setBackground(null);
                }
            }
            filaPanel.setBackground(new Color(230, 255, 240));
        });

        panelUsuarios.add(filaPanel);
    }

    panelUsuarios.revalidate(); // Refresca el panel
    panelUsuarios.repaint();
}




    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        elegirFichero = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        logOutBtn = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        bannerBotnCrearU = new javax.swing.JPanel();
        crearUserLabel = new javax.swing.JLabel();
        userlable = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        paquetesLabel = new javax.swing.JLabel();
        calendarioIcon = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        asignacioPlanes = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        elpanelorigins = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        buscador = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelTabla = new javax.swing.JPanel();
        panelAsignarPlanTuristico = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        turista1 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        txtImagen = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        asignacioPlanes1 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        buscador2 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        panelTabla2 = new javax.swing.JPanel();
        panelAsignarPlanTuristico1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        total1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        turistaEmail1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        imaPanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        crearUsuarios = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel71 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        turista = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        turistaNombre = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        turistaTel = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        turistaEmail = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        turistaAdress = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        pan = new javax.swing.JPasswordField();
        jdtCalendario = new com.toedter.calendar.JDateChooser();
        btnOjo = new javax.swing.JToggleButton();
        usertype = new javax.swing.JComboBox<>();
        establecimiento = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        buscador1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        panelTabla1 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        panelInicial = new javax.swing.JPanel();
        panelAddUsers5 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        planesBtn5 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        panelAddUsers6 = new javax.swing.JPanel();
        paneluserlabel1 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        planesBtn6 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/LOGO.png"))); // NOI18N
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 170, 50));

        logOutBtn.setBackground(new java.awt.Color(255, 255, 255));
        logOutBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        logOutBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logOutBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logOutBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logOutBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logOutBtnMouseExited(evt);
            }
        });
        logOutBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Roboto Light", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Log Out");
        logOutBtn.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 8, -1, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/salir-alt.png"))); // NOI18N
        logOutBtn.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 50));

        jPanel1.add(logOutBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 10, 140, 50));

        bannerBotnCrearU.setBackground(new java.awt.Color(255, 255, 255));
        bannerBotnCrearU.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bannerBotnCrearU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bannerBotnCrearUMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bannerBotnCrearUMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bannerBotnCrearUMouseExited(evt);
            }
        });
        bannerBotnCrearU.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        crearUserLabel.setBackground(new java.awt.Color(51, 51, 51));
        crearUserLabel.setFont(new java.awt.Font("Roboto Light", 1, 15)); // NOI18N
        crearUserLabel.setForeground(new java.awt.Color(51, 51, 51));
        crearUserLabel.setText("Trabajadores");
        bannerBotnCrearU.add(crearUserLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        userlable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios.png"))); // NOI18N
        bannerBotnCrearU.add(userlable, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        jPanel1.add(bannerBotnCrearU, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 280, 50));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel2MouseExited(evt);
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        paquetesLabel.setBackground(new java.awt.Color(255, 255, 255));
        paquetesLabel.setFont(new java.awt.Font("Roboto Light", 1, 15)); // NOI18N
        paquetesLabel.setForeground(new java.awt.Color(51, 51, 51));
        paquetesLabel.setText("Planes");
        jPanel2.add(paquetesLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        calendarioIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/calendario.png"))); // NOI18N
        calendarioIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                calendarioIconMouseEntered(evt);
            }
        });
        jPanel2.add(calendarioIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 230, 50));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bannerMinimalista.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-280, 0, 2170, 80));

        asignacioPlanes.setBackground(new java.awt.Color(255, 255, 255));
        asignacioPlanes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel15.setBackground(new java.awt.Color(204, 204, 204));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel18MouseClicked(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Roboto SemiBold", 0, 18)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(51, 51, 51));
        jLabel58.setText("Administrar Planes Turísticos");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel20MouseClicked(evt);
            }
        });

        jLabel59.setFont(new java.awt.Font("Roboto SemiBold", 0, 18)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(51, 51, 51));
        jLabel59.setText("Añadir Plan Turistico ");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel19MouseClicked(evt);
            }
        });

        jLabel60.setFont(new java.awt.Font("Roboto SemiBold", 0, 18)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(51, 51, 51));
        jLabel60.setText("Comprar panel turístico");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel60)
                .addGap(29, 29, 29))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(105, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        asignacioPlanes.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 1070, 70));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1180, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        asignacioPlanes.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 1180, 40));

        elpanelorigins.setBackground(new java.awt.Color(255, 255, 255));
        elpanelorigins.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setBackground(new java.awt.Color(51, 51, 51));
        jLabel8.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel8.setText("Administrar planes");
        jPanel7.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 310, 40));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/busqueda-de-miembros (2).png"))); // NOI18N
        jPanel7.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 40, 40));

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Nombre");
        jPanel7.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Descripción");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jPanel7.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, -1, -1));

        buscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscadorKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscadorKeyTyped(evt);
            }
        });
        jPanel7.add(buscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 140, 30));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panelTabla.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelTablaLayout = new javax.swing.GroupLayout(panelTabla);
        panelTabla.setLayout(panelTablaLayout);
        panelTablaLayout.setHorizontalGroup(
            panelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 708, Short.MAX_VALUE)
        );
        panelTablaLayout.setVerticalGroup(
            panelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 408, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panelTabla);

        jPanel7.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 710, 360));

        elpanelorigins.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 730, 520));

        panelAsignarPlanTuristico.setBackground(new java.awt.Color(255, 255, 255));
        panelAsignarPlanTuristico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelAsignarPlanTuristico.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(235, 235, 235));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Roboto SemiBold", 0, 14)); // NOI18N
        jLabel6.setText("Plan seleccionado");
        jPanel9.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 170, 30));

        jLabel17.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel17.setText("Nombre plan");
        jPanel9.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 250, 20));

        panelAsignarPlanTuristico.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 330, 140));

        total.setFont(new java.awt.Font("Roboto SemiBold", 1, 18)); // NOI18N
        panelAsignarPlanTuristico.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, 150, 30));

        jLabel74.setFont(new java.awt.Font("Roboto SemiBold", 1, 18)); // NOI18N
        jLabel74.setText("Precio del plan:");
        panelAsignarPlanTuristico.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 150, 30));

        jButton2.setText("Eliminar plan turistico");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        panelAsignarPlanTuristico.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, -1, -1));

        jButton4.setText("Modificar plan");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        panelAsignarPlanTuristico.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 480, 130, -1));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );

        panelAsignarPlanTuristico.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 260, 240));

        elpanelorigins.add(panelAsignarPlanTuristico, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, 400, 520));

        jTabbedPane3.addTab("tab1", elpanelorigins);

        jLabel32.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel32.setText("ID");
        jTabbedPane3.addTab("tab5", jLabel32);

        jComboBox1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(51, 51, 51));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "planLoco" }));
        jComboBox1.setBorder(null);
        jTabbedPane3.addTab("tab3", jComboBox1);

        turista1.setBackground(new java.awt.Color(255, 255, 255));
        turista1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel49.setFont(new java.awt.Font("Roboto SemiBold", 0, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(51, 51, 51));
        jLabel49.setText("Registro de Plan turístico");
        turista1.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 403, 30));

        jLabel50.setForeground(new java.awt.Color(102, 102, 102));
        jLabel50.setText("Ingresa los datos del nuevo turista");
        turista1.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        jLabel51.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        jLabel51.setText("Nombre del plan");
        turista1.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        txtNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNombreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombreFocusLost(evt);
            }
        });
        txtNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNombreMouseClicked(evt);
            }
        });
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        turista1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 510, 40));

        jLabel52.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        jLabel52.setText("Descripción");
        turista1.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 100, -1, -1));

        txtDescripcion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDescripcionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDescripcionFocusLost(evt);
            }
        });
        txtDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionActionPerformed(evt);
            }
        });
        turista1.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 120, 510, 40));

        jLabel53.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        jLabel53.setText("Url de imagen:");
        turista1.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 190, -1, -1));

        txtPrecio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPrecioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPrecioFocusLost(evt);
            }
        });
        txtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioActionPerformed(evt);
            }
        });
        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioKeyTyped(evt);
            }
        });
        turista1.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 510, 40));

        jPanel17.setBackground(new java.awt.Color(48, 172, 255));
        jPanel17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel17MouseClicked(evt);
            }
        });
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel67.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tarjeta-sd.png"))); // NOI18N
        jPanel17.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 66, 30));

        jLabel68.setFont(new java.awt.Font("Roboto SemiBold", 1, 14)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 255, 255));
        jLabel68.setText("Guardar Plan turistico");
        jPanel17.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 150, 30));

        turista1.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 1070, 50));

        jLabel55.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        jLabel55.setText("Precio");
        turista1.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, -1));

        txtImagen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtImagenFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtImagenFocusLost(evt);
            }
        });
        txtImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtImagenActionPerformed(evt);
            }
        });
        turista1.add(txtImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 220, 410, 40));

        jButton3.setText("Elegir imagen");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        turista1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 220, 110, 40));

        jTabbedPane3.addTab("tab2", turista1);

        asignacioPlanes1.setBackground(new java.awt.Color(255, 255, 255));
        asignacioPlanes1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonGroup3.add(jRadioButton5);
        jRadioButton5.setSelected(true);
        jRadioButton5.setText("Nombre");
        jPanel13.add(jRadioButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        buttonGroup3.add(jRadioButton6);
        jRadioButton6.setText("Descripción");
        jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton6ActionPerformed(evt);
            }
        });
        jPanel13.add(jRadioButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, -1, -1));

        buscador2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscador2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscador2KeyTyped(evt);
            }
        });
        jPanel13.add(buscador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 150, 30));

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panelTabla2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelTabla2Layout = new javax.swing.GroupLayout(panelTabla2);
        panelTabla2.setLayout(panelTabla2Layout);
        panelTabla2Layout.setHorizontalGroup(
            panelTabla2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1115, Short.MAX_VALUE)
        );
        panelTabla2Layout.setVerticalGroup(
            panelTabla2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 408, Short.MAX_VALUE)
        );

        jScrollPane3.setViewportView(panelTabla2);

        jPanel13.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 710, 360));

        asignacioPlanes1.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 730, 450));

        panelAsignarPlanTuristico1.setBackground(new java.awt.Color(255, 255, 255));
        panelAsignarPlanTuristico1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelAsignarPlanTuristico1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/calendario (1).png"))); // NOI18N
        panelAsignarPlanTuristico1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 40, 40));

        jLabel15.setBackground(new java.awt.Color(51, 51, 51));
        jLabel15.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel15.setText("Selecciona el plan Y DALE COMPRAR!!!");
        panelAsignarPlanTuristico1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 310, 20));

        total1.setFont(new java.awt.Font("Roboto SemiBold", 1, 18)); // NOI18N
        panelAsignarPlanTuristico1.add(total1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, 150, 30));

        jButton1.setBackground(new java.awt.Color(153, 153, 153));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Comprar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        panelAsignarPlanTuristico1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, 250, 60));

        turistaEmail1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                turistaEmail1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                turistaEmail1FocusLost(evt);
            }
        });
        turistaEmail1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                turistaEmail1ActionPerformed(evt);
            }
        });
        panelAsignarPlanTuristico1.add(turistaEmail1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 300, 40));

        jLabel4.setText("Email del turista");
        panelAsignarPlanTuristico1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, -1, -1));

        javax.swing.GroupLayout imaPanelLayout = new javax.swing.GroupLayout(imaPanel);
        imaPanel.setLayout(imaPanelLayout);
        imaPanelLayout.setHorizontalGroup(
            imaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        imaPanelLayout.setVerticalGroup(
            imaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );

        panelAsignarPlanTuristico1.add(imaPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 200, 170));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Total:");
        panelAsignarPlanTuristico1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, -1, 30));

        asignacioPlanes1.add(panelAsignarPlanTuristico1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 80, 400, 450));

        jTabbedPane3.addTab("tab4", asignacioPlanes1);

        asignacioPlanes.add(jTabbedPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 580));

        jTabbedPane1.addTab("asignacionPlanes", asignacioPlanes);

        jLabel33.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel33.setText("ID");
        jTabbedPane1.addTab("tab5", jLabel33);

        jLabel5.setText("ID del plan:");
        jTabbedPane1.addTab("tab4", jLabel5);

        crearUsuarios.setBackground(new java.awt.Color(241, 241, 241));
        crearUsuarios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(241, 241, 241));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1460, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        crearUsuarios.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 1460, 80));

        jPanel11.setBackground(new java.awt.Color(204, 204, 204));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel12MouseClicked(evt);
            }
        });

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar-usuario.png"))); // NOI18N

        jLabel21.setFont(new java.awt.Font("Roboto SemiBold", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setText("Añadir Trabajador");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel20)
                .addGap(18, 18, 18)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel14.setBackground(new java.awt.Color(204, 204, 204));
        jPanel14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel14MouseClicked(evt);
            }
        });

        jLabel71.setFont(new java.awt.Font("Roboto SemiBold", 0, 18)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(51, 51, 51));
        jLabel71.setText("Administrar trabajadores");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        crearUsuarios.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, 650, 60));

        turista.setBackground(new java.awt.Color(255, 255, 255));
        turista.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar-usuario (1).png"))); // NOI18N
        turista.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, 36));

        jLabel35.setFont(new java.awt.Font("Roboto SemiBold", 0, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(51, 51, 51));
        jLabel35.setText("Registro ");
        turista.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 270, 30));

        jLabel37.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        jLabel37.setText("Nombre Completo");
        turista.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        turistaNombre.setText("Nombre y Apellidos");
        turistaNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                turistaNombreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                turistaNombreFocusLost(evt);
            }
        });
        turistaNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                turistaNombreMouseClicked(evt);
            }
        });
        turistaNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                turistaNombreActionPerformed(evt);
            }
        });
        turistaNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                turistaNombreKeyTyped(evt);
            }
        });
        turista.add(turistaNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 510, 40));

        jLabel39.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        jLabel39.setText("Telefono");
        turista.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        turistaTel.setText("Ej: 9516155244");
        turistaTel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                turistaTelFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                turistaTelFocusLost(evt);
            }
        });
        turistaTel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                turistaTelActionPerformed(evt);
            }
        });
        turistaTel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                turistaTelKeyTyped(evt);
            }
        });
        turista.add(turistaTel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 510, 40));

        jLabel40.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        jLabel40.setText("Correo");
        turista.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, -1, -1));

        turistaEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                turistaEmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                turistaEmailFocusLost(evt);
            }
        });
        turistaEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                turistaEmailActionPerformed(evt);
            }
        });
        turista.add(turistaEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 80, 510, 40));

        jLabel41.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        jLabel41.setText("Contraseña");
        turista.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 200, -1, -1));

        jLabel42.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        jLabel42.setText("Direccion");
        turista.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 130, -1, -1));

        turistaAdress.setText("Ej: ferrocarril 4");
        turistaAdress.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                turistaAdressFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                turistaAdressFocusLost(evt);
            }
        });
        turistaAdress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                turistaAdressActionPerformed(evt);
            }
        });
        turista.add(turistaAdress, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 150, 510, 40));

        jPanel16.setBackground(new java.awt.Color(48, 172, 255));
        jPanel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel16MouseClicked(evt);
            }
        });
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tarjeta-sd.png"))); // NOI18N
        jPanel16.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 66, 30));

        jLabel44.setFont(new java.awt.Font("Roboto SemiBold", 1, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Guardar ");
        jPanel16.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 180, 30));

        turista.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 1230, 50));

        jLabel54.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        jLabel54.setText("Fecha de nacimiento");
        turista.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));
        turista.add(pan, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 220, 360, 40));
        turista.add(jdtCalendario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 510, 40));

        btnOjo.setIcon(new javax.swing.ImageIcon("C:\\Users\\migue\\Documents\\NetBeansProjects\\AppKant\\imagen\\ojoao.jpg")); // NOI18N
        btnOjo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOjoActionPerformed(evt);
            }
        });
        turista.add(btnOjo, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 220, 50, 40));

        usertype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Recepcionista" }));
        usertype.setBorder(null);
        turista.add(usertype, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 290, 250, 40));

        jTabbedPane2.addTab("tab1", turista);

        establecimiento.setBackground(new java.awt.Color(255, 255, 255));
        establecimiento.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setBackground(new java.awt.Color(51, 51, 51));
        jLabel13.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel13.setText("Busqueda de Trabajadores");
        jPanel8.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 310, 40));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/busqueda-de-miembros (2).png"))); // NOI18N
        jPanel8.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 40, 40));

        jLabel36.setBackground(new java.awt.Color(51, 51, 51));
        jLabel36.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel36.setText("Busca al trabajador por nombre o número de id");
        jPanel8.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 430, 20));

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setSelected(true);
        jRadioButton3.setText("Nombre");
        jPanel8.add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setText("Id");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        jPanel8.add(jRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, -1, -1));

        buscador1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscador1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscador1KeyTyped(evt);
            }
        });
        jPanel8.add(buscador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 118, 200, -1));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panelTabla1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelTabla1Layout = new javax.swing.GroupLayout(panelTabla1);
        panelTabla1.setLayout(panelTabla1Layout);
        panelTabla1Layout.setHorizontalGroup(
            panelTabla1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 788, Short.MAX_VALUE)
        );
        panelTabla1Layout.setVerticalGroup(
            panelTabla1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 408, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(panelTabla1);

        jPanel8.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 800, 290));

        establecimiento.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 870, 450));

        jButton6.setText("Modificar Trabajador");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        establecimiento.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 130, 150, -1));

        jButton5.setText("Eliminar Trabajador");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        establecimiento.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 170, 150, -1));

        jPanel10.setBackground(new java.awt.Color(235, 235, 235));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuario (1).png"))); // NOI18N
        jPanel10.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        jLabel38.setFont(new java.awt.Font("Roboto SemiBold", 0, 14)); // NOI18N
        jLabel38.setText("Trabajador Seleccionado");
        jPanel10.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 170, 30));

        jLabel45.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel45.setText("Nombre ApellidoP Apellido M");
        jPanel10.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 250, 20));

        jLabel46.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel46.setText("Usuario");
        jPanel10.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jLabel56.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel56.setText("ID");
        jPanel10.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, -1, -1));

        establecimiento.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 270, 330, 140));

        jTabbedPane2.addTab("tab3", establecimiento);

        crearUsuarios.add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 1290, 530));

        jTabbedPane1.addTab("crearUsuario", crearUsuarios);

        panelInicial.setBackground(new java.awt.Color(255, 255, 255));

        panelAddUsers5.setBackground(new java.awt.Color(239, 239, 239));
        panelAddUsers5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/calendario (1).png"))); // NOI18N

        jLabel24.setFont(new java.awt.Font("Roboto Medium", 1, 18)); // NOI18N
        jLabel24.setText("Planes Turísticos");

        planesBtn5.setBackground(new java.awt.Color(1, 174, 110));
        planesBtn5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        planesBtn5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                planesBtn5MouseClicked(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Gestionar Planes");

        javax.swing.GroupLayout planesBtn5Layout = new javax.swing.GroupLayout(planesBtn5);
        planesBtn5.setLayout(planesBtn5Layout);
        planesBtn5Layout.setHorizontalGroup(
            planesBtn5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(planesBtn5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                .addContainerGap())
        );
        planesBtn5Layout.setVerticalGroup(
            planesBtn5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(planesBtn5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel26.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("<html>Asigna planes turísticos a visitantes<br>" +
            "Gestiona experiencias turísticas como visitas<br> guiadas y excursiones.</html>");
        jLabel26.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel26.setVerifyInputWhenFocusTarget(false);
        jLabel26.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout panelAddUsers5Layout = new javax.swing.GroupLayout(panelAddUsers5);
        panelAddUsers5.setLayout(panelAddUsers5Layout);
        panelAddUsers5Layout.setHorizontalGroup(
            panelAddUsers5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAddUsers5Layout.createSequentialGroup()
                .addGroup(panelAddUsers5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAddUsers5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelAddUsers5Layout.createSequentialGroup()
                        .addGroup(panelAddUsers5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAddUsers5Layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(jLabel23)
                                .addGap(38, 38, 38)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelAddUsers5Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(planesBtn5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 31, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelAddUsers5Layout.setVerticalGroup(
            panelAddUsers5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAddUsers5Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(panelAddUsers5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGap(18, 18, 18)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(planesBtn5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        panelAddUsers6.setBackground(new java.awt.Color(239, 239, 239));

        paneluserlabel1.setFont(new java.awt.Font("Roboto Medium", 1, 18)); // NOI18N
        paneluserlabel1.setText("Gestion de Trabajadores");

        jLabel28.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 51, 51));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("<html>Crea y administra usuarios del sistema<br>" +
            "<br>" +
            "Registra nuevos turistas, guías y actualiza información.</html>");
        jLabel28.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel28.setVerifyInputWhenFocusTarget(false);
        jLabel28.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        planesBtn6.setBackground(new java.awt.Color(1, 174, 110));
        planesBtn6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        planesBtn6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                planesBtn6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                planesBtn6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                planesBtn6MouseExited(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Agregar Trabajadores");

        javax.swing.GroupLayout planesBtn6Layout = new javax.swing.GroupLayout(planesBtn6);
        planesBtn6.setLayout(planesBtn6Layout);
        planesBtn6Layout.setHorizontalGroup(
            planesBtn6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(planesBtn6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                .addContainerGap())
        );
        planesBtn6Layout.setVerticalGroup(
            planesBtn6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(planesBtn6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar-usuario (1).png"))); // NOI18N

        javax.swing.GroupLayout panelAddUsers6Layout = new javax.swing.GroupLayout(panelAddUsers6);
        panelAddUsers6.setLayout(panelAddUsers6Layout);
        panelAddUsers6Layout.setHorizontalGroup(
            panelAddUsers6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAddUsers6Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(paneluserlabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(panelAddUsers6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelAddUsers6Layout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addGroup(panelAddUsers6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(planesBtn6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(38, Short.MAX_VALUE)))
        );
        panelAddUsers6Layout.setVerticalGroup(
            panelAddUsers6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAddUsers6Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelAddUsers6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paneluserlabel1))
                .addContainerGap(174, Short.MAX_VALUE))
            .addGroup(panelAddUsers6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelAddUsers6Layout.createSequentialGroup()
                    .addGap(73, 73, 73)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(26, 26, 26)
                    .addComponent(planesBtn6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(34, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout panelInicialLayout = new javax.swing.GroupLayout(panelInicial);
        panelInicial.setLayout(panelInicialLayout);
        panelInicialLayout.setHorizontalGroup(
            panelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInicialLayout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(panelAddUsers5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200)
                .addComponent(panelAddUsers6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(513, Short.MAX_VALUE))
        );
        panelInicialLayout.setVerticalGroup(
            panelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInicialLayout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addGroup(panelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelAddUsers5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelAddUsers6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(468, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("panelInicial", panelInicial);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1610, 900));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseEntered
        ImageIcon iconoHover = new ImageIcon(getClass().getResource("/imagenes/calendario (1).png"));

        calendarioIcon.setIcon(iconoHover);
        paquetesLabel.setForeground(new java.awt.Color(0, 203, 128));
    }//GEN-LAST:event_jPanel2MouseEntered

    private void jPanel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseExited
        ImageIcon iconoNormal = new ImageIcon(getClass().getResource("/imagenes/calendario.png"));
        calendarioIcon.setIcon(iconoNormal);
        paquetesLabel.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_jPanel2MouseExited

    private void logOutBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logOutBtnMouseEntered
        logOutBtn.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_logOutBtnMouseEntered

    private void logOutBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logOutBtnMouseExited
        logOutBtn.setBackground(Color.white);
    }//GEN-LAST:event_logOutBtnMouseExited

    private void bannerBotnCrearUMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bannerBotnCrearUMouseClicked
        jTabbedPane1.setSelectedComponent(crearUsuarios);
    }//GEN-LAST:event_bannerBotnCrearUMouseClicked

    private void bannerBotnCrearUMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bannerBotnCrearUMouseEntered
        crearUserLabel.setForeground(new Color(0, 203, 128));
        ImageIcon iconoNormal = new ImageIcon(getClass().getResource("/imagenes/agregar-usuario (1).png"));
        userlable.setIcon(iconoNormal);
    }//GEN-LAST:event_bannerBotnCrearUMouseEntered

    private void bannerBotnCrearUMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bannerBotnCrearUMouseExited
        crearUserLabel.setForeground(new Color(0, 0, 0));
        ImageIcon iconoNormal = new ImageIcon(getClass().getResource("/imagenes/agregar-usuario.png"));
        userlable.setIcon(iconoNormal);
    }//GEN-LAST:event_bannerBotnCrearUMouseExited

    private void planesBtn6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_planesBtn6MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_planesBtn6MouseEntered

    private void planesBtn6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_planesBtn6MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_planesBtn6MouseExited

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void turistaNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_turistaNombreActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_turistaNombreActionPerformed

    private void turistaTelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_turistaTelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_turistaTelActionPerformed

    private void turistaEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_turistaEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_turistaEmailActionPerformed

    private void turistaAdressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_turistaAdressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_turistaAdressActionPerformed

    private void jPanel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseClicked

        jTabbedPane2.setSelectedComponent(turista);
        jPanel12.setBackground(Color.white);
       
        jPanel14.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel12MouseClicked

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        jTabbedPane1.setSelectedComponent(asignacioPlanes);
    }//GEN-LAST:event_jPanel2MouseClicked

    private void planesBtn5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_planesBtn5MouseClicked
        jTabbedPane1.setSelectedComponent(asignacioPlanes);
    }//GEN-LAST:event_planesBtn5MouseClicked

    private void planesBtn6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_planesBtn6MouseClicked
       jTabbedPane1.setSelectedComponent(crearUsuarios);
    }//GEN-LAST:event_planesBtn6MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        jTabbedPane1.setSelectedComponent(panelInicial);
    }//GEN-LAST:event_jLabel16MouseClicked
        
    
public static String generarIDUnico(Connection conn) throws SQLException {
    String idGenerado;
    boolean existe;

    do {
        idGenerado = generarID(); // Usamos el mismo generador que ya tienes

        String sql = "SELECT COUNT(*) FROM Usuarios WHERE UsuarioID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idGenerado);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            existe = rs.getInt(1) > 0; // Si existe, lo volvemos a generar
        }
    } while (existe);

    return idGenerado;
}

    
    
        public static String generarID() {
    String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    SecureRandom random = new SecureRandom();
    StringBuilder sb = new StringBuilder(8); // longitud
    for (int i = 0; i < 8; i++) {
        sb.append(caracteres.charAt(random.nextInt(caracteres.length())));
    }
    return "UK_" + sb.toString();
}
        /*
         Conexion conexion = new Conexion();
    Connection conn = null;

    try {
        conexion.conectar();
        */
    private void jPanel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel16MouseClicked
         Conexion conexion = new Conexion(); 
        Connection conn = null;
      
        try {
        
    conexion.conectar();
    conn = conexion.getConexion();

   String  usuarioID = generarIDUnico(conn); // ahora sí compila

    // ... el resto de tu código aquí


         String contrau = new String(pan.getPassword()).trim();
           String fechao=""; 
         java.util.Date fecha = jdtCalendario.getDate();
     
         if (fecha != null) {
      SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    fechao = sdf.format(fecha);
}
         
         
          if(     Validador.validarCamposLlenos(contrau, turistaNombre.getText().trim(),
               turistaTel.getText().trim(),turistaEmail.getText().trim(), fechao,turistaAdress.getText().trim())==false
         ){
     JOptionPane.showMessageDialog(null,
            "Todos los campos deben que estar llenos" ,
            "",
            JOptionPane.ERROR_MESSAGE);
    }else if(Validador.validarCorreo(turistaEmail.getText().trim())==false ){
            JOptionPane.showMessageDialog(null,
            "Debe dar un correo real" ,
            "",
            JOptionPane.ERROR_MESSAGE);}
    else if(Validador.validarContrasena(contrau)==false){
    JOptionPane.showMessageDialog(null,
            "La contraseña tiene que ser de 8 carácteres" ,
            "",
            JOptionPane.ERROR_MESSAGE);
    }else if (fecha == null) {
   
    JOptionPane.showMessageDialog(null, "Falta elegir la fecha, aún no se ha seleccionado.", "Fecha requerida", JOptionPane.WARNING_MESSAGE);
}else{
      
        
        
         int index = usertype.getSelectedIndex();
     
        
String emaill=turistaEmail.getText().trim();
if (index == 0 &&  guardarRecepcionista(
    usuarioID,
    turistaEmail.getText().trim(),
    contrau,
  turistaNombre.getText().trim(),
   turistaTel.getText().trim(),
    turistaEmail.getText().trim(),
   fechao,
   turistaAdress.getText().trim()
) !=false ) {
      
       enviarCorreo(emaill);
         turistaNombre.setText("");
         turistaEmail.setText("");
         turistaTel.setText("");
         turistaAdress.setText("");
         jdtCalendario.setDate(null);
         pan.setText("");
            }
    
 else if (index == 1 &&   guardarTurista(
            usuarioID,
            turistaEmail.getText().trim(),
            contrau,
            turistaNombre.getText().trim(),
            turistaTel.getText().trim(),
            turistaEmail.getText().trim(),
            fechao,
            turistaAdress.getText().trim()
        )!=false   ) {
     
      enviarCorreo(emaill);
        turistaNombre.setText("");
         turistaEmail.setText("");
         turistaTel.setText("");
         turistaAdress.setText("");
         jdtCalendario.setDate(null);
         pan.setText("");
}

       
         
       
          construirTablaUsuarios(panelTabla1, buscador1, jRadioButton4, jRadioButton3, jLabel45, jLabel46);
       
    
          
    }
          } catch (SQLException e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(null,
        "Error al generar ID único o conectar:\n" + e.getMessage(),
        "Error SQL",
        JOptionPane.ERROR_MESSAGE);
}
        finally {
    conexion.cerrar();
    System.out.println("Conexión cerrada");
}
        /*turistaEmail.getText().trim()*/
    }//GEN-LAST:event_jPanel16MouseClicked
public void enviarCorreo(String es){
    final String remitente = "kantkickgarabatoinc@gmail.com";
    final String clave = "lqrx slza fgij gmwu";

    // Correo destinatario
    String destinatario =es;
System.out.println("Correo destinatario:" + destinatario);

    // Asunto y cuerpo del mensaje
    String asunto = "Bienvenido al equipo KANT ";
    String cuerpo = "Bienvenido al equipo";

    // Ruta al archivo adjunto - ¡ESCRITA BIEN Y SIN CARACTERES RAROS!
  String archivoRuta = new File("pdfs/documentob.pdf").getAbsolutePath();
//new File("pdfs/documentok.pdf").getAbsolutePath()
    // Opcional: imprime ruta para depuración
    System.out.println("Ruta archivo: [" + archivoRuta + "]");

    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");

    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(remitente, clave);
        }
    });

    try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(remitente));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
        message.setSubject(asunto);

        MimeBodyPart texto = new MimeBodyPart();
        texto.setText(cuerpo);

        MimeBodyPart adjunto = new MimeBodyPart();
        File archivo = new File(archivoRuta);

        if (!archivo.exists()) {
            JOptionPane.showMessageDialog(null, "Archivo no encontrado en: " + archivo.getCanonicalPath());
            return;
        }

        adjunto.attachFile(archivo);

        Multipart multiParte = new MimeMultipart();
        multiParte.addBodyPart(texto);
        multiParte.addBodyPart(adjunto);

        message.setContent(multiParte);
        
        
        System.out.println("Enviando correo a: " + destinatario);
System.out.println("Desde: " + remitente);
System.out.println("Asunto: " + asunto);
System.out.println("Cuerpo: " + cuerpo);

        Transport.send(message);

        JOptionPane.showMessageDialog(null, "Correo enviado correctamente");

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al enviar: " + e.getMessage());
        e.printStackTrace();
    }
           
}
    private void buscadorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscadorKeyTyped

    }//GEN-LAST:event_buscadorKeyTyped

    private void buscadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscadorKeyReleased
       
    }//GEN-LAST:event_buscadorKeyReleased

    private void turistaNombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_turistaNombreMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_turistaNombreMouseClicked

    private void turistaNombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_turistaNombreFocusGained
        if (turistaNombre.getText().equals("Nombre y Apellidos")) {
        turistaNombre.setText("");
        turistaNombre.setForeground(Color.BLACK);
    }
    }//GEN-LAST:event_turistaNombreFocusGained

    private void turistaNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_turistaNombreFocusLost
        if (turistaNombre.getText().trim().equals("")) {
        turistaNombre.setText("Nombre y Apellidos");
        turistaNombre.setForeground(Color.GRAY);
    }
    }//GEN-LAST:event_turistaNombreFocusLost

    private void turistaTelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_turistaTelFocusGained
       if (turistaTel.getText().equals("Ej: 9516155244")) {
        turistaTel.setText("");
        turistaTel.setForeground(Color.BLACK);
    }
    }//GEN-LAST:event_turistaTelFocusGained

    private void turistaTelFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_turistaTelFocusLost
        if (turistaTel.getText().trim().equals("")) {
        turistaTel.setText("Ej: 9516155244");
        turistaTel.setForeground(Color.GRAY);
    }
    }//GEN-LAST:event_turistaTelFocusLost

    private void turistaEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_turistaEmailFocusGained
        if (turistaEmail.getText().equals("Ej: kickgarabato@gmail.com")) {
        turistaEmail.setText("");
        turistaEmail.setForeground(Color.BLACK);
    }
    }//GEN-LAST:event_turistaEmailFocusGained

    private void turistaEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_turistaEmailFocusLost
        if (turistaEmail.getText().trim().equals("")) {
        turistaEmail.setText("Ej: kickgarabato@gmail.com");
        turistaEmail.setForeground(Color.GRAY);
    }
    }//GEN-LAST:event_turistaEmailFocusLost

    private void turistaAdressFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_turistaAdressFocusGained
        if (turistaAdress.getText().equals("Ej: ferrocarril 4")) {
        turistaAdress.setText("");
        turistaAdress.setForeground(Color.BLACK);
    }
    }//GEN-LAST:event_turistaAdressFocusGained

    private void turistaAdressFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_turistaAdressFocusLost
        if (turistaAdress.getText().trim().equals("")) {
        turistaAdress.setText("Ej: ferrocarril 4");
        turistaAdress.setForeground(Color.GRAY);
    }
    }//GEN-LAST:event_turistaAdressFocusLost

    private void logOutBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logOutBtnMouseClicked
  int confirm = JOptionPane.showConfirmDialog(null,
            "¿Estás seguro de que deseas cerrar sesión?",
            "Confirmación", JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) return;

        Login nuevaVentana = new Login();
                nuevaVentana.setVisible(true);
                // Cerrar la ventana actual
                
                Recepcionista.this.dispose();
    }//GEN-LAST:event_logOutBtnMouseClicked

    private void jPanel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel14MouseClicked
    

        jTabbedPane2.setSelectedComponent(establecimiento);
        jPanel12.setBackground(new Color(204,204,204));
        
        jPanel14.setBackground(Color.white);
    }//GEN-LAST:event_jPanel14MouseClicked


public class EncriptadorAES {
    private static final String CLAVE_SECRETA = "1234567890123456"; // 16 caracteres = 128 bits

    public static String encriptar(String texto) {
        try {
            SecretKeySpec key = new SecretKeySpec(CLAVE_SECRETA.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(texto.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("Error al encriptar: " + e.getMessage());
        }
    }

    public static String desencriptar(String textoEncriptado) {
        try {
            SecretKeySpec key = new SecretKeySpec(CLAVE_SECRETA.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(textoEncriptado));
            return new String(decrypted);
        } catch (Exception e) {
            throw new RuntimeException("Error al desencriptar: " + e.getMessage());
        }
    }
}

    public boolean guardarRecepcionista(String usuarioID, String claveUsuario, String password, String nombre,
                                 String telefono, String correo, String fechaNacimiento, String direccion) {
   Conexion conexion = new Conexion();
    Connection conn = null;

    try {
        conexion.conectar();
        conn = conexion.getConexion();
        conn.setAutoCommit(false);

        String sqlCheckCorreo = "SELECT COUNT(*) FROM Usuarios WHERE Correo = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(sqlCheckCorreo)) {
            checkStmt.setString(1, correo);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(null, "El correo ya está registrado. No se puede guardar.");
                return false;
            }
        }

        // Encriptar la contraseña antes de guardarla
        String passwordEncriptado = EncriptadorAES.encriptar(password);

        String sqlUsuarios = "INSERT INTO Usuarios (UsuarioID, Correo, Password, Nombre, Estatus, rol, Telefono, FechaNacimiento, FechaReg, Direccion) " +
                             "VALUES (?, ?, ?, ?, 'activo', '1', ?, ?, date('now'), ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sqlUsuarios)) {
            stmt.setString(1, usuarioID);
            stmt.setString(2, claveUsuario);
            stmt.setString(3, passwordEncriptado);
            stmt.setString(4, nombre);
            stmt.setString(5, telefono);
            stmt.setString(6, fechaNacimiento);
            stmt.setString(7, direccion);
            stmt.executeUpdate();
        }

        conn.commit();
        JOptionPane.showMessageDialog(null, "Administrador registrado con éxito.");
        turistaNombre.setText("");
        turistaTel.setText("");
        turistaEmail.setText("");
        turistaAdress.setText("");
        jdtCalendario.setDate(null);
        pan.setText("");

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al registrar recepcionista:\n" + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        try {
            if (conn != null) conn.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al hacer rollback:\n" + ex.getMessage(), "Error crítico", JOptionPane.ERROR_MESSAGE);
        }
    } finally {
        conexion.cerrar();
    }
    return true;
}

    
    
    
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String nombrePlan = jLabel32.getText();

        if (nombrePlan.equals("ID")) {
            JOptionPane.showMessageDialog(null, "Selecciona un plan válido.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null,
            "¿Estás seguro de que deseas eliminar el plan \"" + nombrePlan + "\"?",
            "Confirmación", JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) return;

        Conexion conexion = new Conexion();
        Connection conn = null;

        try {
            conexion.conectar();
            conn = conexion.getConexion();

            // Eliminar el plan con ese nombre
            String sql = "DELETE FROM Planes WHERE planesID= ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nombrePlan);
                int afectados = stmt.executeUpdate();

                if (afectados > 0) {
                    construirTablaPlanes(panelTabla); 
                    construirTablaPlanes2(panelTabla2); 
                    JOptionPane.showMessageDialog(null, "Plan eliminado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró ningún plan con ese nombre.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Error al eliminar el plan:\n" + e.getMessage(),
                "Error SQL", JOptionPane.ERROR_MESSAGE);
        } finally {
            conexion.cerrar();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtNombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreFocusGained

    private void txtNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreFocusLost

    private void txtNombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNombreMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreMouseClicked

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtDescripcionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDescripcionFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionFocusGained

    private void txtDescripcionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDescripcionFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionFocusLost

    private void txtDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionActionPerformed

    private void txtPrecioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPrecioFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioFocusGained

    private void txtPrecioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPrecioFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioFocusLost

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioActionPerformed

    private void jPanel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseClicked
        // TODO add your handling code here:
          
        String nombre = txtNombre.getText().trim();
    String descripcion = txtDescripcion.getText().trim();
    String precioStr = txtPrecio.getText().trim();
 String imagen=txtImagen.getText();
    if (nombre.isEmpty() || descripcion.isEmpty() || precioStr.isEmpty() ||imagenSeleccionada == null ) {
        JOptionPane.showMessageDialog(null, "Por favor completa todos los campos.");
        return;
    }

    double precio;
    try {
        precio = Double.parseDouble(precioStr);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "El precio debe ser un número válido.");
        return;
    }
/*
     conexionBD = new Conexion();
conexionBD.conectar();
conn = conexionBD.getConexion();
    */
    Conexion conexion = new Conexion();
    Connection conn = null;

    try {
        conexion.conectar();
        conn = conexion.getConexion();

        if (conn == null) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos.");
            return;
        }

       
       String obtenerUltimoID = "SELECT planesID FROM Planes WHERE planesID LIKE 'PLN%' ORDER BY CAST(SUBSTR(planesID, 4) AS INTEGER) DESC LIMIT 1";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(obtenerUltimoID);

        int nuevoNumero = 1;
        if (rs.next()) {
    String ultimoID = rs.getString("planesID"); 
    if (ultimoID != null && ultimoID.startsWith("PLN")) {
        nuevoNumero = Integer.parseInt(ultimoID.substring(3)) + 1;
    }
}
String nuevoID = "PLN" + nuevoNumero;

        
        String sql = "INSERT INTO Planes (planesID, nombre, descripcion, precio, imagen) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement insertStmt = conn.prepareStatement(sql);
        insertStmt.setString(1, nuevoID);
        insertStmt.setString(2, nombre);
        insertStmt.setString(3, descripcion);
        insertStmt.setDouble(4, precio);
          insertStmt.setString(5,imagen);
        int filasInsertadas = insertStmt.executeUpdate();

        if (filasInsertadas > 0) {
            JOptionPane.showMessageDialog(null, "Plan turístico guardado con éxito.");
            txtNombre.setText("");
            txtPrecio.setText("");
            txtDescripcion.setText("");
            txtImagen.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Error al guardar el plan turístico.");
        }
cargarPlanesEnComboBox(); 
        construirTablaPlanes(panelTabla); 
        insertStmt.close();

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error en la base de datos.");
    } finally {
        conexion.cerrar();  
    }
    
       
    }//GEN-LAST:event_jPanel17MouseClicked

    private void turistaNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_turistaNombreKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
    if (!Character.isLetter(c) && c != ' ') {
        evt.consume(); // Bloquea cualquier carácter que no sea letra o espacio
    }
    }//GEN-LAST:event_turistaNombreKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
         char c=evt.getKeyChar();
        if(Character.isDigit(c)){
        evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void btnOjoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOjoActionPerformed
        // TODO add your handling code here:

        if(btnOjo.isSelected()){
          btnOjo.setIcon(new javax.swing.ImageIcon("imagen/ojoco.jpg"));

            pan.setEchoChar((char)0);

        }else{
           btnOjo.setIcon(new javax.swing.ImageIcon("imagen/ojoao.jpg"));
            pan.setEchoChar('*');
        }
    }//GEN-LAST:event_btnOjoActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        
          String nombre = jLabel17.getText();

        if (nombre.equals("Nombre plan")) {
            JOptionPane.showMessageDialog(null, "Selecciona un plan válido.");
            
        }else{
  
   
modificarplan a=new modificarplan(nombre);
a.setVisible(true);
   this.dispose();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void turistaTelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_turistaTelKeyTyped
        // TODO add your handling code here:
              char c = evt.getKeyChar();
    if (!Character.isDigit(c)) {
        evt.consume(); // Cancela el evento, no permite la tecla
    }
    }//GEN-LAST:event_turistaTelKeyTyped

    private void txtPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyTyped
        // TODO add your handling code here:
          char c = evt.getKeyChar();

    if (!Character.isDigit(c) && c != '.' && c != ',') {
        evt.consume(); 
    }
    }//GEN-LAST:event_txtPrecioKeyTyped

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void buscador1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscador1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_buscador1KeyReleased

    private void buscador1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscador1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_buscador1KeyTyped

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
           try {
            String id = jLabel56.getText().trim();       
            String nombre = jLabel45.getText().trim();   

            if ((id == null || id.isEmpty()) && (nombre == null || nombre.isEmpty())) {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún turista.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar al seleccionado?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) return;

            Conexion conexion = new Conexion();
            conexion.conectar();
            Connection conn = conexion.getConexion();
            conn.setAutoCommit(false); 

          
            String sqlID = "SELECT UsuarioID FROM Usuarios WHERE UsuarioID = ?";
            PreparedStatement psBuscar = conn.prepareStatement(sqlID);
            psBuscar.setString(1, id);
          
            ResultSet rs = psBuscar.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "No ha seleccionado a alguien");
                return;
            }

            String usuarioID = rs.getString("UsuarioID");
            rs.close();
            psBuscar.close();

          
            String[] sentencias = {
            
                "DELETE FROM Usuarios WHERE UsuarioID = ?"
            };

            for (String sql : sentencias) {
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, usuarioID);
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    System.err.println("Advertencia en tabla: " + ex.getMessage());
                }
            }

            conn.commit(); 
            conexion.cerrar();

            JOptionPane.showMessageDialog(null, "Persona eliminada correctamente.");

              actualizar();

               

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al eliminar turista:\n" + ex.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
            }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
          try {
        String id = jLabel56.getText().trim();       // ID o Correo
        String nombre = jLabel45.getText().trim();   // Nombre

        if ((id == null || id.isEmpty()) && (nombre == null || nombre.isEmpty())) {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún usuario para modificar.");
            return;
        }

        Conexion conexion = new Conexion();
        conexion.conectar();
        Connection conn = conexion.getConexion();

        String sqlID = "SELECT UsuarioID FROM Usuarios WHERE UsuarioID = ?";
        PreparedStatement psBuscar = conn.prepareStatement(sqlID);
        psBuscar.setString(1, id);

        ResultSet rs = psBuscar.executeQuery();

        if (rs.next()) {
            String usuarioID = rs.getString("UsuarioID");

            // Cerrar recursos antes de abrir la nueva ventana
            rs.close();
            psBuscar.close();
            conexion.cerrar();

            // Abrir ventana de modificación
            modificar m = new modificar(usuarioID);
            m.setVisible(true);
            this.dispose();
        } else {
            rs.close();
            psBuscar.close();
            conexion.cerrar();
            JOptionPane.showMessageDialog(null, "No se encontró el usuario seleccionado.");
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al buscar usuario:\n" + ex.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void calendarioIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calendarioIconMouseEntered

    }//GEN-LAST:event_calendarioIconMouseEntered

    private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton6ActionPerformed

    private void buscador2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscador2KeyReleased

    }//GEN-LAST:event_buscador2KeyReleased

    private void buscador2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscador2KeyTyped

    }//GEN-LAST:event_buscador2KeyTyped
 class Plan {
    String id;
    String nombre;
    String descripcion;
    double precio;

    Plan(String id, String nombre, String descripcion, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }
}

    
private java.util.List<Plan> manejarSeleccionCheckbox() {
    java.util.List<Plan> planesSeleccionados = new ArrayList<>();

    for (Component comp : panelTabla2.getComponents()) {
        if (comp instanceof JPanel fila) {
            Component[] celdas = fila.getComponents();

            if (celdas.length < 4) {
                System.out.println("Fila con menos de 4 celdas. Se omite.");
                continue;
            }

            JCheckBox checkBox = null;
            String id = "", nombre = "", descripcion = "";
            double precio = 0;

            // ⚠️ Celda 0: se espera que contenga un JCheckBox dentro de un JPanel
            Component celda0 = celdas[0];
            if (celda0 instanceof JPanel panel && panel.getComponentCount() > 0) {
                Component hijo = panel.getComponent(0);
                if (hijo instanceof JCheckBox cb) {
                    checkBox = cb;
                    id = cb.getName(); // Ya lo habías guardado con .setName(id)
                } else {
                    System.out.println("El contenido de la celda 0 no es un JCheckBox.");
                }
            } else {
                System.out.println("La celda 0 no es un JPanel válido.");
            }

            if (checkBox != null && checkBox.isSelected()) {
                // Celda 1: nombre
                if (celdas[1] instanceof JPanel p1 && p1.getComponentCount() > 0 && p1.getComponent(0) instanceof JLabel lbl1) {
                    nombre = lbl1.getText();
                }

                // Celda 2: descripción
                if (celdas[2] instanceof JPanel p2 && p2.getComponentCount() > 0 && p2.getComponent(0) instanceof JLabel lbl2) {
                    descripcion = lbl2.getText();
                }

                // Celda 3: precio
                if (celdas[3] instanceof JPanel p3 && p3.getComponentCount() > 0 && p3.getComponent(0) instanceof JLabel lbl3) {
                    String precioTexto = lbl3.getText().replace("$", "").replace(",", "");
                    try {
                        precio = Double.parseDouble(precioTexto);
                    } catch (NumberFormatException e) {
                        System.out.println("Precio no válido: " + lbl3.getText());
                        precio = 0;
                    }
                }

                // Agregar el plan a la lista
                planesSeleccionados.add(new Plan(id, nombre, descripcion, precio));
            }
        }
    }

    return planesSeleccionados;
}


    
    
    
    
    public void generarPDFPlanesComprados(java.util.List<Plan> planesSeleccionados) {
    com.itextpdf.text.Document document = new com.itextpdf.text.Document(com.itextpdf.text.PageSize.A4, 50, 50, 50, 50);

    try {
      String ruta = new File("pdfs/documentok.pdf").getAbsolutePath();

        com.itextpdf.text.pdf.PdfWriter.getInstance(document, new java.io.FileOutputStream(ruta));
        document.open();

        // Fuente personalizada
        com.itextpdf.text.Font tituloFont = new com.itextpdf.text.Font(
            com.itextpdf.text.Font.FontFamily.HELVETICA, 20, com.itextpdf.text.Font.BOLD, 
            new com.itextpdf.text.BaseColor(34, 139, 34)); // verde

        com.itextpdf.text.Font subtituloFont = new com.itextpdf.text.Font(
            com.itextpdf.text.Font.FontFamily.HELVETICA, 14, com.itextpdf.text.Font.BOLD);

        com.itextpdf.text.Font textoFont = new com.itextpdf.text.Font(
            com.itextpdf.text.Font.FontFamily.HELVETICA, 12);

        com.itextpdf.text.Paragraph titulo = new com.itextpdf.text.Paragraph("Gracias por tu compra", tituloFont);
        titulo.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        titulo.setSpacingAfter(20f);
        document.add(titulo);

        for (Plan plan : planesSeleccionados) {
            com.itextpdf.text.pdf.PdfPTable table = new com.itextpdf.text.pdf.PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);
            table.setWidths(new float[]{1f, 3f});

            // Agregar las celdas con estilo
            table.addCell(getCell("ID:", subtituloFont));
            table.addCell(getCell(plan.id, textoFont));
            table.addCell(getCell("Nombre:", subtituloFont));
            table.addCell(getCell(plan.nombre, textoFont));
            table.addCell(getCell("Descripción:", subtituloFont));
            table.addCell(getCell(plan.descripcion, textoFont));
            table.addCell(getCell("Precio:", subtituloFont));
            table.addCell(getCell(String.format("$%.2f", plan.precio), textoFont));

            document.add(table);
        }

        document.close();
        javax.swing.JOptionPane.showMessageDialog(null, "PDF generado exitosamente:\n" + ruta);

    } catch (Exception e) {
        e.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(null, "Error al generar PDF:\n" + e.getMessage());
    }
}

// Método para generar celdas bonitas
private com.itextpdf.text.pdf.PdfPCell getCell(String text, com.itextpdf.text.Font font) {
    com.itextpdf.text.pdf.PdfPCell cell = new com.itextpdf.text.pdf.PdfPCell(
        new com.itextpdf.text.Phrase(text, font)
    );
    cell.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
    cell.setPadding(5f);
    return cell;
}
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String id = jLabel33.getText().trim();
        String email = turistaEmail1.getText().trim();

        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe llenar el campo");
            return;
        } else if (!Validador.validarCorreo(email)) {
            JOptionPane.showMessageDialog(null, "Debe dar un correo real", "", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (id.equals("ID")) {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún plan turístico.");
            return;
        }
        /*java.util.List<Plan> planesSeleccionados*/
        // Paso 1: Recolectar planes seleccionados (suponiendo que este método ya existe y retorna List<Plan>)
        java.util.List<Plan> planesSeleccionados = manejarSeleccionCheckbox();  // <<-- este método lo defines tú

        if (planesSeleccionados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se seleccionó ningún plan.");
            return;
        }

        // Paso 2: Generar el PDF
        generarPDFPlanesComprados(planesSeleccionados);

        // Paso 3: Enviar el correo (tu código tal como lo tenías)
        final String remitente = "kantkickgarabatoinc@gmail.com";
        final String clave = "lqrx slza fgij gmwu";
        String destinatario = email;
        String asunto = "Felicidades has hecho una compra en kant ";
        String cuerpo = "El plan para los grandes";
       String archivoRuta = new File("pdfs/documentok.pdf").getAbsolutePath();

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, clave);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);

            MimeBodyPart texto = new MimeBodyPart();
            texto.setText(cuerpo);

            MimeBodyPart adjunto = new MimeBodyPart();
            File archivo = new File(archivoRuta);

            if (!archivo.exists()) {
                JOptionPane.showMessageDialog(null, "Archivo no encontrado en: " + archivo.getCanonicalPath());
                return;
            }

            adjunto.attachFile(archivo);

            Multipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);

            message.setContent(multiParte);

            Transport.send(message);
            JOptionPane.showMessageDialog(null, "Correo enviado correctamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al enviar: " + e.getMessage());
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void turistaEmail1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_turistaEmail1FocusGained

    }//GEN-LAST:event_turistaEmail1FocusGained

    private void turistaEmail1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_turistaEmail1FocusLost

    }//GEN-LAST:event_turistaEmail1FocusLost

    private void turistaEmail1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_turistaEmail1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_turistaEmail1ActionPerformed

    private void jPanel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel20MouseClicked
        // TODO add your handling code here:
        jTabbedPane3.setSelectedComponent(turista1);
        jPanel20.setBackground(Color.white);
jPanel19.setBackground(new Color(204,204,204));
        jPanel18.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel20MouseClicked

    private void jPanel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel18MouseClicked
        // TODO add your handling code here:
        jTabbedPane3.setSelectedComponent(elpanelorigins);
        jPanel18.setBackground(Color.white);
jPanel19.setBackground(new Color(204,204,204));
        jPanel20.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel18MouseClicked

    private void jPanel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel19MouseClicked
        // TODO add your handling code here:
        
        jTabbedPane3.setSelectedComponent(asignacioPlanes1);
        jPanel19.setBackground(Color.white);
          jPanel20.setBackground(new Color(204,204,204));
          jPanel18.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel19MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         
    JFileChooser chooser = new JFileChooser();
    chooser.setVisible(true);
    chooser.setDialogTitle("Selecciona una imagen");

    FileNameExtensionFilter filtro = new FileNameExtensionFilter(
        "Imágenes (jpg, jpeg, png, gif)", "jpg", "jpeg", "png", "gif");
    chooser.setFileFilter(filtro);

    int resultado = chooser.showOpenDialog(this);
    if (resultado == JFileChooser.APPROVE_OPTION) {
        File archivo = chooser.getSelectedFile();
        if (archivo != null && archivo.isFile()) {
            imagenSeleccionada = archivo;

            // Asegúrate de que txtRuta existe y es un JTextField (o bórralo si no lo usas)
            txtImagen.setText(archivo.getAbsolutePath());
        } else {
            JOptionPane.showMessageDialog(this,
                "No se seleccionó un archivo válido",
                "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    } 

    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImagenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImagenActionPerformed

    private void txtImagenFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtImagenFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImagenFocusLost

    private void txtImagenFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtImagenFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImagenFocusGained



    public void guardarEstablecimiento(String usuarioID, String claveUsuario, String password, String nombre,
                                   String telefono, String correo, String fechaNacimiento, String direccion,
                                   String razonSocial) {
       

}

    public boolean guardarTurista(String usuarioID, String claveUsuario, String password, String nombre,
                           String telefono, String correo, String fechaNacimiento, String direccion) {
    Conexion conexion = new Conexion();
    Connection conn = null;

    try {
        conexion.conectar();
        conn = conexion.getConexion();
        conn.setAutoCommit(false);
    String sqlCheckCorreo = "SELECT COUNT(*) FROM Usuarios WHERE Correo = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(sqlCheckCorreo)) {
            checkStmt.setString(1, correo);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(null, "El correo ya está registrado. No se puede guardar.");
                return false;
            }
        }

         String passwordEncriptado = EncriptadorAES.encriptar(password);

        String sqlUsuarios = "INSERT INTO Usuarios (UsuarioID, Correo, Password, Nombre,Telefono,FechaNacimiento,FechaReg,Direccion, Estatus, rol) " +
                             "VALUES (?, ?, ?, ?,?,?,date('now'),?,'activo', '2')";
        try (PreparedStatement stmt = conn.prepareStatement(sqlUsuarios)) {
            stmt.setString(1, usuarioID);
            stmt.setString(2,claveUsuario);
            stmt.setString(3,passwordEncriptado);
            stmt.setString(4, nombre);
            stmt.setString(5, telefono);
            stmt.setString(6, fechaNacimiento);
            stmt.setString(7, direccion);
            stmt.executeUpdate();
        }

        

        conn.commit();
        JOptionPane.showMessageDialog(null, "Recepcionista agregado con exito");

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null,
            "Error al registrar turista:\n" + e.getMessage(),
            "Error SQL",
            JOptionPane.ERROR_MESSAGE);
        try {
            if (conn != null) conn.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Error al hacer rollback:\n" + ex.getMessage(),
                "Error crítico",
                JOptionPane.ERROR_MESSAGE);
        }
    } finally {
        conexion.cerrar();
    }
    return true;
}

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Recepcionista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Recepcionista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Recepcionista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Recepcionista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Recepcionista().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel asignacioPlanes;
    private javax.swing.JPanel asignacioPlanes1;
    private javax.swing.JPanel bannerBotnCrearU;
    private javax.swing.JToggleButton btnOjo;
    private javax.swing.JTextField buscador;
    private javax.swing.JTextField buscador1;
    private javax.swing.JTextField buscador2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JLabel calendarioIcon;
    private javax.swing.JLabel crearUserLabel;
    private javax.swing.JPanel crearUsuarios;
    private javax.swing.JFileChooser elegirFichero;
    private javax.swing.JPanel elpanelorigins;
    private javax.swing.JPanel establecimiento;
    private javax.swing.JPanel imaPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private com.toedter.calendar.JDateChooser jdtCalendario;
    private javax.swing.JPanel logOutBtn;
    private javax.swing.JPasswordField pan;
    private javax.swing.JPanel panelAddUsers5;
    private javax.swing.JPanel panelAddUsers6;
    private javax.swing.JPanel panelAsignarPlanTuristico;
    private javax.swing.JPanel panelAsignarPlanTuristico1;
    private javax.swing.JPanel panelInicial;
    private javax.swing.JPanel panelTabla;
    private javax.swing.JPanel panelTabla1;
    private javax.swing.JPanel panelTabla2;
    private javax.swing.JLabel paneluserlabel1;
    private javax.swing.JLabel paquetesLabel;
    private javax.swing.JPanel planesBtn5;
    private javax.swing.JPanel planesBtn6;
    private javax.swing.JLabel total;
    private javax.swing.JLabel total1;
    private javax.swing.JPanel turista;
    private javax.swing.JPanel turista1;
    private javax.swing.JTextField turistaAdress;
    private javax.swing.JTextField turistaEmail;
    private javax.swing.JTextField turistaEmail1;
    private javax.swing.JTextField turistaNombre;
    private javax.swing.JTextField turistaTel;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtImagen;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JLabel userlable;
    private javax.swing.JComboBox<String> usertype;
    // End of variables declaration//GEN-END:variables

    public class RoundedBorder extends AbstractBorder {
    private int radius;

    public RoundedBorder(int radius) {
        this.radius = radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(c.getBackground());
        g2.fillRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
}
}
