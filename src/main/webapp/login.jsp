<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>

    <title>Login</title>

    <link rel="stylesheet" href="css/style.css">

    <!-- Font Awesome -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">

</head>

<body>
    

<div class="auth-container">

    <div class="auth-card">

        <h2>Welcome Back</h2>

        <p class="auth-subtitle">
            Login to access your expense tracker.
        </p>

        <%
            String success = request.getParameter("success");
            String error = request.getParameter("error");
        %>
        <% if("email".equals(error)){ %>

            <div class="error-message">
                Email is required.
            </div>
            
            <% } %>
            
            <% if("password".equals(error)){ %>
            
            <div class="error-message">
                Password must contain at least 8 characters.
            </div>
            
            <% } %>

        <% if("registered".equals(success)){ %>

            <div class="success-message">
                Registration successful! Please login.
            </div>

        <% } %>

        <% if("passwordUpdated".equals(success)){ %>

            <div class="success-message">
                Password updated successfully! Please login.
            </div>

        <% } %>

        <% if("loggedOut".equals(success)){ %>

            <div class="success-message">
                You have been logged out successfully.
            </div>

        <% } %>

        <% if("invalidCredentials".equals(error)){ %>

            <div class="error-message">
                Invalid email or password.
            </div>

        <% } %>

        <form
    action="${pageContext.request.contextPath}/login"
    method="post"
    onsubmit="return validateLoginForm()">

            <!-- Email -->
            <div class="form-group">

                <label>Email</label>

                <input
    type="email"
    id="email"
    name="email"
    placeholder="Enter your email"
    required
    maxlength="100">
            </div>

            <!-- Password -->
            <div class="form-group">

                <label>Password</label>

                <div class="password-container">

                    <input
    type="password"
    id="password"
    name="password"
    placeholder="Enter your password"
    required
    minlength="8"
    maxlength="30">

                    <i class="fa-solid fa-eye eye-icon"
                       id="togglePassword"></i>

                </div>

            </div>

            <!-- Forgot Password -->
            <div class="forgot-password">

                <a href="${pageContext.request.contextPath}/forgot-password-page">
                    Forgot Password?
                </a>

            </div>

            <button
    type="submit"
    class="btn-primary"
    id="loginBtn">

    Login

</button>

        </form>

        <p class="auth-footer">

            Don't have an account?

            <a href="${pageContext.request.contextPath}/register-page">
                Register
            </a>

        </p>

    </div>

</div>

<script>

    const password = document.getElementById("password");
    const toggle = document.getElementById("togglePassword");

    toggle.addEventListener("click", function () {

        if (password.type === "password") {

            password.type = "text";

            toggle.classList.remove("fa-eye");
            toggle.classList.add("fa-eye-slash");

        } else {

            password.type = "password";

            toggle.classList.remove("fa-eye-slash");
            toggle.classList.add("fa-eye");

        }

    });
    function validateLoginForm(){

const email = document.getElementById("email").value.trim();
const password = document.getElementById("password").value;

if(email === ""){

    alert("Please enter your email.");
    return false;

}

if(password.length < 8){

    alert("Password must contain at least 8 characters.");
    return false;

}

const btn = document.getElementById("loginBtn");
btn.disabled = true;
btn.innerHTML = "Signing In...";

return true;
}


</script>

</body>
</html>