package proyectos.cibertec.core.util.report;

import java.sql.Connection;
import java.sql.DriverManager;
 
public class Database {
 
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/animalc_chip247", "root", "mysql");
            //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/animalc_chip247", "animalc_admin", "myadmin");
            return con;
        } catch (Exception ex) {
            System.out.println("Database.getConnection() Error -->" + ex.getMessage());
            return null;
        }
        
        
        
    }
 
    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
        }
    }
}