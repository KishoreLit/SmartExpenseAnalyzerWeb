package com.saikishore.expenseanalyzer.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login-page")
public class LoginPageServlet extends HttpServlet {

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

    request.getRequestDispatcher("/login.jsp")
           .forward(request, response);
}

}