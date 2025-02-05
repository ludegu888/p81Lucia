
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
   
    // Estos parámetros de conexión deben ser establecidos en función de 
    // tu servidor y usuario de conexión
    private static final String SERVIDOR = "jdbc:mysql://127.0.0.1:3306/";
    private static final String NOMBRE_BASE_DATOS = "p81Lucia";
    private static final String USER = "lucia";
    private static final String PASS = "plfcgz";

    private static Connection instancia = null;
    
    // Constructor privado no accesible desde otras clases
    private Conexion() {

    }

    // Método de clase para acceder a la instancia del objeto Connection
    public static Connection getInstance() {
        // Si el objeto Connection no está creado, se crea
        if (instancia == null) {
            try {

                // Se crea el objeto Connection	
                instancia = DriverManager.getConnection(SERVIDOR + NOMBRE_BASE_DATOS, USER, PASS);

                System.out.println("Conexión realizada con éxito.");

            } catch (SQLException e) {

                // Error en la conexión
                System.out.println("Conexión fallida: " + e.getMessage());
            }
        }
        return instancia;
    }

}
