package com.saikishore.expenseanalyzer.servlet;

import com.saikishore.expenseanalyzer.dao.BudgetDAO;
import com.saikishore.expenseanalyzer.dao.UserDAO;
import com.saikishore.expenseanalyzer.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

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

        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        if(fullName == null || fullName.trim().length() < 3){

            response.sendRedirect(
                    request.getContextPath()
                    + "/register-page?error=name");
        
            return;
        }
        
        if(email == null || email.trim().isEmpty()){
        
            response.sendRedirect(
                    request.getContextPath()
                    + "/register-page?error=email");
        
            return;
        }
        
        if(password == null || password.length() < 8){
        
            response.sendRedirect(
                    request.getContextPath()
                    + "/register-page?error=passwordLength");
        
            return;
        }
        
        if(!password.equals(confirmPassword)){
        
            response.sendRedirect(
                    request.getContextPath()
                    + "/register-page?error=passwordMismatch");
        
            return;
        }
       
        if(!password.equals(confirmPassword)){

            request.setAttribute("error",
                    "Passwords do not match!");
        
            request.getRequestDispatcher("/register.jsp")
                    .forward(request,response);
        
            return;
        }

        if (userDAO.emailExists(email)) {

            request.setAttribute("error", "Email already registered!");

            request.getRequestDispatcher("/register.jsp")
                    .forward(request, response);

            return;
        }

        User user = new User(fullName, email, password);

        boolean success = userDAO.registerUser(user);

        if (success) {

            // Get the newly registered user
            User registeredUser = userDAO.loginUser(email, password);

            if (registeredUser != null) {
                budgetDAO.createBudget(user.getId(), 0);
            }

            response.sendRedirect(
                request.getContextPath()
                + "/login-page?success=registered");

        } else {

            request.setAttribute("error", "Registration failed!");

            request.getRequestDispatcher("/register.jsp")
                    .forward(request, response);

        }
    }
}