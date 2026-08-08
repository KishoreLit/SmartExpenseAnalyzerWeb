package com.saikishore.expenseanalyzer.servlet;

import com.saikishore.expenseanalyzer.dao.BudgetDAO;
import com.saikishore.expenseanalyzer.dao.UserDAO;
import com.saikishore.expenseanalyzer.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDAO userDAO;
    private BudgetDAO budgetDAO;

    @Override
    public void init() {

        userDAO = new UserDAO();
        budgetDAO = new BudgetDAO();

    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if(email == null || email.trim().isEmpty()){

            response.sendRedirect(
                    request.getContextPath()
                    + "/login-page?error=email");
        
            return;
        }
        
        if(password == null || password.length() < 8){
        
            response.sendRedirect(
                    request.getContextPath()
                    + "/login-page?error=password");
        
            return;
        }

        User user = userDAO.loginUser(email, password);

        if (user != null) {

            HttpSession session = request.getSession();

            session.setAttribute("loggedInUser", user);

            if (budgetDAO.isBudgetSet(user.getId())) {

                response.sendRedirect(
                        request.getContextPath() + "/dashboard");

            } else {

                response.sendRedirect(
                        request.getContextPath() + "/budget");

            }

        } else {

            response.sendRedirect(
                    request.getContextPath()
                            + "/login-page?error=invalidCredentials");

        }

    }

}