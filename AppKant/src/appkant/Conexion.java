/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appkant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sqlite.SQLiteException;

/**
 *
 * C:\Users\migue\Music\\KantDBNew.db
 */
public class Conexion {
    private String url="C:\\Users\\migue\\Music\\KantDBNew.db";
    private Connection conexion;
    
    public void conectar()throws SQLException{
        try{
            conexion=DriverManager.getConnection("jdbc:sqlite:"+url);
            if (conexion != null) {
                System.out.println("Conexión exitosa a la base de datos.");
            }
        }catch (SQLiteException ex) {
            System.out.println("Error al conectar a la base de datos.");
            ex.printStackTrace();
        }
    }

    public Connection getConexion() {
        return conexion;
    }
    
    public void cerrar(){
        try{
            conexion.close();
        }catch(SQLException e){
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    public static Connection obtenerConexion() throws ClassNotFoundException {
        String url = "jdbc:sqlite:KantDBNew.db";


        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    
}
