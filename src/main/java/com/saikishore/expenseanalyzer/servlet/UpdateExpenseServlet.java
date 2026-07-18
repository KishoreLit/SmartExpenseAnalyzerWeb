package com.saikishore.expenseanalyzer.servlet;

import com.saikishore.expenseanalyzer.dao.ExpenseDAO;
import com.saikishore.expenseanalyzer.model.Expense;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/updateExpense")
public class UpdateExpenseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        ExpenseDAO dao = new ExpenseDAO();

        Expense expense = dao.getExpenseById(id);

        request.setAttribute("expense", expense);

        request.getRequestDispatcher("edit-expense.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        String title = request.getParameter("title");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String category = request.getParameter("category");
        LocalDate expenseDate = LocalDate.parse(request.getParameter("expenseDate"));
        String notes = request.getParameter("notes");

        Expense expense = new Expense();

        expense.setId(id);
        expense.setTitle(title);
        expense.setAmount(amount);
        expense.setCategory(category);
        expense.setExpenseDate(expenseDate);
        expense.setNotes(notes);

        ExpenseDAO dao = new ExpenseDAO();

        boolean updated = dao.updateExpense(expense);

        if (updated) {
            response.sendRedirect("viewExpenses");
        } else {
            response.getWriter().println("Failed to Update Expense!");
        }
    }
}