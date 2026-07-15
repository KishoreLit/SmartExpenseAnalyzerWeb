package com.saikishore.expenseanalyzer.servlet;

import com.saikishore.expenseanalyzer.dao.ExpenseDAO;
import com.saikishore.expenseanalyzer.model.Expense;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/addExpense")
public class AddExpenseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("title");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String category = request.getParameter("category");
        LocalDate expenseDate = LocalDate.parse(request.getParameter("expenseDate"));
        String notes = request.getParameter("notes");

        Expense expense = new Expense();

        expense.setTitle(title);
        expense.setAmount(amount);
        expense.setCategory(category);
        expense.setExpenseDate(expenseDate);
        expense.setNotes(notes);

        ExpenseDAO dao = new ExpenseDAO();

        boolean saved = dao.saveExpense(expense);

        if (saved) {
            response.sendRedirect("viewExpenses");
        } else {
            response.getWriter().println("Failed to Add Expense!");
        }
    }
}