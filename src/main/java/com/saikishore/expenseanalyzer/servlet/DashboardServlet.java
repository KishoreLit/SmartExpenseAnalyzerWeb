package com.saikishore.expenseanalyzer.servlet;

import com.saikishore.expenseanalyzer.dao.BudgetDAO;
import com.saikishore.expenseanalyzer.dao.ExpenseDAO;
import com.saikishore.expenseanalyzer.model.Expense;
import com.saikishore.expenseanalyzer.model.User;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

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

        User user = (User) session.getAttribute("loggedInUser");
        int userId = user.getId();
        

        ExpenseDAO dao = new ExpenseDAO();
        BudgetDAO budgetDAO = new BudgetDAO();
        
if (!budgetDAO.isBudgetSet(userId)) {

    response.sendRedirect(request.getContextPath() + "/budget");
    return;

}

        // Dashboard Statistics
        double totalExpense = dao.getTotalExpense(userId);
        int totalTransactions = dao.getTotalTransactions(userId);
        double highestExpense = dao.getHighestExpense(userId);
        String latestExpense = dao.getLatestExpense(userId);

        // Budget Details
        double monthlyBudget = budgetDAO.getMonthlyBudget(userId);
        double remainingBudget = monthlyBudget - totalExpense;

        double budgetPercentage = 0;

        if (monthlyBudget > 0) {
            budgetPercentage = (totalExpense / monthlyBudget) * 100;
        }

        if (budgetPercentage > 100) {
            budgetPercentage = 100;
        }

        // Recent Expenses
        List<Expense> recentExpenses = dao.getRecentExpenses(userId);

        // Send data to JSP
        request.setAttribute("totalExpense", totalExpense);
        request.setAttribute("totalTransactions", totalTransactions);
        request.setAttribute("highestExpense", highestExpense);
        request.setAttribute("latestExpense", latestExpense);

        request.setAttribute("monthlyBudget", monthlyBudget);
        request.setAttribute("remainingBudget", remainingBudget);
        request.setAttribute("budgetPercentage", budgetPercentage);

        request.setAttribute("recentExpenses", recentExpenses);

        // Highlight active menu
        request.setAttribute("activePage", "dashboard");

        // Forward to dashboard
        request.getRequestDispatcher("dashboard.jsp")
               .forward(request, response);
    }
}