package appkant;

import java.sql.SQLException;

public class AppKant {
    public static void main(String[] args) throws SQLException {
        //Conector c = new Conector(); // Se conecta automáticamente en el constructor

        // Crear e iniciar la ventana de Login
        Login l1 = new Login();
        //Utilidad u1=new Utilidad();
        l1.setVisible(true);
        //u1.setVisible(true);

    }
}
