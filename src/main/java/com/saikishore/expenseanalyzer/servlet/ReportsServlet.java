package com.saikishore.expenseanalyzer.servlet;

import com.saikishore.expenseanalyzer.dao.BudgetDAO;
import com.saikishore.expenseanalyzer.dao.ExpenseDAO;
import com.saikishore.expenseanalyzer.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

@WebServlet("/reports")
public class ReportsServlet extends HttpServlet {

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

BudgetDAO budgetDAO = new BudgetDAO();

if (!budgetDAO.isBudgetSet(userId)) {

    response.sendRedirect(request.getContextPath() + "/budget");
    return;

}

        ExpenseDAO dao = new ExpenseDAO();

        Map<String, Double> categorySummary = dao.getCategorySummary(userId);

        request.setAttribute("categorySummary", categorySummary);
        request.setAttribute("activePage", "reports");

        request.getRequestDispatcher("reports.jsp")
               .forward(request, response);
    }
}