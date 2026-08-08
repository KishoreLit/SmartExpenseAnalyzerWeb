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

@WebServlet("/budget")
public class BudgetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loggedInUser") == null) {

            response.sendRedirect(request.getContextPath() + "/login-page");
            return;

        }

        User user = (User) session.getAttribute("loggedInUser");
        int userId = user.getId();

        BudgetDAO budgetDAO = new BudgetDAO();

        double monthlyBudget = budgetDAO.getMonthlyBudget(userId);

        boolean firstTime = monthlyBudget <= 0;

        request.setAttribute("monthlyBudget", monthlyBudget);
        request.setAttribute("activePage", "budget");
        request.setAttribute("firstTimeSetup", firstTime);

        request.getRequestDispatcher("budget.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loggedInUser") == null) {

            response.sendRedirect(request.getContextPath() + "/login-page");
            return;

        }

        User user = (User) session.getAttribute("loggedInUser");
        int userId = user.getId();

        String budgetStr = request.getParameter("monthlyBudget");

        // Validation
        if (budgetStr == null || budgetStr.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/budget?error=amount");

            return;
        }

        double budget;

        try {

            budget = Double.parseDouble(budgetStr.trim());

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/budget?error=amount");

            return;
        }

        if (Double.isNaN(budget)
                || Double.isInfinite(budget)
                || budget <= 0) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/budget?error=negative");

            return;
        }

        BudgetDAO budgetDAO = new BudgetDAO();

        boolean success;

        if (budgetDAO.isBudgetSet(userId)) {

            success = budgetDAO.updateMonthlyBudget(userId, budget);

        } else {

            success = budgetDAO.createBudget(userId, budget);

        }

        if (success) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/dashboard");

        } else {

            response.sendRedirect(
                    request.getContextPath()
                            + "/budget?error=saveFailed");

        }
    }
}