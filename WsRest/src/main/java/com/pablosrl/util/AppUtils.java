package com.pablosrl.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.log4j.Logger;

public class AppUtils {

    static Logger logger = Logger.getLogger(AppUtils.class);

 // Configuraciones de conexión a la base de datos
    private static final String DATABASE_URL = "jdbc:oracle:thin:@192.168.100.225:1521:ORCL"; // DESARROLLO INV casa robus 29-07-2023
    // private static final String DATABASE_URL = "jdbc:oracle:thin:@201.222.48.18:3321:cab"; // DESARROLLO INV REMOTO
    // private static final String DATABASE_URL = "jdbc:oracle:thin:@192.168.0.56:1521:ORCL"; // PRODUCCION

    // Añadir la constante para la ruta de las imágenes
    public static final String IMAGE_DIRECTORY = "\\\\192.168.100.225\\fotos_articulos\\"; // Prueba local casa robus
    // public static final String IMAGE_DIRECTORY = "\\\\\\\\192.168.0.56\\\\inventiva\\\\INVENTIVA\\\\EXE\\\\FOTOS_ARTICULOS\\\\";

    // Usuario y contraseña de la base de datos
    private static final String DATABASE_USER = "inv";
    private static final String DATABASE_PASS = "inv";

    // Configuración del pool de conexiones
    private static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(DATABASE_URL);
        config.setUsername(DATABASE_USER);
        config.setPassword(DATABASE_PASS);
        config.setMaximumPoolSize(30);  // Máximo de 30 conexiones simultáneas
        config.setMinimumIdle(10);      // Mínimo de conexiones inactivas en el pool
        config.setConnectionTimeout(30000);  // Tiempo de espera para obtener una conexión: 30 segundos
        config.setIdleTimeout(600000);   // Tiempo máximo que una conexión inactiva se mantendrá abierta: 10 minutos
        config.setMaxLifetime(1800000);  // Vida útil máxima de una conexión: 30 minutos
        dataSource = new HikariDataSource(config);
    }

    // Método para obtener una conexión desde el pool
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // Método para cerrar el pool cuando la aplicación se cierre (opcional)
    public static void closeDataSource() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
    
 // Convertir una cadena de texto en formato 'DD/MM/YYYY' a LocalDate
    public static LocalDate convertirStringAFecha(String fecha) {
        try {
            if (fecha != null) {
                // Usar DateTimeFormatter para manejar fechas en formato DD/MM/YYYY
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date = LocalDate.parse(fecha, formatter);
                return date;
            } else {
                return null;
            }
        } catch (DateTimeParseException e) {
            logger.error("No se pudo parsear la fecha " + fecha + " " + e);
            return null;
        }
    }
    
 // Convertir una cadena de texto en formato 'DD/MM/YYYY HH:mm:ss' a LocalDateTime (fecha y hora)
    public static LocalDateTime convertirStringAFechaYHora(String fechaHora) {
        try {
            if (fechaHora != null) {
                // Usar DateTimeFormatter para manejar fechas con hora en formato DD/MM/YYYY HH:mm:ss
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(fechaHora, formatter);
                return dateTime;
            } else {
                return null;
            }
        } catch (DateTimeParseException e) {
            logger.error("No se pudo parsear la fecha y hora " + fechaHora + " " + e);
            return null;
        }
    }
}


