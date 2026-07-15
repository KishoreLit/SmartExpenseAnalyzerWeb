package com.saikishore.expenseanalyzer;

import com.saikishore.expenseanalyzer.util.DBConnection;
import java.sql.Connection;

public class TestConnection {

    public static void main(String[] args) {

        Connection connection = DBConnection.getConnection();

        if (connection != null) {
            System.out.println("✅ Database Connected Successfully!");

            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("❌ Database Connection Failed!");
        }

    }
}