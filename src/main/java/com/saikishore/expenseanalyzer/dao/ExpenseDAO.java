package com.saikishore.expenseanalyzer.dao;

import com.saikishore.expenseanalyzer.model.Expense;
import com.saikishore.expenseanalyzer.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ExpenseDAO {

    public boolean saveExpense(Expense expense) {

        String sql = "INSERT INTO expenses(title, amount, category, expense_date, notes) VALUES (?, ?, ?, ?, ?)";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {

            preparedStatement.setString(1, expense.getTitle());
            preparedStatement.setDouble(2, expense.getAmount());
            preparedStatement.setString(3, expense.getCategory());
            preparedStatement.setDate(4, java.sql.Date.valueOf(expense.getExpenseDate()));
            preparedStatement.setString(5, expense.getNotes());

            int rows = preparedStatement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    public List<Expense> getAllExpenses() {

        List<Expense> expenses = new ArrayList<>();
    
        String sql = "SELECT * FROM expenses ORDER BY expense_date DESC";
    
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
    
            while (resultSet.next()) {
    
                Expense expense = new Expense();
    
                expense.setId(resultSet.getInt("id"));
                expense.setTitle(resultSet.getString("title"));
                expense.setAmount(resultSet.getDouble("amount"));
                expense.setCategory(resultSet.getString("category"));
                expense.setExpenseDate(resultSet.getDate("expense_date").toLocalDate());
                expense.setNotes(resultSet.getString("notes"));
    
                expenses.add(expense);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return expenses;
    }
}