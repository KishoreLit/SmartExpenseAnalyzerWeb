package com.saikishore.expenseanalyzer.servlet;

import com.saikishore.expenseanalyzer.dao.UserDAO;
import com.saikishore.expenseanalyzer.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/edit-profile")
public class EditProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null ||
                session.getAttribute("loggedInUser") == null) {

            response.sendRedirect(request.getContextPath() + "/login-page");
            return;
        }

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");

        UserDAO dao = new UserDAO();

        // Full Name Validation
        if (fullName == null || fullName.trim().length() < 3) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/edit-profile-page?error=name");

            return;
        }

        // Only letters and spaces
        if (!fullName.matches("[A-Za-z ]+")) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/edit-profile-page?error=invalidName");

            return;
        }

        // Email Validation
        if (email == null || email.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/edit-profile-page?error=email");

            return;
        }

        // Check whether email already belongs to another user
        if (dao.emailExistsForAnotherUser(email, loggedInUser.getId())) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/edit-profile-page?error=emailExists");

            return;
        }

        // Update Profile
        boolean updated = dao.updateProfile(
                loggedInUser.getId(),
                fullName.trim(),
                email.trim());

        if (updated) {

            // Refresh session with latest user details
            User updatedUser = dao.getUserById(loggedInUser.getId());

            session.setAttribute("loggedInUser", updatedUser);

            response.sendRedirect(
                    request.getContextPath()
                            + "/profile?success=updated");

        } else {

            response.sendRedirect(
                    request.getContextPath()
                            + "/edit-profile-page?error=updateFailed");
        }
    }
}