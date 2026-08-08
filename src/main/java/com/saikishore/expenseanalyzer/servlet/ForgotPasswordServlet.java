package com.saikishore.expenseanalyzer.servlet;

import com.saikishore.expenseanalyzer.dao.UserDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/forgot-password")
public class ForgotPasswordServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Check passwords match
        if (!password.equals(confirmPassword)) {

            request.setAttribute("error",
                    "Passwords do not match!");

            request.getRequestDispatcher("/forgotPassword.jsp")
                    .forward(request, response);

            return;
        }

        // Check email exists
        if (!userDAO.emailExists(email)) {

            request.setAttribute("error",
                    "Email not found!");

            request.getRequestDispatcher("/forgotPassword.jsp")
                    .forward(request, response);

            return;
        }

        // Update password
        boolean updated = userDAO.updatePassword(email, password);

        if (updated) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/login-page?success=passwordUpdated");

        } else {

            request.setAttribute("error",
                    "Unable to update password.");

            request.getRequestDispatcher("/forgotPassword.jsp")
                    .forward(request, response);

        }

    }
}