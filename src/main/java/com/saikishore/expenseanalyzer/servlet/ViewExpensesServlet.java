package com.saikishore.expenseanalyzer.servlet;

import com.saikishore.expenseanalyzer.dao.ExpenseDAO;
import com.saikishore.expenseanalyzer.model.Expense;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/viewExpenses")
public class ViewExpensesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        ExpenseDAO expenseDAO = new ExpenseDAO();

        List<Expense> expenses = expenseDAO.getAllExpenses();

        request.setAttribute("expenses", expenses);

        request.getRequestDispatcher("view-expenses.jsp")
               .forward(request, response);
    }
}