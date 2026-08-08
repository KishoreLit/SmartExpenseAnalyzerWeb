package com.saikishore.expenseanalyzer.servlet;

import com.saikishore.expenseanalyzer.dao.UserDAO;
import com.saikishore.expenseanalyzer.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/change-password")
public class ChangePasswordServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if(session == null ||
           session.getAttribute("loggedInUser") == null){

            response.sendRedirect(
                    request.getContextPath()+"/login-page");

            return;
        }

        User user = (User) session.getAttribute("loggedInUser");

        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        UserDAO dao = new UserDAO();

if(!dao.verifyPassword(user.getId(), currentPassword)){

    response.sendRedirect(
            request.getContextPath()
            + "/change-password-page?error=current");

    return;
}

if(currentPassword.equals(newPassword)){

    response.sendRedirect(
            request.getContextPath()
            + "/change-password-page?error=same");

    return;
}

if(!newPassword.equals(confirmPassword)){

    response.sendRedirect(
            request.getContextPath()
            + "/change-password-page?error=confirm");

    return;
}

dao.updatePassword(user.getEmail(), newPassword);

response.sendRedirect(
        request.getContextPath()
        + "/profile?success=passwordChanged");

}
}