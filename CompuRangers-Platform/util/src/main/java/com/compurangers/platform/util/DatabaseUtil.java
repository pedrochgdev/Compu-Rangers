package com.compurangers.platform.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static DatabaseUtil dbManager;
    private String host;
    private int puerto;
    private String esquema;
    private String usuario;
    private String password;
    
    private DatabaseUtil() throws IOException {
        cargarProperties();
    }
    
    public synchronized static DatabaseUtil getInstance() throws IOException {
        if (dbManager == null) {
            createInstance();
        }
        return dbManager;
    }
    
    private static void createInstance() throws IOException {
        dbManager = new DatabaseUtil();
    }
    
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String cadenaConexion = cadenaConexion(host, puerto, esquema);
            return DriverManager.getConnection(cadenaConexion, usuario, password);
        }
        catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
            throw e;
        }
    }
    
    private void cargarProperties() throws IOException {
        host = Config.getEnv("DB_HOST");
        puerto = Integer.parseInt(Config.getEnv("DB_PORT"));
        esquema = Config.getEnv("DB_SCHEME");
        usuario = Config.getEnv("DB_USER");
        password = Config.getEnv("DB_PASSWORD");
    }
    
    private String cadenaConexion(String host, int puerto, String esquema) {
        return String.format("jdbc:mysql://%s:%d/%s?useSSL=false&allowPublicKeyRetrieval=true", host, puerto, esquema);
    }

}