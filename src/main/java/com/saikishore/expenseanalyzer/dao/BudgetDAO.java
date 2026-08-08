package com.saikishore.expenseanalyzer.dao;

import com.saikishore.expenseanalyzer.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BudgetDAO {

    // Get current user's monthly budget
    public double getMonthlyBudget(int userId) {

        String sql = """
                SELECT monthly_budget
                FROM budget
                WHERE user_id = ?
                """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql)
        ) {

            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getDouble("monthly_budget");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    // Update existing budget
    public boolean updateMonthlyBudget(int userId, double budget) {

        String sql = """
                UPDATE budget
                SET monthly_budget = ?
                WHERE user_id = ?
                """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql)
        ) {

            preparedStatement.setDouble(1, budget);
            preparedStatement.setInt(2, userId);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Create first budget
    public boolean createBudget(int userId, double budget) {

        String sql = """
                INSERT INTO budget(user_id, monthly_budget)
                VALUES(?, ?)
                """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql)
        ) {

            preparedStatement.setInt(1, userId);
            preparedStatement.setDouble(2, budget);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Check whether a budget row exists
    public boolean isBudgetSet(int userId) {

        String sql = """
                SELECT 1
                FROM budget
                WHERE user_id = ?
                """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql)
        ) {

            preparedStatement.setInt(1, userId);

            ResultSet rs = preparedStatement.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}