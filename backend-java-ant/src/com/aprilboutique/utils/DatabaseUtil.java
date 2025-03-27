
package com.aprilboutique.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//Manejo de la conexion a la base de datos a traves de JDBC

public class DatabaseUtil {
    private static final Properties props = new Properties();
    private static Connection connection = null;

    static {
        try (InputStream input = DatabaseUtil.class.getClassLoader()
                .getResourceAsStream("config/database.properties")) {
            if (input == null) {
                throw new IOException("No se encontró config/database.properties en el classpath");
            }
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar las propiedades de la base de datos", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(
                props.getProperty("db.url"),
                props.getProperty("db.user"),
                props.getProperty("db.password")
            );
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connection = null; // Evita posibles fugas de memoria
            }
        }
    }
}