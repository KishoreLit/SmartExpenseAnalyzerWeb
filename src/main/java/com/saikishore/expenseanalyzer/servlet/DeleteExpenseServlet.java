package com.saikishore.expenseanalyzer.servlet;

import com.saikishore.expenseanalyzer.dao.ExpenseDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/deleteExpense")
public class DeleteExpenseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        ExpenseDAO dao = new ExpenseDAO();

        boolean deleted = dao.deleteExpense(id);

        if (deleted) {
            response.sendRedirect("viewExpenses");
        } else {
            response.getWriter().println("Failed to Delete Expense!");
        }
    }
}