package com.saikishore.expenseanalyzer.servlet;

import com.saikishore.expenseanalyzer.dao.UserDAO;
import com.saikishore.expenseanalyzer.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/edit-profile-page")
public class EditProfilePageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session=request.getSession(false);

        if(session==null ||
           session.getAttribute("loggedInUser")==null){

            response.sendRedirect(request.getContextPath()+"/login-page");
            return;
        }

        User loggedInUser=
                (User)session.getAttribute("loggedInUser");

        UserDAO dao=new UserDAO();

        User user=dao.getUserById(loggedInUser.getId());

        request.setAttribute("user",user);

        request.getRequestDispatcher("/editProfile.jsp")
                .forward(request,response);

    }

}