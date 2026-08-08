package com.saikishore.expenseanalyzer.dao;

import com.saikishore.expenseanalyzer.model.Expense;
import com.saikishore.expenseanalyzer.util.DBConnection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;


public class ExpenseDAO {

    public boolean saveExpense(Expense expense) {

        String sql = """
                INSERT INTO expenses
                (title, amount, category, expense_date, notes, user_id)
                VALUES (?, ?, ?, ?, ?, ?)
                """;
    
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
    
            preparedStatement.setString(1, expense.getTitle());
            preparedStatement.setDouble(2, expense.getAmount());
            preparedStatement.setString(3, expense.getCategory());
            preparedStatement.setDate(4, java.sql.Date.valueOf(expense.getExpenseDate()));
            preparedStatement.setString(5, expense.getNotes());
            preparedStatement.setInt(6, expense.getUserId());
    
            return preparedStatement.executeUpdate() > 0;
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return false;
    }
    public List<Expense> getAllExpenses(int userId) {

        List<Expense> expenses = new ArrayList<>();
    
        String sql = """
                SELECT *
                FROM expenses
                WHERE user_id = ?
                ORDER BY expense_date DESC
                """;
    
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
    
            preparedStatement.setInt(1, userId);
    
            ResultSet resultSet = preparedStatement.executeQuery();
    
            while (resultSet.next()) {
    
                Expense expense = new Expense();
    
                expense.setId(resultSet.getInt("id"));
                expense.setTitle(resultSet.getString("title"));
                expense.setAmount(resultSet.getDouble("amount"));
                expense.setCategory(resultSet.getString("category"));
                expense.setExpenseDate(resultSet.getDate("expense_date").toLocalDate());
                expense.setNotes(resultSet.getString("notes"));
                expense.setUserId(resultSet.getInt("user_id"));
    
                expenses.add(expense);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return expenses;
    }
    public Expense getExpenseById(int id, int userId) {

        String sql = """
                SELECT *
                FROM expenses
                WHERE id = ? AND user_id = ?
                """;
    
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
    
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, userId);
    
            ResultSet resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) {
    
                Expense expense = new Expense();
    
                expense.setId(resultSet.getInt("id"));
                expense.setTitle(resultSet.getString("title"));
                expense.setAmount(resultSet.getDouble("amount"));
                expense.setCategory(resultSet.getString("category"));
                expense.setExpenseDate(resultSet.getDate("expense_date").toLocalDate());
                expense.setNotes(resultSet.getString("notes"));
                expense.setUserId(resultSet.getInt("user_id"));
    
                return expense;
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return null;
    }
    public boolean updateExpense(Expense expense) {

        String sql = """
                UPDATE expenses
                SET title = ?,
                    amount = ?,
                    category = ?,
                    expense_date = ?,
                    notes = ?
                WHERE id = ?
                AND user_id = ?
                """;
    
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
    
            preparedStatement.setString(1, expense.getTitle());
            preparedStatement.setDouble(2, expense.getAmount());
            preparedStatement.setString(3, expense.getCategory());
            preparedStatement.setDate(4, java.sql.Date.valueOf(expense.getExpenseDate()));
            preparedStatement.setString(5, expense.getNotes());
            preparedStatement.setInt(6, expense.getId());
            preparedStatement.setInt(7, expense.getUserId());
    
            return preparedStatement.executeUpdate() > 0;
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return false;
    }
    public boolean deleteExpense(int id, int userId) {

        String sql = """
                DELETE FROM expenses
                WHERE id = ?
                AND user_id = ?
                """;
    
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
    
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, userId);
    
