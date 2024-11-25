package com.pablosrl.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
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

    // URLs de conexión a la base de datos para desarrollo y producción
    private static final String DEV_DATABASE_URL = "jdbc:oracle:thin:@192.168.100.225:1521:ORCL"; // DESARROLLO
    private static final String PROD_DATABASE_URL = "jdbc:oracle:thin:@192.168.0.56:1521:ORCL";   // PRODUCCION

    // Ruta de imágenes
    public static final String IMAGE_DIRECTORY_DEV = "\\\\192.168.100.225\\fotos_articulos\\"; // Desarrollo
    public static final String IMAGE_DIRECTORY_PROD = "\\\\192.168.0.56\\inventiva\\INVENTIVA\\EXE\\FOTOS_ARTICULOS\\"; // Producción

    // Configuración del pool de conexiones
    private static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();

        try {
            // Detectar la IP del servidor donde está corriendo la aplicación
            String serverIp = InetAddress.getLocalHost().getHostAddress();
            System.out.println("IP del servidor detectada: " + serverIp);

            // Compara la IP y selecciona la URL de la base de datos correspondiente
            if (serverIp.equals("192.168.0.56")) {
                // Si está en producción
                config.setJdbcUrl(PROD_DATABASE_URL);
                System.out.println("Conectado al servidor de PRODUCCIÓN");
            } else {
                // Si está en desarrollo
                config.setJdbcUrl(DEV_DATABASE_URL);
                System.out.println("Conectado al servidor de DESARROLLO");
            }

            // Credenciales por defecto para evitar excepciones en inicialización
            config.setUsername("inv");
            config.setPassword("inv");

        } catch (UnknownHostException e) {
            e.printStackTrace();
            // Fallback en caso de que no se pueda detectar la IP
            config.setJdbcUrl(DEV_DATABASE_URL);  // Por defecto a desarrollo
            System.out.println("Conectado al servidor de DESARROLLO (fallback)");
        }

        config.setMaximumPoolSize(30);  // Máximo de 30 conexiones simultáneas
        config.setMinimumIdle(10);      // Mínimo de conexiones inactivas en el pool
        config.setConnectionTimeout(30000);  // Tiempo de espera para obtener una conexión: 30 segundos
        config.setIdleTimeout(600000);   // Tiempo máximo que una conexión inactiva se mantendrá abierta: 10 minutos
        config.setMaxLifetime(1800000);  // Vida útil máxima de una conexión: 30 minutos
        dataSource = new HikariDataSource(config);
    }

    // Método para obtener una conexión desde el pool con credenciales por defecto
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // Nuevo método para obtener una conexión personalizada
    public static Connection getConnection(String username, String password) throws SQLException {
        HikariConfig dynamicConfig = new HikariConfig();

        try {
            // Detectar la IP del servidor donde está corriendo la aplicación
            String serverIp = InetAddress.getLocalHost().getHostAddress();
            System.out.println("IP del servidor detectada: " + serverIp);

            // Configurar la URL de conexión según el entorno
            if (serverIp.equals("192.168.0.56")) {
                dynamicConfig.setJdbcUrl(PROD_DATABASE_URL);
                System.out.println("Conectado al servidor de PRODUCCIÓN");
            } else {
                dynamicConfig.setJdbcUrl(DEV_DATABASE_URL);
                System.out.println("Conectado al servidor de DESARROLLO");
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
            dynamicConfig.setJdbcUrl(DEV_DATABASE_URL); // Fallback a desarrollo
            System.out.println("Conectado al servidor de DESARROLLO (fallback)");
        }

        // Configurar las credenciales proporcionadas
        dynamicConfig.setUsername(username);
        dynamicConfig.setPassword(password);

        // Validar la conexión antes de devolverla
        try (HikariDataSource dynamicDataSource = new HikariDataSource(dynamicConfig)) {
            Connection connection = dynamicDataSource.getConnection();
            System.out.println("Usuario autenticado con éxito.");
            return connection; // Devuelve la conexión válida
        } catch (SQLException e) {
            System.out.println("Error de autenticación: " + e.getMessage());
            throw new SQLException("Usuario o contraseña incorrectos.");
        }
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
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                return LocalDate.parse(fecha, formatter);
            } else {
                return null;
            }
        } catch (DateTimeParseException e) {
            logger.error("No se pudo parsear la fecha " + fecha, e);
            return null;
        }
    }

    // Convertir una cadena de texto en formato 'DD/MM/YYYY HH:mm:ss' a LocalDateTime
    public static LocalDateTime convertirStringAFechaYHora(String fechaHora) {
        try {
            if (fechaHora != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                return LocalDateTime.parse(fechaHora, formatter);
            } else {
                return null;
            }
        } catch (DateTimeParseException e) {
            logger.error("No se pudo parsear la fecha y hora " + fechaHora, e);
            return null;
        }
    }
}

