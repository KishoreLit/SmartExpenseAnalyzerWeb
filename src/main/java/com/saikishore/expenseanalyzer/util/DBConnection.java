package com.saikishore.expenseanalyzer.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = getEnvOrDefault(
            "DB_URL",
            "jdbc:mysql://localhost:3306/expense_db"
    );

    private static final String USERNAME = getEnvOrDefault(
            "DB_USERNAME",
            "root"
    );

    private static final String PASSWORD = getEnvOrDefault(
            "DB_PASSWORD",
            ""
    );

    private static String getEnvOrDefault(String key, String defaultValue) {
        String value = System.getenv(key);
        return (value == null || value.isBlank()) ? defaultValue : value;
    }

    public static Connection getConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(
                    URL,
                    USERNAME,
                    PASSWORD
            );

        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();

            return null;
        }
    }
}