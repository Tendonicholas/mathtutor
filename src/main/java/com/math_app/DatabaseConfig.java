package com.math_app;

import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseConfig {
    private static final Dotenv dotenv = Dotenv.load();

    public static String getUrl() {
        return dotenv.get("DB_URL");
    }

    public static String getUser() {
        return dotenv.get("DB_USER");
    }

    public static String getPassword() {
        return dotenv.get("DB_PASSWORD");
    }
}

