package com.saikishore.expenseanalyzer.servlet;

import com.saikishore.expenseanalyzer.dao.ExpenseDAO;
import com.saikishore.expenseanalyzer.model.User;

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

        // Check if user is logged in
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect(request.getContextPath() + "/login-page");
            return;
        }

        // Get logged-in user
        User user = (User) session.getAttribute("loggedInUser");
        int userId = user.getId();

        int id = Integer.parseInt(request.getParameter("id"));

        ExpenseDAO dao = new ExpenseDAO();

        boolean deleted = dao.deleteExpense(id, userId);

        if (deleted) {
            response.sendRedirect(request.getContextPath() + "/viewExpenses");
        } else {
            response.getWriter().println("Failed to Delete Expense!");
        }
    }
}