package com.saikishore.expenseanalyzer.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/change-password-page")
public class ChangePasswordPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if(session == null ||
           session.getAttribute("loggedInUser") == null){

            response.sendRedirect(
                    request.getContextPath()+"/login-page");

            return;
        }

        request.getRequestDispatcher("/changePassword.jsp")
                .forward(request,response);

    }

}