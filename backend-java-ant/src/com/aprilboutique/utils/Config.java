package com.aprilboutique.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    private static final Dotenv dotenv = Dotenv.configure()
            .directory("../") // Sube un nivel desde backend-java-ant hasta mi-ecommerce
            .load();

    public static String getEnv(String key) {
        String value = System.getenv(key); // Primero busca en variables de entorno del sistema
        return (value != null) ? value : dotenv.get(key); // Si no, usa el .env
    }
}