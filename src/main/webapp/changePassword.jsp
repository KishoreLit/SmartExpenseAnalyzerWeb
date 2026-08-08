<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="WEB-INF/header.jsp" %>

<%
String error = request.getParameter("error");
%>

<div class="page-header">

    <h2>🔒 Change Password</h2>

    <p class="page-subtitle">
        Update your account password.
    </p>

</div>

<div class="profile-card">

    <% if("current".equals(error)){ %>

        <div class="error-message">
            Current password is incorrect.
        </div>

    <% } %>

    <% if("confirm".equals(error)){ %>

        <div class="error-message">
            New passwords do not match.
        </div>

    <% } %>

    <% if("same".equals(error)){ %>

        <div class="error-message">
            New password must be different from your current password.
        </div>

    <% } %>

    <% if("length".equals(error)){ %>

        <div class="error-message">
            Password must contain at least 8 characters.
        </div>

    <% } %>

    <form
            action="${pageContext.request.contextPath}/change-password"
            method="post"
            onsubmit="return validateChangePasswordForm()">

        <!-- Current Password -->

        <div class="form-group">

            <label>Current Password</label>

            <div class="password-container">

                <input
                        type="password"
                        id="currentPassword"
                        name="currentPassword"
                        placeholder="Enter current password"
                        required
                        minlength="8"
                        maxlength="30">

                <i class="fa-solid fa-eye eye-icon"
                   id="toggleCurrentPassword"></i>

            </div>

        </div>

        <!-- New Password -->

        <div class="form-group">

            <label>New Password</label>

            <div class="password-container">

                <input
                        type="password"
                        id="newPassword"
                        name="newPassword"
                        placeholder="Enter new password"
                        required
                        minlength="8"
                        maxlength="30">

                <i class="fa-solid fa-eye eye-icon"
                   id="toggleNewPassword"></i>

            </div>

        </div>

        <!-- Confirm Password -->

        <div class="form-group">

            <label>Confirm Password</label>

            <div class="password-container">

                <input
                        type="password"
                        id="confirmPassword"
                        name="confirmPassword"
                        placeholder="Confirm new password"
                        required
                        minlength="8"
                        maxlength="30">

                <i class="fa-solid fa-eye eye-icon"
                   id="toggleConfirmPassword"></i>

            </div>

        </div>

        <div class="expense-buttons">

            <button
                    type="submit"
                    class="btn-primary"
                    id="changePasswordBtn">

                🔒 Update Password

            </button>

            <a href="${pageContext.request.contextPath}/profile"
               class="btn-secondary">

                ← Back

            </a>

        </div>

    </form>

</div>

<script>

function togglePassword(inputId, iconId){

    const input = document.getElementById(inputId);
    const icon = document.getElementById(iconId);

    icon.addEventListener("click", function(){

        if(input.type === "password"){

            input.type = "text";

            icon.classList.remove("fa-eye");
            icon.classList.add("fa-eye-slash");

        }else{

            input.type = "password";

            icon.classList.remove("fa-eye-slash");
            icon.classList.add("fa-eye");

        }

    });

}

togglePassword("currentPassword","toggleCurrentPassword");
togglePassword("newPassword","toggleNewPassword");
togglePassword("confirmPassword","toggleConfirmPassword");

function validateChangePasswordForm(){

    const currentPassword =
            document.getElementById("currentPassword").value;

    const newPassword =
            document.getElementById("newPassword").value;

    const confirmPassword =
            document.getElementById("confirmPassword").value;

    if(currentPassword.length < 8){

        alert("Current password must contain at least 8 characters.");
        return false;

    }

    if(newPassword.length < 8){

        alert("New password must contain at least 8 characters.");
        return false;

    }

    if(currentPassword === newPassword){

        alert("New password must be different from your current password.");
        return false;

    }

    if(newPassword !== confirmPassword){

        alert("Passwords do not match.");
        return false;

    }

    const btn = document.getElementById("changePasswordBtn");

    btn.disabled = true;
    btn.innerHTML = "Updating Password...";

    return true;

}

</script>

</body>
</html>