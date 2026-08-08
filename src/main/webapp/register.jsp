<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<head>

    <title>Register</title>

    <link rel="stylesheet" href="css/style.css">

    <!-- Font Awesome -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">

</head>
<%
String error = request.getParameter("error");
%>

<body>
    <% if("name".equals(error)){ %>

        <div class="error-message">
        
        Full name must contain at least 3 characters.
        
        </div>
        
        <% } %>
        
        <% if("email".equals(error)){ %>
        
        <div class="error-message">
        
        Email is required.
        
        </div>
        
        <% } %>
        
        <% if("passwordLength".equals(error)){ %>
        
        <div class="error-message">
        
        Password must contain at least 8 characters.
        
        </div>
        
        <% } %>
        
        <% if("passwordMismatch".equals(error)){ %>
        
        <div class="error-message">
        
        Passwords do not match.
        
        </div>
        
        <% } %>

<div class="auth-container">

    <div class="auth-card">

        <h2>Create Account</h2>

        <p class="auth-subtitle">
            Register to manage your expenses.
        </p>

        <form
    action="${pageContext.request.contextPath}/register"
    method="post"
    onsubmit="return validateRegisterForm()">

            <div class="form-group">

                <label>Full Name</label>

                <input
    type="text"
    name="fullName"
    id="fullName"
    placeholder="Enter your full name"
    required
    minlength="3"
    maxlength="50"
    pattern="[A-Za-z ]+"
    title="Only letters and spaces are allowed.">

            </div>

            <div class="form-group">

                <label>Email</label>

                <input
    type="email"
    name="email"
    id="email"
    placeholder="Enter your email"
    required
    maxlength="100">

            </div>

            <div class="form-group">

                <label>Password</label>
            
                <div class="password-container">
            
                    <input
    type="password"
    name="password"
    id="password"
    placeholder="Enter your password"
    required
    minlength="8"
    maxlength="30">
            
                    <i class="fa-solid fa-eye eye-icon"
                       id="togglePassword"></i>
            
                </div>
            
            </div>
            
            <div class="form-group">
            
                <label>Confirm Password</label>
            
                <div class="password-container">
            
                    <input
    type="password"
    id="confirmPassword"
    name="confirmPassword"
    placeholder="Confirm your password"
    required>
            
                    <i class="fa-solid fa-eye eye-icon"
                       id="toggleConfirmPassword"></i>
            
                </div>
            
            </div>

            <button
    type="submit"
    class="btn-primary"
    id="registerBtn">

    Register

</button>

        </form>

        <p class="auth-footer">

            Already have an account?

            <a href="${pageContext.request.contextPath}/login-page">
                Login
            </a>

        </p>

    </div>

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
    
    togglePassword("password","togglePassword");
    togglePassword("confirmPassword","toggleConfirmPassword");

    function validateRegisterForm(){

const fullName = document.getElementById("fullName").value.trim();
const password = document.getElementById("password").value;
const confirmPassword = document.getElementById("confirmPassword").value;

const nameRegex = /^[A-Za-z ]+$/;

if(fullName.length < 3){

    alert("Full name must contain at least 3 characters.");
    return false;
}

if(!nameRegex.test(fullName)){

    alert("Full name can contain only letters and spaces.");
    return false;
}

if(password.length < 8){

    alert("Password must contain at least 8 characters.");
    return false;
}

if(password !== confirmPassword){

    alert("Passwords do not match.");
    return false;
}

// Disable the button to prevent multiple submissions
const btn = document.getElementById("registerBtn");
btn.disabled = true;
btn.innerHTML = "Registering...";

return true;
}
    
    </script>

</body>
</html>