            return preparedStatement.executeUpdate() > 0;
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return false;
    }
    public double getTotalExpense(int userId) {

        String sql = """
                SELECT SUM(amount) AS total
                FROM expenses
                WHERE user_id = ?
                """;
    
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
    
            preparedStatement.setInt(1, userId);
    
            ResultSet resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) {
                return resultSet.getDouble("total");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return 0;
    }
    public int getTotalTransactions(int userId) {

        String sql = """
                SELECT COUNT(*) AS total
                FROM expenses
                WHERE user_id = ?
                """;
    
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
    
            preparedStatement.setInt(1, userId);
    
            ResultSet resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) {
                return resultSet.getInt("total");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return 0;
    }
    public double getHighestExpense(int userId) {

        String sql = """
                SELECT MAX(amount) AS highest
                FROM expenses
                WHERE user_id = ?
                """;
    
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
    
            preparedStatement.setInt(1, userId);
    
            ResultSet resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) {
                return resultSet.getDouble("highest");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return 0;
    }
    public String getLatestExpense(int userId) {

        String sql = """
                SELECT title
                FROM expenses
                WHERE user_id = ?
                ORDER BY id DESC
                LIMIT 1
                """;
    
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
    
            preparedStatement.setInt(1, userId);
    
            ResultSet resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) {
                return resultSet.getString("title");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return "No Expenses";
    }
    public List<Expense> getRecentExpenses(int userId) {

        List<Expense> expenses = new ArrayList<>();
    
        String sql = """
                SELECT *
                FROM expenses
                WHERE user_id = ?
                ORDER BY id DESC
                LIMIT 5
                """;
    
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
    
            preparedStatement.setInt(1, userId);
    
            ResultSet resultSet = preparedStatement.executeQuery();
    
            while (resultSet.next()) {
    
                Expense expense = new Expense();
    
                expense.setId(resultSet.getInt("id"));
                expense.setTitle(resultSet.getString("title"));
                expense.setAmount(resultSet.getDouble("amount"));
                expense.setCategory(resultSet.getString("category"));
                expense.setExpenseDate(resultSet.getDate("expense_date").toLocalDate());
                expense.setNotes(resultSet.getString("notes"));
                expense.setUserId(resultSet.getInt("user_id"));
    
                expenses.add(expense);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return expenses;
    }
    public List<Expense> searchExpenses(int userId,
        String title,
        String category,
        String fromDate,
        String toDate) {

            List<Expense> expenses = new ArrayList<>();

            StringBuilder sql = new StringBuilder(
                    "SELECT * FROM expenses WHERE user_id = ?"
            );
        
            List<Object> parameters = new ArrayList<>();
            parameters.add(userId);
        
            if (title != null && !title.isBlank()) {
                sql.append(" AND title LIKE ?");
                parameters.add("%" + title + "%");
            }
        
            if (category != null && !category.isBlank()) {
                sql.append(" AND category = ?");
                parameters.add(category);
            }
        
            if (fromDate != null && !fromDate.isBlank()) {
                sql.append(" AND expense_date >= ?");
                parameters.add(java.sql.Date.valueOf(fromDate));
            }
        
            if (toDate != null && !toDate.isBlank()) {
                sql.append(" AND expense_date <= ?");
                parameters.add(java.sql.Date.valueOf(toDate));
            }
        
            sql.append(" ORDER BY expense_date DESC");
        
            try (
                    Connection connection = DBConnection.getConnection();
                    PreparedStatement preparedStatement =
                            connection.prepareStatement(sql.toString())
            ) {
        
                for (int i = 0; i < parameters.size(); i++) {
                    preparedStatement.setObject(i + 1, parameters.get(i));
                }
        
                ResultSet resultSet = preparedStatement.executeQuery();
        
                while (resultSet.next()) {
        
                    Expense expense = new Expense();
        
                    expense.setId(resultSet.getInt("id"));
                    expense.setTitle(resultSet.getString("title"));
                    expense.setAmount(resultSet.getDouble("amount"));
                    expense.setCategory(resultSet.getString("category"));
                    expense.setExpenseDate(
                            resultSet.getDate("expense_date").toLocalDate()
                    );
                    expense.setNotes(resultSet.getString("notes"));
                    expense.setUserId(resultSet.getInt("user_id"));
        
                    expenses.add(expense);
                }
        
            } catch (SQLException e) {
                e.printStackTrace();
            }
        
            return expenses;
        }
        public Map<String, Double> getCategorySummary(int userId) {
            Map<String, Double> summary = new LinkedHashMap<>();

            String sql = """
                    SELECT category,
                           SUM(amount) AS total
                    FROM expenses
                    WHERE user_id = ?
                    GROUP BY category
                    ORDER BY total DESC
                    """;
        
            try (
                    Connection connection = DBConnection.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(sql)
            ) {
        
                preparedStatement.setInt(1, userId);
        
                ResultSet resultSet = preparedStatement.executeQuery();
        
                while (resultSet.next()) {
        
                    summary.put(
                            resultSet.getString("category"),
                            resultSet.getDouble("total")
                    );
                }
        
            } catch (SQLException e) {
                e.printStackTrace();
            }
        
            return summary;
        }
}