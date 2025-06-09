package appkant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sqlite.SQLiteException;
//C:\Users\migue\Music/KantApp.db
public class Conector {
    private final String url = "jdbc:sqlite:C:\\Users\\migue\\Music\\KantApp.db"; 
    private Connection connection;

    public Conector() {
        connect();
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection(url);
            if (connection != null) {
                System.out.println("Conexión exitosa a la base de datos.");
            }
        } catch (SQLException ex) {
            System.out.println("Error al conectar a la base de datos.");
            ex.printStackTrace();
        }
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada correctamente.");
            }
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la conexión.");
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
