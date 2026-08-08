<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>

    <title>Forgot Password</title>

    <link rel="stylesheet" href="css/style.css">

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">

</head>

<body>

<div class="auth-container">

    <div class="auth-card">

        <h2>Forgot Password</h2>

        <p class="auth-subtitle">
            Reset your account password.
        </p>

        <% if(request.getAttribute("error") != null){ %>

        <div class="error-message">
            <%= request.getAttribute("error") %>
        </div>

        <% } %>

        <form action="${pageContext.request.contextPath}/forgot-password"
              method="post">

            <div class="form-group">

                <label>Email</label>

                <input type="email"
                       name="email"
                       placeholder="Enter your registered email"
                       required>

            </div>

            <div class="form-group">

                <label>New Password</label>

                <div class="password-container">

                    <input type="password"
                           id="password"
                           name="password"
                           placeholder="Enter new password"
                           required>

                    <i class="fa-solid fa-eye eye-icon"
                       id="togglePassword"></i>

                </div>

            </div>

            <div class="form-group">

                <label>Confirm Password</label>

                <div class="password-container">

                    <input type="password"
                           id="confirmPassword"
                           name="confirmPassword"
                           placeholder="Confirm password"
                           required>

                    <i class="fa-solid fa-eye eye-icon"
                       id="toggleConfirmPassword"></i>

                </div>

            </div>

            <button class="btn-primary" type="submit">

                Update Password

            </button>

        </form>

        <p class="auth-footer">

            <a href="${pageContext.request.contextPath}/login-page">

                Back to Login

            </a>

        </p>

    </div>

</div>

<script>

function togglePassword(inputId, iconId){

    const input=document.getElementById(inputId);
    const icon=document.getElementById(iconId);

    icon.addEventListener("click",function(){

        if(input.type==="password"){

            input.type="text";

            icon.classList.remove("fa-eye");
            icon.classList.add("fa-eye-slash");

        }else{

            input.type="password";

            icon.classList.remove("fa-eye-slash");
            icon.classList.add("fa-eye");

        }

    });

}

togglePassword("password","togglePassword");
togglePassword("confirmPassword","toggleConfirmPassword");

</script>

</body>
</html>