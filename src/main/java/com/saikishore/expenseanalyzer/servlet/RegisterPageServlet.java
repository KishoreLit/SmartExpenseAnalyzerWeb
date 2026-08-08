package com.saikishore.expenseanalyzer.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/register-page")
public class RegisterPageServlet extends HttpServlet {

    @Override
protected void doGet(HttpServletRequest request,
                     HttpServletResponse response)
        throws ServletException, IOException {

    HttpSession session = request.getSession(false);

    if(session != null &&
       session.getAttribute("loggedInUser") != null){

        response.sendRedirect(request.getContextPath() + "/dashboard");
        return;
    }

    request.getRequestDispatcher("/register.jsp")
           .forward(request, response);
}
}