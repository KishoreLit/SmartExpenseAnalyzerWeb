package com.saikishore.expenseanalyzer.servlet;

import com.saikishore.expenseanalyzer.dao.BudgetDAO;
import com.saikishore.expenseanalyzer.dao.ExpenseDAO;
import com.saikishore.expenseanalyzer.dao.UserDAO;
import com.saikishore.expenseanalyzer.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // Check login
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect(request.getContextPath() + "/login-page");
            return;
        }

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        int userId = loggedInUser.getId();

        // Check budget
        BudgetDAO budgetDAO = new BudgetDAO();

        if (!budgetDAO.isBudgetSet(userId)) {
            response.sendRedirect(request.getContextPath() + "/budget");
            return;
        }

        // DAO objects
        UserDAO userDAO = new UserDAO();
        ExpenseDAO expenseDAO = new ExpenseDAO();

        // Fetch user details
        User user = userDAO.getUserById(userId);

        // Fetch statistics
        double monthlyBudget = budgetDAO.getMonthlyBudget(userId);
        double totalExpense = expenseDAO.getTotalExpense(userId);
        int totalTransactions = expenseDAO.getTotalTransactions(userId);
        double remainingBudget = monthlyBudget - totalExpense;

        // Send data to JSP
        request.setAttribute("user", user);
        request.setAttribute("monthlyBudget", monthlyBudget);
        request.setAttribute("totalExpense", totalExpense);
        request.setAttribute("remainingBudget", remainingBudget);
        request.setAttribute("totalTransactions", totalTransactions);

        request.getRequestDispatcher("/profile.jsp")
               .forward(request, response);
    }
}