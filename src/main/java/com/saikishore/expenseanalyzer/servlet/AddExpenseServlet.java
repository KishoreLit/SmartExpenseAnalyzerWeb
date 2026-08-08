package com.saikishore.expenseanalyzer.servlet;

import com.saikishore.expenseanalyzer.dao.BudgetDAO;
import com.saikishore.expenseanalyzer.dao.ExpenseDAO;
import com.saikishore.expenseanalyzer.model.Expense;
import com.saikishore.expenseanalyzer.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@WebServlet("/add-expense")
public class AddExpenseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null ||
                session.getAttribute("loggedInUser") == null) {

            response.sendRedirect(request.getContextPath() + "/login-page");
            return;
        }

        User user = (User) session.getAttribute("loggedInUser");
        int userId = user.getId();

        BudgetDAO budgetDAO = new BudgetDAO();

        if (!budgetDAO.isBudgetSet(userId)) {

            response.sendRedirect(
                    request.getContextPath() + "/budget");
            return;
        }

        String title = request.getParameter("title");
        String amountStr = request.getParameter("amount");
        String category = request.getParameter("category");
        String dateStr = request.getParameter("date");
        String notes = request.getParameter("notes");

        // Title Validation
        if (title == null || title.trim().length() < 3) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/addExpensePage?error=title");

            return;
        }

        // Amount Validation
        if (amountStr == null || amountStr.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/addExpensePage?error=amount");

            return;
        }

        double amount;

        try {

            amount = Double.parseDouble(amountStr.trim());

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/addExpensePage?error=amount");

            return;
        }

        if (amount <= 0) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/addExpensePage?error=negative");

            return;
        }

        // Category Validation
        if (category == null || category.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/addExpensePage?error=category");

            return;
        }

        // Date Validation
        if (dateStr == null || dateStr.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/addExpensePage?error=date");

            return;
        }

        LocalDate expenseDate;

        try {

            expenseDate = LocalDate.parse(dateStr);

        } catch (DateTimeParseException e) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/addExpensePage?error=date");

            return;
        }

        Expense expense = new Expense();

        expense.setTitle(title.trim());
        expense.setAmount(amount);
        expense.setCategory(category);
        expense.setExpenseDate(expenseDate);
        expense.setNotes(notes == null ? "" : notes.trim());
        expense.setUserId(userId);

        ExpenseDAO dao = new ExpenseDAO();

        boolean saved = dao.saveExpense(expense);

        if (saved) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/viewExpenses");

        } else {

            response.sendRedirect(
                    request.getContextPath()
                            + "/addExpensePage?error=saveFailed");
        }
    }
}