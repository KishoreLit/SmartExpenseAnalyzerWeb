package com.saikishore.expenseanalyzer.servlet;

import com.saikishore.expenseanalyzer.dao.BudgetDAO;
import com.saikishore.expenseanalyzer.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/saveBudget")
public class SaveBudgetServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
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

        double monthlyBudget =
                Double.parseDouble(request.getParameter("monthlyBudget"));

        BudgetDAO budgetDAO = new BudgetDAO();

        boolean updated = budgetDAO.updateMonthlyBudget(userId, monthlyBudget);

        if (updated) {

            response.sendRedirect(
                request.getContextPath()
                + "/dashboard?success=budgetSaved");
        } else {

            request.setAttribute("error", "Unable to update budget.");

            request.getRequestDispatcher("/budget.jsp")
                   .forward(request, response);
        }
    }
}